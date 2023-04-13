package com.iscas.base.biz.aop.ratelimiter;

import java.lang.annotation.*;

/**
 * 方法限流注解
 *
 * @author zhuquanwen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MethodRateLimit {
    //每秒产生的令牌数
    double permitsPerSecond() default 10;

    //最大等待时间，超过这个时间就反馈服务繁忙
    long maxWait() default 500;
}
