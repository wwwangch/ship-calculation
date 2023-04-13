package com.iscas.common.zeromq.tools.repreq;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

/**
 * zeromq-req rep模式的服务端
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/16 12:13
 * @since jdk1.8
 */
public class ZmqRep {

    //线程数
    private int threadCount = 1;

    //端口
    private int port = 7111;

    private ZMQ.Context context;
    private ZMQ.Socket repSocket;

    public ZmqRep(int threadCount, int port) {
        this.threadCount = threadCount;
        this.port = port;
        initZMQ();
    }

    private void initZMQ() {
        context = ZMQ.context(threadCount);
        repSocket = context.socket(SocketType.REP);
        String bindUri = "tcp://*:" + port;
        repSocket.bind(bindUri);
    }

    /**
     * 获取socket对象
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param
     * @throws
     * @return org.zeromq.ZMQ.Socket
     */
    public ZMQ.Socket getSocket() {
        return repSocket;
    }

    /**
     * 接受数据
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param
     * @throws
     * @return byte[]
     */
    public byte[] recv() {
        byte[] bytes = repSocket.recv(0);
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
        if (repSocket != null) {
            repSocket.close();
        }
        if (context != null) {
            context.term();
        }
    }

    /**
     * 发送字节数组
     * @version 1.0
     * @since jdk1.8
     * @date 2021/1/16
     * @param msg
     * @throws
     * @return void
     */
    public void sendData(byte[] msg) {
        repSocket.send(msg);
    }
 }
