package com.iscas.rule.engine.model;

import lombok.Data;

/**
 * 门限
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/1/19 14:54
 * @since jdk1.8
 */
@Data
@SuppressWarnings("JavadocDeclaration")
public class Threshold {
    /**下限*/
    private Double low;

    /**上限*/
    private Double high;

    /**类型 0有效值， 1轻度 2 中度 3重度*/
    private int type = 0;

    /**是否有效*/
    private boolean isEnabled = true;
}
