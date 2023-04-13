package com.iscas.common.rpc.tools.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * grpc 客户端工具类
 *
 * @author zhuquanwen
 *  @version 1.0
 * @date 2020/11/20 18:47
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Slf4j
public class GrpcClientUtils {
    private GrpcClientUtils(){}

    /**
     * 获取ManagedChannel
     *
     * 使用例子:
     * PersonServiceGrpc.PersonServiceBlockingStub blockingStub = PersonServiceGrpc
     *                 .newBlockingStub(managedChannel);
     * MyResponse myResponse = blockingStub
     *                 .getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());
     *
     * @since jdk1.8
     * @date 2020/11/20
     * @param ip 服务端IP
     * @param port 端口
     * @return io.grpc.ManagedChannel
     */
    public static ManagedChannel getManagedChannel(String ip, int port) {
        return ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build();
    }

    public static void shutdown(ManagedChannel channel) throws InterruptedException {
        // 调用shutdown方法后等待1秒关闭channel
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
        log.info("gRPC client shut down successfully.");
    }

}

