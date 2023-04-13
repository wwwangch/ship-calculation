package com.iscas.biz.controller.webtestclient;

import com.iscas.biz.BizApp;
import com.iscas.templet.common.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/17 14:10
 * @since jdk1.8
 */
@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebClientTest1 {
    @Test
    public void test1(@Autowired TestRestTemplate testRestTemplate) {
        ResponseEntity responseEntity = testRestTemplate.getForObject("/user/t1", ResponseEntity.class);
        Assertions.assertNotNull(responseEntity);
    }
}
