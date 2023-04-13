package com.iscas.biz.okhttp;

import com.iscas.base.biz.service.common.OkHttpCustomClient;
import com.iscas.biz.BizApp;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/18 11:38
 * @since jdk1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OkHttpCustomClientTest {
    @Autowired
    private OkHttpCustomClient okHttpCustomClient;

    @Test
    public void doGet() throws IOException {
        String res = okHttpCustomClient.doGet("https://www.12306.cn/index/");
        System.out.println(res);
    }


}