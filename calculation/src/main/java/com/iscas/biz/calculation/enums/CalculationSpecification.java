package com.iscas.biz.calculation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 16:12
 * 计算规范枚举
 */
public enum CalculationSpecification {
    COMMON_SPECIFICATION("0","common_specification","通用规范"),
    BIG_SHIP("1","big_ship","大船准则"),
    DQ("2","dq","DQ准则"),
    ;
    @EnumValue
    @JsonValue
    private String value;

    private String desc;

    private String descCH;

    CalculationSpecification(String value, String desc, String descCH) {
        this.value = value;
        this.desc = desc;
        this.descCH = descCH;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescCH() {
        return descCH;
    }
}
