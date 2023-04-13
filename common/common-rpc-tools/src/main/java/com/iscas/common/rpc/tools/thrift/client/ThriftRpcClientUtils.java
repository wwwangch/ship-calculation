package com.iscas.common.rpc.tools.thrift.client;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * thrift客户端工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 13:02
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class ThriftRpcClientUtils {
    private ThriftRpcClientUtils() {
    }

    /**
     * 获取transport
     * 使用例子：
     * TTransport transport = null;
     * try {
     * transport = new TSocket("127.0.0.1", 6367, 2000);
     * // 协议要和服务端一致
     * TProtocol protocol = new TBinaryProtocol(transport);
     * <p>
     * RPCDateService.Client client = new RPCDateService.Client(protocol);
     * transport.open();
     * String result = client.getDate("张三");
     * System.out.println("Thrify client result =: " + result);
     * } catch (TTransportException e) {
     * e.printStackTrace();
     * } catch (TException e) {
     * e.printStackTrace();
     * } finally {
     * if (null != transport) {
     * transport.close();
     * }
     * }
     *
     * @param ip      ip
     * @param port    端口
     * @param timeout 超时时间
     * @return TTransport
     * @date 2020/11/21
     * @since jdk1.8
     */
    public static TTransport getTransport(String ip, int port, long timeout) {
        return new TSocket("127.0.0.1", 6367, 2000);
    }

    public static void close(TTransport tTransport) {
        tTransport.close();
    }
}
