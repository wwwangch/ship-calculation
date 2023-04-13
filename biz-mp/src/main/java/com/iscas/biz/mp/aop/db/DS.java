package com.iscas.biz.mp.aop.db;

import java.lang.annotation.*;

/**
 * 数据源切换注解，可以与配置文件配置的包同时使用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/31 13:21
 * @since jdk1.8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface DS {
    String value();
}
