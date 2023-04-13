package com.iscas.biz.controller.common;

import com.iscas.base.biz.service.common.OkHttpCustomClient;
import com.iscas.biz.BizApp;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OkHttpCustomClientTest {
    @Autowired
    private OkHttpCustomClient okHttpCustomClient;

    @Test
    public void doGet() throws IOException {
//        String res = okHttpCustomClient.doGet("https://172.16.10.168:1443");
        String res = okHttpCustomClient.doGet("http://localhost:8180/health");
        System.out.println(res);
    }
}