package com.iscas.common.rpc.tools.grpc;

import io.grpc.stub.StreamObserver;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 18:03
 * @since jdk1.8
 */
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {
    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        MyResponse myResponse = MyResponse.newBuilder().setRealname("真实姓名:" + request.getUsername()).build();
        responseObserver.onNext(myResponse);
        responseObserver.onCompleted();
    }
}
