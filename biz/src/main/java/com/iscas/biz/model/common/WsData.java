package com.iscas.biz.model.common;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * websocket消息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26 9:14
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class WsData<T> {

    /**消息ID*/
    private String msgId;

    /**消息类型*/
    @SuppressWarnings("UnusedAssignment")
    private MsgTypeEnum type = MsgTypeEnum.BUSINESS;

    /**用户标识*/
    @JsonIgnore
    private String userIdentity;

    /**是否持久化*/
    private boolean persistent;

    /**推送得数据*/
    private T data;

    /**消息地址*/
    @JsonIgnore
    private String destination = "/queue/message";

    /**是否已接收*/
    @JsonIgnore
    private boolean ack;

    public enum MsgTypeEnum {
        /**系统通知（显示在上方消息通知内）*/
        SYSTEM,
        /**业务消息*/
        BUSINESS
    }

    public WsData(String msgId, MsgTypeEnum type, String userIdentity, boolean persistent, T data) {
        this.msgId = msgId;
        this.type = type;
        this.userIdentity = userIdentity;
        this.persistent = persistent;
        this.data = data;
    }
}
