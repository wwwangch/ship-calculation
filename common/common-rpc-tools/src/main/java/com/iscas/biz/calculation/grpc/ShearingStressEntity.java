// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.ShearingStressEntity}
 */
public  final class ShearingStressEntity extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.ShearingStressEntity)
    ShearingStressEntityOrBuilder {
  // Use ShearingStressEntity.newBuilder() to construct.
  private ShearingStressEntity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ShearingStressEntity() {
    zhongchuiMax_ = 0D;
    zhonggongMax_ = 0D;
    shearingStress_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ShearingStressEntity(
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

            zhongchuiMax_ = input.readDouble();
            break;
          }
          case 17: {

            zhonggongMax_ = input.readDouble();
            break;
          }
          case 25: {

            shearingStress_ = input.readDouble();
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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressEntity_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressEntity_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.ShearingStressEntity.class, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder.class);
  }

  public static final int ZHONGCHUIMAX_FIELD_NUMBER = 1;
  private double zhongchuiMax_;
  /**
   * <pre>
   *中垂剖面最大剪力值τ  中拱剖面最大剪力值τ  许用剪应力[τ]
   *中垂剖面最大剪力值τ
   * </pre>
   *
   * <code>double zhongchuiMax = 1;</code>
   */
  public double getZhongchuiMax() {
    return zhongchuiMax_;
  }

  public static final int ZHONGGONGMAX_FIELD_NUMBER = 2;
  private double zhonggongMax_;
  /**
   * <pre>
   *中拱剖面最大剪力值τ
   * </pre>
   *
   * <code>double zhonggongMax = 2;</code>
   */
  public double getZhonggongMax() {
    return zhonggongMax_;
  }

  public static final int SHEARINGSTRESS_FIELD_NUMBER = 3;
  private double shearingStress_;
  /**
   * <pre>
   *许用剪应力[τ]
   * </pre>
   *
   * <code>double shearingStress = 3;</code>
   */
  public double getShearingStress() {
    return shearingStress_;
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
    if (zhongchuiMax_ != 0D) {
      output.writeDouble(1, zhongchuiMax_);
    }
    if (zhonggongMax_ != 0D) {
      output.writeDouble(2, zhonggongMax_);
    }
    if (shearingStress_ != 0D) {
      output.writeDouble(3, shearingStress_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (zhongchuiMax_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, zhongchuiMax_);
    }
    if (zhonggongMax_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, zhonggongMax_);
    }
    if (shearingStress_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, shearingStress_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.ShearingStressEntity)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.ShearingStressEntity other = (com.iscas.biz.calculation.grpc.ShearingStressEntity) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getZhongchuiMax())
        == java.lang.Double.doubleToLongBits(
            other.getZhongchuiMax()));
    result = result && (
        java.lang.Double.doubleToLongBits(getZhonggongMax())
        == java.lang.Double.doubleToLongBits(
            other.getZhonggongMax()));
    result = result && (
        java.lang.Double.doubleToLongBits(getShearingStress())
        == java.lang.Double.doubleToLongBits(
            other.getShearingStress()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ZHONGCHUIMAX_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZhongchuiMax()));
    hash = (37 * hash) + ZHONGGONGMAX_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZhonggongMax()));
    hash = (37 * hash) + SHEARINGSTRESS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getShearingStress()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressEntity parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.ShearingStressEntity prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.ShearingStressEntity}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.ShearingStressEntity)
      com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressEntity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressEntity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.ShearingStressEntity.class, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.ShearingStressEntity.newBuilder()
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
      zhongchuiMax_ = 0D;

      zhonggongMax_ = 0D;

      shearingStress_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressEntity_descriptor;
    }

    public com.iscas.biz.calculation.grpc.ShearingStressEntity getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.ShearingStressEntity.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.ShearingStressEntity build() {
      com.iscas.biz.calculation.grpc.ShearingStressEntity result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.ShearingStressEntity buildPartial() {
      com.iscas.biz.calculation.grpc.ShearingStressEntity result = new com.iscas.biz.calculation.grpc.ShearingStressEntity(this);
      result.zhongchuiMax_ = zhongchuiMax_;
      result.zhonggongMax_ = zhonggongMax_;
      result.shearingStress_ = shearingStress_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.ShearingStressEntity) {
        return mergeFrom((com.iscas.biz.calculation.grpc.ShearingStressEntity)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.ShearingStressEntity other) {
      if (other == com.iscas.biz.calculation.grpc.ShearingStressEntity.getDefaultInstance()) return this;
      if (other.getZhongchuiMax() != 0D) {
        setZhongchuiMax(other.getZhongchuiMax());
      }
      if (other.getZhonggongMax() != 0D) {
        setZhonggongMax(other.getZhonggongMax());
      }
      if (other.getShearingStress() != 0D) {
        setShearingStress(other.getShearingStress());
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
      com.iscas.biz.calculation.grpc.ShearingStressEntity parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.ShearingStressEntity) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double zhongchuiMax_ ;
    /**
     * <pre>
     *中垂剖面最大剪力值τ  中拱剖面最大剪力值τ  许用剪应力[τ]
     *中垂剖面最大剪力值τ
     * </pre>
     *
     * <code>double zhongchuiMax = 1;</code>
     */
    public double getZhongchuiMax() {
      return zhongchuiMax_;
    }
    /**
     * <pre>
     *中垂剖面最大剪力值τ  中拱剖面最大剪力值τ  许用剪应力[τ]
     *中垂剖面最大剪力值τ
     * </pre>
     *
     * <code>double zhongchuiMax = 1;</code>
     */
    public Builder setZhongchuiMax(double value) {
      
      zhongchuiMax_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中垂剖面最大剪力值τ  中拱剖面最大剪力值τ  许用剪应力[τ]
     *中垂剖面最大剪力值τ
     * </pre>
     *
     * <code>double zhongchuiMax = 1;</code>
     */
    public Builder clearZhongchuiMax() {
      
      zhongchuiMax_ = 0D;
      onChanged();
      return this;
    }

    private double zhonggongMax_ ;
    /**
     * <pre>
     *中拱剖面最大剪力值τ
     * </pre>
     *
     * <code>double zhonggongMax = 2;</code>
     */
    public double getZhonggongMax() {
      return zhonggongMax_;
    }
    /**
     * <pre>
     *中拱剖面最大剪力值τ
     * </pre>
     *
     * <code>double zhonggongMax = 2;</code>
     */
    public Builder setZhonggongMax(double value) {
      
      zhonggongMax_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中拱剖面最大剪力值τ
     * </pre>
     *
     * <code>double zhonggongMax = 2;</code>
     */
    public Builder clearZhonggongMax() {
      
      zhonggongMax_ = 0D;
      onChanged();
      return this;
    }

    private double shearingStress_ ;
    /**
     * <pre>
     *许用剪应力[τ]
     * </pre>
     *
     * <code>double shearingStress = 3;</code>
     */
    public double getShearingStress() {
      return shearingStress_;
    }
    /**
     * <pre>
     *许用剪应力[τ]
     * </pre>
     *
     * <code>double shearingStress = 3;</code>
     */
    public Builder setShearingStress(double value) {
      
      shearingStress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *许用剪应力[τ]
     * </pre>
     *
     * <code>double shearingStress = 3;</code>
     */
    public Builder clearShearingStress() {
      
      shearingStress_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.ShearingStressEntity)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.ShearingStressEntity)
  private static final com.iscas.biz.calculation.grpc.ShearingStressEntity DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.ShearingStressEntity();
  }

  public static com.iscas.biz.calculation.grpc.ShearingStressEntity getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ShearingStressEntity>
      PARSER = new com.google.protobuf.AbstractParser<ShearingStressEntity>() {
    public ShearingStressEntity parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ShearingStressEntity(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ShearingStressEntity> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ShearingStressEntity> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.ShearingStressEntity getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

