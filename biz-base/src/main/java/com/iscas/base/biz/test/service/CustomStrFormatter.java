package com.iscas.base.biz.test.service;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/6 14:02
 * @since jdk11
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = CustomSerialize.class)
@JsonDeserialize(using = CustomDeserialize.class)
public @interface CustomStrFormatter {
    // todo 可以定义格式化方式
    String pattern() default "";
}
