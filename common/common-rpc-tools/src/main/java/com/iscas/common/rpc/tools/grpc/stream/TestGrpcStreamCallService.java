package com.iscas.common.rpc.tools.grpc.stream;


import com.iscas.common.rpc.tools.grpc.server.GrpcServerUtils;
import io.grpc.Server;

import java.io.IOException;

/**
 * 测试GRPC流式调用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/12 17:39
 * @since jdk1.8
 */

public class TestGrpcStreamCallService {
    public static void main(String[] args) throws IOException, InterruptedException {
        testCall();
    }
    /**
     * 测试使用grpc发布服务，并调用
     * */
    public static void testCall() throws IOException, InterruptedException {
        StreamServiceImpl psi = new StreamServiceImpl();
        Server server = GrpcServerUtils.start(psi, 8888);
        server.awaitTermination();
    }

}
