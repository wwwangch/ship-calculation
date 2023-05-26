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

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.WeightRequest,
      com.iscas.biz.calculation.grpc.WeightResponse> getCalWeightDistributeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calWeightDistribute",
      requestType = com.iscas.biz.calculation.grpc.WeightRequest.class,
      responseType = com.iscas.biz.calculation.grpc.WeightResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.WeightRequest,
      com.iscas.biz.calculation.grpc.WeightResponse> getCalWeightDistributeMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.WeightRequest, com.iscas.biz.calculation.grpc.WeightResponse> getCalWeightDistributeMethod;
    if ((getCalWeightDistributeMethod = CalculationGrpc.getCalWeightDistributeMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalWeightDistributeMethod = CalculationGrpc.getCalWeightDistributeMethod) == null) {
          CalculationGrpc.getCalWeightDistributeMethod = getCalWeightDistributeMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.WeightRequest, com.iscas.biz.calculation.grpc.WeightResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calWeightDistribute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.WeightRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.WeightResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calWeightDistribute"))
              .build();
        }
      }
    }
    return getCalWeightDistributeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.StaticLoadRequest,
      com.iscas.biz.calculation.grpc.StaticLoadResponse> getCalStaticLoadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calStaticLoad",
      requestType = com.iscas.biz.calculation.grpc.StaticLoadRequest.class,
      responseType = com.iscas.biz.calculation.grpc.StaticLoadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.StaticLoadRequest,
      com.iscas.biz.calculation.grpc.StaticLoadResponse> getCalStaticLoadMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.StaticLoadRequest, com.iscas.biz.calculation.grpc.StaticLoadResponse> getCalStaticLoadMethod;
    if ((getCalStaticLoadMethod = CalculationGrpc.getCalStaticLoadMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalStaticLoadMethod = CalculationGrpc.getCalStaticLoadMethod) == null) {
          CalculationGrpc.getCalStaticLoadMethod = getCalStaticLoadMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.StaticLoadRequest, com.iscas.biz.calculation.grpc.StaticLoadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calStaticLoad"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.StaticLoadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.StaticLoadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calStaticLoad"))
              .build();
        }
      }
    }
    return getCalStaticLoadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.WaveLoadRequest,
      com.iscas.biz.calculation.grpc.WaveLoadResponse> getCalWaveLoadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calWaveLoad",
      requestType = com.iscas.biz.calculation.grpc.WaveLoadRequest.class,
      responseType = com.iscas.biz.calculation.grpc.WaveLoadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.WaveLoadRequest,
      com.iscas.biz.calculation.grpc.WaveLoadResponse> getCalWaveLoadMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.WaveLoadRequest, com.iscas.biz.calculation.grpc.WaveLoadResponse> getCalWaveLoadMethod;
    if ((getCalWaveLoadMethod = CalculationGrpc.getCalWaveLoadMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalWaveLoadMethod = CalculationGrpc.getCalWaveLoadMethod) == null) {
          CalculationGrpc.getCalWaveLoadMethod = getCalWaveLoadMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.WaveLoadRequest, com.iscas.biz.calculation.grpc.WaveLoadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calWaveLoad"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.WaveLoadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.WaveLoadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calWaveLoad"))
              .build();
        }
      }
    }
    return getCalWaveLoadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.SlamLoadRequest,
      com.iscas.biz.calculation.grpc.SlamLoadResponse> getCalSlamLoadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calSlamLoad",
      requestType = com.iscas.biz.calculation.grpc.SlamLoadRequest.class,
      responseType = com.iscas.biz.calculation.grpc.SlamLoadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.SlamLoadRequest,
      com.iscas.biz.calculation.grpc.SlamLoadResponse> getCalSlamLoadMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.SlamLoadRequest, com.iscas.biz.calculation.grpc.SlamLoadResponse> getCalSlamLoadMethod;
    if ((getCalSlamLoadMethod = CalculationGrpc.getCalSlamLoadMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalSlamLoadMethod = CalculationGrpc.getCalSlamLoadMethod) == null) {
          CalculationGrpc.getCalSlamLoadMethod = getCalSlamLoadMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.SlamLoadRequest, com.iscas.biz.calculation.grpc.SlamLoadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calSlamLoad"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.SlamLoadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.SlamLoadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calSlamLoad"))
              .build();
        }
      }
    }
    return getCalSlamLoadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.SectionRequest,
      com.iscas.biz.calculation.grpc.SectionResponse> getCalSectionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calSection",
      requestType = com.iscas.biz.calculation.grpc.SectionRequest.class,
      responseType = com.iscas.biz.calculation.grpc.SectionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.SectionRequest,
      com.iscas.biz.calculation.grpc.SectionResponse> getCalSectionMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.SectionRequest, com.iscas.biz.calculation.grpc.SectionResponse> getCalSectionMethod;
    if ((getCalSectionMethod = CalculationGrpc.getCalSectionMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalSectionMethod = CalculationGrpc.getCalSectionMethod) == null) {
          CalculationGrpc.getCalSectionMethod = getCalSectionMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.SectionRequest, com.iscas.biz.calculation.grpc.SectionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calSection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.SectionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.SectionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calSection"))
              .build();
        }
      }
    }
    return getCalSectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.GirderStrengthRequest,
      com.iscas.biz.calculation.grpc.GirderStrengthResponse> getCalGirderStrengthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calGirderStrength",
      requestType = com.iscas.biz.calculation.grpc.GirderStrengthRequest.class,
      responseType = com.iscas.biz.calculation.grpc.GirderStrengthResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.GirderStrengthRequest,
      com.iscas.biz.calculation.grpc.GirderStrengthResponse> getCalGirderStrengthMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.GirderStrengthRequest, com.iscas.biz.calculation.grpc.GirderStrengthResponse> getCalGirderStrengthMethod;
    if ((getCalGirderStrengthMethod = CalculationGrpc.getCalGirderStrengthMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalGirderStrengthMethod = CalculationGrpc.getCalGirderStrengthMethod) == null) {
          CalculationGrpc.getCalGirderStrengthMethod = getCalGirderStrengthMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.GirderStrengthRequest, com.iscas.biz.calculation.grpc.GirderStrengthResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calGirderStrength"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.GirderStrengthRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.GirderStrengthResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calGirderStrength"))
              .build();
        }
      }
    }
    return getCalGirderStrengthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.DistRequest,
      com.iscas.biz.calculation.grpc.DistResponse> getCalDistMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "calDist",
      requestType = com.iscas.biz.calculation.grpc.DistRequest.class,
      responseType = com.iscas.biz.calculation.grpc.DistResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.DistRequest,
      com.iscas.biz.calculation.grpc.DistResponse> getCalDistMethod() {
    io.grpc.MethodDescriptor<com.iscas.biz.calculation.grpc.DistRequest, com.iscas.biz.calculation.grpc.DistResponse> getCalDistMethod;
    if ((getCalDistMethod = CalculationGrpc.getCalDistMethod) == null) {
      synchronized (CalculationGrpc.class) {
        if ((getCalDistMethod = CalculationGrpc.getCalDistMethod) == null) {
          CalculationGrpc.getCalDistMethod = getCalDistMethod =
              io.grpc.MethodDescriptor.<com.iscas.biz.calculation.grpc.DistRequest, com.iscas.biz.calculation.grpc.DistResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "calDist"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.DistRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.iscas.biz.calculation.grpc.DistResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CalculationMethodDescriptorSupplier("calDist"))
              .build();
        }
      }
    }
    return getCalDistMethod;
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

    /**
     * <pre>
     *计算重力分布
     * </pre>
     */
    default void calWeightDistribute(com.iscas.biz.calculation.grpc.WeightRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.WeightResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalWeightDistributeMethod(), responseObserver);
    }

    /**
     * <pre>
     *计算静水载荷
     * </pre>
     */
    default void calStaticLoad(com.iscas.biz.calculation.grpc.StaticLoadRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.StaticLoadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalStaticLoadMethod(), responseObserver);
    }

    /**
     * <pre>
     *波浪载荷计算
     * </pre>
     */
    default void calWaveLoad(com.iscas.biz.calculation.grpc.WaveLoadRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.WaveLoadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalWaveLoadMethod(), responseObserver);
    }

    /**
     * <pre>
     *计算砰击载荷
     * </pre>
     */
    default void calSlamLoad(com.iscas.biz.calculation.grpc.SlamLoadRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.SlamLoadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalSlamLoadMethod(), responseObserver);
    }

    /**
     * <pre>
     *剖面计算
     * </pre>
     */
    default void calSection(com.iscas.biz.calculation.grpc.SectionRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.SectionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalSectionMethod(), responseObserver);
    }

    /**
     * <pre>
     *板件弯矩应力计算  对应总纵强度校核
     * </pre>
     */
    default void calGirderStrength(com.iscas.biz.calculation.grpc.GirderStrengthRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.GirderStrengthResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalGirderStrengthMethod(), responseObserver);
    }

    /**
     * <pre>
     *应力分布 对应极限弯矩校验
     * </pre>
     */
    default void calDist(com.iscas.biz.calculation.grpc.DistRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.DistResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCalDistMethod(), responseObserver);
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

    /**
     * <pre>
     *计算重力分布
     * </pre>
     */
    public void calWeightDistribute(com.iscas.biz.calculation.grpc.WeightRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.WeightResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalWeightDistributeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *计算静水载荷
     * </pre>
     */
    public void calStaticLoad(com.iscas.biz.calculation.grpc.StaticLoadRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.StaticLoadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalStaticLoadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *波浪载荷计算
     * </pre>
     */
    public void calWaveLoad(com.iscas.biz.calculation.grpc.WaveLoadRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.WaveLoadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalWaveLoadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *计算砰击载荷
     * </pre>
     */
    public void calSlamLoad(com.iscas.biz.calculation.grpc.SlamLoadRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.SlamLoadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalSlamLoadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *剖面计算
     * </pre>
     */
    public void calSection(com.iscas.biz.calculation.grpc.SectionRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.SectionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalSectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *板件弯矩应力计算  对应总纵强度校核
     * </pre>
     */
    public void calGirderStrength(com.iscas.biz.calculation.grpc.GirderStrengthRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.GirderStrengthResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalGirderStrengthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *应力分布 对应极限弯矩校验
     * </pre>
     */
    public void calDist(com.iscas.biz.calculation.grpc.DistRequest request,
        io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.DistResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCalDistMethod(), getCallOptions()), request, responseObserver);
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

    /**
     * <pre>
     *计算重力分布
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.WeightResponse calWeightDistribute(com.iscas.biz.calculation.grpc.WeightRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalWeightDistributeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *计算静水载荷
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.StaticLoadResponse calStaticLoad(com.iscas.biz.calculation.grpc.StaticLoadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalStaticLoadMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *波浪载荷计算
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.WaveLoadResponse calWaveLoad(com.iscas.biz.calculation.grpc.WaveLoadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalWaveLoadMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *计算砰击载荷
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.SlamLoadResponse calSlamLoad(com.iscas.biz.calculation.grpc.SlamLoadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalSlamLoadMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *剖面计算
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.SectionResponse calSection(com.iscas.biz.calculation.grpc.SectionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalSectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *板件弯矩应力计算  对应总纵强度校核
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.GirderStrengthResponse calGirderStrength(com.iscas.biz.calculation.grpc.GirderStrengthRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalGirderStrengthMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *应力分布 对应极限弯矩校验
     * </pre>
     */
    public com.iscas.biz.calculation.grpc.DistResponse calDist(com.iscas.biz.calculation.grpc.DistRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCalDistMethod(), getCallOptions(), request);
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

    /**
     * <pre>
     *计算重力分布
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.WeightResponse> calWeightDistribute(
        com.iscas.biz.calculation.grpc.WeightRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalWeightDistributeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *计算静水载荷
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.StaticLoadResponse> calStaticLoad(
        com.iscas.biz.calculation.grpc.StaticLoadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalStaticLoadMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *波浪载荷计算
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.WaveLoadResponse> calWaveLoad(
        com.iscas.biz.calculation.grpc.WaveLoadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalWaveLoadMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *计算砰击载荷
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.SlamLoadResponse> calSlamLoad(
        com.iscas.biz.calculation.grpc.SlamLoadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalSlamLoadMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *剖面计算
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.SectionResponse> calSection(
        com.iscas.biz.calculation.grpc.SectionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalSectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *板件弯矩应力计算  对应总纵强度校核
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.GirderStrengthResponse> calGirderStrength(
        com.iscas.biz.calculation.grpc.GirderStrengthRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalGirderStrengthMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *应力分布 对应极限弯矩校验
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.iscas.biz.calculation.grpc.DistResponse> calDist(
        com.iscas.biz.calculation.grpc.DistRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCalDistMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SHIP_PARAM = 0;
  private static final int METHODID_BUOYANCY = 1;
  private static final int METHODID_CAL_WEIGHT_DISTRIBUTE = 2;
  private static final int METHODID_CAL_STATIC_LOAD = 3;
  private static final int METHODID_CAL_WAVE_LOAD = 4;
  private static final int METHODID_CAL_SLAM_LOAD = 5;
  private static final int METHODID_CAL_SECTION = 6;
  private static final int METHODID_CAL_GIRDER_STRENGTH = 7;
  private static final int METHODID_CAL_DIST = 8;

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
        case METHODID_CAL_WEIGHT_DISTRIBUTE:
          serviceImpl.calWeightDistribute((com.iscas.biz.calculation.grpc.WeightRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.WeightResponse>) responseObserver);
          break;
        case METHODID_CAL_STATIC_LOAD:
          serviceImpl.calStaticLoad((com.iscas.biz.calculation.grpc.StaticLoadRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.StaticLoadResponse>) responseObserver);
          break;
        case METHODID_CAL_WAVE_LOAD:
          serviceImpl.calWaveLoad((com.iscas.biz.calculation.grpc.WaveLoadRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.WaveLoadResponse>) responseObserver);
          break;
        case METHODID_CAL_SLAM_LOAD:
          serviceImpl.calSlamLoad((com.iscas.biz.calculation.grpc.SlamLoadRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.SlamLoadResponse>) responseObserver);
          break;
        case METHODID_CAL_SECTION:
          serviceImpl.calSection((com.iscas.biz.calculation.grpc.SectionRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.SectionResponse>) responseObserver);
          break;
        case METHODID_CAL_GIRDER_STRENGTH:
          serviceImpl.calGirderStrength((com.iscas.biz.calculation.grpc.GirderStrengthRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.GirderStrengthResponse>) responseObserver);
          break;
        case METHODID_CAL_DIST:
          serviceImpl.calDist((com.iscas.biz.calculation.grpc.DistRequest) request,
              (io.grpc.stub.StreamObserver<com.iscas.biz.calculation.grpc.DistResponse>) responseObserver);
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
        .addMethod(
          getCalWeightDistributeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.WeightRequest,
              com.iscas.biz.calculation.grpc.WeightResponse>(
                service, METHODID_CAL_WEIGHT_DISTRIBUTE)))
        .addMethod(
          getCalStaticLoadMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.StaticLoadRequest,
              com.iscas.biz.calculation.grpc.StaticLoadResponse>(
                service, METHODID_CAL_STATIC_LOAD)))
        .addMethod(
          getCalWaveLoadMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.WaveLoadRequest,
              com.iscas.biz.calculation.grpc.WaveLoadResponse>(
                service, METHODID_CAL_WAVE_LOAD)))
        .addMethod(
          getCalSlamLoadMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.SlamLoadRequest,
              com.iscas.biz.calculation.grpc.SlamLoadResponse>(
                service, METHODID_CAL_SLAM_LOAD)))
        .addMethod(
          getCalSectionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.SectionRequest,
              com.iscas.biz.calculation.grpc.SectionResponse>(
                service, METHODID_CAL_SECTION)))
        .addMethod(
          getCalGirderStrengthMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.GirderStrengthRequest,
              com.iscas.biz.calculation.grpc.GirderStrengthResponse>(
                service, METHODID_CAL_GIRDER_STRENGTH)))
        .addMethod(
          getCalDistMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.iscas.biz.calculation.grpc.DistRequest,
              com.iscas.biz.calculation.grpc.DistResponse>(
                service, METHODID_CAL_DIST)))
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
              .addMethod(getCalWeightDistributeMethod())
              .addMethod(getCalStaticLoadMethod())
              .addMethod(getCalWaveLoadMethod())
              .addMethod(getCalSlamLoadMethod())
              .addMethod(getCalSectionMethod())
              .addMethod(getCalGirderStrengthMethod())
              .addMethod(getCalDistMethod())
              .build();
        }
      }
    }
    return result;
  }
}
