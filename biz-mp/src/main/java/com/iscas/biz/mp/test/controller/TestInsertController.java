package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.test.mapper.TestInsertMapper;
import com.iscas.biz.mp.test.model.TestInsert;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/20 17:05
 */
@RestController
@RequestMapping("/test/insert")
public class TestInsertController {
    @Autowired
    private TestInsertMapper testInsertMapper;

    @GetMapping
    public ResponseEntity test() {
        int line = testInsertMapper.insertBatchSomeColumn(List.of(new TestInsert("zhangsan", 33)), 10);
        ResponseEntity<Integer> res = new ResponseEntity<>();
        res.setValue(line);
        return res;
    }
}
