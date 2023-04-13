package com.iscas.rule.engine.model;

import lombok.Data;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/13 11:00
 * @since jdk1.8
 */
@Data
@SuppressWarnings("JavadocDeclaration")
public class FlowExpression {

    private String level;

    private String expression;

    private int alarmCount = 1;

    /**
     *参数名称
     */
    private String paramName;

    /**
     * 参数状态
     * */
    private String status;

    /**
     * 描述
     * */
    private String desc;

    /**
     * 预案
     * */
    private String plan;
}
