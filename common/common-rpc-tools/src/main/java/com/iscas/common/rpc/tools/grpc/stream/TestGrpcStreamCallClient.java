package com.iscas.common.rpc.tools.grpc.stream;


import com.iscas.common.rpc.tools.grpc.client.GrpcClientUtils;
import com.iscas.common.rpc.tools.grpc.server.GrpcServerUtils;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 测试GRPC流式调用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/12 17:39
 * @since jdk1.8
 */

public class TestGrpcStreamCallClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        testCall2();
    }


    /**
     * 测试使用grpc发布服务，服务端流式
     * */
    public static void testCall1() throws IOException, InterruptedException {
        Thread thread = Thread.currentThread();
        //客户端调用
        ManagedChannel managedChannel = GrpcClientUtils.getManagedChannel("127.0.0.1", 8888);
        StreamServiceGrpc.StreamServiceStub streamServiceStub = StreamServiceGrpc.newStub(managedChannel);
        StreamPoint streamPoint = StreamPoint.newBuilder().setName("xxx").setValue(1).build();
        StreamRequest streamRequest = StreamRequest.newBuilder().setPt(streamPoint).build();
        streamServiceStub.list(streamRequest, new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getPt().getValue());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("complete");
                thread.interrupt();
            }
        });
//        TimeUnit.SECONDS.sleep(10000);
        Thread.currentThread().join();

    }


    /**
     * 测试使用grpc发布服务，客户端流式
     * */
    public static void testCall2() throws IOException, InterruptedException {
        Thread thread = Thread.currentThread();
        //客户端调用
        ManagedChannel managedChannel = GrpcClientUtils.getManagedChannel("127.0.0.1", 8888);
        StreamServiceGrpc.StreamServiceStub streamServiceStub = StreamServiceGrpc.newStub(managedChannel);
        StreamPoint streamPoint = StreamPoint.newBuilder().setName("xxx").setValue(1).build();
        StreamRequest streamRequest = StreamRequest.newBuilder().setPt(streamPoint).build();
        StreamObserver<StreamRequest> streamObserver = streamServiceStub.record(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getPt().getValue());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("complete");
                thread.interrupt();
            }
        });

        streamObserver.onNext(streamRequest);

        for (int i = 0; i < 30 ; i++) {
            StreamPoint streamPoint2 = StreamPoint.newBuilder().setName("xxx").setValue(i).build();
            StreamRequest streamRequest2 = StreamRequest.newBuilder().setPt(streamPoint2).build();
            streamObserver.onNext(streamRequest2);
        }
        streamObserver.onCompleted();
//        TimeUnit.SECONDS.sleep(10000);
        Thread.currentThread().join();

    }


    /**
     * 测试使用grpc发布服务，客户端流式
     * */
    public static void testCall3() throws IOException, InterruptedException {
        Thread thread = Thread.currentThread();
        //客户端调用
        ManagedChannel managedChannel = GrpcClientUtils.getManagedChannel("127.0.0.1", 8888);
        StreamServiceGrpc.StreamServiceStub streamServiceStub = StreamServiceGrpc.newStub(managedChannel);
        StreamPoint streamPoint = StreamPoint.newBuilder().setName("xxx").setValue(1).build();
        StreamRequest streamRequest = StreamRequest.newBuilder().setPt(streamPoint).build();
        StreamObserver<StreamRequest> streamObserver = streamServiceStub.route(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getPt().getValue());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("complete");
                thread.interrupt();
            }
        });

        streamObserver.onNext(streamRequest);

        for (int i = 0; i < 30 ; i++) {
            StreamPoint streamPoint2 = StreamPoint.newBuilder().setName("xxx").setValue(i).build();
            StreamRequest streamRequest2 = StreamRequest.newBuilder().setPt(streamPoint2).build();
            streamObserver.onNext(streamRequest2);
        }
//        TimeUnit.SECONDS.sleep(10000);
        Thread.currentThread().join();

    }

}
