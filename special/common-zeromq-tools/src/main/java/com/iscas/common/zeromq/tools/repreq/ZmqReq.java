package com.iscas.common.zeromq.tools.repreq;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

/**
 * zeromq-req rep模式的客户端
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/16 12:13
 * @since jdk1.8
 */
public class ZmqReq {

    //线程数
    private int threadCount = 1;

    //serverip
    private String serverIp;

    //端口
    private int port = 7111;

    private ZMQ.Context context;
    private ZMQ.Socket reqSocket;

    public ZmqReq(int threadCount, String serverIp, int port) {
        this.threadCount = threadCount;
        this.serverIp = serverIp;
        this.port = port;
        initZMQ();
    }

    private void initZMQ() {
        context = ZMQ.context(threadCount);
        reqSocket = context.socket(SocketType.REQ);
        String ConUri = "tcp://" + serverIp + ":" + port;
        reqSocket.connect(ConUri);
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
        return reqSocket;
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
        byte[] bytes = reqSocket.recv(0);
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
        if (reqSocket != null) {
            reqSocket.close();
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
        reqSocket.send(msg);
    }
 }
