package com.iscas.biz.test.controller;

import com.iscas.base.biz.config.property.MixPropertySourceFactory;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 测试@PropertyResource
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/21 21:37
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/property/resource")
@PropertySource(name = "test-property-source.yml", value = "classpath:test-property-source.yml", factory = MixPropertySourceFactory.class)
public class TestPropertyResource extends BaseController {

    @Value("${person.zhangsan.age}")
    private int age;

    /**split函数*/
    @Value("#{'${cities}'.split(';')}")
    private String[] cities;

    /**map或对象*/
    @Value("#{${map}['a']}")
    private int a;

    /**字符串拼接*/
    @Value("#{'hello' + ' ' + 'world'.concat('!')}")
    private String helloWorld;

    /**算数运算:+, -, *, /, %, ^, div, mod*/
    @Value("#{(3 + 4 - 1) / 4 * 2 % 2 ^ 3}")
    private int arithmetic;

    /**关系运算：<, >, ==, !=, <=, >=, lt, gt, eq, ne, le, ge*/
    @Value("#{3 < 4 && 3 == 3 && 3 != 4}")
    private boolean relational;

    /**逻辑运算：and, or, not, &&*/
    @Value("#{3 < 4 && 3 == 3 || 3 != 4}")
    private boolean logical;

    /**条件运算符: ? : */
    @Value("#{2 > 1 ? 'a' : 'b'}")
    private String condition;

    /**正则表达式*/
    @Value("#{'aa'.matches('.+')}")
    private boolean matches;

    @Value("#{systemProperties['user.dir']}")
    private String userDir;

    @Value("#{systemProperties['java.home']}")
    private String javaHome;

    @Value("${user_default_pwd}")
    private String defaultPwd;

    @GetMapping
    public ResponseEntity testPropertyResource() {
        System.out.println("张三年龄：" + age);
        return getResponse();
    }

    @GetMapping("/spel")
    public ResponseEntity testSpel() {
        System.out.println("--测试spel#split:" + Arrays.toString(cities));
        System.out.println("--测试spel#字符串拼接:" + helloWorld);
        System.out.println("--测试spel#算数运算:" + arithmetic);
        System.out.println("--测试spel#关系运算:" + relational);
        System.out.println("--测试spel#逻辑运算:" + logical);
        System.out.println("--测试spel#条件运算:" + condition);
        System.out.println("--测试spel#正则运算:" + matches);
        System.out.println("--测试spel#map:" + a);
        System.out.println("--测试spel#javahome:" + javaHome);
        System.out.println("--测试spel#userdir:" + userDir);
        return getResponse();
    }
}
