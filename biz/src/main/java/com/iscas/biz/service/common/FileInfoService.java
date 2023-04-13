package com.iscas.biz.service.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.domain.common.FileInfo;
import com.iscas.biz.mapper.common.FileInfoMapper;
import com.iscas.common.tools.core.io.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/10 15:05
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Service
@Slf4j
public class FileInfoService extends ServiceImpl<FileInfoMapper, FileInfo> {
    @Value("${file.server.path:temp}")
    private String fileServerPath;

    /**
     * 保存文件
     */
    public void saveFile(FileInfo fileInfo) {
        LambdaQueryWrapper<FileInfo> lambdaQuery = new QueryWrapper<FileInfo>().lambda();
        lambdaQuery.eq(FileInfo::getFileKey, fileInfo.getFileKey());
        saveOrUpdate(fileInfo, lambdaQuery);
    }

    /**
     * 检查文件
     */
    public List<FileInfo> check(String key) {
        LambdaQueryWrapper<FileInfo> lambda = new QueryWrapper<FileInfo>().lambda();
        lambda.eq(FileInfo::getFileKey, key);
        return list(lambda);
    }

    /**
     * 文件上传
     */
    public void upload(MultipartFile file, String suffix, int shardIndex, int shardSize,
                       int shardTotal, long size, String key, String name) throws IOException, InterruptedException {
        log.info("上传文件开始");
        File pFile = new File(fileServerPath, "files");
        FileUtils.makeDirectory(pFile);
        //设置文件新的名字
        String fileName = MessageFormat.format("{0}.{1}", key, suffix);
        //生成分片的名字
        String localFileName = MessageFormat.format("{0}.{1}", fileName, shardIndex);

        // 以绝对路径保存重名命后的文件
        File targetFile = new File(pFile, localFileName);
        //上传这个文件
        file.transferTo(targetFile);
        //数据库持久化这个数据
        LocalDateTime now = LocalDateTime.now();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setPath("files" + File.separator + localFileName)
                .setName(name)
                .setSuffix(suffix)
                .setSize(size)
                .setCreatedAt(now)
                .setUpdatedAt(now)
                .setShardIndex(shardIndex)
                .setShardSize(shardSize)
                .setShardTotal(shardTotal)
                .setFileKey(key);

        //判断当前是不是最后一个分页 如果不是就继续等待其他分页 合并分页
        if (shardIndex == shardTotal) {
            fileInfo.setPath("files" + File.separator + name);
            this.merge(fileInfo, fileName);
        }
        //插入到数据库中
        //保存的时候 去处理一下 这个逻辑
        saveFile(fileInfo);
        log.info("上传成功");
    }

    /**
     * 合并文件
     */
    private void merge(FileInfo fileInfo, String fileName) throws IOException {
        //合并分片开始
        log.info("分片合并开始");
        //获取到的路径 没有.1 .2 这样的东西
        Integer shardTotal = fileInfo.getShardTotal();
        String newFileName = fileServerPath + File.separator + "files" + File.separator + fileInfo.getName();
        File newFile = new File(newFileName);
        // 文件追加写入
        FileOutputStream os = new FileOutputStream(newFile, true);
        //分片文件
        FileInputStream fis = null;
        try {
            for (int i = 0; i < shardTotal; i++) {
                // 读取第i个分片
                String shardFileName = fileServerPath + File.separator + "files" + File.separator + fileName + "." + (i + 1);
                fis = new FileInputStream(shardFileName);
                fis.transferTo(os);
                fis.close();
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
            os.close();
            log.info("IO流关闭");
        }
        log.debug("分片结束了");

        log.debug("删除分片开始");
        for (int i = 0; i < shardTotal; i++) {
            String filePath = fileServerPath + File.separator + "files" + File.separator + fileName + "." + (i + 1);
            File file = new File(filePath);
            boolean result = file.delete();
            log.info("删除{}，{}", filePath, result ? "成功" : "失败");
        }
        log.info("删除分片结束");
    }

}
