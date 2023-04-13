package com.iscas.common.zeromq.tools.pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

/**
 * zeromq推送数据端
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/16 10:24
 * @since jdk1.8
 */
public class ZmqPub {

    //线程数
    private int threadCount = 1;

    //端口
    private int port = 7111;

    //
    private static ZMQ.Context context = null;
    private static ZMQ.Socket pubSock = null;

    public ZmqPub(int threadCount, int port) {
        this.threadCount = threadCount;
        this.port = port;
        initZMQ();
    }

    /**
     * 初始化ZMQ对象
     */
    private void initZMQ() {
        context = ZMQ.context(threadCount);
        pubSock = context.socket(SocketType.PUB);
        String bindUri = "tcp://*:" + port;
        pubSock.bind(bindUri);
    }

    /**
     * 推送字符串数据
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param msg
     * @throws
     * @return void
     */
    public void pushData(String msg) {
//        pubSock.send(msg, ZMQ.NOBLOCK);
        pubSock.send(msg.getBytes());
    }

    /**
     * 推送字节数组
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param msg
     * @throws
     * @return void
     */
    public void pushData(byte[] msg) {
        pubSock.send(msg);
    }

    /**
     * 获取ZMQ.Socket对象，可以做其他类型的推送
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param
     * @throws
     * @return org.zeromq.ZMQ.Socket
     */
    public ZMQ.Socket getPubSock() {
        return pubSock;
    }

    /**
     * 关闭
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param
     * @throws
     * @return void
     */
    public void close() {
        if (pubSock != null) {
            pubSock.close();
        }
        if (context != null) {
            context.term();
        }
    }

}
