package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.mp.util.MybatisGeneralUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/6/15 10:11
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@RestController
@RequestMapping("/t/batch")
@ConditionalOnMybatis
public class TestBatchController {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private DynamicMapper dynamicMapper;

    @GetMapping("/t1")
    public void t1() throws SQLException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("insert into test values('3')");
        MybatisGeneralUtils.executeBatch(sqlSessionFactory,
                stringArrayList, 6, true);
    }

    @GetMapping("/t11")
    public void t11() throws SQLException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("insert into test values('3')");
        MybatisGeneralUtils.executeBatch2(sqlSessionFactory,
                stringArrayList, 6, true);
    }

    @GetMapping("/t2")
    public Map t2() throws SQLException {
        return dynamicMapper.selectOneBySql("select id as id from test");
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/t3")
    public void t3() throws SQLException {
        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("insert into test values('6')");
        stringArrayList.add("insert into test values('7')");
        dynamicMapper.batchBySql(stringArrayList);
    }

    @SuppressWarnings("AlibabaClassNamingShouldBeCamel")
    static class TTT{
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
