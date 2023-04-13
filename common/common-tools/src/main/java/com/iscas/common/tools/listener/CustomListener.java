package com.iscas.common.tools.listener;

import java.lang.annotation.*;

/**
 * 自定义监听注解
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/31 17:28
 * @since jdk1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.TYPE)
public @interface CustomListener {
    long order() default Long.MAX_VALUE;
    String name();
}
