package com.iscas.common.rpc.tools.grpc.test;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 *rpc方法
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: Person.proto")
public final class PersonServiceGrpc {

  private PersonServiceGrpc() {}

  public static final String SERVICE_NAME = "com.iscas.common.rpc.tools.grpc.test.PersonService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.test.MyRequest,
      com.iscas.common.rpc.tools.grpc.test.MyResponse> METHOD_GET_REAL_NAME_BY_USERNAME =
      io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.test.MyRequest, com.iscas.common.rpc.tools.grpc.test.MyResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "com.iscas.common.rpc.tools.grpc.test.PersonService", "GetRealNameByUsername"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.test.MyRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.test.MyResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PersonServiceStub newStub(io.grpc.Channel channel) {
    return new PersonServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PersonServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PersonServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PersonServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PersonServiceFutureStub(channel);
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static abstract class PersonServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getRealNameByUsername(com.iscas.common.rpc.tools.grpc.test.MyRequest request,
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.test.MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_REAL_NAME_BY_USERNAME, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_REAL_NAME_BY_USERNAME,
            asyncUnaryCall(
              new MethodHandlers<
                com.iscas.common.rpc.tools.grpc.test.MyRequest,
                com.iscas.common.rpc.tools.grpc.test.MyResponse>(
                  this, METHODID_GET_REAL_NAME_BY_USERNAME)))
          .build();
    }
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class PersonServiceStub extends io.grpc.stub.AbstractStub<PersonServiceStub> {
    private PersonServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PersonServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PersonServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PersonServiceStub(channel, callOptions);
    }

    /**
     */
    public void getRealNameByUsername(com.iscas.common.rpc.tools.grpc.test.MyRequest request,
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.test.MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_REAL_NAME_BY_USERNAME, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class PersonServiceBlockingStub extends io.grpc.stub.AbstractStub<PersonServiceBlockingStub> {
    private PersonServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PersonServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PersonServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PersonServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.iscas.common.rpc.tools.grpc.test.MyResponse getRealNameByUsername(com.iscas.common.rpc.tools.grpc.test.MyRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_REAL_NAME_BY_USERNAME, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class PersonServiceFutureStub extends io.grpc.stub.AbstractStub<PersonServiceFutureStub> {
    private PersonServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PersonServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PersonServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PersonServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.common.rpc.tools.grpc.test.MyResponse> getRealNameByUsername(
        com.iscas.common.rpc.tools.grpc.test.MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_REAL_NAME_BY_USERNAME, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_USERNAME = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PersonServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PersonServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_USERNAME:
          serviceImpl.getRealNameByUsername((com.iscas.common.rpc.tools.grpc.test.MyRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.test.MyResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class PersonServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.iscas.common.rpc.tools.grpc.test.PersonProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PersonServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PersonServiceDescriptorSupplier())
              .addMethod(METHOD_GET_REAL_NAME_BY_USERNAME)
              .build();
        }
      }
    }
    return result;
  }
}
