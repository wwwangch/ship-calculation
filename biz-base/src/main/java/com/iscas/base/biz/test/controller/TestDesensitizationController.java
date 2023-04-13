package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.desensitization.Desensitization;
import com.iscas.base.biz.desensitization.DesensitizationTypeEnum;
import com.iscas.base.biz.desensitization.PrivacyTypeEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/6 8:40
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/desensitization")
public class TestDesensitizationController {

    @GetMapping
    public List<TestModel> test() {
        TestModel t1 = new TestModel();
        t1.setPassword("123456");
        t1.setEmail("zzz@163.com");
        t1.setPhone("137654879451");
        t1.setFixPhone("0453-4785462");
        t1.setBankCard("622648754896457");
        t1.setIdCard("245874563214578965");
        t1.setName("张王钊");
        t1.setAddress("北京市昌平区xxx街道xxx小区1-1-101");
        t1.setHeadStr("测试头部脱敏");
        t1.setTailStr("测试尾部脱敏");
        t1.setMiddleStr("测试中间脱敏");
        t1.setHeadTailStr("测试头尾脱敏");
        t1.setAllStr("测试全部脱敏");
        t1.setNoneStr("测试不脱敏");

        TestModel t2 = new TestModel();
        t2.setPassword("iscas123");
        t2.setEmail("xwg@sina.com");
        t2.setPhone("18547896547");
        t2.setFixPhone("010-62268795");
        t2.setBankCard("622648754896487");
        t2.setIdCard("100412547865478947");
        t2.setName("李二麻子");
        t2.setAddress("新疆省克拉玛依市xxx街道xxx小区1-1-101");
        t2.setHeadStr("测试头部脱敏");
        t2.setTailStr("测试尾部脱敏");
        t2.setMiddleStr("测试中间脱敏");
        t2.setHeadTailStr("测试头尾脱敏");
        t2.setAllStr("测试全部脱敏");
        t2.setNoneStr("测试不脱敏");


        return new ArrayList<>(){{
            add(t1);
            add(t2);
        }};
    }


    @Data

    private static class TestModel {

        /**
         * 模拟密码
         * */
        @Desensitization(dataType = PrivacyTypeEnum.PASSWORD)
        private String password;

        /**
         * 模拟邮箱
         * */
        @Desensitization(dataType = PrivacyTypeEnum.EMAIL)
        private String email;

        /**
         * 模拟手机号
         * */
        @Desensitization(dataType = PrivacyTypeEnum.MOBILE_PHONE)
        private String phone;

        /**
         * 模拟座机
         * */
        @Desensitization(dataType = PrivacyTypeEnum.FIXED_PHONE)
        private String fixPhone;

        /**
         * 模拟银行卡
         * */
        @Desensitization(dataType = PrivacyTypeEnum.BANK_CARD)
        private String bankCard;

        /**
         * 模拟身份证号
         * */
        @Desensitization(dataType = PrivacyTypeEnum.ID_CARD)
        private String idCard;

        /**
         * 模拟中文名
         * */
        @Desensitization(dataType = PrivacyTypeEnum.CHINESE_NAME)
        private String name;

        /**
         * 模拟住址
         * */
        @Desensitization(dataType = PrivacyTypeEnum.ADDRESS)
        private String address;

        /**
         * 模拟自定义脱敏-头部脱敏
         * */
        @Desensitization(dataType = PrivacyTypeEnum.OTHER, mode = DesensitizationTypeEnum.HEAD, tailNoMaskLen = 4)
        private String headStr;

        /**
         * 模拟自定义脱敏-尾部脱敏
         * */
        @Desensitization(dataType = PrivacyTypeEnum.OTHER, mode = DesensitizationTypeEnum.TAIL, headNoMaskLen = 4)
        private String tailStr;

        /**
         * 模拟自定义脱敏-中间脱敏
         * */
        @Desensitization(dataType = PrivacyTypeEnum.OTHER, mode = DesensitizationTypeEnum.MIDDLE, headNoMaskLen = 2, tailNoMaskLen = 2)
        private String middleStr;

        /**
         * 模拟自定义脱敏-两头脱敏
         * */
        @Desensitization(dataType = PrivacyTypeEnum.OTHER, mode = DesensitizationTypeEnum.HEAD_TAIL, middleNoMaskLen = 4)
        private String headTailStr;

        /**
         * 模拟自定义脱敏-全部脱敏
         * */
        @Desensitization(dataType = PrivacyTypeEnum.OTHER, mode = DesensitizationTypeEnum.ALL)
        private String allStr;

        /**
         * 模拟自定义脱敏-不脱敏
         * */
        @Desensitization(dataType = PrivacyTypeEnum.OTHER, mode = DesensitizationTypeEnum.NONE)
        private String noneStr;

    }

}
