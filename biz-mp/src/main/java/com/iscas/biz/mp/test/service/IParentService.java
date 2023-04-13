package com.iscas.biz.mp.test.service;



import com.iscas.biz.mp.test.model.Parent;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface IParentService {
    List<Parent> findCascadeAllByCondition(Parent parent);
    List<Parent> findCascadeAll();
    int save(Parent parent);
}
