package com.iscas.base.biz.config.stomp;

import com.iscas.base.biz.autoconfigure.cors.CorsProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * webscoket配置
 *
 * @author zhuquanwen
 **/

@SuppressWarnings({"AlibabaRemoveCommentedCode", "unused"})
@EnableWebSocketMessageBroker
public class WebSocketStompSimpleConfig implements WebSocketMessageBrokerConfigurer {
    public static StompEndpointRegistry endpointRegistry;
    @Autowired
    private CorsProps corsProps;
    @Value("${ws.sockjs.enabled:true}")
    private boolean wsSockJsEnabled;

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
        StompWebSocketEndpointRegistration stompWebSocketEndpointRegistration = registry.addEndpoint("/webSocketServer", "/webSsh")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOriginPatterns(corsProps.getOriginPattern());
//                .setAllowedOrigins(corsProps.getOrigin());
        if (wsSockJsEnabled) {
            stompWebSocketEndpointRegistration.withSockJS();
        }
//                .withSockJS();
//                .setClientLibraryUrl("http://localhost:7901/demo/websocketTest/sockjs.js");
        endpointRegistry = registry;

    }

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称

        //setHeartbeatValue设置后台向前台发送的心跳，
        //注意：setHeartbeatValue这个不能单独设置，不然不起作用，要配合后面setTaskScheduler才可以生效。
        //对应的解决方法的网址：https://stackoverflow.com/questions/39220647/spring-stomp-over-websockets-not-scheduling-heartbeats
        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
        te.setPoolSize(1);
        te.setThreadNamePrefix("wss-heartbeat-thread-");
        te.initialize();
        registry.enableSimpleBroker("/queue", "/topic").setHeartbeatValue(new long[]{0L, 20000L}).setTaskScheduler(te);
        // 全局前端给后台发消息的前缀，为了是的@SubscribeMapping注解生效，将/user, /queue, /topic也添加进来
        registry.setApplicationDestinationPrefixes("/app", "/user", "/queue", "/topic");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(createUserInterceptor());

    }


    /**将客户端渠道拦截器加入spring ioc容器*/
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }


    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(500 * 1024 * 1024);
        registration.setSendBufferSizeLimit(1024 * 1024 * 1024);
        registration.setSendTimeLimit(200000);
    }


}
