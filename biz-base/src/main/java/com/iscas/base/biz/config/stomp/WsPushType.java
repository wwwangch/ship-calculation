package com.iscas.base.biz.config.stomp;

/**
 * Websocket推送方式
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/21 9:48
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
public enum WsPushType {
    SIMPLE, RABBITMQ, SERVER_CLUSTER_USE_RABBIT
}
