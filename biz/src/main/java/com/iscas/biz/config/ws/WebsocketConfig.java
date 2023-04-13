package com.iscas.biz.config.ws;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/8 21:44
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@AutoConfiguration
public class WebsocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
