package com.iscas.base.biz.test.controller;

import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/8/31 15:47
 * @since jdk1.8
 */
@RestController
public class TestException {
    @GetMapping("/testE1")
    public String testE1(){
        int a = 3/0;
        return "dg";
    }

    @GetMapping("/testE2")
    public String testE2() throws BaseException {
        try {
            File file = new File("weg/wegwg");
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (Exception e){
            throw Exceptions.baseException("出错啦啦啦啦", e);
        }
        return "1111";
    }
}
