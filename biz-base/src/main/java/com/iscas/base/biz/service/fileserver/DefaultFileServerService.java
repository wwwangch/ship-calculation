package com.iscas.base.biz.service.fileserver;

import com.iscas.base.biz.util.MultipartFileUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.tools.constant.TimeConstant;
import com.iscas.common.tools.core.io.file.FileUtils;
import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/25 15:52
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaServiceOrDaoClassShouldEndWithImpl"})
@Service
@Slf4j
public class DefaultFileServerService implements FileServerService, TimeConstant {
    @Value("${file.server.path:temp}")
    private String fileServerPath;

    /**
     * 文件上传处理，返回文件的key以及对应的文件存储路径
     */
    @Override
    public Map<String, String> upload(MultipartFile[] files) throws IOException {
        Map<String, String> result = new HashMap<>(2 << 2);
        //获取文件存储的根路径
        File path = getPath();
        for (MultipartFile multipartFile : files) {
            String key = multipartFile.getOriginalFilename();
            assert key != null;
            File file = new File(path, key);
            FileUtils.touch(file);
            MultipartFileUtils.copy(multipartFile, file);
            //将路径中的\\替换为/
            String absolutePath = file.getAbsolutePath().replaceAll("\\\\", "/");
            result.put(key, absolutePath);
        }
        return result;
    }


    @Override
    public void download(String path) throws BaseException {
        File file = new File(path);
        if (!file.exists()) {
            throw Exceptions.baseException("下载的文件不存在或已删除");
        }
        try {
            FileDownloadUtils.downFile(SpringUtils.getRequest(), SpringUtils.getResponse(), path, file.getName());
        } catch (Exception e) {
            throw Exceptions.baseException("文件下载出错", e);
        }
    }


    private File getPath() {
        File pfile = new File(fileServerPath);
        FileUtils.makeDirectory(pfile);
        LocalDateTime now = LocalDateTime.now();
        String timeStr = now.format(DATE_FORMATTER);
        File file = new File(pfile, timeStr);
        FileUtils.makeDirectory(file);
        String randomStr = RandomStringUtils.randomStr(8);
        file = new File(file, randomStr);
        FileUtils.makeDirectory(file);
        return file;
    }

}
