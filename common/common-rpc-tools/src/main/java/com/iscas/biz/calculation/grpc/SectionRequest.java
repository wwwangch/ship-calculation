// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * <pre>
 *剖面计算输入
 * </pre>
 *
 * Protobuf type {@code com.iscas.biz.calculation.grpc.SectionRequest}
 */
public  final class SectionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.SectionRequest)
    SectionRequestOrBuilder {
  // Use SectionRequest.newBuilder() to construct.
  private SectionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SectionRequest() {
    profileFilePath_ = "";
    ribNumber_ = 0D;
    executeAutoCadPath_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SectionRequest(
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

            profileFilePath_ = s;
            break;
          }
          case 17: {

            ribNumber_ = input.readDouble();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            executeAutoCadPath_ = s;
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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.SectionRequest.class, com.iscas.biz.calculation.grpc.SectionRequest.Builder.class);
  }

  public static final int PROFILEFILEPATH_FIELD_NUMBER = 1;
  private volatile java.lang.Object profileFilePath_;
  /**
   * <pre>
   *剖面文件路径
   * </pre>
   *
   * <code>string profileFilePath = 1;</code>
   */
  public java.lang.String getProfileFilePath() {
    java.lang.Object ref = profileFilePath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      profileFilePath_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *剖面文件路径
   * </pre>
   *
   * <code>string profileFilePath = 1;</code>
   */
  public com.google.protobuf.ByteString
      getProfileFilePathBytes() {
    java.lang.Object ref = profileFilePath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      profileFilePath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int RIBNUMBER_FIELD_NUMBER = 2;
  private double ribNumber_;
  /**
   * <pre>
   *肋位号
   * </pre>
   *
   * <code>double ribNumber = 2;</code>
   */
  public double getRibNumber() {
    return ribNumber_;
  }

  public static final int EXECUTEAUTOCADPATH_FIELD_NUMBER = 3;
  private volatile java.lang.Object executeAutoCadPath_;
  /**
   * <pre>
   *CAD执行路径[增0728]
   * </pre>
   *
   * <code>string executeAutoCadPath = 3;</code>
   */
  public java.lang.String getExecuteAutoCadPath() {
    java.lang.Object ref = executeAutoCadPath_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      executeAutoCadPath_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *CAD执行路径[增0728]
   * </pre>
   *
   * <code>string executeAutoCadPath = 3;</code>
   */
  public com.google.protobuf.ByteString
      getExecuteAutoCadPathBytes() {
    java.lang.Object ref = executeAutoCadPath_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      executeAutoCadPath_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getProfileFilePathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, profileFilePath_);
    }
    if (ribNumber_ != 0D) {
      output.writeDouble(2, ribNumber_);
    }
    if (!getExecuteAutoCadPathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, executeAutoCadPath_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getProfileFilePathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, profileFilePath_);
    }
    if (ribNumber_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, ribNumber_);
    }
    if (!getExecuteAutoCadPathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, executeAutoCadPath_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.SectionRequest)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.SectionRequest other = (com.iscas.biz.calculation.grpc.SectionRequest) obj;

    boolean result = true;
    result = result && getProfileFilePath()
        .equals(other.getProfileFilePath());
    result = result && (
        java.lang.Double.doubleToLongBits(getRibNumber())
        == java.lang.Double.doubleToLongBits(
            other.getRibNumber()));
    result = result && getExecuteAutoCadPath()
        .equals(other.getExecuteAutoCadPath());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PROFILEFILEPATH_FIELD_NUMBER;
    hash = (53 * hash) + getProfileFilePath().hashCode();
    hash = (37 * hash) + RIBNUMBER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getRibNumber()));
    hash = (37 * hash) + EXECUTEAUTOCADPATH_FIELD_NUMBER;
    hash = (53 * hash) + getExecuteAutoCadPath().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SectionRequest parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.SectionRequest prototype) {
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
   *剖面计算输入
   * </pre>
   *
   * Protobuf type {@code com.iscas.biz.calculation.grpc.SectionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.SectionRequest)
      com.iscas.biz.calculation.grpc.SectionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.SectionRequest.class, com.iscas.biz.calculation.grpc.SectionRequest.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.SectionRequest.newBuilder()
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
      profileFilePath_ = "";

      ribNumber_ = 0D;

      executeAutoCadPath_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionRequest_descriptor;
    }

    public com.iscas.biz.calculation.grpc.SectionRequest getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.SectionRequest.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.SectionRequest build() {
      com.iscas.biz.calculation.grpc.SectionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.SectionRequest buildPartial() {
      com.iscas.biz.calculation.grpc.SectionRequest result = new com.iscas.biz.calculation.grpc.SectionRequest(this);
      result.profileFilePath_ = profileFilePath_;
      result.ribNumber_ = ribNumber_;
      result.executeAutoCadPath_ = executeAutoCadPath_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.SectionRequest) {
        return mergeFrom((com.iscas.biz.calculation.grpc.SectionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.SectionRequest other) {
      if (other == com.iscas.biz.calculation.grpc.SectionRequest.getDefaultInstance()) return this;
      if (!other.getProfileFilePath().isEmpty()) {
        profileFilePath_ = other.profileFilePath_;
        onChanged();
      }
      if (other.getRibNumber() != 0D) {
        setRibNumber(other.getRibNumber());
      }
      if (!other.getExecuteAutoCadPath().isEmpty()) {
        executeAutoCadPath_ = other.executeAutoCadPath_;
        onChanged();
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
      com.iscas.biz.calculation.grpc.SectionRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.SectionRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object profileFilePath_ = "";
    /**
     * <pre>
     *剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 1;</code>
     */
    public java.lang.String getProfileFilePath() {
      java.lang.Object ref = profileFilePath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        profileFilePath_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 1;</code>
     */
    public com.google.protobuf.ByteString
        getProfileFilePathBytes() {
      java.lang.Object ref = profileFilePath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        profileFilePath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 1;</code>
     */
    public Builder setProfileFilePath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      profileFilePath_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 1;</code>
     */
    public Builder clearProfileFilePath() {
      
      profileFilePath_ = getDefaultInstance().getProfileFilePath();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 1;</code>
     */
    public Builder setProfileFilePathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      profileFilePath_ = value;
      onChanged();
      return this;
    }

    private double ribNumber_ ;
    /**
     * <pre>
     *肋位号
     * </pre>
     *
     * <code>double ribNumber = 2;</code>
     */
    public double getRibNumber() {
      return ribNumber_;
    }
    /**
     * <pre>
     *肋位号
     * </pre>
     *
     * <code>double ribNumber = 2;</code>
     */
    public Builder setRibNumber(double value) {
      
      ribNumber_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *肋位号
     * </pre>
     *
     * <code>double ribNumber = 2;</code>
     */
    public Builder clearRibNumber() {
      
      ribNumber_ = 0D;
      onChanged();
      return this;
    }

    private java.lang.Object executeAutoCadPath_ = "";
    /**
     * <pre>
     *CAD执行路径[增0728]
     * </pre>
     *
     * <code>string executeAutoCadPath = 3;</code>
     */
    public java.lang.String getExecuteAutoCadPath() {
      java.lang.Object ref = executeAutoCadPath_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        executeAutoCadPath_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *CAD执行路径[增0728]
     * </pre>
     *
     * <code>string executeAutoCadPath = 3;</code>
     */
    public com.google.protobuf.ByteString
        getExecuteAutoCadPathBytes() {
      java.lang.Object ref = executeAutoCadPath_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        executeAutoCadPath_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *CAD执行路径[增0728]
     * </pre>
     *
     * <code>string executeAutoCadPath = 3;</code>
     */
    public Builder setExecuteAutoCadPath(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      executeAutoCadPath_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *CAD执行路径[增0728]
     * </pre>
     *
     * <code>string executeAutoCadPath = 3;</code>
     */
    public Builder clearExecuteAutoCadPath() {
      
      executeAutoCadPath_ = getDefaultInstance().getExecuteAutoCadPath();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *CAD执行路径[增0728]
     * </pre>
     *
     * <code>string executeAutoCadPath = 3;</code>
     */
    public Builder setExecuteAutoCadPathBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      executeAutoCadPath_ = value;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.SectionRequest)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.SectionRequest)
  private static final com.iscas.biz.calculation.grpc.SectionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.SectionRequest();
  }

  public static com.iscas.biz.calculation.grpc.SectionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SectionRequest>
      PARSER = new com.google.protobuf.AbstractParser<SectionRequest>() {
    public SectionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SectionRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SectionRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SectionRequest> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.SectionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

