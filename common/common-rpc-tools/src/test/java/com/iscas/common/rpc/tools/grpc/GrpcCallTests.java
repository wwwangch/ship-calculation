package com.iscas.common.rpc.tools.grpc;

import com.iscas.common.rpc.tools.grpc.client.GrpcClientUtils;
import com.iscas.common.rpc.tools.grpc.server.GrpcServerUtils;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 19:19
 * @since jdk1.8
 */
public class GrpcCallTests {

    /**
     * 测试使用grpc发布服务，并调用
     * */
    @Test
    public void testCall() throws IOException, InterruptedException {
        PersonServiceImpl psi = new PersonServiceImpl();
        Server server = GrpcServerUtils.start(psi, 8888);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //客户端调用
            ManagedChannel managedChannel = GrpcClientUtils.getManagedChannel("127.0.0.1", 8888);
            PersonServiceGrpc.PersonServiceBlockingStub psbs = PersonServiceGrpc.newBlockingStub(managedChannel);
            MyRequest req = MyRequest.newBuilder().setUsername("张三").build();
            MyResponse res = psbs.getRealNameByUsername(req);
            System.out.println(res.getRealname());
            Assert.assertEquals("真实姓名:张三", res.getRealname());
            server.shutdownNow();
        }).start();
        server.awaitTermination();
    }
}
