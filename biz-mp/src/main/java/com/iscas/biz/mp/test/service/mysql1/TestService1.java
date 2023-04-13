package com.iscas.biz.mp.test.service.mysql1;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iscas.biz.mp.aop.db.DS;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.config.db.DbContextHolder;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.biz.mp.test.mapper.TestEntityMapper;
import com.iscas.biz.mp.test.model.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/10 15:07
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "unused", "rawtypes", "unchecked"})
@Service
@ConditionalOnMybatis
public class TestService1 {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Autowired
    private TestEntityMapper testEntityMapper;

    public List<Map> getIds() {
        return dynamicMapper.selectBySql("select * from test");
    }

    public List<TestEntity> get() {
        return testEntityMapper.selectList(null);
    }

    public List<TestEntity> get2() {
        IPage<TestEntity> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);
        IPage<TestEntity> testEntityIPage = testEntityMapper.selectPage(page, null);
        return testEntityIPage.getRecords();
    }

    @Transactional
    public void insert() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        throw new RuntimeException("测试回滚，抛出异常");
    }

    public void testMulti() {
        DbContextHolder.setDbType("mysql1");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        DbContextHolder.clearDbType();
        DbContextHolder.setDbType("mysql2");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        DbContextHolder.clearDbType();
    }


    @Transactional
    public void testMultiWithTransctional() {
        DbContextHolder.setDbType("mysql1");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        DbContextHolder.clearDbType();
        DbContextHolder.setDbType("mysql2");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        DbContextHolder.clearDbType();
    }

    @Transactional
    public void testMultiWithTransctional2() {
        DbContextHolder.setDbType("mysql1");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        DbContextHolder.clearDbType();
        DbContextHolder.setDbType("mysql2");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        DbContextHolder.clearDbType();
        throw new RuntimeException("测试回滚");
    }

    @Transactional
    @DS("mysql1")
    public void testMultiWithTransctional3() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");

    }

    @Transactional
    @DS("mysql1")
    public void testMultiWithTransctional4() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        throw new RuntimeException("测试回滚");

    }

    @Transactional
    @DS("mysql1")
    public void testMultiWithTransctional5() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        throw new RuntimeException("测试回滚");

    }

    @Transactional
    @DS("mysql1")
    public void testMultiWithTransctional6() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        throw new RuntimeException("测试回滚");

    }

}
