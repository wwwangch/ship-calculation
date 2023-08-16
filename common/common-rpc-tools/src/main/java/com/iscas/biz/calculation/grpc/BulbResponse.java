// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.BulbResponse}
 */
public  final class BulbResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.BulbResponse)
    BulbResponseOrBuilder {
  // Use BulbResponse.newBuilder() to construct.
  private BulbResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BulbResponse() {
    code_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private BulbResponse(
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
          case 8: {

            code_ = input.readInt32();
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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BulbResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BulbResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.BulbResponse.class, com.iscas.biz.calculation.grpc.BulbResponse.Builder.class);
  }

  public static final int CODE_FIELD_NUMBER = 1;
  private int code_;
  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 1;</code>
   */
  public int getCode() {
    return code_;
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
    if (code_ != 0) {
      output.writeInt32(1, code_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (code_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, code_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.BulbResponse)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.BulbResponse other = (com.iscas.biz.calculation.grpc.BulbResponse) obj;

    boolean result = true;
    result = result && (getCode()
        == other.getCode());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + getCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BulbResponse parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.BulbResponse prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.BulbResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.BulbResponse)
      com.iscas.biz.calculation.grpc.BulbResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BulbResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BulbResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.BulbResponse.class, com.iscas.biz.calculation.grpc.BulbResponse.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.BulbResponse.newBuilder()
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
      code_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BulbResponse_descriptor;
    }

    public com.iscas.biz.calculation.grpc.BulbResponse getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.BulbResponse.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.BulbResponse build() {
      com.iscas.biz.calculation.grpc.BulbResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.BulbResponse buildPartial() {
      com.iscas.biz.calculation.grpc.BulbResponse result = new com.iscas.biz.calculation.grpc.BulbResponse(this);
      result.code_ = code_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.BulbResponse) {
        return mergeFrom((com.iscas.biz.calculation.grpc.BulbResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.BulbResponse other) {
      if (other == com.iscas.biz.calculation.grpc.BulbResponse.getDefaultInstance()) return this;
      if (other.getCode() != 0) {
        setCode(other.getCode());
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
      com.iscas.biz.calculation.grpc.BulbResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.BulbResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int code_ ;
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 1;</code>
     */
    public int getCode() {
      return code_;
    }
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 1;</code>
     */
    public Builder setCode(int value) {
      
      code_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 1;</code>
     */
    public Builder clearCode() {
      
      code_ = 0;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.BulbResponse)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.BulbResponse)
  private static final com.iscas.biz.calculation.grpc.BulbResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.BulbResponse();
  }

  public static com.iscas.biz.calculation.grpc.BulbResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BulbResponse>
      PARSER = new com.google.protobuf.AbstractParser<BulbResponse>() {
    public BulbResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BulbResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BulbResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BulbResponse> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.BulbResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
