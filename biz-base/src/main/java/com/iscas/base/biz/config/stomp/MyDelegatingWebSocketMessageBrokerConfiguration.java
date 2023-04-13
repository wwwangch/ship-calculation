package com.iscas.base.biz.config.stomp;

import cn.hutool.core.util.ReflectUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.DelegatingWebSocketMessageBrokerConfiguration;

import java.lang.reflect.Method;

/**
 * 升级springboot到2.4.0后websocket出现跨域问题处理，重写DelegatingWebSocketMessageBrokerConfiguration
 * <p>不需要此处理了，过期了，即将删除</>
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/25 13:12
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Deprecated
//@AutoConfiguration
@ConditionalOnProperty(name = "ws.stomp.register", havingValue = "true")
public class MyDelegatingWebSocketMessageBrokerConfiguration extends DelegatingWebSocketMessageBrokerConfiguration {

    @Override
    public @NotNull HandlerMapping stompWebSocketHandlerMapping(@NotNull WebSocketHandler subProtocolWebSocketHandler,
                                                                @NotNull TaskScheduler messageBrokerTaskScheduler) {
        WebSocketHandler handler = decorateWebSocketHandler(subProtocolWebSocketHandler);
        MyWebMvcStompEndpointRegistry registry = new MyWebMvcStompEndpointRegistry(
                handler, getTransportRegistration(), messageBrokerTaskScheduler());

        ApplicationContext applicationContext = getApplicationContext();
        if (applicationContext != null) {
            Method method = ReflectUtil.getMethod(MyWebMvcStompEndpointRegistry.class, "setApplicationContext", ApplicationContext.class);
            ReflectUtil.invoke(registry, method, applicationContext);
        }
        registerStompEndpoints(registry);
        return registry.getHandlerMapping();
    }

}
