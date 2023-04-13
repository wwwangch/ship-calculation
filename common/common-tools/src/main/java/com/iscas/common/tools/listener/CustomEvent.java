package com.iscas.common.tools.listener;

import java.util.EventObject;

/**
 * 网关服务的监听事件
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/31 17:22
 * @since jdk1.8
 */
public class CustomEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source) {
        super(source);
    }
}
