package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.mapper.TestEntityMapper;
import com.iscas.biz.mp.test.model.TestEntity;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/4/5 15:22
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/testEntity")
@ConditionalOnMybatis
public class TestMpEnumController extends BaseController {
    @Autowired
    private TestEntityMapper testEntityMapper;

    @GetMapping("/get")
    public ResponseEntity testEntity() {
        ResponseEntity response = getResponse();
        List<TestEntity> testEntities = testEntityMapper.selectList(null);
        response.setValue(testEntities);
        return response;
    }
    @PostMapping("/post")
    public ResponseEntity testSaveEntity(@RequestBody TestEntity testEntity) {
        ResponseEntity response = getResponse();
        int insert = testEntityMapper.insert(testEntity);
        response.setValue(insert);
        return response;
    }

}
