package com.iscas.common.zeromq.tools.pubsub;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 测试zeromq发布订阅模式
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/16 11:23
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class ZeromqPubSubTests {

    @Test
    public void test() throws InterruptedException {
        ZmqSub zmqSub = new ZmqSub(1, "127.0.0.1", 7111);
        new Thread(() -> {
            while (true) {
                byte[] recv = zmqSub.recv();
                String s = new String(recv);
                System.out.println(s);
                if ("END".equals(s)) {
                    break;
                }
            }
            zmqSub.close();
        }).start();
        ZmqPub zmqPub = new ZmqPub(1, 7111);
        Thread.sleep(1000);
        zmqPub.pushData("aaaa");
        Thread.sleep(1000);
        zmqPub.pushData("bbbb");
        Thread.sleep(1000);
        zmqPub.pushData("cccc");
        Thread.sleep(1000);
        zmqPub.pushData("dddd");
        Thread.sleep(1000);
        zmqPub.pushData("END");
        Thread.sleep(1000);
        zmqPub.close();
    }
}
