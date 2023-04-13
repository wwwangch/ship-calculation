package com.iscas.common.tools.listener.event;


import com.iscas.common.tools.listener.CustomEvent;

/**
 * 测试的一个事件
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/1 9:21
 * @since jdk1.8
 */
public class TestEvent extends CustomEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TestEvent(Object source) {
        super(source);
    }
}
