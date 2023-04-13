package com.iscas.common.tools.ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 判断连接
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/13 13:46
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class PingUtils {
    private PingUtils() {}

    /**判断IP端口是否可连接*/
    public static boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 判断某个IP是否可以ping通
     * */
    public static boolean isHostReachable(String host, int timeout) {
        try {
            return InetAddress.getByName(host).isReachable(timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
