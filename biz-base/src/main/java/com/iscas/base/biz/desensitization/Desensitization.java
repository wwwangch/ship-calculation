package com.iscas.base.biz.desensitization;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏注解
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/5 8:48
 * @since jdk1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationSerializer.class)
public @interface Desensitization {
    /**
     * 脱敏的隐私数据类型
     */
    PrivacyTypeEnum dataType();

    /**
     * 脱敏方式,默认方式不需要定义下面脱敏长度等信息，根据脱敏的隐私数据类型自动脱敏
     */
    DesensitizationTypeEnum mode() default DesensitizationTypeEnum.DEFAULT;

    /**
     * 尾部不脱敏的长度，当mode为HEAD或MIDDLE时使用
     */
    int tailNoMaskLen() default 1;

    /**
     * 头部不脱敏的长度，当mode为TAIL或MIDDLE时使用
     */
    int headNoMaskLen() default 1;

    /**
     * 中间不脱敏的长度，当mode为HEAD_TAIL时使用
     */
    int middleNoMaskLen() default 1;

    /**
     * 打码
     */
    char maskCode() default '*';

}
