package com.iscas.biz.test.datasong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.test.datasong.model.TestTable;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/8 8:33
 * @since jdk11
 */
public interface ITestTableService extends IService<TestTable> {
    void testSave();

    void testSelect();

    void testUpdate();

    void testDelete();

}
