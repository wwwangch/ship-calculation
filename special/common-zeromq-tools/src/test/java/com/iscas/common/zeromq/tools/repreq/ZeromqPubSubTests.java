package com.iscas.common.zeromq.tools.repreq;

import com.iscas.common.zeromq.tools.pubsub.ZmqPub;
import com.iscas.common.zeromq.tools.pubsub.ZmqSub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

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

        //模拟的服务端
        ZmqRep zmqRep = new ZmqRep(1, 7111);
        new Thread(() -> {
            while (true) {
                byte[] recv = zmqRep.recv();
                String s = new String(recv);
                System.out.println("服务端收到的数据:" + s);
                zmqRep.sendData("lalala".getBytes());
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("END".equals(s)) {
                    zmqRep.close();
                }
            }
        }).start();

        //模拟的客户端
        ZmqReq zmqReq = new ZmqReq(1, "localhost", 7111);
        for (int i = 0; i < 10; i++) {
            zmqReq.sendData("hahaha".getBytes());
            TimeUnit.MILLISECONDS.sleep(200);
            byte[] recv = zmqReq.recv();
            String s = new String(recv);
            System.out.println("客户端收到的数据:" + s);
            if (i == 9) {
                zmqReq.sendData("END".getBytes());
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }
        zmqRep.close();
    }
}
