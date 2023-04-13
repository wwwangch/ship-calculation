package com.iscas.base.biz.config.stomp;

import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.model.auth.User;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.util.List;
import java.util.Map;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/7 14:34
 * @since jdk1.8
 * 注册用户信息的默认方式
 */
@SuppressWarnings("rawtypes")
public class DefaultUserAccessor implements UserAccessor {
    @Override
    public void accessor(Message<?> message, StompHeaderAccessor accessor) {
        Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
        if (raw instanceof Map) {
            //这里就是token
            Object name = ((Map) raw).get(Constants.TOKEN_KEY);
            if (name instanceof List) {
                User user = new User();
                user.setUsername(((List) name).get(0).toString());
                accessor.setUser(user);
            }
        }
    }
}
