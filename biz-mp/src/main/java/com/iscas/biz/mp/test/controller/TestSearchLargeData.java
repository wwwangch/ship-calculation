package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/6/15 10:11
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "unused", "rawtypes", "unchecked"})
@RestController
@ConditionalOnMybatis
public class TestSearchLargeData {

    @Autowired
    private DynamicMapper dynamicMapper;

    @GetMapping("/insertLargeData")
    public void insertLargeData() {
        List<String> sqls = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            String sql = "insert into test(name) values('aaaa%d')";
            sql = String.format(sql, i);
            sqls.add(sql);
        }
        dynamicMapper.batchBySql(sqls);
    }

    @GetMapping("/getLargeData")
    public void getLargeData() {
        String sql = "select * from test";
        dynamicMapper.selectLargeDataBySql(sql, (ResultHandler<Map>) resultContext -> {
            Map resultObject = resultContext.getResultObject();
        });
    }
}
