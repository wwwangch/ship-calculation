package com.iscas.common.rpc.tools.grpc.server;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * grpc 服务端工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 17:56
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Slf4j
public class GrpcServerUtils {
    private GrpcServerUtils() {
    }

    /**
     * 服务发布
     *
     * @param entity 实体
     * @param port   发布的端口
     * @return void
     * @throws IOException          IO异常
     * @throws InterruptedException 线程打断异常
     * @date 2020/11/20
     * @since jdk1.8
     */
    public static Server start(BindableService entity, int port) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port).addService(entity).build().start();
        String msg = String.format("grpc服务:[%s]已发布", entity.getClass().getName());
        log.info(msg);
        if (!log.isInfoEnabled()) {
            System.out.println(msg);
        }
        return server;
    }

    /**
     * 服务发布
     *
     * @param entity                实体
     * @param port                  发布的端口
     * @param serverTransportFilter 过滤器
     * @return void
     * @throws IOException          IO异常
     * @throws InterruptedException 线程打断异常
     * @date 2020/11/20
     * @since jdk1.8
     */
    public static Server start(BindableService entity, int port, ServerTransportFilter serverTransportFilter) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port).addService(entity).addTransportFilter(serverTransportFilter).build().start();
        String msg = String.format("grpc服务:[%s]已发布", entity.getClass().getName());
        log.info(msg);
        if (!log.isInfoEnabled()) {
            System.out.println(msg);
        }
        return server;
    }

    /**
     * 服务发布
     *
     * @param entity            实体
     * @param port              发布的端口
     * @param serverInterceptor 拦截器
     * @return void
     * @throws IOException          IO异常
     * @throws InterruptedException 线程打断异常
     * @date 2020/11/20
     * @since jdk1.8
     */
    public static Server start(BindableService entity, int port, ServerInterceptor serverInterceptor) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port).addService(entity).intercept(serverInterceptor).build().start();
        String msg = String.format("grpc服务:[%s]已发布", entity.getClass().getName());
        log.info(msg);
        if (!log.isInfoEnabled()) {
            System.out.println(msg);
        }
        return server;
    }

    /**
     * 务启动之后就会立刻停止,awaitTermination会保持服务一直运行
     *
     * @param server 服务
     * @throws InterruptedException 线程打断异常
     * @date 2020/11/20
     * @since jdk1.8
     */
    @SuppressWarnings("unused")
    public static void awaitTermination(Server server) throws InterruptedException {
        server.awaitTermination();
    }

    /**
     * shutdown
     *
     * @param server 服务
     * @date 2020/11/20
     * @since jdk1.8
     */
    public static void shutdown(Server server) {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * 立即shutdown
     *
     * @param server 服务
     * @date 2020/11/20
     * @since jdk1.8
     */
    public static void shutdownNow(Server server) {
        server.shutdownNow();
    }
}
