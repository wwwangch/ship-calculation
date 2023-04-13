package com.iscas.biz.mp.test.service.mysql2;

import com.iscas.biz.mp.aop.db.DS;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.templet.exception.BaseException;
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
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Service
@ConditionalOnMybatis
public class TestService2 {
    @Autowired
    private DynamicMapper dynamicMapper;

    public List<Map> getIds() {
        return dynamicMapper.selectBySql("select * from alg");
    }

    @Transactional
    @DS("mysql2")
    public void testMultiWithTransctional() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
    }

    @Transactional
    @DS("mysql2")
    public void testMultiWithTransctional2() {
        dynamicMapper.insertBySql("insert into test (name, age) values('张三', 20)");
        throw new RuntimeException("测试回滚");
    }

}

