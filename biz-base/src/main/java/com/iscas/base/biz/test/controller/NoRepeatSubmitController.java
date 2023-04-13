package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.aop.norepeat.submit.NoRepeatSubmit;
import com.iscas.base.biz.service.common.OkHttpCustomClient;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 22:34
 * @since jdk1.8
 */
@RestController
public class NoRepeatSubmitController {
    @Autowired
    OkHttpCustomClient okHttpCustomClient;
    @NoRepeatSubmit
    @GetMapping("/testNoRepeat")
    public ResponseEntity test() throws InterruptedException, IOException {
        for (int i = 0; i < 1000000; i++) {
//            System.out.println(1111);
        }
        return new ResponseEntity();
    }
}
