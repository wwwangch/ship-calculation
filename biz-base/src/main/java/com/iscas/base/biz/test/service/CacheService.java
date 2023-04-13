//package com.iscas.base.biz.test.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * 缓存测试
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/7/16 17:29
// * @since jdk1.8
// */
//@Service
//public class CacheService {
//    @Autowired
//    private MapResultMapper mapResultMapper;
//
//    //查询，如果有缓存就不执行函数内代码，直接从缓存拿。没有执行函数 把结果写入缓存
//    @Cacheable(value = "test", key = "'map'.concat(#id.toString())")
//    public Map selectById(Integer id) {
//        return mapResultMapper.selectById(id);
//    }
//
//
//    //每次都插入缓存
//    @CachePut(value = "test", key = "'map'.concat(#id.toString())")
//    public Map selectById2(Integer id) {
//        return mapResultMapper.selectById(id);
//    }
//
//    //清除缓存
//    @CacheEvict(value = "test", key = "'map'.concat(#id.toString())")
//    public Map selectById3(Integer id) {
//        return mapResultMapper.selectById(id);
//    }
//}
