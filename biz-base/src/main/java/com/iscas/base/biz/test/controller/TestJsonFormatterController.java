package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.test.service.CustomStrFormatter;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/6 14:00
 * @since jdk11
 */
@RequestMapping("/test/serial")
@RestController
@Slf4j
public class TestJsonFormatterController {

    /**
     * 测试序列化
     * */
    @GetMapping
    public TestModel test1() {
        TestModel testModel = new TestModel();
        testModel.setId("1").setStrs1(List.of("1", "2", "3"))
                .setStrs2("[\"3\", \"4\"]");
        return testModel;
    }

    /**
     * 测试反序列化
     * */
    @PostMapping
    public String test2(@RequestBody TestModel testModel) {
        log.info("接收到的testModel:{}", testModel.toString());
        return "success";
    }

    @Data
    @Accessors(chain = true)
    public static class TestModel {
        private String id;

        private List<String> strs1;

        @CustomStrFormatter
        private String strs2;
    }
}
