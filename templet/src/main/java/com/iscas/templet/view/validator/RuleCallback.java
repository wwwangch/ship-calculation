package com.iscas.templet.view.validator;

import com.iscas.templet.view.table.TableField;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/6 18:57
 * @since jdk1.8
 */
@FunctionalInterface
public interface RuleCallback {
    /**
     * 校验
     * @since jdk11
     * @date 2022/4/21
     * @param obj obj
     * @param tableField 列
     * @return boolean
     */
    boolean validate(Object obj, TableField tableField);
}
