package com.iscas.biz.calculation.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *rpc方法
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.1)",
    comments = "Source: calculation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CalculationGrpc {

  private CalculationGrpc() {}

  public static final String SERVICE_NAME = "com.iscas.biz.calculation.grpc.Calculation";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.ShipParamRequest,
      com.iscas.biz.calculation.grpc.ShipParamResponse> getShipParamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "shipParam",
      requestType = com.iscas.biz.calculation.grpc.ShipParamRequest.class,
      responseType = com.iscas.biz.calculation.grpc.ShipParamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.ShipParamRequest,
      com.iscas.biz.calculation.grpc.ShipParamResponse> getShipParamMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.ShipParamRequest, com.iscas.biz.calculation.grpc.ShipParamResponse> getShipParamMethod;
    if ((getShipParamMethod = CalculationGrpc.getShipParamMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getShipParamMethod = CalculationGrpc.getShipParamMethod) == null) {
          CalculationGrpc.getShipParamMethod = getShipParamMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.ShipParamRequest, com.iscas.biz.calculation.grpc.ShipParamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "shipParam"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.ShipParamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.ShipParamResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("shipParam"))
              .build();
        }
      }
    }
    return getShipParamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.BuoyancyRequest,
      com.iscas.biz.calculation.grpc.BuoyancyResponse> getBuoyancyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "buoyancy",
      requestType = com.iscas.biz.calculation.grpc.BuoyancyRequest.class,
      responseType = com.iscas.biz.calculation.grpc.BuoyancyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.BuoyancyRequest,
      com.iscas.biz.calculation.grpc.BuoyancyResponse> getBuoyancyMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.BuoyancyRequest, com.iscas.biz.calculation.grpc.BuoyancyResponse> getBuoyancyMethod;
    if ((getBuoyancyMethod = CalculationGrpc.getBuoyancyMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getBuoyancyMethod = CalculationGrpc.getBuoyancyMethod) == null) {
          CalculationGrpc.getBuoyancyMethod = getBuoyancyMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.BuoyancyRequest, com.iscas.biz.calculation.grpc.BuoyancyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "buoyancy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.BuoyancyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.BuoyancyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("buoyancy"))
              .build();
        }
      }
    }
    return getBuoyancyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CalculationStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculationStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculationStub>() {
        @java.lang.Override
        public CalculationStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculationStub(channel, callOptions);
        }
      };
    return CalculationStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CalculationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculationBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculationBlockingStub>() {
        @java.lang.Override
        public CalculationBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculationBlockingStub(channel, callOptions);
        }
      };
    return CalculationBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CalculationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CalculationFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CalculationFutureStub>() {
        @java.lang.Override
        public CalculationFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CalculationFutureStub(channel, callOptions);
        }
      };
    return CalculationFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *rpc方法
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     *船舶参数配置
     * </pre>
     */
    default void shipParam(com.iscas.biz.calculation.grpc.ShipParamRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.ShipParamResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getShipParamMethod(), responseObserver);
    }

    /**
     * <pre>
     *浮力计算
     * </pre>
     */
    default void buoyancy(com.iscas.biz.calculation.grpc.BuoyancyRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.BuoyancyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBuoyancyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Calculation.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static abstract class CalculationImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CalculationGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Calculation.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class CalculationStub
      extends io.grpc.stub.AbstractAsyncStub<CalculationStub> {
    private CalculationStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculationStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculationStub(channel, callOptions);
    }

    /**
     * <pre>
     *船舶参数配置
     * </pre>
     */
    public void shipParam(com.iscas.biz.calculation.grpc.ShipParamRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.ShipParamResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getShipParamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *浮力计算
     * </pre>
     */
    public void buoyancy(com.iscas.biz.calculation.grpc.BuoyancyRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.BuoyancyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBuoyancyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Calculation.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class CalculationBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CalculationBlockingStub> {
    private CalculationBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculationBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculationBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *船舶参数配置
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.ShipParamResponse shipParam(com.iscas.biz.calculation.grpc.ShipParamRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getShipParamMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *浮力计算
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.BuoyancyResponse buoyancy(com.iscas.biz.calculation.grpc.BuoyancyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBuoyancyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Calculation.
   * <pre>
   *rpc方法
   * </pre>
   */
  public static final class CalculationFutureStub
      extends io.grpc.stub.AbstractFutureStub<CalculationFutureStub> {
    private CalculationFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CalculationFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CalculationFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *船舶参数配置
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.ShipParamResponse> shipParam(
        com.iscas.biz.calculation.grpc.ShipParamRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getShipParamMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *浮力计算
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.BuoyancyResponse> buoyancy(
        com.iscas.biz.calculation.grpc.BuoyancyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBuoyancyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SHIP_PARAM = 0;
  private static final int METHODID_BUOYANCY = 1;

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
        case METHODID_SHIP_PARAM:
          serviceImpl.shipParam((com.iscas.biz.calculation.grpc.ShipParamRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.ShipParamResponse>) responseObserver);
          break;
        case METHODID_BUOYANCY:
          serviceImpl.buoyancy((com.iscas.biz.calculation.grpc.BuoyancyRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.BuoyancyResponse>) responseObserver);
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

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getShipParamMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.ShipParamRequest,
              com.iscas.biz.calculation.grpc.ShipParamResponse>(
                service, METHODID_SHIP_PARAM)))
        .addMethod(
          getBuoyancyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.BuoyancyRequest,
              com.iscas.biz.calculation.grpc.BuoyancyResponse>(
                service, METHODID_BUOYANCY)))
        .build();
  }

  private static abstract class CalculationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CalculationBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Calculation");
    }
  }

  private static final class CalculationFileDescriptorSupplier
      extends CalculationBaseDescriptorSupplier {
    CalculationFileDescriptorSupplier() {}
  }

  private static final class CalculationMethodDescriptorSupplier
      extends CalculationBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CalculationMethodDescriptorSupplier(String methodName) {
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
      synchronized (CalculationGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CalculationFileDescriptorSupplier())
              .addMethod(getShipParamMethod())
              .addMethod(getBuoyancyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
