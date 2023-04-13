package com.iscas.biz.test.controller;

import com.iscas.base.biz.service.common.OkHttpCustomClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/27 14:29
 * @since jdk1.8
 */
@RestController
@RequestMapping("/testfile")
public class TestFileController {
    @Autowired
    private OkHttpCustomClient okHttpCustomClient;
    @PostMapping()
    public String test(@RequestParam("file") MultipartFile file) throws IOException {

        //UploadInfo 构造函数第一个参数为文件，支持InputStream、File、String(文件路径)、byte[]
        //UploadInfo 构造函数第二个参数为文件名
        //UploadInfo 构造函数第三个参数为上传form表单的key
        OkHttpCustomClient.UploadInfo<InputStream> uploadInfo = new OkHttpCustomClient.UploadInfo(file.getInputStream(), file.getName(), "file");
        List<OkHttpCustomClient.UploadInfo> uploadInfos = Arrays.asList(uploadInfo);
        String s = okHttpCustomClient.doUpload("http://192.168.100.88:7776/satelite/regulation/import", uploadInfos, new HashMap<>());
        return s;
    }
}
