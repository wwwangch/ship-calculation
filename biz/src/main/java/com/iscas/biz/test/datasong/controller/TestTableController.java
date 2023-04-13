package com.iscas.biz.test.datasong.controller;

import com.iscas.biz.test.datasong.model.TestTable;
import com.iscas.biz.test.datasong.service.ITestTableService;
import com.iscas.templet.common.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/8 8:36
 * @since jdk11
 */
@RestController
@RequestMapping("/test-table")
@RequiredArgsConstructor
public class TestTableController {
    private final ITestTableService testTableService;

    @PostMapping
    public ResponseEntity testInsert() {
        testTableService.testSave();
        return new ResponseEntity();
    }

    @GetMapping
    public ResponseEntity testSelect() {
        testTableService.testSelect();
        return new ResponseEntity();
    }

    @PutMapping
    public ResponseEntity testUpdate() {
        testTableService.testUpdate();
        return new ResponseEntity();
    }

    @DeleteMapping
    public ResponseEntity testDelete() {
        testTableService.testDelete();
        return new ResponseEntity();
    }
}
