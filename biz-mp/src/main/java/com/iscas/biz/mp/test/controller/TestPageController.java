package com.iscas.biz.mp.test.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.mp.test.mapper.TestDataMapper;
import com.iscas.biz.mp.test.model.TestData;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/30 17:36
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/test/page")
@Slf4j
@ConditionalOnMybatis
public class TestPageController {
    @Autowired
    private TestDataMapper testDataMapper;
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @GetMapping("/create")
    public void create() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int j = 0; j < 1000 ; j++) {
            List<String> testDataList = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                String sql = "insert into test_data(name, age, description) values('@name@', @age@, '@desc@')";
                sql = sql.replace("@name@", UUID.randomUUID().toString())
                        .replace("@age@", random.nextInt(100) + "")
                        .replace("@desc@", UUID.randomUUID().toString());
                testDataList.add(sql);
            }
            dynamicMapper.batchBySql(testDataList);
        }
    }

    @GetMapping("/testRowBounds")
    public void testRowBounds() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10 ; i++) {
            RowBounds rowBounds = new RowBounds(600000 + i * 20, 20);
            List<TestData> result = testDataMapper.toSelectAll(rowBounds);
        }
        long end = System.currentTimeMillis();
        log.info("=============rowBounds的耗时" + (end - start) + "ms=============");
    }

    @GetMapping("/testPage")
    public void testPage() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10 ; i++) {
            Page page = new Page();
            page.setCurrent(i + 30000);
            page.setSize(20);
            Page page1 = testDataMapper.selectPage(page, null);
            System.out.println("=======" + page1.getRecords().size());
        }
        long end = System.currentTimeMillis();
        log.info("=============testPage的耗时" + (end - start) + "ms=============");
    }

    @GetMapping("/testPageHelper")
    public void testPageHelper() {
        long start = System.currentTimeMillis();
        @SuppressWarnings("resource") SqlSession session = sqlSessionFactory.openSession();
        String method = "com.iscas.biz.mp.enhancer.mapper.DynamicMapper.dynamicSelect";
        for (int i = 0; i < 10 ; i++) {
            PageHelper.startPage(30000 + i, 20);
            Map<String, String> sqlMap = new HashMap<>(2);
            sqlMap.put("sql", "select * from test_data");
            List<Map> result = session.selectList(method, sqlMap);
            PageInfo page = new PageInfo(result);
            System.out.println("=======" + page.getList().size());
        }
        long end = System.currentTimeMillis();
        log.info("=============testPageHelper的耗时" + (end - start) + "ms=============");
    }
}
