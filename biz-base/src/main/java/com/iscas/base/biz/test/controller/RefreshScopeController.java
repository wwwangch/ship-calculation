//package com.iscas.base.biz.test.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/8/31 9:47
// * @since jdk1.8
// */
//@RestController
//@RefreshScope
//@RequestMapping("/refresh")
//public class RefreshScopeController {
//    @Value("${refresh.test.prop1:test1}")
//    private String prop1;
//    @GetMapping("/test1")
//    public String test1(){
//        return prop1;
//    }
//}
