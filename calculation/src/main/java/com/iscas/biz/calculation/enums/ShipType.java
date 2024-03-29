package com.iscas.biz.calculation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 15:42
 */
public enum ShipType {
    QZJ("GJB4000-2000"),
    QZJ2("QZJ"),
    DC("DC"),
    DQ("DQ");

    ShipType(String value) {
        this.value = value;
    }

    @EnumValue
    private String value;


    public String getValue() {
        return value;
    }
}
