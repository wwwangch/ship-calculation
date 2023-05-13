// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * <pre>
 *浮力计算参数值
 * </pre>
 *
 * Protobuf type {@code com.iscas.biz.calculation.grpc.BuoyancyRequest}
 */
public  final class BuoyancyRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.BuoyancyRequest)
    BuoyancyRequestOrBuilder {
  // Use BuoyancyRequest.newBuilder() to construct.
  private BuoyancyRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuoyancyRequest() {
    brojeanFilePath_ = "";
    buoyancycurveFilePath_ = "";
    precision_ = java.util.Collections.emptyList();
    target_ = 0;
    tm_ = 0D;
    ta_ = 0D;
    tf_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private BuoyancyRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            brojeanFilePath_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            buoyancycurveFilePath_ = s;
            break;
          }
          case 25: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              precision_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000004;
            }
            precision_.add(input.readDouble());
            break;
          }
          case 26: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
              precision_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000004;
            }
            while (input.getBytesUntilLimit() > 0) {
              precision_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 32: {

            target_ = input.readInt32();
            break;
          }
          case 41: {

            tm_ = input.readDouble();
            break;
          }
          case 49: {

            ta_ = input.readDouble();
            break;
          }
          case 57: {

            tf_ = input.readDouble();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        precision_ = java.util.Collections.unmodifiableList(precision_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.BuoyancyRequest.class, com.iscas.biz.calculation.grpc.BuoyancyRequest.Builder.class);
  }

  private int bitField0_;
  public static final int BROJEANFILEPATH_FIELD_NUMBER = 1;
  private volatile java.lang.Object brojeanFilePath_;
  /**
   * <pre>
   *邦戎曲线路径
   * </pre>
   *
   * <code>string brojeanFilePath = 1;</code>
   */
  public java.lang.String getBrojeanFilePath() {
    java.lang.Object ref = brojeanFilePath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      brojeanFilePath_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *邦戎曲线路径
   * </pre>
   *
   * <code>string brojeanFilePath = 1;</code>
   */
  public com.google.protobuf.ByteString
      getBrojeanFilePathBytes() {
    java.lang.Object ref = brojeanFilePath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      brojeanFilePath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BUOYANCYCURVEFILEPATH_FIELD_NUMBER = 2;
  private volatile java.lang.Object buoyancycurveFilePath_;
  /**
   * <pre>
   *静水力曲线文件路径
   * </pre>
   *
   * <code>string buoyancycurveFilePath = 2;</code>
   */
  public java.lang.String getBuoyancycurveFilePath() {
    java.lang.Object ref = buoyancycurveFilePath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      buoyancycurveFilePath_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *静水力曲线文件路径
   * </pre>
   *
   * <code>string buoyancycurveFilePath = 2;</code>
   */
  public com.google.protobuf.ByteString
      getBuoyancycurveFilePathBytes() {
    java.lang.Object ref = buoyancycurveFilePath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      buoyancycurveFilePath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRECISION_FIELD_NUMBER = 3;
  private java.util.List<java.lang.Double> precision_;
  /**
   * <pre>
   *核校精度 两个double值 第一个排水量精度  第二个重心精度
   * </pre>
   *
   * <code>repeated double precision = 3;</code>
   */
  public java.util.List<java.lang.Double>
      getPrecisionList() {
    return precision_;
  }
  /**
   * <pre>
   *核校精度 两个double值 第一个排水量精度  第二个重心精度
   * </pre>
   *
   * <code>repeated double precision = 3;</code>
   */
  public int getPrecisionCount() {
    return precision_.size();
  }
  /**
   * <pre>
   *核校精度 两个double值 第一个排水量精度  第二个重心精度
   * </pre>
   *
   * <code>repeated double precision = 3;</code>
   */
  public double getPrecision(int index) {
    return precision_.get(index);
  }
  private int precisionMemoizedSerializedSize = -1;

  public static final int TARGET_FIELD_NUMBER = 4;
  private int target_;
  /**
   * <pre>
   *校核目标 0-极限 1-巡航
   * </pre>
   *
   * <code>int32 target = 4;</code>
   */
  public int getTarget() {
    return target_;
  }

  public static final int TM_FIELD_NUMBER = 5;
  private double tm_;
  /**
   * <pre>
   *平均吃水
   * </pre>
   *
   * <code>double tm = 5;</code>
   */
  public double getTm() {
    return tm_;
  }

  public static final int TA_FIELD_NUMBER = 6;
  private double ta_;
  /**
   * <pre>
   *尾吃水
   * </pre>
   *
   * <code>double ta = 6;</code>
   */
  public double getTa() {
    return ta_;
  }

  public static final int TF_FIELD_NUMBER = 7;
  private double tf_;
  /**
   * <pre>
   *船首吃水
   * </pre>
   *
   * <code>double tf = 7;</code>
   */
  public double getTf() {
    return tf_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (!getBrojeanFilePathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, brojeanFilePath_);
    }
    if (!getBuoyancycurveFilePathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, buoyancycurveFilePath_);
    }
    if (getPrecisionList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(precisionMemoizedSerializedSize);
    }
    for (int i = 0; i < precision_.size(); i++) {
      output.writeDoubleNoTag(precision_.get(i));
    }
    if (target_ != 0) {
      output.writeInt32(4, target_);
    }
    if (tm_ != 0D) {
      output.writeDouble(5, tm_);
    }
    if (ta_ != 0D) {
      output.writeDouble(6, ta_);
    }
    if (tf_ != 0D) {
      output.writeDouble(7, tf_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getBrojeanFilePathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, brojeanFilePath_);
    }
    if (!getBuoyancycurveFilePathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, buoyancycurveFilePath_);
    }
    {
      int dataSize = 0;
      dataSize = 8 * getPrecisionList().size();
      size += dataSize;
      if (!getPrecisionList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      precisionMemoizedSerializedSize = dataSize;
    }
    if (target_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, target_);
    }
    if (tm_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, tm_);
    }
    if (ta_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(6, ta_);
    }
    if (tf_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(7, tf_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.iscas.biz.calculation.grpc.BuoyancyRequest)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.BuoyancyRequest other = (com.iscas.biz.calculation.grpc.BuoyancyRequest) obj;

    boolean result = true;
    result = result && getBrojeanFilePath()
        .equals(other.getBrojeanFilePath());
    result = result && getBuoyancycurveFilePath()
        .equals(other.getBuoyancycurveFilePath());
    result = result && getPrecisionList()
        .equals(other.getPrecisionList());
    result = result && (getTarget()
        == other.getTarget());
    result = result && (
        java.lang.Double.doubleToLongBits(getTm())
        == java.lang.Double.doubleToLongBits(
            other.getTm()));
    result = result && (
        java.lang.Double.doubleToLongBits(getTa())
        == java.lang.Double.doubleToLongBits(
            other.getTa()));
    result = result && (
        java.lang.Double.doubleToLongBits(getTf())
        == java.lang.Double.doubleToLongBits(
            other.getTf()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + BROJEANFILEPATH_FIELD_NUMBER;
    hash = (53 * hash) + getBrojeanFilePath().hashCode();
    hash = (37 * hash) + BUOYANCYCURVEFILEPATH_FIELD_NUMBER;
    hash = (53 * hash) + getBuoyancycurveFilePath().hashCode();
    if (getPrecisionCount() > 0) {
      hash = (37 * hash) + PRECISION_FIELD_NUMBER;
      hash = (53 * hash) + getPrecisionList().hashCode();
    }
    hash = (37 * hash) + TARGET_FIELD_NUMBER;
    hash = (53 * hash) + getTarget();
    hash = (37 * hash) + TM_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getTm()));
    hash = (37 * hash) + TA_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getTa()));
    hash = (37 * hash) + TF_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getTf()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.BuoyancyRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   *浮力计算参数值
   * </pre>
   *
   * Protobuf type {@code com.iscas.biz.calculation.grpc.BuoyancyRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.BuoyancyRequest)
      com.iscas.biz.calculation.grpc.BuoyancyRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.BuoyancyRequest.class, com.iscas.biz.calculation.grpc.BuoyancyRequest.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.BuoyancyRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      brojeanFilePath_ = "";

      buoyancycurveFilePath_ = "";

      precision_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      target_ = 0;

      tm_ = 0D;

      ta_ = 0D;

      tf_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyRequest_descriptor;
    }

    public com.iscas.biz.calculation.grpc.BuoyancyRequest getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.BuoyancyRequest.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.BuoyancyRequest build() {
      com.iscas.biz.calculation.grpc.BuoyancyRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.BuoyancyRequest buildPartial() {
      com.iscas.biz.calculation.grpc.BuoyancyRequest result = new com.iscas.biz.calculation.grpc.BuoyancyRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.brojeanFilePath_ = brojeanFilePath_;
      result.buoyancycurveFilePath_ = buoyancycurveFilePath_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        precision_ = java.util.Collections.unmodifiableList(precision_);
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.precision_ = precision_;
      result.target_ = target_;
      result.tm_ = tm_;
      result.ta_ = ta_;
      result.tf_ = tf_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.iscas.biz.calculation.grpc.BuoyancyRequest) {
        return mergeFrom((com.iscas.biz.calculation.grpc.BuoyancyRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.BuoyancyRequest other) {
      if (other == com.iscas.biz.calculation.grpc.BuoyancyRequest.getDefaultInstance()) return this;
      if (!other.getBrojeanFilePath().isEmpty()) {
        brojeanFilePath_ = other.brojeanFilePath_;
        onChanged();
      }
      if (!other.getBuoyancycurveFilePath().isEmpty()) {
        buoyancycurveFilePath_ = other.buoyancycurveFilePath_;
        onChanged();
      }
      if (!other.precision_.isEmpty()) {
        if (precision_.isEmpty()) {
          precision_ = other.precision_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensurePrecisionIsMutable();
          precision_.addAll(other.precision_);
        }
        onChanged();
      }
      if (other.getTarget() != 0) {
        setTarget(other.getTarget());
      }
      if (other.getTm() != 0D) {
        setTm(other.getTm());
      }
      if (other.getTa() != 0D) {
        setTa(other.getTa());
      }
      if (other.getTf() != 0D) {
        setTf(other.getTf());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.iscas.biz.calculation.grpc.BuoyancyRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.BuoyancyRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object brojeanFilePath_ = "";
    /**
     * <pre>
     *邦戎曲线路径
     * </pre>
     *
     * <code>string brojeanFilePath = 1;</code>
     */
    public java.lang.String getBrojeanFilePath() {
      java.lang.Object ref = brojeanFilePath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        brojeanFilePath_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *邦戎曲线路径
     * </pre>
     *
     * <code>string brojeanFilePath = 1;</code>
     */
    public com.google.protobuf.ByteString
        getBrojeanFilePathBytes() {
      java.lang.Object ref = brojeanFilePath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        brojeanFilePath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *邦戎曲线路径
     * </pre>
     *
     * <code>string brojeanFilePath = 1;</code>
     */
    public Builder setBrojeanFilePath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      brojeanFilePath_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邦戎曲线路径
     * </pre>
     *
     * <code>string brojeanFilePath = 1;</code>
     */
    public Builder clearBrojeanFilePath() {
      
      brojeanFilePath_ = getDefaultInstance().getBrojeanFilePath();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *邦戎曲线路径
     * </pre>
     *
     * <code>string brojeanFilePath = 1;</code>
     */
    public Builder setBrojeanFilePathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      brojeanFilePath_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object buoyancycurveFilePath_ = "";
    /**
     * <pre>
     *静水力曲线文件路径
     * </pre>
     *
     * <code>string buoyancycurveFilePath = 2;</code>
     */
    public java.lang.String getBuoyancycurveFilePath() {
      java.lang.Object ref = buoyancycurveFilePath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        buoyancycurveFilePath_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *静水力曲线文件路径
     * </pre>
     *
     * <code>string buoyancycurveFilePath = 2;</code>
     */
    public com.google.protobuf.ByteString
        getBuoyancycurveFilePathBytes() {
      java.lang.Object ref = buoyancycurveFilePath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        buoyancycurveFilePath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *静水力曲线文件路径
     * </pre>
     *
     * <code>string buoyancycurveFilePath = 2;</code>
     */
    public Builder setBuoyancycurveFilePath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      buoyancycurveFilePath_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *静水力曲线文件路径
     * </pre>
     *
     * <code>string buoyancycurveFilePath = 2;</code>
     */
    public Builder clearBuoyancycurveFilePath() {
      
      buoyancycurveFilePath_ = getDefaultInstance().getBuoyancycurveFilePath();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *静水力曲线文件路径
     * </pre>
     *
     * <code>string buoyancycurveFilePath = 2;</code>
     */
    public Builder setBuoyancycurveFilePathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      buoyancycurveFilePath_ = value;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> precision_ = java.util.Collections.emptyList();
    private void ensurePrecisionIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        precision_ = new java.util.ArrayList<java.lang.Double>(precision_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public java.util.List<java.lang.Double>
        getPrecisionList() {
      return java.util.Collections.unmodifiableList(precision_);
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public int getPrecisionCount() {
      return precision_.size();
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public double getPrecision(int index) {
      return precision_.get(index);
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public Builder setPrecision(
        int index, double value) {
      ensurePrecisionIsMutable();
      precision_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public Builder addPrecision(double value) {
      ensurePrecisionIsMutable();
      precision_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public Builder addAllPrecision(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensurePrecisionIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, precision_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *核校精度 两个double值 第一个排水量精度  第二个重心精度
     * </pre>
     *
     * <code>repeated double precision = 3;</code>
     */
    public Builder clearPrecision() {
      precision_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }

    private int target_ ;
    /**
     * <pre>
     *校核目标 0-极限 1-巡航
     * </pre>
     *
     * <code>int32 target = 4;</code>
     */
    public int getTarget() {
      return target_;
    }
    /**
     * <pre>
     *校核目标 0-极限 1-巡航
     * </pre>
     *
     * <code>int32 target = 4;</code>
     */
    public Builder setTarget(int value) {
      
      target_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *校核目标 0-极限 1-巡航
     * </pre>
     *
     * <code>int32 target = 4;</code>
     */
    public Builder clearTarget() {
      
      target_ = 0;
      onChanged();
      return this;
    }

    private double tm_ ;
    /**
     * <pre>
     *平均吃水
     * </pre>
     *
     * <code>double tm = 5;</code>
     */
    public double getTm() {
      return tm_;
    }
    /**
     * <pre>
     *平均吃水
     * </pre>
     *
     * <code>double tm = 5;</code>
     */
    public Builder setTm(double value) {
      
      tm_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *平均吃水
     * </pre>
     *
     * <code>double tm = 5;</code>
     */
    public Builder clearTm() {
      
      tm_ = 0D;
      onChanged();
      return this;
    }

    private double ta_ ;
    /**
     * <pre>
     *尾吃水
     * </pre>
     *
     * <code>double ta = 6;</code>
     */
    public double getTa() {
      return ta_;
    }
    /**
     * <pre>
     *尾吃水
     * </pre>
     *
     * <code>double ta = 6;</code>
     */
    public Builder setTa(double value) {
      
      ta_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *尾吃水
     * </pre>
     *
     * <code>double ta = 6;</code>
     */
    public Builder clearTa() {
      
      ta_ = 0D;
      onChanged();
      return this;
    }

    private double tf_ ;
    /**
     * <pre>
     *船首吃水
     * </pre>
     *
     * <code>double tf = 7;</code>
     */
    public double getTf() {
      return tf_;
    }
    /**
     * <pre>
     *船首吃水
     * </pre>
     *
     * <code>double tf = 7;</code>
     */
    public Builder setTf(double value) {
      
      tf_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *船首吃水
     * </pre>
     *
     * <code>double tf = 7;</code>
     */
    public Builder clearTf() {
      
      tf_ = 0D;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.BuoyancyRequest)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.BuoyancyRequest)
  private static final com.iscas.biz.calculation.grpc.BuoyancyRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.BuoyancyRequest();
  }

  public static com.iscas.biz.calculation.grpc.BuoyancyRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BuoyancyRequest>
      PARSER = new com.google.protobuf.AbstractParser<BuoyancyRequest>() {
    public BuoyancyRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuoyancyRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuoyancyRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuoyancyRequest> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.BuoyancyRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

