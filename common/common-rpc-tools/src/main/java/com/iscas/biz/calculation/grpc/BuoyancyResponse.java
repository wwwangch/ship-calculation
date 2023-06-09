// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * <pre>
 *浮力计算返回值
 * </pre>
 *
 * Protobuf type {@code com.iscas.biz.calculation.grpc.BuoyancyResponse}
 */
public  final class BuoyancyResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.BuoyancyResponse)
    BuoyancyResponseOrBuilder {
  // Use BuoyancyResponse.newBuilder() to construct.
  private BuoyancyResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuoyancyResponse() {
    code_ = 0;
    message_ = "";
    blist_ = java.util.Collections.emptyList();
    calrst_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private BuoyancyResponse(
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
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 25: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              blist_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000004;
            }
            blist_.add(input.readDouble());
            break;
          }
          case 26: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
              blist_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000004;
            }
            while (input.getBytesUntilLimit() > 0) {
              blist_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 34: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              calrst_ = new java.util.ArrayList<com.iscas.biz.calculation.grpc.Buoyant>();
              mutable_bitField0_ |= 0x00000008;
            }
            calrst_.add(
                input.readMessage(com.iscas.biz.calculation.grpc.Buoyant.parser(), extensionRegistry));
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
        blist_ = java.util.Collections.unmodifiableList(blist_);
      }
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        calrst_ = java.util.Collections.unmodifiableList(calrst_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.BuoyancyResponse.class, com.iscas.biz.calculation.grpc.BuoyancyResponse.Builder.class);
  }

  private int bitField0_;
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

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private volatile java.lang.Object message_;
  /**
   * <code>string message = 2;</code>
   */
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>string message = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BLIST_FIELD_NUMBER = 3;
  private java.util.List<java.lang.Double> blist_;
  /**
   * <pre>
   *校核b(x)的计算结果 21个值的数组
   * </pre>
   *
   * <code>repeated double blist = 3;</code>
   */
  public java.util.List<java.lang.Double>
      getBlistList() {
    return blist_;
  }
  /**
   * <pre>
   *校核b(x)的计算结果 21个值的数组
   * </pre>
   *
   * <code>repeated double blist = 3;</code>
   */
  public int getBlistCount() {
    return blist_.size();
  }
  /**
   * <pre>
   *校核b(x)的计算结果 21个值的数组
   * </pre>
   *
   * <code>repeated double blist = 3;</code>
   */
  public double getBlist(int index) {
    return blist_.get(index);
  }
  private int blistMemoizedSerializedSize = -1;

  public static final int CALRST_FIELD_NUMBER = 4;
  private java.util.List<com.iscas.biz.calculation.grpc.Buoyant> calrst_;
  /**
   * <pre>
   *每次校核计算得到的校核结果
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
   */
  public java.util.List<com.iscas.biz.calculation.grpc.Buoyant> getCalrstList() {
    return calrst_;
  }
  /**
   * <pre>
   *每次校核计算得到的校核结果
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
   */
  public java.util.List<? extends com.iscas.biz.calculation.grpc.BuoyantOrBuilder> 
      getCalrstOrBuilderList() {
    return calrst_;
  }
  /**
   * <pre>
   *每次校核计算得到的校核结果
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
   */
  public int getCalrstCount() {
    return calrst_.size();
  }
  /**
   * <pre>
   *每次校核计算得到的校核结果
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
   */
  public com.iscas.biz.calculation.grpc.Buoyant getCalrst(int index) {
    return calrst_.get(index);
  }
  /**
   * <pre>
   *每次校核计算得到的校核结果
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
   */
  public com.iscas.biz.calculation.grpc.BuoyantOrBuilder getCalrstOrBuilder(
      int index) {
    return calrst_.get(index);
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
    if (code_ != 0) {
      output.writeInt32(1, code_);
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, message_);
    }
    if (getBlistList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(blistMemoizedSerializedSize);
    }
    for (int i = 0; i < blist_.size(); i++) {
      output.writeDoubleNoTag(blist_.get(i));
    }
    for (int i = 0; i < calrst_.size(); i++) {
      output.writeMessage(4, calrst_.get(i));
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
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, message_);
    }
    {
      int dataSize = 0;
      dataSize = 8 * getBlistList().size();
      size += dataSize;
      if (!getBlistList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      blistMemoizedSerializedSize = dataSize;
    }
    for (int i = 0; i < calrst_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, calrst_.get(i));
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.BuoyancyResponse)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.BuoyancyResponse other = (com.iscas.biz.calculation.grpc.BuoyancyResponse) obj;

    boolean result = true;
    result = result && (getCode()
        == other.getCode());
    result = result && getMessage()
        .equals(other.getMessage());
    result = result && getBlistList()
        .equals(other.getBlistList());
    result = result && getCalrstList()
        .equals(other.getCalrstList());
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
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    if (getBlistCount() > 0) {
      hash = (37 * hash) + BLIST_FIELD_NUMBER;
      hash = (53 * hash) + getBlistList().hashCode();
    }
    if (getCalrstCount() > 0) {
      hash = (37 * hash) + CALRST_FIELD_NUMBER;
      hash = (53 * hash) + getCalrstList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.BuoyancyResponse parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.BuoyancyResponse prototype) {
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
   *浮力计算返回值
   * </pre>
   *
   * Protobuf type {@code com.iscas.biz.calculation.grpc.BuoyancyResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.BuoyancyResponse)
      com.iscas.biz.calculation.grpc.BuoyancyResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.BuoyancyResponse.class, com.iscas.biz.calculation.grpc.BuoyancyResponse.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.BuoyancyResponse.newBuilder()
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
        getCalrstFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      code_ = 0;

      message_ = "";

      blist_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      if (calrstBuilder_ == null) {
        calrst_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
      } else {
        calrstBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_BuoyancyResponse_descriptor;
    }

    public com.iscas.biz.calculation.grpc.BuoyancyResponse getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.BuoyancyResponse.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.BuoyancyResponse build() {
      com.iscas.biz.calculation.grpc.BuoyancyResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.BuoyancyResponse buildPartial() {
      com.iscas.biz.calculation.grpc.BuoyancyResponse result = new com.iscas.biz.calculation.grpc.BuoyancyResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.code_ = code_;
      result.message_ = message_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        blist_ = java.util.Collections.unmodifiableList(blist_);
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.blist_ = blist_;
      if (calrstBuilder_ == null) {
        if (((bitField0_ & 0x00000008) == 0x00000008)) {
          calrst_ = java.util.Collections.unmodifiableList(calrst_);
          bitField0_ = (bitField0_ & ~0x00000008);
        }
        result.calrst_ = calrst_;
      } else {
        result.calrst_ = calrstBuilder_.build();
      }
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
      if (other instanceof com.iscas.biz.calculation.grpc.BuoyancyResponse) {
        return mergeFrom((com.iscas.biz.calculation.grpc.BuoyancyResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.BuoyancyResponse other) {
      if (other == com.iscas.biz.calculation.grpc.BuoyancyResponse.getDefaultInstance()) return this;
      if (other.getCode() != 0) {
        setCode(other.getCode());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (!other.blist_.isEmpty()) {
        if (blist_.isEmpty()) {
          blist_ = other.blist_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureBlistIsMutable();
          blist_.addAll(other.blist_);
        }
        onChanged();
      }
      if (calrstBuilder_ == null) {
        if (!other.calrst_.isEmpty()) {
          if (calrst_.isEmpty()) {
            calrst_ = other.calrst_;
            bitField0_ = (bitField0_ & ~0x00000008);
          } else {
            ensureCalrstIsMutable();
            calrst_.addAll(other.calrst_);
          }
          onChanged();
        }
      } else {
        if (!other.calrst_.isEmpty()) {
          if (calrstBuilder_.isEmpty()) {
            calrstBuilder_.dispose();
            calrstBuilder_ = null;
            calrst_ = other.calrst_;
            bitField0_ = (bitField0_ & ~0x00000008);
            calrstBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCalrstFieldBuilder() : null;
          } else {
            calrstBuilder_.addAllMessages(other.calrst_);
          }
        }
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
      com.iscas.biz.calculation.grpc.BuoyancyResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.BuoyancyResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

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

    private java.lang.Object message_ = "";
    /**
     * <code>string message = 2;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> blist_ = java.util.Collections.emptyList();
    private void ensureBlistIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        blist_ = new java.util.ArrayList<java.lang.Double>(blist_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public java.util.List<java.lang.Double>
        getBlistList() {
      return java.util.Collections.unmodifiableList(blist_);
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public int getBlistCount() {
      return blist_.size();
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public double getBlist(int index) {
      return blist_.get(index);
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public Builder setBlist(
        int index, double value) {
      ensureBlistIsMutable();
      blist_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public Builder addBlist(double value) {
      ensureBlistIsMutable();
      blist_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public Builder addAllBlist(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureBlistIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, blist_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *校核b(x)的计算结果 21个值的数组
     * </pre>
     *
     * <code>repeated double blist = 3;</code>
     */
    public Builder clearBlist() {
      blist_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }

    private java.util.List<com.iscas.biz.calculation.grpc.Buoyant> calrst_ =
      java.util.Collections.emptyList();
    private void ensureCalrstIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        calrst_ = new java.util.ArrayList<com.iscas.biz.calculation.grpc.Buoyant>(calrst_);
        bitField0_ |= 0x00000008;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.iscas.biz.calculation.grpc.Buoyant, com.iscas.biz.calculation.grpc.Buoyant.Builder, com.iscas.biz.calculation.grpc.BuoyantOrBuilder> calrstBuilder_;

    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public java.util.List<com.iscas.biz.calculation.grpc.Buoyant> getCalrstList() {
      if (calrstBuilder_ == null) {
        return java.util.Collections.unmodifiableList(calrst_);
      } else {
        return calrstBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public int getCalrstCount() {
      if (calrstBuilder_ == null) {
        return calrst_.size();
      } else {
        return calrstBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public com.iscas.biz.calculation.grpc.Buoyant getCalrst(int index) {
      if (calrstBuilder_ == null) {
        return calrst_.get(index);
      } else {
        return calrstBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder setCalrst(
        int index, com.iscas.biz.calculation.grpc.Buoyant value) {
      if (calrstBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCalrstIsMutable();
        calrst_.set(index, value);
        onChanged();
      } else {
        calrstBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder setCalrst(
        int index, com.iscas.biz.calculation.grpc.Buoyant.Builder builderForValue) {
      if (calrstBuilder_ == null) {
        ensureCalrstIsMutable();
        calrst_.set(index, builderForValue.build());
        onChanged();
      } else {
        calrstBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder addCalrst(com.iscas.biz.calculation.grpc.Buoyant value) {
      if (calrstBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCalrstIsMutable();
        calrst_.add(value);
        onChanged();
      } else {
        calrstBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder addCalrst(
        int index, com.iscas.biz.calculation.grpc.Buoyant value) {
      if (calrstBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCalrstIsMutable();
        calrst_.add(index, value);
        onChanged();
      } else {
        calrstBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder addCalrst(
        com.iscas.biz.calculation.grpc.Buoyant.Builder builderForValue) {
      if (calrstBuilder_ == null) {
        ensureCalrstIsMutable();
        calrst_.add(builderForValue.build());
        onChanged();
      } else {
        calrstBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder addCalrst(
        int index, com.iscas.biz.calculation.grpc.Buoyant.Builder builderForValue) {
      if (calrstBuilder_ == null) {
        ensureCalrstIsMutable();
        calrst_.add(index, builderForValue.build());
        onChanged();
      } else {
        calrstBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder addAllCalrst(
        java.lang.Iterable<? extends com.iscas.biz.calculation.grpc.Buoyant> values) {
      if (calrstBuilder_ == null) {
        ensureCalrstIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, calrst_);
        onChanged();
      } else {
        calrstBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder clearCalrst() {
      if (calrstBuilder_ == null) {
        calrst_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000008);
        onChanged();
      } else {
        calrstBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public Builder removeCalrst(int index) {
      if (calrstBuilder_ == null) {
        ensureCalrstIsMutable();
        calrst_.remove(index);
        onChanged();
      } else {
        calrstBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public com.iscas.biz.calculation.grpc.Buoyant.Builder getCalrstBuilder(
        int index) {
      return getCalrstFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public com.iscas.biz.calculation.grpc.BuoyantOrBuilder getCalrstOrBuilder(
        int index) {
      if (calrstBuilder_ == null) {
        return calrst_.get(index);  } else {
        return calrstBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public java.util.List<? extends com.iscas.biz.calculation.grpc.BuoyantOrBuilder> 
         getCalrstOrBuilderList() {
      if (calrstBuilder_ != null) {
        return calrstBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(calrst_);
      }
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public com.iscas.biz.calculation.grpc.Buoyant.Builder addCalrstBuilder() {
      return getCalrstFieldBuilder().addBuilder(
          com.iscas.biz.calculation.grpc.Buoyant.getDefaultInstance());
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public com.iscas.biz.calculation.grpc.Buoyant.Builder addCalrstBuilder(
        int index) {
      return getCalrstFieldBuilder().addBuilder(
          index, com.iscas.biz.calculation.grpc.Buoyant.getDefaultInstance());
    }
    /**
     * <pre>
     *每次校核计算得到的校核结果
     * </pre>
     *
     * <code>repeated .com.iscas.biz.calculation.grpc.Buoyant calrst = 4;</code>
     */
    public java.util.List<com.iscas.biz.calculation.grpc.Buoyant.Builder> 
         getCalrstBuilderList() {
      return getCalrstFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.iscas.biz.calculation.grpc.Buoyant, com.iscas.biz.calculation.grpc.Buoyant.Builder, com.iscas.biz.calculation.grpc.BuoyantOrBuilder> 
        getCalrstFieldBuilder() {
      if (calrstBuilder_ == null) {
        calrstBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.iscas.biz.calculation.grpc.Buoyant, com.iscas.biz.calculation.grpc.Buoyant.Builder, com.iscas.biz.calculation.grpc.BuoyantOrBuilder>(
                calrst_,
                ((bitField0_ & 0x00000008) == 0x00000008),
                getParentForChildren(),
                isClean());
        calrst_ = null;
      }
      return calrstBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.BuoyancyResponse)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.BuoyancyResponse)
  private static final com.iscas.biz.calculation.grpc.BuoyancyResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.BuoyancyResponse();
  }

  public static com.iscas.biz.calculation.grpc.BuoyancyResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BuoyancyResponse>
      PARSER = new com.google.protobuf.AbstractParser<BuoyancyResponse>() {
    public BuoyancyResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuoyancyResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuoyancyResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuoyancyResponse> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.BuoyancyResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

