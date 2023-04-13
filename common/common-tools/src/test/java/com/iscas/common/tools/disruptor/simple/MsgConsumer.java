package com.iscas.common.tools.disruptor.simple;


import com.lmax.disruptor.EventHandler;
import lombok.AllArgsConstructor;

/**
 * disruptor简单消费者
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/18 14:50
 * @since jdk1.8
 */
@AllArgsConstructor
public class MsgConsumer implements EventHandler<MsgEvent> {
    private String name;

    @Override
    public void onEvent(MsgEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(name + "-> 接收到消息：" + event);
    }
}
