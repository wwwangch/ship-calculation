package com.iscas.rule.engine.model;

import lombok.Data;

import java.util.List;

/**
 * 单变量区间
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/19 14:32
 * @since jdk1.8
 */
@Data
@SuppressWarnings("JavadocDeclaration")
public class SingleVariableRegion {

    /**参数代号*/
    private String paramCode;

    /**计算条件*/
    private List<VariableCondition> variableConditions;

}
