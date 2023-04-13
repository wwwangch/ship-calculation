package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.model.TestEntity;
import com.iscas.biz.mp.test.service.mysql1.TestService1;
import com.iscas.biz.mp.test.service.mysql2.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/10 15:10
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@RestController
@ConditionalOnMybatis
public class TestMultiDbController {
    @Autowired
    private TestService1 testService1;
    @Autowired
    private TestService2 testService2;

    @GetMapping("/t11")
    public List<Map> test1() {
        return testService1.getIds();
    }

    @GetMapping("/t22")
    public List<Map> test2() {
        return testService2.getIds();
    }

    @GetMapping("/t33")
    public List<TestEntity> test3() {
        return testService1.get();
    }

    @GetMapping("/t44")
    public List<TestEntity> test4() {
        return testService1.get2();
    }

    @GetMapping("/testInsert")
    public String testInsert() {
        testService1.insert();
        return "success";
    }

    @GetMapping("/testMulti")
    public String testMulti() {
        testService1.testMulti();
        return "success";
    }

    @GetMapping("/testMulti2")
    public String testMulti2() {
        testService1.testMultiWithTransctional();
        return "success";
    }

    @GetMapping("/testMulti3")
    public String testMulti3() {
        testService1.testMultiWithTransctional2();
        return "success";
    }

    @GetMapping("/testMulti4")
    public String testMulti4() {
        testService1.testMultiWithTransctional3();
        testService2.testMultiWithTransctional();
        return "success";
    }

    @GetMapping("/testMulti5")
    public String testMulti5() {
        testService1.testMultiWithTransctional4();
        testService2.testMultiWithTransctional2();
        return "success";
    }

    @GetMapping("/testMulti6")
    public String testMulti6() {
        try {
            testService1.testMultiWithTransctional5();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testService2.testMultiWithTransctional();
        return "success";
    }

    @GetMapping("/testMulti7")
    public String testMulti7() {
        try {
            testService1.testMultiWithTransctional6();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testService2.testMultiWithTransctional();
        return "success";
    }

}
