package com.iscas.base.biz.aop.enable;

import com.iscas.base.biz.config.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启限流注解
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 11:27
 * @since jdk1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RateLimiterConfig.class)
public @interface EnableRateLimiter {

}
