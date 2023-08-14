// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.ShearingStressResponse}
 */
public  final class ShearingStressResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.ShearingStressResponse)
    ShearingStressResponseOrBuilder {
  // Use ShearingStressResponse.newBuilder() to construct.
  private ShearingStressResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ShearingStressResponse() {
    shearingStress_ = java.util.Collections.emptyList();
    code_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ShearingStressResponse(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              shearingStress_ = new java.util.ArrayList<com.iscas.biz.calculation.grpc.ShearingStressEntity>();
              mutable_bitField0_ |= 0x00000001;
            }
            shearingStress_.add(
                input.readMessage(com.iscas.biz.calculation.grpc.ShearingStressEntity.parser(), extensionRegistry));
            break;
          }
          case 16: {

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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        shearingStress_ = java.util.Collections.unmodifiableList(shearingStress_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.ShearingStressResponse.class, com.iscas.biz.calculation.grpc.ShearingStressResponse.Builder.class);
  }

  private int bitField0_;
  public static final int SHEARINGSTRESS_FIELD_NUMBER = 1;
  private java.util.List<com.iscas.biz.calculation.grpc.ShearingStressEntity> shearingStress_;
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
   */
  public java.util.List<com.iscas.biz.calculation.grpc.ShearingStressEntity> getShearingStressList() {
    return shearingStress_;
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
   */
  public java.util.List<? extends com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder> 
      getShearingStressOrBuilderList() {
    return shearingStress_;
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
   */
  public int getShearingStressCount() {
    return shearingStress_.size();
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
   */
  public com.iscas.biz.calculation.grpc.ShearingStressEntity getShearingStress(int index) {
    return shearingStress_.get(index);
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
   */
  public com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder getShearingStressOrBuilder(
      int index) {
    return shearingStress_.get(index);
  }

  public static final int CODE_FIELD_NUMBER = 2;
  private int code_;
  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 2;</code>
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
    for (int i = 0; i < shearingStress_.size(); i++) {
      output.writeMessage(1, shearingStress_.get(i));
    }
    if (code_ != 0) {
      output.writeInt32(2, code_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < shearingStress_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, shearingStress_.get(i));
    }
    if (code_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, code_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.ShearingStressResponse)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.ShearingStressResponse other = (com.iscas.biz.calculation.grpc.ShearingStressResponse) obj;

    boolean result = true;
    result = result && getShearingStressList()
        .equals(other.getShearingStressList());
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
    if (getShearingStressCount() > 0) {
      hash = (37 * hash) + SHEARINGSTRESS_FIELD_NUMBER;
      hash = (53 * hash) + getShearingStressList().hashCode();
    }
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + getCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShearingStressResponse parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.ShearingStressResponse prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.ShearingStressResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.ShearingStressResponse)
      com.iscas.biz.calculation.grpc.ShearingStressResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.ShearingStressResponse.class, com.iscas.biz.calculation.grpc.ShearingStressResponse.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.ShearingStressResponse.newBuilder()
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
        getShearingStressFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (shearingStressBuilder_ == null) {
        shearingStress_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        shearingStressBuilder_.clear();
      }
      code_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShearingStressResponse_descriptor;
    }

    public com.iscas.biz.calculation.grpc.ShearingStressResponse getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.ShearingStressResponse.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.ShearingStressResponse build() {
      com.iscas.biz.calculation.grpc.ShearingStressResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.ShearingStressResponse buildPartial() {
      com.iscas.biz.calculation.grpc.ShearingStressResponse result = new com.iscas.biz.calculation.grpc.ShearingStressResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (shearingStressBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          shearingStress_ = java.util.Collections.unmodifiableList(shearingStress_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.shearingStress_ = shearingStress_;
      } else {
        result.shearingStress_ = shearingStressBuilder_.build();
      }
      result.code_ = code_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.ShearingStressResponse) {
        return mergeFrom((com.iscas.biz.calculation.grpc.ShearingStressResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.ShearingStressResponse other) {
      if (other == com.iscas.biz.calculation.grpc.ShearingStressResponse.getDefaultInstance()) return this;
      if (shearingStressBuilder_ == null) {
        if (!other.shearingStress_.isEmpty()) {
          if (shearingStress_.isEmpty()) {
            shearingStress_ = other.shearingStress_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureShearingStressIsMutable();
            shearingStress_.addAll(other.shearingStress_);
          }
          onChanged();
        }
      } else {
        if (!other.shearingStress_.isEmpty()) {
          if (shearingStressBuilder_.isEmpty()) {
            shearingStressBuilder_.dispose();
            shearingStressBuilder_ = null;
            shearingStress_ = other.shearingStress_;
            bitField0_ = (bitField0_ & ~0x00000001);
            shearingStressBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getShearingStressFieldBuilder() : null;
          } else {
            shearingStressBuilder_.addAllMessages(other.shearingStress_);
          }
        }
      }
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
      com.iscas.biz.calculation.grpc.ShearingStressResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.ShearingStressResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.iscas.biz.calculation.grpc.ShearingStressEntity> shearingStress_ =
      java.util.Collections.emptyList();
    private void ensureShearingStressIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        shearingStress_ = new java.util.ArrayList<com.iscas.biz.calculation.grpc.ShearingStressEntity>(shearingStress_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.iscas.biz.calculation.grpc.ShearingStressEntity, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder, com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder> shearingStressBuilder_;

    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public java.util.List<com.iscas.biz.calculation.grpc.ShearingStressEntity> getShearingStressList() {
      if (shearingStressBuilder_ == null) {
        return java.util.Collections.unmodifiableList(shearingStress_);
      } else {
        return shearingStressBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public int getShearingStressCount() {
      if (shearingStressBuilder_ == null) {
        return shearingStress_.size();
      } else {
        return shearingStressBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.ShearingStressEntity getShearingStress(int index) {
      if (shearingStressBuilder_ == null) {
        return shearingStress_.get(index);
      } else {
        return shearingStressBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder setShearingStress(
        int index, com.iscas.biz.calculation.grpc.ShearingStressEntity value) {
      if (shearingStressBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureShearingStressIsMutable();
        shearingStress_.set(index, value);
        onChanged();
      } else {
        shearingStressBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder setShearingStress(
        int index, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder builderForValue) {
      if (shearingStressBuilder_ == null) {
        ensureShearingStressIsMutable();
        shearingStress_.set(index, builderForValue.build());
        onChanged();
      } else {
        shearingStressBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder addShearingStress(com.iscas.biz.calculation.grpc.ShearingStressEntity value) {
      if (shearingStressBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureShearingStressIsMutable();
        shearingStress_.add(value);
        onChanged();
      } else {
        shearingStressBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder addShearingStress(
        int index, com.iscas.biz.calculation.grpc.ShearingStressEntity value) {
      if (shearingStressBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureShearingStressIsMutable();
        shearingStress_.add(index, value);
        onChanged();
      } else {
        shearingStressBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder addShearingStress(
        com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder builderForValue) {
      if (shearingStressBuilder_ == null) {
        ensureShearingStressIsMutable();
        shearingStress_.add(builderForValue.build());
        onChanged();
      } else {
        shearingStressBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder addShearingStress(
        int index, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder builderForValue) {
      if (shearingStressBuilder_ == null) {
        ensureShearingStressIsMutable();
        shearingStress_.add(index, builderForValue.build());
        onChanged();
      } else {
        shearingStressBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder addAllShearingStress(
        java.lang.Iterable<? extends com.iscas.biz.calculation.grpc.ShearingStressEntity> values) {
      if (shearingStressBuilder_ == null) {
        ensureShearingStressIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, shearingStress_);
        onChanged();
      } else {
        shearingStressBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder clearShearingStress() {
      if (shearingStressBuilder_ == null) {
        shearingStress_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        shearingStressBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public Builder removeShearingStress(int index) {
      if (shearingStressBuilder_ == null) {
        ensureShearingStressIsMutable();
        shearingStress_.remove(index);
        onChanged();
      } else {
        shearingStressBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder getShearingStressBuilder(
        int index) {
      return getShearingStressFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder getShearingStressOrBuilder(
        int index) {
      if (shearingStressBuilder_ == null) {
        return shearingStress_.get(index);  } else {
        return shearingStressBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public java.util.List<? extends com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder> 
         getShearingStressOrBuilderList() {
      if (shearingStressBuilder_ != null) {
        return shearingStressBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(shearingStress_);
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder addShearingStressBuilder() {
      return getShearingStressFieldBuilder().addBuilder(
          com.iscas.biz.calculation.grpc.ShearingStressEntity.getDefaultInstance());
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder addShearingStressBuilder(
        int index) {
      return getShearingStressFieldBuilder().addBuilder(
          index, com.iscas.biz.calculation.grpc.ShearingStressEntity.getDefaultInstance());
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.ShearingStressEntity shearingStress = 1;</code>
     */
    public java.util.List<com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder> 
         getShearingStressBuilderList() {
      return getShearingStressFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.iscas.biz.calculation.grpc.ShearingStressEntity, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder, com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder> 
        getShearingStressFieldBuilder() {
      if (shearingStressBuilder_ == null) {
        shearingStressBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.iscas.biz.calculation.grpc.ShearingStressEntity, com.iscas.biz.calculation.grpc.ShearingStressEntity.Builder, com.iscas.biz.calculation.grpc.ShearingStressEntityOrBuilder>(
                shearingStress_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        shearingStress_ = null;
      }
      return shearingStressBuilder_;
    }

    private int code_ ;
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 2;</code>
     */
    public int getCode() {
      return code_;
    }
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 2;</code>
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
     * <code>int32 code = 2;</code>
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.ShearingStressResponse)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.ShearingStressResponse)
  private static final com.iscas.biz.calculation.grpc.ShearingStressResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.ShearingStressResponse();
  }

  public static com.iscas.biz.calculation.grpc.ShearingStressResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ShearingStressResponse>
      PARSER = new com.google.protobuf.AbstractParser<ShearingStressResponse>() {
    public ShearingStressResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ShearingStressResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ShearingStressResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ShearingStressResponse> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.ShearingStressResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

