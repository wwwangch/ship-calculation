package com.iscas.common.rpc.tools.thrift;

import com.iscas.common.rpc.tools.thrift.client.ThriftRpcClientUtils;
import com.iscas.common.rpc.tools.thrift.server.ThriftRpcServerUtils;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * thrift测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 13:11
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class ThriftCallTests {

    /**
     * 测试thrift发布服务，并测试
     * */
    @Test
    public void call() throws InvocationTargetException, NoSuchMethodException, TTransportException, InstantiationException, IllegalAccessException {
        TProcessor tprocessor = new RPCDateService.Processor<RPCDateService.Iface>( new RPCDateServiceImpl());
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //客户端调用
            TTransport transport = ThriftRpcClientUtils.getTransport("127.0.0.1", 6367, 2000);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            RPCDateService.Client client = new RPCDateService.Client(protocol);
            try {
                transport.open();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = client.getDate("张三");
            } catch (TException e) {
                e.printStackTrace();
            }
            System.out.println("Thrify client result =: " + result);
            ThriftRpcClientUtils.close(transport);
        }).start();
        ThriftRpcServerUtils.serve(tprocessor, 6367);

    }

}
