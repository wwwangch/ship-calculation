package com.iscas.common.zeromq.tools.pubsub;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

/**
 * zeromq订阅数据端
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/16 10:50
 * @since jdk1.8
 */
public class ZmqSub {
    /**
     * ZMQ启动线程数
     */
    private int threadCount = 1;

    /**
     * ZMQ接收端口
     */
    private int port;

    /**
     * ZMQ监听接收端口
     */
    private String serverIp;

    private ZMQ.Context context = null;
    private ZMQ.Socket subSock = null;

    public ZmqSub(int threadCount, String serverIp, int port) {
        this.threadCount = threadCount;
        this.serverIp = serverIp;
        this.port = port;
        initZMQ();
    }

    /**
     * 初始化ZMQ对象
     */
    private void initZMQ() {
        context = ZMQ.context(threadCount);
        subSock = context.socket(SocketType.SUB);
        String ConUri = "tcp://" + serverIp + ":" + port;
        subSock.connect(ConUri);
        subSock.subscribe("".getBytes());
    }

    /**
     * 接受订阅的数据
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param
     * @throws
     * @return byte[]
     */
    public byte[] recv() {
        byte[] bytes = subSock.recv(zmq.ZMQ.ZMQ_SUB);
        return bytes;
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
        if (subSock != null) {
            subSock.close();
        }
        if (context != null) {
            context.term();
        }
    }

}
