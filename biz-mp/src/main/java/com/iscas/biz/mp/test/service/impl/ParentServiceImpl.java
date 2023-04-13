package com.iscas.biz.mp.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.test.mapper.ParentMapper;
import com.iscas.biz.mp.test.model.Parent;
import com.iscas.biz.mp.test.service.IParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Service
@ConditionalOnMybatis
public class ParentServiceImpl implements IParentService {
    @Autowired
    private ParentMapper parentMapper;

    @Override
    public List<Parent> findCascadeAllByCondition(Parent parent) {
        return parentMapper.selectCascadeAllByCondition(parent);
    }

    @Override
    public List<Parent> findCascadeAll() {
        //分页插件: 查询第1页，每页2行
        Page<Parent> page = PageHelper.startPage(1, 2);
        parentMapper.selectCascadeAll();
        return page.getResult();
    }

    @Override
    public int save(Parent parent) {
        return parentMapper.insert(parent);
    }
}
