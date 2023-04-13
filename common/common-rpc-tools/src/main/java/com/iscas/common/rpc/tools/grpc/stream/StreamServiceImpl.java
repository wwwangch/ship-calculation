package com.iscas.common.rpc.tools.grpc.stream;

import io.grpc.stub.StreamObserver;

/**
 * 自定义的服务端实现类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/12 17:40
 * @since jdk1.8
 */
public class StreamServiceImpl extends StreamServiceGrpc.StreamServiceImplBase {
    @Override
    public void list(StreamRequest request, StreamObserver<StreamResponse> responseObserver) {
        for (int i = 0; i < 30 ; i++) {
            StreamPoint streamPoint = StreamPoint.newBuilder().setName("xxx").setValue(i).build();
            StreamResponse streamRequest = StreamResponse.newBuilder().setPt(streamPoint).build();
            responseObserver.onNext(streamRequest);
        }
        responseObserver.onCompleted();
//        super.list(request, responseObserver);
    }

    @Override
    public StreamObserver<StreamRequest> record(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getPt().getValue());
//                if (value.getPt().getValue() == 20) {
//                    responseObserver.onCompleted();
//                }
//                else {
//                    StreamPoint streamPoint = StreamPoint.newBuilder().setName("yyy").setValue(value.getPt().getValue()).build();
//                    StreamResponse streamResponse = StreamResponse.newBuilder().setPt(streamPoint).build();
//                    responseObserver.onNext(streamResponse);
//                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
//                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> route(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                if (value.getPt().getValue() == 20) {
                    responseObserver.onCompleted();
                } else {
                    StreamPoint streamPoint = StreamPoint.newBuilder().setName("yyy").setValue(value.getPt().getValue()).build();
                    StreamResponse streamResponse = StreamResponse.newBuilder().setPt(streamPoint).build();
                    responseObserver.onNext(streamResponse);
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
