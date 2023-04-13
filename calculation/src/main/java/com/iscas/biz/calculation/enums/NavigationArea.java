package com.iscas.biz.calculation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 17:09
 */
public enum NavigationArea {
    UNLIMITED(0,"无限航区"),
    LIMITED(1,"有限航区"),
    ;

    @EnumValue
    private Integer value;

    private String descCH;

    NavigationArea(Integer value, String descCH) {
        this.value = value;
        this.descCH = descCH;
    }
    @JsonValue
    public Integer getValue() {
        return value;
    }

    public String getDescCH() {
        return descCH;
    }
}
