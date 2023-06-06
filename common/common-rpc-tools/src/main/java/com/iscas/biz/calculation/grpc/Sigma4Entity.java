// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma4Entity}
 */
public  final class Sigma4Entity extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.Sigma4Entity)
    Sigma4EntityOrBuilder {
  // Use Sigma4Entity.newBuilder() to construct.
  private Sigma4Entity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Sigma4Entity() {
    zhonggongZhizuo_ = 0D;
    zhonggongKuazhong_ = 0D;
    zhongchuiZhizuo_ = 0D;
    zhongchuiKuazhong_ = 0D;
    combineAllowStress_ = 0D;
    combineZhonggongZhizuo_ = 0D;
    combineZhonggongKuazhong_ = 0D;
    combineZhongchuiZhizuo_ = 0D;
    combineZhongchuiKuazhong_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Sigma4Entity(
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
          case 9: {

            zhonggongZhizuo_ = input.readDouble();
            break;
          }
          case 17: {

            zhonggongKuazhong_ = input.readDouble();
            break;
          }
          case 25: {

            zhongchuiZhizuo_ = input.readDouble();
            break;
          }
          case 33: {

            zhongchuiKuazhong_ = input.readDouble();
            break;
          }
          case 41: {

            combineAllowStress_ = input.readDouble();
            break;
          }
          case 49: {

            combineZhonggongZhizuo_ = input.readDouble();
            break;
          }
          case 57: {

            combineZhonggongKuazhong_ = input.readDouble();
            break;
          }
          case 65: {

            combineZhongchuiZhizuo_ = input.readDouble();
            break;
          }
          case 73: {

            combineZhongchuiKuazhong_ = input.readDouble();
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma4Entity_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma4Entity_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.Sigma4Entity.class, com.iscas.biz.calculation.grpc.Sigma4Entity.Builder.class);
  }

  public static final int ZHONGGONGZHIZUO_FIELD_NUMBER = 1;
  private double zhonggongZhizuo_;
  /**
   * <pre>
   *中拱支座
   * </pre>
   *
   * <code>double zhonggongZhizuo = 1;</code>
   */
  public double getZhonggongZhizuo() {
    return zhonggongZhizuo_;
  }

  public static final int ZHONGGONGKUAZHONG_FIELD_NUMBER = 2;
  private double zhonggongKuazhong_;
  /**
   * <pre>
   *中拱跨中
   * </pre>
   *
   * <code>double zhonggongKuazhong = 2;</code>
   */
  public double getZhonggongKuazhong() {
    return zhonggongKuazhong_;
  }

  public static final int ZHONGCHUIZHIZUO_FIELD_NUMBER = 3;
  private double zhongchuiZhizuo_;
  /**
   * <pre>
   *中垂支座
   * </pre>
   *
   * <code>double zhongchuiZhizuo = 3;</code>
   */
  public double getZhongchuiZhizuo() {
    return zhongchuiZhizuo_;
  }

  public static final int ZHONGCHUIKUAZHONG_FIELD_NUMBER = 4;
  private double zhongchuiKuazhong_;
  /**
   * <pre>
   *中垂跨中
   * </pre>
   *
   * <code>double zhongchuiKuazhong = 4;</code>
   */
  public double getZhongchuiKuazhong() {
    return zhongchuiKuazhong_;
  }

  public static final int COMBINEALLOWSTRESS_FIELD_NUMBER = 5;
  private double combineAllowStress_;
  /**
   * <pre>
   *合成许用应力
   * </pre>
   *
   * <code>double combineAllowStress = 5;</code>
   */
  public double getCombineAllowStress() {
    return combineAllowStress_;
  }

  public static final int COMBINEZHONGGONGZHIZUO_FIELD_NUMBER = 6;
  private double combineZhonggongZhizuo_;
  /**
   * <pre>
   *合成中拱支座
   * </pre>
   *
   * <code>double combineZhonggongZhizuo = 6;</code>
   */
  public double getCombineZhonggongZhizuo() {
    return combineZhonggongZhizuo_;
  }

  public static final int COMBINEZHONGGONGKUAZHONG_FIELD_NUMBER = 7;
  private double combineZhonggongKuazhong_;
  /**
   * <pre>
   *合成中拱跨中
   * </pre>
   *
   * <code>double combineZhonggongKuazhong = 7;</code>
   */
  public double getCombineZhonggongKuazhong() {
    return combineZhonggongKuazhong_;
  }

  public static final int COMBINEZHONGCHUIZHIZUO_FIELD_NUMBER = 8;
  private double combineZhongchuiZhizuo_;
  /**
   * <pre>
   *合成中垂支座
   * </pre>
   *
   * <code>double combineZhongchuiZhizuo = 8;</code>
   */
  public double getCombineZhongchuiZhizuo() {
    return combineZhongchuiZhizuo_;
  }

  public static final int COMBINEZHONGCHUIKUAZHONG_FIELD_NUMBER = 9;
  private double combineZhongchuiKuazhong_;
  /**
   * <pre>
   *合成中垂跨中
   * </pre>
   *
   * <code>double combineZhongchuiKuazhong = 9;</code>
   */
  public double getCombineZhongchuiKuazhong() {
    return combineZhongchuiKuazhong_;
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
    if (zhonggongZhizuo_ != 0D) {
      output.writeDouble(1, zhonggongZhizuo_);
    }
    if (zhonggongKuazhong_ != 0D) {
      output.writeDouble(2, zhonggongKuazhong_);
    }
    if (zhongchuiZhizuo_ != 0D) {
      output.writeDouble(3, zhongchuiZhizuo_);
    }
    if (zhongchuiKuazhong_ != 0D) {
      output.writeDouble(4, zhongchuiKuazhong_);
    }
    if (combineAllowStress_ != 0D) {
      output.writeDouble(5, combineAllowStress_);
    }
    if (combineZhonggongZhizuo_ != 0D) {
      output.writeDouble(6, combineZhonggongZhizuo_);
    }
    if (combineZhonggongKuazhong_ != 0D) {
      output.writeDouble(7, combineZhonggongKuazhong_);
    }
    if (combineZhongchuiZhizuo_ != 0D) {
      output.writeDouble(8, combineZhongchuiZhizuo_);
    }
    if (combineZhongchuiKuazhong_ != 0D) {
      output.writeDouble(9, combineZhongchuiKuazhong_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (zhonggongZhizuo_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, zhonggongZhizuo_);
    }
    if (zhonggongKuazhong_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, zhonggongKuazhong_);
    }
    if (zhongchuiZhizuo_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, zhongchuiZhizuo_);
    }
    if (zhongchuiKuazhong_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, zhongchuiKuazhong_);
    }
    if (combineAllowStress_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, combineAllowStress_);
    }
    if (combineZhonggongZhizuo_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(6, combineZhonggongZhizuo_);
    }
    if (combineZhonggongKuazhong_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(7, combineZhonggongKuazhong_);
    }
    if (combineZhongchuiZhizuo_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(8, combineZhongchuiZhizuo_);
    }
    if (combineZhongchuiKuazhong_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(9, combineZhongchuiKuazhong_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.Sigma4Entity)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.Sigma4Entity other = (com.iscas.biz.calculation.grpc.Sigma4Entity) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getZhonggongZhizuo())
        == java.lang.Double.doubleToLongBits(
            other.getZhonggongZhizuo()));
    result = result && (
        java.lang.Double.doubleToLongBits(getZhonggongKuazhong())
        == java.lang.Double.doubleToLongBits(
            other.getZhonggongKuazhong()));
    result = result && (
        java.lang.Double.doubleToLongBits(getZhongchuiZhizuo())
        == java.lang.Double.doubleToLongBits(
            other.getZhongchuiZhizuo()));
    result = result && (
        java.lang.Double.doubleToLongBits(getZhongchuiKuazhong())
        == java.lang.Double.doubleToLongBits(
            other.getZhongchuiKuazhong()));
    result = result && (
        java.lang.Double.doubleToLongBits(getCombineAllowStress())
        == java.lang.Double.doubleToLongBits(
            other.getCombineAllowStress()));
    result = result && (
        java.lang.Double.doubleToLongBits(getCombineZhonggongZhizuo())
        == java.lang.Double.doubleToLongBits(
            other.getCombineZhonggongZhizuo()));
    result = result && (
        java.lang.Double.doubleToLongBits(getCombineZhonggongKuazhong())
        == java.lang.Double.doubleToLongBits(
            other.getCombineZhonggongKuazhong()));
    result = result && (
        java.lang.Double.doubleToLongBits(getCombineZhongchuiZhizuo())
        == java.lang.Double.doubleToLongBits(
            other.getCombineZhongchuiZhizuo()));
    result = result && (
        java.lang.Double.doubleToLongBits(getCombineZhongchuiKuazhong())
        == java.lang.Double.doubleToLongBits(
            other.getCombineZhongchuiKuazhong()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ZHONGGONGZHIZUO_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZhonggongZhizuo()));
    hash = (37 * hash) + ZHONGGONGKUAZHONG_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZhonggongKuazhong()));
    hash = (37 * hash) + ZHONGCHUIZHIZUO_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZhongchuiZhizuo()));
    hash = (37 * hash) + ZHONGCHUIKUAZHONG_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZhongchuiKuazhong()));
    hash = (37 * hash) + COMBINEALLOWSTRESS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCombineAllowStress()));
    hash = (37 * hash) + COMBINEZHONGGONGZHIZUO_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCombineZhonggongZhizuo()));
    hash = (37 * hash) + COMBINEZHONGGONGKUAZHONG_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCombineZhonggongKuazhong()));
    hash = (37 * hash) + COMBINEZHONGCHUIZHIZUO_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCombineZhongchuiZhizuo()));
    hash = (37 * hash) + COMBINEZHONGCHUIKUAZHONG_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCombineZhongchuiKuazhong()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma4Entity parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.Sigma4Entity prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma4Entity}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.Sigma4Entity)
      com.iscas.biz.calculation.grpc.Sigma4EntityOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma4Entity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma4Entity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.Sigma4Entity.class, com.iscas.biz.calculation.grpc.Sigma4Entity.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.Sigma4Entity.newBuilder()
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
      zhonggongZhizuo_ = 0D;

      zhonggongKuazhong_ = 0D;

      zhongchuiZhizuo_ = 0D;

      zhongchuiKuazhong_ = 0D;

      combineAllowStress_ = 0D;

      combineZhonggongZhizuo_ = 0D;

      combineZhonggongKuazhong_ = 0D;

      combineZhongchuiZhizuo_ = 0D;

      combineZhongchuiKuazhong_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma4Entity_descriptor;
    }

    public com.iscas.biz.calculation.grpc.Sigma4Entity getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.Sigma4Entity.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.Sigma4Entity build() {
      com.iscas.biz.calculation.grpc.Sigma4Entity result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.Sigma4Entity buildPartial() {
      com.iscas.biz.calculation.grpc.Sigma4Entity result = new com.iscas.biz.calculation.grpc.Sigma4Entity(this);
      result.zhonggongZhizuo_ = zhonggongZhizuo_;
      result.zhonggongKuazhong_ = zhonggongKuazhong_;
      result.zhongchuiZhizuo_ = zhongchuiZhizuo_;
      result.zhongchuiKuazhong_ = zhongchuiKuazhong_;
      result.combineAllowStress_ = combineAllowStress_;
      result.combineZhonggongZhizuo_ = combineZhonggongZhizuo_;
      result.combineZhonggongKuazhong_ = combineZhonggongKuazhong_;
      result.combineZhongchuiZhizuo_ = combineZhongchuiZhizuo_;
      result.combineZhongchuiKuazhong_ = combineZhongchuiKuazhong_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.Sigma4Entity) {
        return mergeFrom((com.iscas.biz.calculation.grpc.Sigma4Entity)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.Sigma4Entity other) {
      if (other == com.iscas.biz.calculation.grpc.Sigma4Entity.getDefaultInstance()) return this;
      if (other.getZhonggongZhizuo() != 0D) {
        setZhonggongZhizuo(other.getZhonggongZhizuo());
      }
      if (other.getZhonggongKuazhong() != 0D) {
        setZhonggongKuazhong(other.getZhonggongKuazhong());
      }
      if (other.getZhongchuiZhizuo() != 0D) {
        setZhongchuiZhizuo(other.getZhongchuiZhizuo());
      }
      if (other.getZhongchuiKuazhong() != 0D) {
        setZhongchuiKuazhong(other.getZhongchuiKuazhong());
      }
      if (other.getCombineAllowStress() != 0D) {
        setCombineAllowStress(other.getCombineAllowStress());
      }
      if (other.getCombineZhonggongZhizuo() != 0D) {
        setCombineZhonggongZhizuo(other.getCombineZhonggongZhizuo());
      }
      if (other.getCombineZhonggongKuazhong() != 0D) {
        setCombineZhonggongKuazhong(other.getCombineZhonggongKuazhong());
      }
      if (other.getCombineZhongchuiZhizuo() != 0D) {
        setCombineZhongchuiZhizuo(other.getCombineZhongchuiZhizuo());
      }
      if (other.getCombineZhongchuiKuazhong() != 0D) {
        setCombineZhongchuiKuazhong(other.getCombineZhongchuiKuazhong());
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
      com.iscas.biz.calculation.grpc.Sigma4Entity parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.Sigma4Entity) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double zhonggongZhizuo_ ;
    /**
     * <pre>
     *中拱支座
     * </pre>
     *
     * <code>double zhonggongZhizuo = 1;</code>
     */
    public double getZhonggongZhizuo() {
      return zhonggongZhizuo_;
    }
    /**
     * <pre>
     *中拱支座
     * </pre>
     *
     * <code>double zhonggongZhizuo = 1;</code>
     */
    public Builder setZhonggongZhizuo(double value) {
      
      zhonggongZhizuo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中拱支座
     * </pre>
     *
     * <code>double zhonggongZhizuo = 1;</code>
     */
    public Builder clearZhonggongZhizuo() {
      
      zhonggongZhizuo_ = 0D;
      onChanged();
      return this;
    }

    private double zhonggongKuazhong_ ;
    /**
     * <pre>
     *中拱跨中
     * </pre>
     *
     * <code>double zhonggongKuazhong = 2;</code>
     */
    public double getZhonggongKuazhong() {
      return zhonggongKuazhong_;
    }
    /**
     * <pre>
     *中拱跨中
     * </pre>
     *
     * <code>double zhonggongKuazhong = 2;</code>
     */
    public Builder setZhonggongKuazhong(double value) {
      
      zhonggongKuazhong_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中拱跨中
     * </pre>
     *
     * <code>double zhonggongKuazhong = 2;</code>
     */
    public Builder clearZhonggongKuazhong() {
      
      zhonggongKuazhong_ = 0D;
      onChanged();
      return this;
    }

    private double zhongchuiZhizuo_ ;
    /**
     * <pre>
     *中垂支座
     * </pre>
     *
     * <code>double zhongchuiZhizuo = 3;</code>
     */
    public double getZhongchuiZhizuo() {
      return zhongchuiZhizuo_;
    }
    /**
     * <pre>
     *中垂支座
     * </pre>
     *
     * <code>double zhongchuiZhizuo = 3;</code>
     */
    public Builder setZhongchuiZhizuo(double value) {
      
      zhongchuiZhizuo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中垂支座
     * </pre>
     *
     * <code>double zhongchuiZhizuo = 3;</code>
     */
    public Builder clearZhongchuiZhizuo() {
      
      zhongchuiZhizuo_ = 0D;
      onChanged();
      return this;
    }

    private double zhongchuiKuazhong_ ;
    /**
     * <pre>
     *中垂跨中
     * </pre>
     *
     * <code>double zhongchuiKuazhong = 4;</code>
     */
    public double getZhongchuiKuazhong() {
      return zhongchuiKuazhong_;
    }
    /**
     * <pre>
     *中垂跨中
     * </pre>
     *
     * <code>double zhongchuiKuazhong = 4;</code>
     */
    public Builder setZhongchuiKuazhong(double value) {
      
      zhongchuiKuazhong_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中垂跨中
     * </pre>
     *
     * <code>double zhongchuiKuazhong = 4;</code>
     */
    public Builder clearZhongchuiKuazhong() {
      
      zhongchuiKuazhong_ = 0D;
      onChanged();
      return this;
    }

    private double combineAllowStress_ ;
    /**
     * <pre>
     *合成许用应力
     * </pre>
     *
     * <code>double combineAllowStress = 5;</code>
     */
    public double getCombineAllowStress() {
      return combineAllowStress_;
    }
    /**
     * <pre>
     *合成许用应力
     * </pre>
     *
     * <code>double combineAllowStress = 5;</code>
     */
    public Builder setCombineAllowStress(double value) {
      
      combineAllowStress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *合成许用应力
     * </pre>
     *
     * <code>double combineAllowStress = 5;</code>
     */
    public Builder clearCombineAllowStress() {
      
      combineAllowStress_ = 0D;
      onChanged();
      return this;
    }

    private double combineZhonggongZhizuo_ ;
    /**
     * <pre>
     *合成中拱支座
     * </pre>
     *
     * <code>double combineZhonggongZhizuo = 6;</code>
     */
    public double getCombineZhonggongZhizuo() {
      return combineZhonggongZhizuo_;
    }
    /**
     * <pre>
     *合成中拱支座
     * </pre>
     *
     * <code>double combineZhonggongZhizuo = 6;</code>
     */
    public Builder setCombineZhonggongZhizuo(double value) {
      
      combineZhonggongZhizuo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *合成中拱支座
     * </pre>
     *
     * <code>double combineZhonggongZhizuo = 6;</code>
     */
    public Builder clearCombineZhonggongZhizuo() {
      
      combineZhonggongZhizuo_ = 0D;
      onChanged();
      return this;
    }

    private double combineZhonggongKuazhong_ ;
    /**
     * <pre>
     *合成中拱跨中
     * </pre>
     *
     * <code>double combineZhonggongKuazhong = 7;</code>
     */
    public double getCombineZhonggongKuazhong() {
      return combineZhonggongKuazhong_;
    }
    /**
     * <pre>
     *合成中拱跨中
     * </pre>
     *
     * <code>double combineZhonggongKuazhong = 7;</code>
     */
    public Builder setCombineZhonggongKuazhong(double value) {
      
      combineZhonggongKuazhong_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *合成中拱跨中
     * </pre>
     *
     * <code>double combineZhonggongKuazhong = 7;</code>
     */
    public Builder clearCombineZhonggongKuazhong() {
      
      combineZhonggongKuazhong_ = 0D;
      onChanged();
      return this;
    }

    private double combineZhongchuiZhizuo_ ;
    /**
     * <pre>
     *合成中垂支座
     * </pre>
     *
     * <code>double combineZhongchuiZhizuo = 8;</code>
     */
    public double getCombineZhongchuiZhizuo() {
      return combineZhongchuiZhizuo_;
    }
    /**
     * <pre>
     *合成中垂支座
     * </pre>
     *
     * <code>double combineZhongchuiZhizuo = 8;</code>
     */
    public Builder setCombineZhongchuiZhizuo(double value) {
      
      combineZhongchuiZhizuo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *合成中垂支座
     * </pre>
     *
     * <code>double combineZhongchuiZhizuo = 8;</code>
     */
    public Builder clearCombineZhongchuiZhizuo() {
      
      combineZhongchuiZhizuo_ = 0D;
      onChanged();
      return this;
    }

    private double combineZhongchuiKuazhong_ ;
    /**
     * <pre>
     *合成中垂跨中
     * </pre>
     *
     * <code>double combineZhongchuiKuazhong = 9;</code>
     */
    public double getCombineZhongchuiKuazhong() {
      return combineZhongchuiKuazhong_;
    }
    /**
     * <pre>
     *合成中垂跨中
     * </pre>
     *
     * <code>double combineZhongchuiKuazhong = 9;</code>
     */
    public Builder setCombineZhongchuiKuazhong(double value) {
      
      combineZhongchuiKuazhong_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *合成中垂跨中
     * </pre>
     *
     * <code>double combineZhongchuiKuazhong = 9;</code>
     */
    public Builder clearCombineZhongchuiKuazhong() {
      
      combineZhongchuiKuazhong_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.Sigma4Entity)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.Sigma4Entity)
  private static final com.iscas.biz.calculation.grpc.Sigma4Entity DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.Sigma4Entity();
  }

  public static com.iscas.biz.calculation.grpc.Sigma4Entity getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Sigma4Entity>
      PARSER = new com.google.protobuf.AbstractParser<Sigma4Entity>() {
    public Sigma4Entity parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Sigma4Entity(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Sigma4Entity> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Sigma4Entity> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.Sigma4Entity getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

