package com.iscas.base.biz.config.openauth;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/22 14:56
 * @since jdk1.8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnOpenAuth.class)
public @interface ConditionalOnOpenAuth {
}
