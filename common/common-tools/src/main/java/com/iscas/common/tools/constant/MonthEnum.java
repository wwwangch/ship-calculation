package com.iscas.common.tools.constant;

/**
 * 月份枚举
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/19 14:28
 * @since jdk1.8
 */
public enum MonthEnum {

    /**
     * 一月
     * */
    JAN(1),

    /**
     * 二月
     * */
    FEB(2),

    /**
     * 三月
     * */
    MAR(3),

    /**
     * 四月
     * */
    APR(4),

    /**
     * 五月
     * */
    MAY(5),

    /**
     * 六月
     * */
    JUNE(6),

    /**
     * 七月
     * */
    JULY(7),

    /**
     * 八月
     * */
    AUG(8),

    /**
     * 九月
     * */
    SEPT(9),

    /**
     * 十月
     * */
    OCT(10),

    /**
     * 十一月
     * */
    NOV(11),

    /**
     * 十二月
     * */
    DEC(12);


    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    MonthEnum(int value) {
        this.value = value;
    }
}
