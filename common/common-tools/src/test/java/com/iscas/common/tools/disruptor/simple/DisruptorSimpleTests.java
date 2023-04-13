package com.iscas.common.tools.disruptor.simple;

import com.lmax.disruptor.dsl.Disruptor;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/18 14:57
 * @since jdk1.8
 */
public class DisruptorSimpleTests {


    /**
     * 测试统一消费：每个消费者都消费一份生产者生产的数据
     * */
    @Test
    public void test() {
        Disruptor<MsgEvent> disruptor = new Disruptor<MsgEvent>(MsgEvent::new, 1024, Executors.defaultThreadFactory());

        //定义消费者
        MsgConsumer consumer1 = new MsgConsumer("consumer1");
        MsgConsumer consumer2 = new MsgConsumer("consumer2");
        MsgConsumer consumer3 = new MsgConsumer("consumer3");

        //绑定配置关系
        disruptor.handleEventsWith(consumer1, consumer2, consumer3);
        disruptor.start();

        //定义要发送的数据
        MsgProducer msgProducer = new MsgProducer(disruptor);
        msgProducer.send("disruptor发送一个测试数据");

    }


    /**
     * 测试分组消费：每个生产这生产的数据只被消费一次
     * */
    @Test
    public void test2() {
        Disruptor<MsgEvent> disruptor = new Disruptor<MsgEvent>(MsgEvent::new, 1024, Executors.defaultThreadFactory());

        //定义分组消费
        disruptor.handleEventsWithWorkerPool(new MyWorkHandler("consumer1"), new MyWorkHandler("consumer2"));

        disruptor.start();

        //定义要发送的数据
        MsgProducer msgProducer = new MsgProducer(disruptor);
        msgProducer.send("disruptor发送一个测试数据1");
        msgProducer.send("disruptor发送一个测试数据2");
        msgProducer.send("disruptor发送一个测试数据3");

    }



    /**
     * 测试统一消费，按顺序消费
     * */
    @Test
    public void test3() {
        Disruptor<MsgEvent> disruptor = new Disruptor<MsgEvent>(MsgEvent::new, 1024, Executors.defaultThreadFactory());

        //定义消费者
        MsgConsumer consumer1 = new MsgConsumer("consumer1");
        MsgConsumer consumer2 = new MsgConsumer("consumer2");
        MsgConsumer consumer3 = new MsgConsumer("consumer3");

        //绑定配置关系
        disruptor.handleEventsWith(consumer1, consumer3).then(consumer2);
        disruptor.start();

        //定义要发送的数据
        MsgProducer msgProducer = new MsgProducer(disruptor);
        msgProducer.send("disruptor发送一个测试数据");

    }

    /**
     * 测试统一消费，多支线消费，并设置顺序
     * */
    @Test
    public void test4() {
        Disruptor<MsgEvent> disruptor = new Disruptor<MsgEvent>(MsgEvent::new, 1024, Executors.defaultThreadFactory());

        //定义消费者
        MsgConsumer consumer1 = new MsgConsumer("consumer1");
        MsgConsumer consumer2 = new MsgConsumer("consumer2");
        MsgConsumer consumer3 = new MsgConsumer("consumer3");
        MsgConsumer consumer4 = new MsgConsumer("consumer4");
        MsgConsumer consumer5 = new MsgConsumer("consumer5");

        //绑定配置关系
        disruptor.handleEventsWith(consumer1, consumer2);
        disruptor.handleEventsWith(consumer3, consumer4);
        disruptor.after(consumer1, consumer3).handleEventsWith(consumer5);
        disruptor.start();

        //定义要发送的数据
        MsgProducer msgProducer = new MsgProducer(disruptor);
        msgProducer.send("disruptor发送一个测试数据");

    }

}
