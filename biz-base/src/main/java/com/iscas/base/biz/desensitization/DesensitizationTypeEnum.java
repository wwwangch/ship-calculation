package com.iscas.base.biz.desensitization;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/5 9:11
 * @since jdk1.8
 */
public enum DesensitizationTypeEnum {

    /**
     * 默认方式
     * */
    DEFAULT,

    /**
     * 头部脱敏
     * */
    HEAD,

    /**
     * 尾部脱敏
     * */
    TAIL,

    /**
     * 中间脱敏
     * */
    MIDDLE,

    /**
     * 头尾脱敏
     * */
    HEAD_TAIL,

    /**
     * 全部脱敏
     * */
    ALL,

    /**
     * 不脱敏，相当于没打这个注解
     * */
    NONE;

}
