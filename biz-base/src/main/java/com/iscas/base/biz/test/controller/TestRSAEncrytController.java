package com.iscas.base.biz.test.controller;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/27 14:49
 * @since jdk1.8
 */
@RestController
@RequestMapping("/rsaencryt")
public class TestRSAEncrytController {
    @Encrypt
    @GetMapping("/encryption")
    public String encryption(){

        return "这是一个测试";
    }

    @Decrypt
    @Encrypt
    @PostMapping("/decryption")
    public Map<String, String> Decryption(@RequestBody Map<String, String> json){
        return json;
    }

    @PostMapping("/norsa")
    public String norsa(@RequestBody String json){
        return json;
    }
}
