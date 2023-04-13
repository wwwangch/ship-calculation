package com.iscas.base.biz.config.stomp;

import com.iscas.base.biz.aop.enable.EnableWebsocketStomp;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/27 21:43
 * @since jdk1.8
 */
public class WsImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableWebsocketStomp.class.getName(), false));
        assert attributes != null;
        WsPushType pushType = attributes.getEnum("pushType");
        return switch (pushType) {
            case SIMPLE -> new String[] {WebSocketStompSimpleConfig.class.getName()};
            case RABBITMQ -> new String[] {WebSocketStompProxyRabbitmqConfig.class.getName()};
            case SERVER_CLUSTER_USE_RABBIT -> new String[] {WebSocketStompClusterUseRabbitConfig.class.getName()};
            //noinspection UnnecessaryDefault
            default -> throw new StompRegistryException("不支持的websocket类型，仅支持SIMPLE、RABBITMQ、SERVER_CLUSTER_USE_RABBIT");
        };

    }
}
