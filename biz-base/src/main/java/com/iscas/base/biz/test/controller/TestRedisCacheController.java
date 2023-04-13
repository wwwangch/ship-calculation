package com.iscas.base.biz.test.controller;

import com.iscas.templet.common.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/7 21:47
 * @since jdk1.8
 */
@RestController
@RequestMapping
public class TestRedisCacheController {

    @Cacheable(value = "auth", key = "'xxx'")
    @GetMapping("/ttt/redis/cache")
    public ResponseEntity responseEntity() {
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>();

        return objectResponseEntity;
    }

    @Cacheable(value = "bbbb", key = "'xxx'")
    @GetMapping("/ttt/redis/cache2")
    public ResponseEntity responseEntity2() {
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>();

        return objectResponseEntity;
    }

    @Cacheable(value = "test", key = "'xxx'")
    @GetMapping("/ttt/redis/cache3")
    public ResponseEntity responseEntity3() {
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>();

        return objectResponseEntity;
    }
}
