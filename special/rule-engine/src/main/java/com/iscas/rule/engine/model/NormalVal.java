package com.iscas.rule.engine.model;

import lombok.Data;

/**
 * 正常值
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/19 15:02
 * @since jdk1.8
 */
@Data
@SuppressWarnings("JavadocDeclaration")
public class NormalVal {
    /**条件比较符号*/
    private String comparisonCode;

    /**值*/
    private Double value;
}
