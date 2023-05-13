package com.iscas.biz.calculation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 17:09
 */
public enum NavigationArea {
    UNLIMITED("0","无限航区"),
    LIMITED("1","有限航区"),
    ;

    @EnumValue
    private String value;

    private String descCH;

    NavigationArea(String value, String descCH) {
        this.value = value;
        this.descCH = descCH;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    public String getDescCH() {
        return descCH;
    }
}
