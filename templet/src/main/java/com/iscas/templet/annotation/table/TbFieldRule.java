package com.iscas.templet.annotation.table;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/20 21:26e
 * @since jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface TbFieldRule {
    String desc(); //提示描述

    boolean required() default false; //是否必须填写值

    String reg() default ""; //正则表达式

    int maxLength() default -1; //字符串类型最大长度

    int minLength() default -1; //字符串类型的最小长度

    boolean distinct() default false; //是否要校验重复值

    String highValue() default ""; //数值类型最大值

    String lowValue() default ""; //数值类型最小值

    boolean containsHigh() default false; //是否包含最大值

    boolean containsLow() default false; //是否包含最小值

    String[] mustIn() default {}; // 必须在数组中取某个值

    String[] mustNotIn() default {}; // 值必须不在数组中

}
