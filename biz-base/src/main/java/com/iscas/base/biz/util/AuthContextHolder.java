package com.iscas.base.biz.util;

import com.iscas.base.biz.model.auth.AuthContext;
import com.iscas.templet.exception.AuthenticationRuntimeException;

import java.util.Optional;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/21 11:02
 * @since jdk1.8
 */
public class AuthContextHolder {
    private AuthContextHolder() {
    }

    private static final ThreadLocal<AuthContext> CONTEXT = new ThreadLocal<>();

    public static void setContext(AuthContext authContext) {
        CONTEXT.set(authContext);
    }

    public static AuthContext getContext() {
        return CONTEXT.get();
    }

    public static void removeContext() {
        CONTEXT.remove();
    }


    public static Integer getUserId() {
        return Optional.ofNullable(AuthContextHolder.getContext())
                .map(context -> (Integer) context.getUserId())
                .orElseThrow(() -> new AuthenticationRuntimeException("用户未登录"));
    }

    public static String getUsername() {
        return Optional.ofNullable(AuthContextHolder.getContext())
                .map(AuthContext::getUsername)
                .orElseThrow(() -> new AuthenticationRuntimeException("用户未登录"));
    }
}
