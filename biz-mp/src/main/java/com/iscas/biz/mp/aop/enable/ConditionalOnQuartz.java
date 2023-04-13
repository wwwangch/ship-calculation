package com.iscas.biz.mp.aop.enable;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/3/26
 * @since jdk1.8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnQuartz.class)
public @interface ConditionalOnQuartz {
}