package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.aop.ratelimiter.MethodRateLimit;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@RestController
public class RateLimiterDemoController extends BaseController {
    /**
    * 方法限流测试
    * */
    @MethodRateLimit(maxWait = 500,permitsPerSecond = 1)
    @GetMapping("/test1")
    public ResponseEntity get1(){
        ResponseEntity responseEntity = getResponse();
        return responseEntity;
    }
}
