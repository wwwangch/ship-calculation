package com.iscas.common.rpc.tools.grpc.stream;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *rpc方法
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.1)",
    comments = "Source: steam.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StreamServiceGrpc {

  private StreamServiceGrpc() {}

  public static final String SERVICE_NAME = "com.iscas.common.rpc.tools.grpc.stream.StreamService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "List",
      requestType = com.iscas.common.rpc.tools.grpc.stream.StreamRequest.class,
      responseType = com.iscas.common.rpc.tools.grpc.stream.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getListMethod() {
    io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getListMethod;
    if ((getListMethod = StreamServiceGrpc.getListMethod) == null) {
      synchronized (StreamServiceGrpc.class) {
        if ((getListMethod = StreamServiceGrpc.getListMethod) == null) {
          StreamServiceGrpc.getListMethod = getListMethod =
              io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "List"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.common.rpc.tools.grpc.stream.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.common.rpc.tools.grpc.stream.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StreamServiceMethodDescriptorSupplier("List"))
              .build();
        }
      }
    }
    return getListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getRecordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Record",
      requestType = com.iscas.common.rpc.tools.grpc.stream.StreamRequest.class,
      responseType = com.iscas.common.rpc.tools.grpc.stream.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getRecordMethod() {
    io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getRecordMethod;
    if ((getRecordMethod = StreamServiceGrpc.getRecordMethod) == null) {
      synchronized (StreamServiceGrpc.class) {
        if ((getRecordMethod = StreamServiceGrpc.getRecordMethod) == null) {
          StreamServiceGrpc.getRecordMethod = getRecordMethod =
              io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Record"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.common.rpc.tools.grpc.stream.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.common.rpc.tools.grpc.stream.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StreamServiceMethodDescriptorSupplier("Record"))
              .build();
        }
      }
    }
    return getRecordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getRouteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Route",
      requestType = com.iscas.common.rpc.tools.grpc.stream.StreamRequest.class,
      responseType = com.iscas.common.rpc.tools.grpc.stream.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
      com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getRouteMethod() {
    io.grpc.MethodDescriptor<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse> getRouteMethod;
    if ((getRouteMethod = StreamServiceGrpc.getRouteMethod) == null) {
      synchronized (StreamServiceGrpc.class) {
        if ((getRouteMethod = StreamServiceGrpc.getRouteMethod) == null) {
          StreamServiceGrpc.getRouteMethod = getRouteMethod =
              io.grpc.MethodDescriptor.<com.iscas.common.rpc.tools.grpc.stream.StreamRequest, com.iscas.common.rpc.tools.grpc.stream.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Route"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.common.rpc.tools.grpc.stream.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.common.rpc.tools.grpc.stream.StreamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StreamServiceMethodDescriptorSupplier("Route"))
              .build();
        }
      }
    }
    return getRouteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamServiceStub>() {
        @java.lang.Override
        public StreamServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamServiceStub(channel, callOptions);
        }
      };
    return StreamServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamServiceBlockingStub>() {
        @java.lang.Override
        public StreamServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamServiceBlockingStub(channel, callOptions);
        }
      };
    return StreamServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamServiceFutureStub>() {
        @java.lang.Override
        public StreamServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamServiceFutureStub(channel, callOptions);
        }
      };
    return StreamServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void list(com.iscas.common.rpc.tools.grpc.stream.StreamRequest request,
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> record(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getRecordMethod(), responseObserver);
    }

    /**
     */
    default io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> route(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getRouteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StreamService.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static abstract class StreamServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StreamServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StreamService.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class StreamServiceStub
      extends io.grpc.stub.AbstractAsyncStub<StreamServiceStub> {
    private StreamServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamServiceStub(channel, callOptions);
    }

    /**
     */
    public void list(com.iscas.common.rpc.tools.grpc.stream.StreamRequest request,
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> record(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getRecordMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamRequest> route(
        io.grpc.stub.StreamObserver<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getRouteMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StreamService.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class StreamServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StreamServiceBlockingStub> {
    private StreamServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.iscas.common.rpc.tools.grpc.stream.StreamResponse> list(
        com.iscas.common.rpc.tools.grpc.stream.StreamRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StreamService.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class StreamServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<StreamServiceFutureStub> {
    private StreamServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
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
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getListMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
              com.iscas.common.rpc.tools.grpc.stream.StreamResponse>(
                service, METHODID_LIST)))
        .addMethod(
          getRecordMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
              com.iscas.common.rpc.tools.grpc.stream.StreamResponse>(
                service, METHODID_RECORD)))
        .addMethod(
          getRouteMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.iscas.common.rpc.tools.grpc.stream.StreamRequest,
              com.iscas.common.rpc.tools.grpc.stream.StreamResponse>(
                service, METHODID_ROUTE)))
        .build();
  }

  private static abstract class StreamServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StreamServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.iscas.common.rpc.tools.grpc.stream.StreamProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StreamService");
    }
  }

  private static final class StreamServiceFileDescriptorSupplier
      extends StreamServiceBaseDescriptorSupplier {
    StreamServiceFileDescriptorSupplier() {}
  }

  private static final class StreamServiceMethodDescriptorSupplier
      extends StreamServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StreamServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
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
              .setSchemaDescriptor(new StreamServiceFileDescriptorSupplier())
              .addMethod(getListMethod())
              .addMethod(getRecordMethod())
              .addMethod(getRouteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
