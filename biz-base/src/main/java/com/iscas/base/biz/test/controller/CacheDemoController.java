//
//package com.iscas.base.biz.test.controller;
//
//import com.iscas.base.biz.test.service.CacheService;
//import com.iscas.templet.common.BaseController;
//import com.iscas.templet.common.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
///**
// * 缓存测试
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/7/16 17:29
// * @since jdk1.8
// */
//
//@RestController
//@RequestMapping("/cache")
//public class CacheDemoController extends BaseController {
//    @Autowired
//    private CacheService cacheService;
//    @GetMapping("/find")
//    public ResponseEntity findById(Integer id) {
//        ResponseEntity responseEntity = getResponse();
//        responseEntity.setValue(cacheService.selectById(id));
//        return responseEntity;
//    }
//    @GetMapping("/put")
//    public ResponseEntity put(Integer id) {
//        ResponseEntity responseEntity = getResponse();
//        responseEntity.setValue(cacheService.selectById2(id));
//        return responseEntity;
//    }
//    @GetMapping("/remove")
//    public ResponseEntity remove(Integer id) {
//        ResponseEntity responseEntity = getResponse();
//        responseEntity.setValue(cacheService.selectById3(id));
//        return responseEntity;
//    }
//}
//
