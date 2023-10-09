package com.iscas.biz.calculation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/6/16 16:21
 */
public enum UpBuiltForm {
    NO_BOW_RIDGE(1,"无艏楼无脊弧"),
    Bow(2,"艏楼"),
    BRIDGE_TOWER(3,"桥楼"),
    RIDGE(4,"脊弧"),
    ;
    @EnumValue
    private Integer value;

    private String descCH;

    UpBuiltForm(Integer value, String descCH) {
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
