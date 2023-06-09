// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.SubGravity}
 */
public  final class SubGravity extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.SubGravity)
    SubGravityOrBuilder {
  // Use SubGravity.newBuilder() to construct.
  private SubGravity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SubGravity() {
    name_ = com.google.protobuf.ByteString.EMPTY;
    displacement_ = 0D;
    xg_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SubGravity(
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

            name_ = input.readBytes();
            break;
          }
          case 17: {

            displacement_ = input.readDouble();
            break;
          }
          case 25: {

            xg_ = input.readDouble();
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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SubGravity_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SubGravity_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.SubGravity.class, com.iscas.biz.calculation.grpc.SubGravity.Builder.class);
  }

  public static final int NAME_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString name_;
  /**
   * <pre>
   *专业名-项目名
   * </pre>
   *
   * <code>bytes name = 1;</code>
   */
  public com.google.protobuf.ByteString getName() {
    return name_;
  }

  public static final int DISPLACEMENT_FIELD_NUMBER = 2;
  private double displacement_;
  /**
   * <pre>
   *排水量
   * </pre>
   *
   * <code>double displacement = 2;</code>
   */
  public double getDisplacement() {
    return displacement_;
  }

  public static final int XG_FIELD_NUMBER = 3;
  private double xg_;
  /**
   * <pre>
   *重心纵向坐标
   * </pre>
   *
   * <code>double xg = 3;</code>
   */
  public double getXg() {
    return xg_;
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
    if (!name_.isEmpty()) {
      output.writeBytes(1, name_);
    }
    if (displacement_ != 0D) {
      output.writeDouble(2, displacement_);
    }
    if (xg_ != 0D) {
      output.writeDouble(3, xg_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!name_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, name_);
    }
    if (displacement_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, displacement_);
    }
    if (xg_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, xg_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.SubGravity)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.SubGravity other = (com.iscas.biz.calculation.grpc.SubGravity) obj;

    boolean result = true;
    result = result && getName()
        .equals(other.getName());
    result = result && (
        java.lang.Double.doubleToLongBits(getDisplacement())
        == java.lang.Double.doubleToLongBits(
            other.getDisplacement()));
    result = result && (
        java.lang.Double.doubleToLongBits(getXg())
        == java.lang.Double.doubleToLongBits(
            other.getXg()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + DISPLACEMENT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDisplacement()));
    hash = (37 * hash) + XG_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getXg()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SubGravity parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.SubGravity prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.SubGravity}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.SubGravity)
      com.iscas.biz.calculation.grpc.SubGravityOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SubGravity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SubGravity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.SubGravity.class, com.iscas.biz.calculation.grpc.SubGravity.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.SubGravity.newBuilder()
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
      name_ = com.google.protobuf.ByteString.EMPTY;

      displacement_ = 0D;

      xg_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SubGravity_descriptor;
    }

    public com.iscas.biz.calculation.grpc.SubGravity getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.SubGravity.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.SubGravity build() {
      com.iscas.biz.calculation.grpc.SubGravity result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.SubGravity buildPartial() {
      com.iscas.biz.calculation.grpc.SubGravity result = new com.iscas.biz.calculation.grpc.SubGravity(this);
      result.name_ = name_;
      result.displacement_ = displacement_;
      result.xg_ = xg_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.SubGravity) {
        return mergeFrom((com.iscas.biz.calculation.grpc.SubGravity)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.SubGravity other) {
      if (other == com.iscas.biz.calculation.grpc.SubGravity.getDefaultInstance()) return this;
      if (other.getName() != com.google.protobuf.ByteString.EMPTY) {
        setName(other.getName());
      }
      if (other.getDisplacement() != 0D) {
        setDisplacement(other.getDisplacement());
      }
      if (other.getXg() != 0D) {
        setXg(other.getXg());
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
      com.iscas.biz.calculation.grpc.SubGravity parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.SubGravity) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.ByteString name_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     *专业名-项目名
     * </pre>
     *
     * <code>bytes name = 1;</code>
     */
    public com.google.protobuf.ByteString getName() {
      return name_;
    }
    /**
     * <pre>
     *专业名-项目名
     * </pre>
     *
     * <code>bytes name = 1;</code>
     */
    public Builder setName(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *专业名-项目名
     * </pre>
     *
     * <code>bytes name = 1;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }

    private double displacement_ ;
    /**
     * <pre>
     *排水量
     * </pre>
     *
     * <code>double displacement = 2;</code>
     */
    public double getDisplacement() {
      return displacement_;
    }
    /**
     * <pre>
     *排水量
     * </pre>
     *
     * <code>double displacement = 2;</code>
     */
    public Builder setDisplacement(double value) {
      
      displacement_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *排水量
     * </pre>
     *
     * <code>double displacement = 2;</code>
     */
    public Builder clearDisplacement() {
      
      displacement_ = 0D;
      onChanged();
      return this;
    }

    private double xg_ ;
    /**
     * <pre>
     *重心纵向坐标
     * </pre>
     *
     * <code>double xg = 3;</code>
     */
    public double getXg() {
      return xg_;
    }
    /**
     * <pre>
     *重心纵向坐标
     * </pre>
     *
     * <code>double xg = 3;</code>
     */
    public Builder setXg(double value) {
      
      xg_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *重心纵向坐标
     * </pre>
     *
     * <code>double xg = 3;</code>
     */
    public Builder clearXg() {
      
      xg_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.SubGravity)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.SubGravity)
  private static final com.iscas.biz.calculation.grpc.SubGravity DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.SubGravity();
  }

  public static com.iscas.biz.calculation.grpc.SubGravity getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SubGravity>
      PARSER = new com.google.protobuf.AbstractParser<SubGravity>() {
    public SubGravity parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SubGravity(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SubGravity> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SubGravity> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.SubGravity getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

