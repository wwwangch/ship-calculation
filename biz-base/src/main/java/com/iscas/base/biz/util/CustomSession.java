package com.iscas.base.biz.util;

import com.auth0.jwt.interfaces.Claim;
import com.iscas.base.biz.autoconfigure.auth.TokenProps;
import com.iscas.templet.exception.BaseRuntimeException;
import com.iscas.templet.exception.Exceptions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义session
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/9 16:53
 * @since jdk1.8
 */
public class CustomSession {
    private CustomSession() {}

    private static String getSessionId() {
        String token = AuthUtils.getToken();
        String sessionId;
        try {
            Map<String, Claim> stringClaimMap = JWTUtils.verifyToken(token, SpringUtils.getBean(TokenProps.class).getCreatorMode());
            Claim claim = stringClaimMap.get("sessionId");
            sessionId = claim.asString();
        } catch (Exception e) {
            e.printStackTrace();
            throw Exceptions.baseRuntimeException("校验token信息出错");
        }
        return sessionId;
    }

    public static void setAttribute(String key, Object value) {
        String sessionId = getSessionId();
        Map<String, Object> sessionMap = SessionCacheUtils.get(sessionId);
        if (sessionMap == null) {
            sessionMap = new ConcurrentHashMap<>(2 << 2);
        }
        sessionMap.put(key, value);
        SessionCacheUtils.put(sessionId, sessionMap);
    }

    public static void setAttribute(String sessionId, String key, Object value) {
        Map<String, Object> sessionMap = SessionCacheUtils.get(sessionId);
        if (sessionMap == null) {
            sessionMap = new ConcurrentHashMap<>(2 << 2);
        }
        sessionMap.put(key, value);
        SessionCacheUtils.put(sessionId, sessionMap);
    }

    public static Object getAttribute(String key) {
        String sessionId = getSessionId();
        Map<String, Object> sessionMap = SessionCacheUtils.get(sessionId);
        if (sessionMap != null) {
            return sessionMap.get(key);
        }
        return null;
    }
}
