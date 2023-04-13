package com.iscas.base.biz.config.datasongplus;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/11 13:39
 * @since jdk1.8
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnDatasongPlus.class)
public @interface ConditionalDatasongPlus {
}