package com.iscas.base.biz.config.stomp;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/7 14:32
 * @since jdk1.8
 * 前后台建立websocket连接时，注册用户信息
 */
public interface UserAccessor {
    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    void accessor(Message<?> message, StompHeaderAccessor accessor);
}
