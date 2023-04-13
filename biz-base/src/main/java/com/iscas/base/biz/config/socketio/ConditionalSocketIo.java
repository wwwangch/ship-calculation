package com.iscas.base.biz.config.socketio;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/30
 * @since jdk1.8
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSocketIo.class)
public @interface ConditionalSocketIo {
}