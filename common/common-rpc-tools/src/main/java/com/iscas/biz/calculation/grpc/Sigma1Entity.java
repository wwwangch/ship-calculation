// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma1Entity}
 */
public  final class Sigma1Entity extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.Sigma1Entity)
    Sigma1EntityOrBuilder {
  // Use Sigma1Entity.newBuilder() to construct.
  private Sigma1Entity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Sigma1Entity() {
    sigma1HUp_ = 0D;
    sigma1Down_ = 0D;
    sigma1SUp_ = 0D;
    sigma1SDown_ = 0D;
    allowStress_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Sigma1Entity(
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

            sigma1HUp_ = input.readDouble();
            break;
          }
          case 17: {

            sigma1Down_ = input.readDouble();
            break;
          }
          case 25: {

            sigma1SUp_ = input.readDouble();
            break;
          }
          case 33: {

            sigma1SDown_ = input.readDouble();
            break;
          }
          case 41: {

            allowStress_ = input.readDouble();
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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Entity_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Entity_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.Sigma1Entity.class, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder.class);
  }

  public static final int SIGMA1HUP_FIELD_NUMBER = 1;
  private double sigma1HUp_;
  /**
   * <pre>
   *中拱龙骨上纤维
   * </pre>
   *
   * <code>double sigma1HUp = 1;</code>
   */
  public double getSigma1HUp() {
    return sigma1HUp_;
  }

  public static final int SIGMA1DOWN_FIELD_NUMBER = 2;
  private double sigma1Down_;
  /**
   * <pre>
   *中拱龙骨下纤维
   * </pre>
   *
   * <code>double sigma1Down = 2;</code>
   */
  public double getSigma1Down() {
    return sigma1Down_;
  }

  public static final int SIGMA1SUP_FIELD_NUMBER = 3;
  private double sigma1SUp_;
  /**
   * <pre>
   *中垂龙骨上纤维
   * </pre>
   *
   * <code>double sigma1SUp = 3;</code>
   */
  public double getSigma1SUp() {
    return sigma1SUp_;
  }

  public static final int SIGMA1SDOWN_FIELD_NUMBER = 4;
  private double sigma1SDown_;
  /**
   * <pre>
   *中垂龙骨上纤维
   * </pre>
   *
   * <code>double sigma1SDown = 4;</code>
   */
  public double getSigma1SDown() {
    return sigma1SDown_;
  }

  public static final int ALLOWSTRESS_FIELD_NUMBER = 5;
  private double allowStress_;
  /**
   * <pre>
   *许用应力
   * </pre>
   *
   * <code>double allowStress = 5;</code>
   */
  public double getAllowStress() {
    return allowStress_;
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
    if (sigma1HUp_ != 0D) {
      output.writeDouble(1, sigma1HUp_);
    }
    if (sigma1Down_ != 0D) {
      output.writeDouble(2, sigma1Down_);
    }
    if (sigma1SUp_ != 0D) {
      output.writeDouble(3, sigma1SUp_);
    }
    if (sigma1SDown_ != 0D) {
      output.writeDouble(4, sigma1SDown_);
    }
    if (allowStress_ != 0D) {
      output.writeDouble(5, allowStress_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (sigma1HUp_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, sigma1HUp_);
    }
    if (sigma1Down_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, sigma1Down_);
    }
    if (sigma1SUp_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, sigma1SUp_);
    }
    if (sigma1SDown_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, sigma1SDown_);
    }
    if (allowStress_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, allowStress_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.Sigma1Entity)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.Sigma1Entity other = (com.iscas.biz.calculation.grpc.Sigma1Entity) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getSigma1HUp())
        == java.lang.Double.doubleToLongBits(
            other.getSigma1HUp()));
    result = result && (
        java.lang.Double.doubleToLongBits(getSigma1Down())
        == java.lang.Double.doubleToLongBits(
            other.getSigma1Down()));
    result = result && (
        java.lang.Double.doubleToLongBits(getSigma1SUp())
        == java.lang.Double.doubleToLongBits(
            other.getSigma1SUp()));
    result = result && (
        java.lang.Double.doubleToLongBits(getSigma1SDown())
        == java.lang.Double.doubleToLongBits(
            other.getSigma1SDown()));
    result = result && (
        java.lang.Double.doubleToLongBits(getAllowStress())
        == java.lang.Double.doubleToLongBits(
            other.getAllowStress()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SIGMA1HUP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSigma1HUp()));
    hash = (37 * hash) + SIGMA1DOWN_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSigma1Down()));
    hash = (37 * hash) + SIGMA1SUP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSigma1SUp()));
    hash = (37 * hash) + SIGMA1SDOWN_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSigma1SDown()));
    hash = (37 * hash) + ALLOWSTRESS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getAllowStress()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Entity parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.Sigma1Entity prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma1Entity}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.Sigma1Entity)
      com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Entity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Entity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.Sigma1Entity.class, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.Sigma1Entity.newBuilder()
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
      sigma1HUp_ = 0D;

      sigma1Down_ = 0D;

      sigma1SUp_ = 0D;

      sigma1SDown_ = 0D;

      allowStress_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Entity_descriptor;
    }

    public com.iscas.biz.calculation.grpc.Sigma1Entity getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.Sigma1Entity.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.Sigma1Entity build() {
      com.iscas.biz.calculation.grpc.Sigma1Entity result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.Sigma1Entity buildPartial() {
      com.iscas.biz.calculation.grpc.Sigma1Entity result = new com.iscas.biz.calculation.grpc.Sigma1Entity(this);
      result.sigma1HUp_ = sigma1HUp_;
      result.sigma1Down_ = sigma1Down_;
      result.sigma1SUp_ = sigma1SUp_;
      result.sigma1SDown_ = sigma1SDown_;
      result.allowStress_ = allowStress_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.Sigma1Entity) {
        return mergeFrom((com.iscas.biz.calculation.grpc.Sigma1Entity)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.Sigma1Entity other) {
      if (other == com.iscas.biz.calculation.grpc.Sigma1Entity.getDefaultInstance()) return this;
      if (other.getSigma1HUp() != 0D) {
        setSigma1HUp(other.getSigma1HUp());
      }
      if (other.getSigma1Down() != 0D) {
        setSigma1Down(other.getSigma1Down());
      }
      if (other.getSigma1SUp() != 0D) {
        setSigma1SUp(other.getSigma1SUp());
      }
      if (other.getSigma1SDown() != 0D) {
        setSigma1SDown(other.getSigma1SDown());
      }
      if (other.getAllowStress() != 0D) {
        setAllowStress(other.getAllowStress());
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
      com.iscas.biz.calculation.grpc.Sigma1Entity parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.Sigma1Entity) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double sigma1HUp_ ;
    /**
     * <pre>
     *中拱龙骨上纤维
     * </pre>
     *
     * <code>double sigma1HUp = 1;</code>
     */
    public double getSigma1HUp() {
      return sigma1HUp_;
    }
    /**
     * <pre>
     *中拱龙骨上纤维
     * </pre>
     *
     * <code>double sigma1HUp = 1;</code>
     */
    public Builder setSigma1HUp(double value) {
      
      sigma1HUp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中拱龙骨上纤维
     * </pre>
     *
     * <code>double sigma1HUp = 1;</code>
     */
    public Builder clearSigma1HUp() {
      
      sigma1HUp_ = 0D;
      onChanged();
      return this;
    }

    private double sigma1Down_ ;
    /**
     * <pre>
     *中拱龙骨下纤维
     * </pre>
     *
     * <code>double sigma1Down = 2;</code>
     */
    public double getSigma1Down() {
      return sigma1Down_;
    }
    /**
     * <pre>
     *中拱龙骨下纤维
     * </pre>
     *
     * <code>double sigma1Down = 2;</code>
     */
    public Builder setSigma1Down(double value) {
      
      sigma1Down_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中拱龙骨下纤维
     * </pre>
     *
     * <code>double sigma1Down = 2;</code>
     */
    public Builder clearSigma1Down() {
      
      sigma1Down_ = 0D;
      onChanged();
      return this;
    }

    private double sigma1SUp_ ;
    /**
     * <pre>
     *中垂龙骨上纤维
     * </pre>
     *
     * <code>double sigma1SUp = 3;</code>
     */
    public double getSigma1SUp() {
      return sigma1SUp_;
    }
    /**
     * <pre>
     *中垂龙骨上纤维
     * </pre>
     *
     * <code>double sigma1SUp = 3;</code>
     */
    public Builder setSigma1SUp(double value) {
      
      sigma1SUp_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中垂龙骨上纤维
     * </pre>
     *
     * <code>double sigma1SUp = 3;</code>
     */
    public Builder clearSigma1SUp() {
      
      sigma1SUp_ = 0D;
      onChanged();
      return this;
    }

    private double sigma1SDown_ ;
    /**
     * <pre>
     *中垂龙骨上纤维
     * </pre>
     *
     * <code>double sigma1SDown = 4;</code>
     */
    public double getSigma1SDown() {
      return sigma1SDown_;
    }
    /**
     * <pre>
     *中垂龙骨上纤维
     * </pre>
     *
     * <code>double sigma1SDown = 4;</code>
     */
    public Builder setSigma1SDown(double value) {
      
      sigma1SDown_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *中垂龙骨上纤维
     * </pre>
     *
     * <code>double sigma1SDown = 4;</code>
     */
    public Builder clearSigma1SDown() {
      
      sigma1SDown_ = 0D;
      onChanged();
      return this;
    }

    private double allowStress_ ;
    /**
     * <pre>
     *许用应力
     * </pre>
     *
     * <code>double allowStress = 5;</code>
     */
    public double getAllowStress() {
      return allowStress_;
    }
    /**
     * <pre>
     *许用应力
     * </pre>
     *
     * <code>double allowStress = 5;</code>
     */
    public Builder setAllowStress(double value) {
      
      allowStress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *许用应力
     * </pre>
     *
     * <code>double allowStress = 5;</code>
     */
    public Builder clearAllowStress() {
      
      allowStress_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.Sigma1Entity)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.Sigma1Entity)
  private static final com.iscas.biz.calculation.grpc.Sigma1Entity DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.Sigma1Entity();
  }

  public static com.iscas.biz.calculation.grpc.Sigma1Entity getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Sigma1Entity>
      PARSER = new com.google.protobuf.AbstractParser<Sigma1Entity>() {
    public Sigma1Entity parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Sigma1Entity(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Sigma1Entity> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Sigma1Entity> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.Sigma1Entity getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

