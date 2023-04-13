package com.iscas.biz.test.controller;

import com.iscas.biz.test.service.impl.RetryTestService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/7 18:07
 * @since jdk1.8
 */
@RestController
@RequestMapping("/retry/test")
public class RetryTestController extends BaseController {
    @Autowired
    private RetryTestService retryTestService;

    @GetMapping("/t1")
    public ResponseEntity t1() throws BaseException {
        ResponseEntity response = getResponse();
        String server = retryTestService.server();
        response.setValue(server);
        return response;
    }

    @GetMapping("/t2")
    public ResponseEntity t2() throws BaseException {
        ResponseEntity response = getResponse();
        String server = retryTestService.server2();
        response.setValue(server);
        return response;
    }
}
