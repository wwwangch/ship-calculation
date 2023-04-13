package com.iscas.base.biz.test.controller;


import com.iscas.base.biz.aop.auth.RequiredRole;
import com.iscas.common.web.tools.http.CustomHttpClient;
import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 权限验证过滤器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/17 8:35
 * @since jdk1.8
 */
@RestController
@RequestMapping("/testauth")
@Slf4j
public class AuthDemoController extends BaseController {
    @GetMapping("/t1")
    public ResponseEntity t1() {
        return getResponse().setValue("v2");
    }
    @GetMapping("/t2")
    public ResponseEntity t2() {
        return getResponse();
    }
    @GetMapping("/t3")
    public ResponseEntity t3() {
        return getResponse();
    }

    @RequiredRole(value = {"normal"})
    @GetMapping("/t4")
    public ResponseEntity t4() {
        return getResponse();
    }


    @GetMapping("/call")
    public ResponseEntity call() throws IOException, InterruptedException {
        CustomHttpClient httpClient = new CustomHttpClient(new CustomHttpClient.HttpClientProps());
        String resStr = httpClient.doGet("http://istio-a:7907/demo/testauth/t1");
        log.info(resStr);
        return JsonUtils.fromJson(resStr, ResponseEntity.class);
    }
}
