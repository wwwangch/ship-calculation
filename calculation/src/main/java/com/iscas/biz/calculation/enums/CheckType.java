package com.iscas.biz.calculation.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 14:26
 */
public enum CheckType {
    EXTREME("0", "极限工况校验"),

    CRUISE("1", "巡航工况校验"),
    ;

    @EnumValue
    private String value;

    private String descCH;

    CheckType(String value, String descCH) {
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

    public static CheckType getByValueStr(String valueStr) {
        for (CheckType checkType : CheckType.values()) {
            if (checkType.getValue().equals(valueStr)) {
                return checkType;
            }
        }
        return null;
    }
}
