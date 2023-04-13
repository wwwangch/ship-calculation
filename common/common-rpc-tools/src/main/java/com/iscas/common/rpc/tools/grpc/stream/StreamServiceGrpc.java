package com.iscas.common.rpc.tools.grpc.stream;

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
    comments = "Source: steam.proto")
public final class StreamServiceGrpc {

  private StreamServiceGrpc() {}

  public static final String SERVICE_NAME = "com.iscas.common.rpc.tools.grpc.stream.StreamService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> METHOD_LIST =
      io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "com.iscas.common.rpc.tools.grpc.stream.StreamService", "List"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.stream.StreamRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.stream.StreamResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> METHOD_RECORD =
      io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "com.iscas.common.rpc.tools.grpc.stream.StreamService", "Record"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.stream.StreamRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.stream.StreamResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> METHOD_ROUTE =
      io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "com.iscas.common.rpc.tools.grpc.stream.StreamService", "Route"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.stream.StreamRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.iscas.common.rpc.tools.grpc.stream.StreamResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamServiceStub newStub(io.grpc.Channel channel) {
    return new StreamServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StreamServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StreamServiceFutureStub(channel);
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static abstract class StreamServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void list(com.iscas.common.rpc.tools.grpc.stream.StreamRequest request,
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LIST, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> record(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_RECORD, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> route(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_ROUTE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LIST,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
                com.iscas.common.rpc.tools.grpc.stream.StreamResponse>(
                  this, METHODID_LIST)))
          .addMethod(
            METHOD_RECORD,
            asyncClientStreamingCall(
              new MethodHandlers<
                com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
                com.iscas.common.rpc.tools.grpc.stream.StreamResponse>(
                  this, METHODID_RECORD)))
          .addMethod(
            METHOD_ROUTE,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
                com.iscas.common.rpc.tools.grpc.stream.StreamResponse>(
                  this, METHODID_ROUTE)))
          .build();
    }
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class StreamServiceStub extends io.grpc.stub.AbstractStub<StreamServiceStub> {
    private StreamServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StreamServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StreamServiceStub(channel, callOptions);
    }

    /**
     */
    public void list(com.iscas.common.rpc.tools.grpc.stream.StreamRequest request,
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_LIST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> record(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(METHOD_RECORD, getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> route(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_ROUTE, getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class StreamServiceBlockingStub extends io.grpc.stub.AbstractStub<StreamServiceBlockingStub> {
    private StreamServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StreamServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StreamServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> list(
        com.iscas.common.rpc.tools.grpc.stream.StreamRequest request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_LIST, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class StreamServiceFutureStub extends io.grpc.stub.AbstractStub<StreamServiceFutureStub> {
    private StreamServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StreamServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StreamServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LIST = 0;
  private static final int METHODID_RECORD = 1;
  private static final int METHODID_ROUTE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StreamServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StreamServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST:
          serviceImpl.list((com.iscas.common.rpc.tools.grpc.stream.StreamRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse>) responseObserver);
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
        case METHODID_RECORD:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.record(
              (io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse>) responseObserver);
        case METHODID_ROUTE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.route(
              (io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class StreamServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.iscas.common.rpc.tools.grpc.stream.StreamProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StreamServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StreamServiceDescriptorSupplier())
              .addMethod(METHOD_LIST)
              .addMethod(METHOD_RECORD)
              .addMethod(METHOD_ROUTE)
              .build();
        }
      }
    }
    return result;
  }
}
