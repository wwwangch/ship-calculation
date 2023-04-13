package com.iscas.rule.engine.model;

import lombok.Data;

/**
 * 单变量条件
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/19 14:45
 * @since jdk1.8
 */
@Data
@SuppressWarnings("JavadocDeclaration")
public class VariableCondition {
    /**是否取绝对值*/
    private boolean isAbsolute;

    /**条件比较符号*/
    private String comparisonCode;

    /**值*/
    private Double value;

    /**逻辑运算符，默认null*/
    private String logicCode = null;
}
