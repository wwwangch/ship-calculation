package com.iscas.rule.engine.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 计算条件
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/2/20 11:03
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@SuppressWarnings("JavadocDeclaration")
public class ComputeCondition {

    /**计算符号*/
    private String computeCode;

    /**参数代号*/
    private String paramCode;

    /**是否取绝对值*/
    private boolean isAbsolute;



}
