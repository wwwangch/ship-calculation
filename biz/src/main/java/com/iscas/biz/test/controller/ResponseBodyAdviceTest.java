package com.iscas.biz.test.controller;

import com.iscas.biz.annotation.response.NoneResponseEntity;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseRuntimeException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/2 9:08
 */
@RestController
@RequestMapping("/test/res/body/advice/test")
public class ResponseBodyAdviceTest {

    /**
     * 返回字符串
     * */
    @GetMapping
    public String test() {
        return "success";
    }

    /**
     * 返回Map
     * */
    @GetMapping("/t2")
    public Map<String, String> test2() {
        return Map.of("key", "value");
    }

    /**
     * 出现异常
     * */
    @GetMapping("/t3")
    public Map<String, String> test3() {
        throw new BaseRuntimeException("出错了");
    }

    /**
     * 出现异常
     * */
    @PostMapping("/t4")
    public Map<String, String> test4(@RequestBody Map<String, String> map) {
        return map;
    }

    /**
     * 返回空
     * */
    @PostMapping("/t5")
    public Map<String, String> test5(@RequestBody Map<String, String> map) {
        return null;
    }

    /**
     * 返回ResponseEntity
     * */
    @PostMapping("/t6")
    public ResponseEntity test6(@RequestBody Map<String, String> map) {
        return ResponseEntity.ok(map);
    }

    @GetMapping("/t7")
    public void test7() {

    }

    @GetMapping("/t8")
    @NoneResponseEntity
    public String test8() {
        return "xxxxx";
    }


}
