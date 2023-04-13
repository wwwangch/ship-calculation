package com.iscas.base.biz.autoconfigure.undertow;

import io.undertow.Undertow;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/12 19:49
 * @since jdk1.8
 */
@AutoConfiguration
@ConditionalOnClass(Undertow.class)
public class UndertowAutoConfiguration {
    @Bean
    public CustomizationBean customizationBean() {
        return new CustomizationBean();
    }


    public class CustomizationBean implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
        @Override
        public void customize(UndertowServletWebServerFactory factory) {
            factory.addDeploymentInfoCustomizers(deploymentInfo -> {
                WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
                webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
                deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
            });
        }

    }
}
