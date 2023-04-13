package com.iscas.base.biz.model.stomp;

import lombok.Data;

/**
 * websocket消息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/21 11:16
 * @since jdk1.8
 */
@Data
public class WsMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String to;

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    public enum MessageType {
        BROADCAST,
        P2P
    }



}
