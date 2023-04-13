package com.iscas.biz.okhttp;

import cn.hutool.core.io.IoUtil;
import com.iscas.base.biz.autoconfigure.okhttp.OkHttpProps;
import com.iscas.base.biz.service.common.OkHttpCustomClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/1 11:40
 * @since jdk1.8
 */
public class OkHttpTests {
    private OkHttpCustomClient okHttpCustomClient;
    @BeforeEach
    public void before() {
        okHttpCustomClient = new OkHttpCustomClient(new OkHttpProps());
    }

    /**
     *测试文件上传1
     */
    @Test
    public void testUpload1() throws IOException {
        OkHttpCustomClient.UploadInfo uploadInfo = new OkHttpCustomClient.UploadInfo();
        uploadInfo.setFileName("test.xls");
        uploadInfo.setData(new FileInputStream("D:/test.xls"));
        uploadInfo.setFormKey("myFile");
        String s = okHttpCustomClient.doUpload("http://localhost:7901/demo/test/okhttp/upload",
                Arrays.asList(uploadInfo), null);
        System.out.println(s);
    }

    /**
     *测试文件上传2
     */
    @Test
    public void testUpload2() throws IOException {
        OkHttpCustomClient.UploadInfo uploadInfo = new OkHttpCustomClient.UploadInfo();
        uploadInfo.setFileName("test.xls");
        uploadInfo.setData("D:/test.xls");
        uploadInfo.setFormKey("myFile");
        String s = okHttpCustomClient.doUpload("http://localhost:7901/demo/test/okhttp/upload",
                Arrays.asList(uploadInfo), null);
        System.out.println(s);
    }

    /**
     *测试文件上传3
     */
    @Test
    public void testUpload3() throws IOException {
        OkHttpCustomClient.UploadInfo uploadInfo = new OkHttpCustomClient.UploadInfo();
        uploadInfo.setFileName("test.xls");
        InputStream is = new FileInputStream("D:/test.xls");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IoUtil.copy(is, baos);
        uploadInfo.setData(baos.toByteArray());
        uploadInfo.setFormKey("myFile");
        String s = okHttpCustomClient.doUpload("http://localhost:7901/demo/test/okhttp/upload",
                Arrays.asList(uploadInfo), null);
        System.out.println(s);
    }

    /**
     *测试下载
     */
    @Test
    public void testDownload() throws IOException {
        InputStream is = okHttpCustomClient.doDownload("http://localhost:7901/demo/test/okhttp/download");
        System.out.println(is);
    }
}
