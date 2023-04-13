 package com.iscas.common.rocketmq.tools;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/25 15:34
 * @since jdk1.8
 */
public class RocketMqServiceTest {
    private RocketMqService rocketMqService;
    @BeforeEach
    public void init() {
//        rocketMqService = new RocketMqService("172.16.10.169:9876;172.16.10.168:9876",
//                "test-topic-3", "test-tag", 5000);
        rocketMqService = new RocketMqService("192.168.100.23:9876",
                "test-topic-3", "test-tag", 5000);
    }

    /**
     * 普通批量发送
     * */
    @Test
    public void test() throws Exception {
        List<String> msgs = Stream.of(1, 2, 3, 4, 5)
                .map(i -> "msg" + i)
                .toList();
        List<SendResult> sendResults = rocketMqService.send("test-group", msgs);
        rocketMqService.pull("test-consumer", new RocketMqService.HeaderInterface() {
            @Override
            public void execute(MessageExt message) throws IOException {
                System.out.println(Thread.currentThread().getName() + "--" + new String(message.getBody()));
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 顺序批量发送
     * */
    @Test
    public void test2() throws Exception {
        List<String> msgs = Stream.of(1, 2, 3, 4, 5)
                .map(i -> "msg" + i)
                .toList();

        List<SendResult> sendResults = rocketMqService.send("test-group", msgs,
                true,  1000, 0);

        List<SendResult> sendResults2 = rocketMqService.send("test-group", msgs,
                true,  1000, 0);

        rocketMqService.pull("test-consumer", new RocketMqService.HeaderInterface() {
            @Override
            public void execute(MessageExt message) throws IOException {
                System.out.println(Thread.currentThread().getName() + "--" + new String(message.getBody()));
            }
        }, false, true);
        TimeUnit.SECONDS.sleep(5);
    }

}