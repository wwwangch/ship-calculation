package com.iscas.biz.test.controller;

import cn.hutool.core.io.IoUtil;
import com.iscas.base.biz.util.ResponseUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import lombok.Cleanup;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.Base64;

/**
 * 测试返回图片
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/10 16:04
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/image")
public class ImageController {

    @GetMapping("/stream")
    public void stream() throws BaseException {
        try {
            @Cleanup FileInputStream fileInputStream = new FileInputStream("C:\\Users\\m1531\\Desktop\\111.jpg");
//            ResponseUtils.returnStream(fileInputStream, SpringUtils.getResponse(), "image/jpeg");
            //自动识别文件格式
            ResponseUtils.returnStream(fileInputStream, SpringUtils.getResponse());

        } catch (Exception e) {
            throw Exceptions.baseException("出错了", e);
        }
    }

    @GetMapping(value = "/base64")
    public String base64() throws BaseException {
        try {
            @Cleanup FileInputStream fileInputStream = new FileInputStream("C:\\Users\\m1531\\Desktop\\111.jpg");
//            ResponseUtils.returnStream(fileInputStream, SpringUtils.getResponse(), "image/jpeg");
            @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IoUtil.copyByNIO(fileInputStream, baos, 2048, null);
            String base64Str = Base64.getEncoder().encodeToString(baos.toByteArray());
            base64Str = new StringBuilder("data:image/jpeg;base64,").append(base64Str).toString();
            return base64Str;
        } catch (Exception e) {
            throw Exceptions.baseException("出错了", e);
        }
    }
}