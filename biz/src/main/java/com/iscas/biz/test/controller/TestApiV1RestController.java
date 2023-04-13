package com.iscas.biz.test.controller;

import com.iscas.biz.annotation.api.ApiV1RestController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/29 9:10
 * @since jdk1.8
 */
@ApiV1RestController
@RequestMapping("/test/apiv1")
public class TestApiV1RestController {
    @GetMapping()
    public ResponseEntity get() {
        return new ResponseEntity();
    }
}
