package com.iscas.base.biz.config.stomp;

import com.iscas.base.biz.autoconfigure.cors.CorsProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * webscoket配置
 *
 * @author zhuquanwen
 **/

@SuppressWarnings("unused")
@EnableWebSocketMessageBroker
public class WebSocketStompProxyRabbitmqConfig implements WebSocketMessageBrokerConfigurer {
    @Autowired
    private CorsProps corsProps;

    @Value("${rabbitmq.virtual-host:/}")
    private String virtualHost;
    @Value("${rabbitmq.relay-host}")
    private String relayHost;
    @Value("${rabbitmq.user}")
    private String user;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.heartbeatSendInterval}")
    private long heartbeatSendInterval;
    @Value("${rabbitmq.heartbeatReceiveInterval}")
    private long heartbeatReceiveInterval;
    @Value("${rabbitmq.stomp.port}")
    private int stompPort;
    @Value("${rabbitmq.amqp.port}")
    private int amqpPort;


    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:port/webSocketServer
        // 来和服务器的WebSocket连接
        registry.addEndpoint("/webSocketServer", "/webSsh")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOriginPatterns(corsProps.getOriginPattern())
//                .setAllowedOrigins(corsProps.getOrigin())
                .withSockJS();

        //如果想暴露多个节点,继续addEndpoint就可以


    }

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 使用RabbitMQ做为消息代理，替换默认的Simple Broker
        //定义了服务端接收地址的前缀，也即客户端给服务端发消息的地址前缀,@SendTo(XXX) 也可以重定向
        //这是给sendToUser使用,前端订阅需要加上/user
        registry.setUserDestinationPrefix("/user");
        //这是给客户端推送消息到服务器使用 ，推送的接口加上/app
        registry.setApplicationDestinationPrefixes("/app");
        // "STOMP broker relay"处理所有消息将消息发送到外部的消息代理
        registry.enableStompBrokerRelay("/exchange", "/topic", "/queue", "/amq/queue")
                //对应自己rabbitmq里的虚拟host
                .setVirtualHost(virtualHost)
                .setRelayHost(relayHost)
                .setRelayPort(stompPort)
                .setClientLogin(user)
                .setClientPasscode(password)
                .setSystemLogin(user)
                .setSystemPasscode(password)
                .setSystemHeartbeatSendInterval(heartbeatSendInterval)
                .setSystemHeartbeatReceiveInterval(heartbeatReceiveInterval);
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(createUserInterceptor());
    }


    /**
     * 将客户端渠道拦截器加入spring ioc容器
     */
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
