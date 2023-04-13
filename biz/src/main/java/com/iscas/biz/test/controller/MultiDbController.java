package com.iscas.biz.test.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.test.service.atomikos.AtomikosTestService;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/20 16:29
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/multi/db")
@ConditionalOnMybatis
public class MultiDbController {
    @Autowired
    private AtomikosTestService atomikosTestService;
    @Autowired
    private DynamicMapper dynamicMapper;

    @GetMapping("/t1")
    public ResponseEntity t1() {
        atomikosTestService.test();
        return new ResponseEntity<>();
    }

    @GetMapping("/t2")
//    @Transactional
    public ResponseEntity t2() {
        final List select = dynamicMapper.selectBySql("select * from user");
        return new ResponseEntity();
    }

    @GetMapping("/t3")
    public ResponseEntity t3() {
        atomikosTestService.test2();
        return new ResponseEntity();
    }

    @GetMapping("/t4")
//    @Transactional
    public ResponseEntity t4() {
        atomikosTestService.test2();
        int a = 3 / 0;
        return new ResponseEntity();
    }
}
