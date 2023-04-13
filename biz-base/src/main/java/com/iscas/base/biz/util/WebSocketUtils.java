package com.iscas.base.biz.util;

import com.iscas.base.biz.config.stomp.MyWebMvcStompEndpointRegistry;
import com.iscas.base.biz.config.stomp.WebSocketStompSimpleConfig;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebMvcStompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebMvcStompWebSocketEndpointRegistration;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * websocket工具类
 *
 * <p>可以使用ws://19.168.100.88:7901/demo/webSocketServer/xxx/yyy/websocket</p>
 * <p>返回websocketSessions，其中key为yyy,val为对应的websocket session</p>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/5 21:19
 * @since jdk1.8
 */
@SuppressWarnings({"unchecked", "unused", "deprecation"})
public class WebSocketUtils {
    private WebSocketUtils() {
    }

    private static volatile Map<String, Object> websocketSessionsHolder;

    /**
     * 获取所有的websocket-sessions, Holder形式，不是真正的websoketsession
     */
    public static Map<String, Object> getSessionsHolder() throws IllegalAccessException {
        if (websocketSessionsHolder == null) {
            synchronized (WebSocketUtils.class) {
                if (websocketSessionsHolder == null) {
                    WebMvcStompEndpointRegistry endpointRegistry = (WebMvcStompEndpointRegistry) WebSocketStompSimpleConfig.endpointRegistry;
                    Field registrationsField;
                    if (endpointRegistry instanceof MyWebMvcStompEndpointRegistry) {
                        //自定义的类型，获取父类的
                        registrationsField = ReflectUtils.getField(endpointRegistry.getClass().getSuperclass(), "registrations");
                    } else {
                        registrationsField = ReflectUtils.getField(endpointRegistry.getClass(), "registrations");
                    }
                    List<WebMvcStompWebSocketEndpointRegistration> webMvcStompWebSocketEndpointRegistrations =
                            (List<WebMvcStompWebSocketEndpointRegistration>) ReflectUtils.getValue(registrationsField, endpointRegistry);

                    WebMvcStompWebSocketEndpointRegistration webMvcStompWebSocketEndpointRegistration = webMvcStompWebSocketEndpointRegistrations.get(0);
                    SubProtocolWebSocketHandler webSocketHandler = (SubProtocolWebSocketHandler) ReflectUtils.getValue(webMvcStompWebSocketEndpointRegistration,
                            webMvcStompWebSocketEndpointRegistration.getClass(), "webSocketHandler");
                    websocketSessionsHolder = (Map<String, Object>) ReflectUtils.getValue(webSocketHandler, webSocketHandler.getClass(),
                            "sessions");
                }
            }
        }
        return websocketSessionsHolder;
    }

    /**
     * 获取当前所有的websocket-sessions
     * map的key为ws://19.168.100.88:7901/demo/webSocketServer/xxx/yyy/websocket中的yyy
     */
    public static Map<String, WebSocketSession> getSessions() throws IllegalAccessException {
        Map<String, WebSocketSession> result = new HashMap<>(16);
        Map<String, Object> sessionsHolder = getSessionsHolder();
        if (sessionsHolder != null) {
            for (Map.Entry<String, Object> holderEntry : sessionsHolder.entrySet()) {
                String key = holderEntry.getKey();
                Object val = holderEntry.getValue();
                WebSocketSession session = (WebSocketSession) ReflectUtils.getValue(val, val.getClass(), "session");
                result.put(key, session);
            }
        }
        return result;
    }

    /**
     * 根据key获取WebsocketSession
     * mkey为ws://19.168.100.88:7901/demo/webSocketServer/xxx/yyy/websocket中的yyy
     */
    public static WebSocketSession getSession(String matchKey) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> sessionsHolder = getSessionsHolder();
        if (sessionsHolder != null) {
            for (Map.Entry<String, Object> holderEntry : sessionsHolder.entrySet()) {
                String key = holderEntry.getKey();
                if (Objects.equals(key, matchKey)) {
                    Object val = holderEntry.getValue();
                    return (WebSocketSession) ReflectUtils.getValue(val, val.getClass(), "session");
                }
            }
        }
        return null;
    }

}
