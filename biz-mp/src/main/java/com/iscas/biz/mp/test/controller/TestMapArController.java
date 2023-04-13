package com.iscas.biz.mp.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.mapper.TestMpArMapper;
import com.iscas.biz.mp.test.model.TestMpAr;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/7 20:57
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RequestMapping("/test/mp/ar")
@RestController
@ConditionalOnMybatis
public class TestMapArController extends BaseController {
    @Autowired
    private TestMpArMapper testMpArMapper;

    @PostMapping("/insert")
    public ResponseEntity insert() {
        ResponseEntity response = getResponse();
        TestMpAr testMpAr = new TestMpAr();
        testMpAr.setName("test1");
        testMpAr.insert();
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity update() {
        ResponseEntity response = getResponse();
        TestMpAr testMpAr = new TestMpAr();
        testMpAr = testMpAr.selectById(1);
        testMpAr.setName("test111");
        testMpAr.updateById();
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity search() {
        ResponseEntity response = getResponse();
        TestMpAr testMpAr = new TestMpAr();
        QueryWrapper<TestMpAr> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "test");
        List<TestMpAr> testMpArs = testMpAr.selectList(queryWrapper);
        response.setValue(testMpArs);
        return response;
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete() {
        ResponseEntity response = getResponse();
        TestMpAr testMpAr = new TestMpAr();
        testMpAr.setId(1);
        testMpAr.deleteById();
        return response;
    }


    @GetMapping("/nested")
    public ResponseEntity nested() {
        ResponseEntity response = getResponse();

        QueryWrapper<TestMpAr> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(testMpArQueryWrapper -> testMpArQueryWrapper.like("name", "11")
                .or().like("name", "111")).in("id", 49);
        List<TestMpAr> testMpArs = testMpArMapper.selectList(queryWrapper);
        response.setValue(testMpArs);
        return response;
    }

}
