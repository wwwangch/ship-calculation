package com.iscas.common.tools.disruptor.simple;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.AllArgsConstructor;

/**
 * Disruptor 生产者
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/18 14:54
 * @since jdk1.8
 */
@AllArgsConstructor
public class MsgProducer {
    private Disruptor disruptor;

    public void send(String data) {
        RingBuffer<MsgEvent> ringBuffer = disruptor.getRingBuffer();
        long next = ringBuffer.next();
        try {
            MsgEvent msgEvent = ringBuffer.get(next);
            msgEvent.setMsg(data);
        } finally {
            ringBuffer.publish(next);
        }
    }
}
