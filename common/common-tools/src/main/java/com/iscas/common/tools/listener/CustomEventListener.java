package com.iscas.common.tools.listener;

import java.util.EventListener;

/**
 * 自定义监听接口
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/31 17:25
 * @since jdk1.8
 */
@FunctionalInterface
public interface CustomEventListener<E extends CustomEvent> extends EventListener {
    /**
     * handleEvent
     * @since jdk11
     * @date 2022/4/18
     * @param e 事件
     */
    void handleEvent(E e);
}
