package com.iscas.biz.test.datasong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.mp.aop.db.DS;
import com.iscas.biz.test.datasong.model.TestTable;
import com.iscas.biz.test.datasong.mapper.TestTableMapper;
import com.iscas.biz.test.datasong.service.ITestTableService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/8 8:33
 * @since jdk11
 */
@Service
public class TestTableServiceImpl extends ServiceImpl<TestTableMapper, TestTable> implements ITestTableService {
    @Override
    @DS("datasong")
    public void testSave() {
        TestTable testTable = new TestTable();
//        testTable.setId(100);
        testTable.setAddcol4("test");
        testTable.setName("this is test name");
        boolean save = this.save(testTable);
        System.out.println(save);
    }

    @Override
    @DS("datasong")
    public void testSelect() {
        QueryWrapper<TestTable> testTableQueryWrapper = new QueryWrapper<>();
//        testTableQueryWrapper.like("name", "this is");
        Page<TestTable> page = this.page(new Page<>(1, 20), testTableQueryWrapper);
        System.out.println(page);
    }

    @DS("datasong")
    @Override
    public void testUpdate() {
        UpdateWrapper<TestTable> testTableUpdateWrapper = new UpdateWrapper<>();
        testTableUpdateWrapper.set("add_col4", "adddddd");
        testTableUpdateWrapper.like("name", "this is");
        this.update(testTableUpdateWrapper);
    }

    @Override
    @DS("datasong")
    public void testDelete() {
        this.remove(new QueryWrapper<TestTable>().lambda().eq(TestTable::getName, "testName"));
    }
}
