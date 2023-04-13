package com.iscas.biz.test.controller;

import com.iscas.common.tools.core.io.file.JarPathUtils;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/23 21:03
 * @since jdk1.8
 */
@RestController
@RequestMapping("/jarpath/test")
public class JarPathControllerTest {
    @GetMapping
    public ResponseEntity jarPath() throws UnsupportedEncodingException {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setValue(JarPathUtils.getJarPath(JarPathUtils.class));
        return responseEntity;
    }
}
