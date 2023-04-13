package com.iscas.base.biz.config.socketio;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;
import java.util.Optional;

/**
 * socket.io
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/25 8:51
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
@Lazy(false)
public class SocketioConfig {
    @Value("${socket.io.port:8974}")
    private int socketIoPort;
    @Value("${socket.io.namespaces}")
    private String[] namespaces;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config =
                new com.corundumstudio.socketio.Configuration();
        // 注意如果开放跨域设置，需要设置为null而不是"*"
        config.setOrigin(null);
        config.setPort(socketIoPort);
        config.setSocketConfig(new SocketConfig());
        config.setWorkerThreads(100);
        config.setAuthorizationListener(handshakeData -> true);
        //允许最大帧长度
        config.setMaxFramePayloadLength(1024 * 1024);
        //允许下最大内容
        config.setMaxHttpContentLength(1024 * 1024);
        final SocketIOServer server = new SocketIOServer(config);
        Optional.ofNullable(namespaces).ifPresent(nss ->
                Arrays.stream(nss).forEach(server::addNamespace));
        return server;

    }

    /**
     * 注入OnConnect，OnDisconnect，OnEvent注解。 不写的话Spring无法扫描OnConnect，OnDisconnect等注解
     * */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer){
        return new SpringAnnotationScanner(socketIOServer);
    }
}
