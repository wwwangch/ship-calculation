// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma1Response}
 */
public  final class Sigma1Response extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.Sigma1Response)
    Sigma1ResponseOrBuilder {
  // Use Sigma1Response.newBuilder() to construct.
  private Sigma1Response(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Sigma1Response() {
    sigma1_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Sigma1Response(
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
              sigma1_ = new java.util.ArrayList<com.iscas.biz.calculation.grpc.Sigma1Entity>();
              mutable_bitField0_ |= 0x00000001;
            }
            sigma1_.add(
                input.readMessage(com.iscas.biz.calculation.grpc.Sigma1Entity.parser(), extensionRegistry));
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
        sigma1_ = java.util.Collections.unmodifiableList(sigma1_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Response_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Response_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.Sigma1Response.class, com.iscas.biz.calculation.grpc.Sigma1Response.Builder.class);
  }

  public static final int SIGMA1_FIELD_NUMBER = 1;
  private java.util.List<com.iscas.biz.calculation.grpc.Sigma1Entity> sigma1_;
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
   */
  public java.util.List<com.iscas.biz.calculation.grpc.Sigma1Entity> getSigma1List() {
    return sigma1_;
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
   */
  public java.util.List<? extends com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder> 
      getSigma1OrBuilderList() {
    return sigma1_;
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
   */
  public int getSigma1Count() {
    return sigma1_.size();
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
   */
  public com.iscas.biz.calculation.grpc.Sigma1Entity getSigma1(int index) {
    return sigma1_.get(index);
  }
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
   */
  public com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder getSigma1OrBuilder(
      int index) {
    return sigma1_.get(index);
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
    for (int i = 0; i < sigma1_.size(); i++) {
      output.writeMessage(1, sigma1_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < sigma1_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, sigma1_.get(i));
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.Sigma1Response)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.Sigma1Response other = (com.iscas.biz.calculation.grpc.Sigma1Response) obj;

    boolean result = true;
    result = result && getSigma1List()
        .equals(other.getSigma1List());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getSigma1Count() > 0) {
      hash = (37 * hash) + SIGMA1_FIELD_NUMBER;
      hash = (53 * hash) + getSigma1List().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma1Response parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.Sigma1Response prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma1Response}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.Sigma1Response)
      com.iscas.biz.calculation.grpc.Sigma1ResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Response_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Response_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.Sigma1Response.class, com.iscas.biz.calculation.grpc.Sigma1Response.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.Sigma1Response.newBuilder()
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
        getSigma1FieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (sigma1Builder_ == null) {
        sigma1_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        sigma1Builder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma1Response_descriptor;
    }

    public com.iscas.biz.calculation.grpc.Sigma1Response getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.Sigma1Response.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.Sigma1Response build() {
      com.iscas.biz.calculation.grpc.Sigma1Response result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.Sigma1Response buildPartial() {
      com.iscas.biz.calculation.grpc.Sigma1Response result = new com.iscas.biz.calculation.grpc.Sigma1Response(this);
      int from_bitField0_ = bitField0_;
      if (sigma1Builder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          sigma1_ = java.util.Collections.unmodifiableList(sigma1_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.sigma1_ = sigma1_;
      } else {
        result.sigma1_ = sigma1Builder_.build();
      }
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
      if (other instanceof com.iscas.biz.calculation.grpc.Sigma1Response) {
        return mergeFrom((com.iscas.biz.calculation.grpc.Sigma1Response)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.Sigma1Response other) {
      if (other == com.iscas.biz.calculation.grpc.Sigma1Response.getDefaultInstance()) return this;
      if (sigma1Builder_ == null) {
        if (!other.sigma1_.isEmpty()) {
          if (sigma1_.isEmpty()) {
            sigma1_ = other.sigma1_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureSigma1IsMutable();
            sigma1_.addAll(other.sigma1_);
          }
          onChanged();
        }
      } else {
        if (!other.sigma1_.isEmpty()) {
          if (sigma1Builder_.isEmpty()) {
            sigma1Builder_.dispose();
            sigma1Builder_ = null;
            sigma1_ = other.sigma1_;
            bitField0_ = (bitField0_ & ~0x00000001);
            sigma1Builder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getSigma1FieldBuilder() : null;
          } else {
            sigma1Builder_.addAllMessages(other.sigma1_);
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
      com.iscas.biz.calculation.grpc.Sigma1Response parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.Sigma1Response) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.iscas.biz.calculation.grpc.Sigma1Entity> sigma1_ =
      java.util.Collections.emptyList();
    private void ensureSigma1IsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        sigma1_ = new java.util.ArrayList<com.iscas.biz.calculation.grpc.Sigma1Entity>(sigma1_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.iscas.biz.calculation.grpc.Sigma1Entity, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder, com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder> sigma1Builder_;

    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public java.util.List<com.iscas.biz.calculation.grpc.Sigma1Entity> getSigma1List() {
      if (sigma1Builder_ == null) {
        return java.util.Collections.unmodifiableList(sigma1_);
      } else {
        return sigma1Builder_.getMessageList();
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public int getSigma1Count() {
      if (sigma1Builder_ == null) {
        return sigma1_.size();
      } else {
        return sigma1Builder_.getCount();
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.Sigma1Entity getSigma1(int index) {
      if (sigma1Builder_ == null) {
        return sigma1_.get(index);
      } else {
        return sigma1Builder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder setSigma1(
        int index, com.iscas.biz.calculation.grpc.Sigma1Entity value) {
      if (sigma1Builder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSigma1IsMutable();
        sigma1_.set(index, value);
        onChanged();
      } else {
        sigma1Builder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder setSigma1(
        int index, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder builderForValue) {
      if (sigma1Builder_ == null) {
        ensureSigma1IsMutable();
        sigma1_.set(index, builderForValue.build());
        onChanged();
      } else {
        sigma1Builder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder addSigma1(com.iscas.biz.calculation.grpc.Sigma1Entity value) {
      if (sigma1Builder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSigma1IsMutable();
        sigma1_.add(value);
        onChanged();
      } else {
        sigma1Builder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder addSigma1(
        int index, com.iscas.biz.calculation.grpc.Sigma1Entity value) {
      if (sigma1Builder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSigma1IsMutable();
        sigma1_.add(index, value);
        onChanged();
      } else {
        sigma1Builder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder addSigma1(
        com.iscas.biz.calculation.grpc.Sigma1Entity.Builder builderForValue) {
      if (sigma1Builder_ == null) {
        ensureSigma1IsMutable();
        sigma1_.add(builderForValue.build());
        onChanged();
      } else {
        sigma1Builder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder addSigma1(
        int index, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder builderForValue) {
      if (sigma1Builder_ == null) {
        ensureSigma1IsMutable();
        sigma1_.add(index, builderForValue.build());
        onChanged();
      } else {
        sigma1Builder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder addAllSigma1(
        java.lang.Iterable<? extends com.iscas.biz.calculation.grpc.Sigma1Entity> values) {
      if (sigma1Builder_ == null) {
        ensureSigma1IsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, sigma1_);
        onChanged();
      } else {
        sigma1Builder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder clearSigma1() {
      if (sigma1Builder_ == null) {
        sigma1_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        sigma1Builder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public Builder removeSigma1(int index) {
      if (sigma1Builder_ == null) {
        ensureSigma1IsMutable();
        sigma1_.remove(index);
        onChanged();
      } else {
        sigma1Builder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.Sigma1Entity.Builder getSigma1Builder(
        int index) {
      return getSigma1FieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder getSigma1OrBuilder(
        int index) {
      if (sigma1Builder_ == null) {
        return sigma1_.get(index);  } else {
        return sigma1Builder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public java.util.List<? extends com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder> 
         getSigma1OrBuilderList() {
      if (sigma1Builder_ != null) {
        return sigma1Builder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(sigma1_);
      }
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.Sigma1Entity.Builder addSigma1Builder() {
      return getSigma1FieldBuilder().addBuilder(
          com.iscas.biz.calculation.grpc.Sigma1Entity.getDefaultInstance());
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public com.iscas.biz.calculation.grpc.Sigma1Entity.Builder addSigma1Builder(
        int index) {
      return getSigma1FieldBuilder().addBuilder(
          index, com.iscas.biz.calculation.grpc.Sigma1Entity.getDefaultInstance());
    }
    /**
     * <code>repeated .com.iscas.biz.calculation.grpc.Sigma1Entity sigma1 = 1;</code>
     */
    public java.util.List<com.iscas.biz.calculation.grpc.Sigma1Entity.Builder> 
         getSigma1BuilderList() {
      return getSigma1FieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.iscas.biz.calculation.grpc.Sigma1Entity, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder, com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder> 
        getSigma1FieldBuilder() {
      if (sigma1Builder_ == null) {
        sigma1Builder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.iscas.biz.calculation.grpc.Sigma1Entity, com.iscas.biz.calculation.grpc.Sigma1Entity.Builder, com.iscas.biz.calculation.grpc.Sigma1EntityOrBuilder>(
                sigma1_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        sigma1_ = null;
      }
      return sigma1Builder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.Sigma1Response)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.Sigma1Response)
  private static final com.iscas.biz.calculation.grpc.Sigma1Response DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.Sigma1Response();
  }

  public static com.iscas.biz.calculation.grpc.Sigma1Response getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Sigma1Response>
      PARSER = new com.google.protobuf.AbstractParser<Sigma1Response>() {
    public Sigma1Response parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Sigma1Response(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Sigma1Response> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Sigma1Response> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.Sigma1Response getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

