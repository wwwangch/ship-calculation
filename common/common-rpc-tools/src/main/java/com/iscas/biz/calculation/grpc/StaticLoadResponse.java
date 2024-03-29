// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * <pre>
 *静水载荷返回结果
 * </pre>
 *
 * Protobuf type {@code com.iscas.biz.calculation.grpc.StaticLoadResponse}
 */
public  final class StaticLoadResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.StaticLoadResponse)
    StaticLoadResponseOrBuilder {
  // Use StaticLoadResponse.newBuilder() to construct.
  private StaticLoadResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StaticLoadResponse() {
    nvec_ = java.util.Collections.emptyList();
    mvec_ = java.util.Collections.emptyList();
    nvecM_ = java.util.Collections.emptyList();
    mvecM_ = java.util.Collections.emptyList();
    code_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private StaticLoadResponse(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              nvec_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000001;
            }
            nvec_.add(input.readDouble());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
              nvec_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              nvec_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 17: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              mvec_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000002;
            }
            mvec_.add(input.readDouble());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
              mvec_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              mvec_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 25: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              nvecM_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000004;
            }
            nvecM_.add(input.readDouble());
            break;
          }
          case 26: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
              nvecM_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000004;
            }
            while (input.getBytesUntilLimit() > 0) {
              nvecM_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 33: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              mvecM_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000008;
            }
            mvecM_.add(input.readDouble());
            break;
          }
          case 34: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008) && input.getBytesUntilLimit() > 0) {
              mvecM_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000008;
            }
            while (input.getBytesUntilLimit() > 0) {
              mvecM_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 40: {

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
        nvec_ = java.util.Collections.unmodifiableList(nvec_);
      }
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        mvec_ = java.util.Collections.unmodifiableList(mvec_);
      }
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        nvecM_ = java.util.Collections.unmodifiableList(nvecM_);
      }
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        mvecM_ = java.util.Collections.unmodifiableList(mvecM_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_StaticLoadResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_StaticLoadResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.StaticLoadResponse.class, com.iscas.biz.calculation.grpc.StaticLoadResponse.Builder.class);
  }

  private int bitField0_;
  public static final int NVEC_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Double> nvec_;
  /**
   * <pre>
   *未修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvec = 1;</code>
   */
  public java.util.List<java.lang.Double>
      getNvecList() {
    return nvec_;
  }
  /**
   * <pre>
   *未修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvec = 1;</code>
   */
  public int getNvecCount() {
    return nvec_.size();
  }
  /**
   * <pre>
   *未修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvec = 1;</code>
   */
  public double getNvec(int index) {
    return nvec_.get(index);
  }
  private int nvecMemoizedSerializedSize = -1;

  public static final int MVEC_FIELD_NUMBER = 2;
  private java.util.List<java.lang.Double> mvec_;
  /**
   * <pre>
   *  未修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvec = 2;</code>
   */
  public java.util.List<java.lang.Double>
      getMvecList() {
    return mvec_;
  }
  /**
   * <pre>
   *  未修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvec = 2;</code>
   */
  public int getMvecCount() {
    return mvec_.size();
  }
  /**
   * <pre>
   *  未修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvec = 2;</code>
   */
  public double getMvec(int index) {
    return mvec_.get(index);
  }
  private int mvecMemoizedSerializedSize = -1;

  public static final int NVECM_FIELD_NUMBER = 3;
  private java.util.List<java.lang.Double> nvecM_;
  /**
   * <pre>
   *修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvecM = 3;</code>
   */
  public java.util.List<java.lang.Double>
      getNvecMList() {
    return nvecM_;
  }
  /**
   * <pre>
   *修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvecM = 3;</code>
   */
  public int getNvecMCount() {
    return nvecM_.size();
  }
  /**
   * <pre>
   *修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvecM = 3;</code>
   */
  public double getNvecM(int index) {
    return nvecM_.get(index);
  }
  private int nvecMMemoizedSerializedSize = -1;

  public static final int MVECM_FIELD_NUMBER = 4;
  private java.util.List<java.lang.Double> mvecM_;
  /**
   * <pre>
   *  修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvecM = 4;</code>
   */
  public java.util.List<java.lang.Double>
      getMvecMList() {
    return mvecM_;
  }
  /**
   * <pre>
   *  修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvecM = 4;</code>
   */
  public int getMvecMCount() {
    return mvecM_.size();
  }
  /**
   * <pre>
   *  修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvecM = 4;</code>
   */
  public double getMvecM(int index) {
    return mvecM_.get(index);
  }
  private int mvecMMemoizedSerializedSize = -1;

  public static final int CODE_FIELD_NUMBER = 5;
  private int code_;
  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 5;</code>
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
    getSerializedSize();
    if (getNvecList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(nvecMemoizedSerializedSize);
    }
    for (int i = 0; i < nvec_.size(); i++) {
      output.writeDoubleNoTag(nvec_.get(i));
    }
    if (getMvecList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(mvecMemoizedSerializedSize);
    }
    for (int i = 0; i < mvec_.size(); i++) {
      output.writeDoubleNoTag(mvec_.get(i));
    }
    if (getNvecMList().size() > 0) {
      output.writeUInt32NoTag(26);
      output.writeUInt32NoTag(nvecMMemoizedSerializedSize);
    }
    for (int i = 0; i < nvecM_.size(); i++) {
      output.writeDoubleNoTag(nvecM_.get(i));
    }
    if (getMvecMList().size() > 0) {
      output.writeUInt32NoTag(34);
      output.writeUInt32NoTag(mvecMMemoizedSerializedSize);
    }
    for (int i = 0; i < mvecM_.size(); i++) {
      output.writeDoubleNoTag(mvecM_.get(i));
    }
    if (code_ != 0) {
      output.writeInt32(5, code_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      dataSize = 8 * getNvecList().size();
      size += dataSize;
      if (!getNvecList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      nvecMemoizedSerializedSize = dataSize;
    }
    {
      int dataSize = 0;
      dataSize = 8 * getMvecList().size();
      size += dataSize;
      if (!getMvecList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      mvecMemoizedSerializedSize = dataSize;
    }
    {
      int dataSize = 0;
      dataSize = 8 * getNvecMList().size();
      size += dataSize;
      if (!getNvecMList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      nvecMMemoizedSerializedSize = dataSize;
    }
    {
      int dataSize = 0;
      dataSize = 8 * getMvecMList().size();
      size += dataSize;
      if (!getMvecMList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      mvecMMemoizedSerializedSize = dataSize;
    }
    if (code_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, code_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.StaticLoadResponse)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.StaticLoadResponse other = (com.iscas.biz.calculation.grpc.StaticLoadResponse) obj;

    boolean result = true;
    result = result && getNvecList()
        .equals(other.getNvecList());
    result = result && getMvecList()
        .equals(other.getMvecList());
    result = result && getNvecMList()
        .equals(other.getNvecMList());
    result = result && getMvecMList()
        .equals(other.getMvecMList());
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
    if (getNvecCount() > 0) {
      hash = (37 * hash) + NVEC_FIELD_NUMBER;
      hash = (53 * hash) + getNvecList().hashCode();
    }
    if (getMvecCount() > 0) {
      hash = (37 * hash) + MVEC_FIELD_NUMBER;
      hash = (53 * hash) + getMvecList().hashCode();
    }
    if (getNvecMCount() > 0) {
      hash = (37 * hash) + NVECM_FIELD_NUMBER;
      hash = (53 * hash) + getNvecMList().hashCode();
    }
    if (getMvecMCount() > 0) {
      hash = (37 * hash) + MVECM_FIELD_NUMBER;
      hash = (53 * hash) + getMvecMList().hashCode();
    }
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + getCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.StaticLoadResponse parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.StaticLoadResponse prototype) {
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
   *静水载荷返回结果
   * </pre>
   *
   * Protobuf type {@code com.iscas.biz.calculation.grpc.StaticLoadResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.StaticLoadResponse)
      com.iscas.biz.calculation.grpc.StaticLoadResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_StaticLoadResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_StaticLoadResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.StaticLoadResponse.class, com.iscas.biz.calculation.grpc.StaticLoadResponse.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.StaticLoadResponse.newBuilder()
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
      nvec_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      mvec_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      nvecM_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      mvecM_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      code_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_StaticLoadResponse_descriptor;
    }

    public com.iscas.biz.calculation.grpc.StaticLoadResponse getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.StaticLoadResponse.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.StaticLoadResponse build() {
      com.iscas.biz.calculation.grpc.StaticLoadResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.StaticLoadResponse buildPartial() {
      com.iscas.biz.calculation.grpc.StaticLoadResponse result = new com.iscas.biz.calculation.grpc.StaticLoadResponse(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        nvec_ = java.util.Collections.unmodifiableList(nvec_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.nvec_ = nvec_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        mvec_ = java.util.Collections.unmodifiableList(mvec_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.mvec_ = mvec_;
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        nvecM_ = java.util.Collections.unmodifiableList(nvecM_);
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.nvecM_ = nvecM_;
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        mvecM_ = java.util.Collections.unmodifiableList(mvecM_);
        bitField0_ = (bitField0_ & ~0x00000008);
      }
      result.mvecM_ = mvecM_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.StaticLoadResponse) {
        return mergeFrom((com.iscas.biz.calculation.grpc.StaticLoadResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.StaticLoadResponse other) {
      if (other == com.iscas.biz.calculation.grpc.StaticLoadResponse.getDefaultInstance()) return this;
      if (!other.nvec_.isEmpty()) {
        if (nvec_.isEmpty()) {
          nvec_ = other.nvec_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureNvecIsMutable();
          nvec_.addAll(other.nvec_);
        }
        onChanged();
      }
      if (!other.mvec_.isEmpty()) {
        if (mvec_.isEmpty()) {
          mvec_ = other.mvec_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureMvecIsMutable();
          mvec_.addAll(other.mvec_);
        }
        onChanged();
      }
      if (!other.nvecM_.isEmpty()) {
        if (nvecM_.isEmpty()) {
          nvecM_ = other.nvecM_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensureNvecMIsMutable();
          nvecM_.addAll(other.nvecM_);
        }
        onChanged();
      }
      if (!other.mvecM_.isEmpty()) {
        if (mvecM_.isEmpty()) {
          mvecM_ = other.mvecM_;
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          ensureMvecMIsMutable();
          mvecM_.addAll(other.mvecM_);
        }
        onChanged();
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
      com.iscas.biz.calculation.grpc.StaticLoadResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.StaticLoadResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Double> nvec_ = java.util.Collections.emptyList();
    private void ensureNvecIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        nvec_ = new java.util.ArrayList<java.lang.Double>(nvec_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public java.util.List<java.lang.Double>
        getNvecList() {
      return java.util.Collections.unmodifiableList(nvec_);
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public int getNvecCount() {
      return nvec_.size();
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public double getNvec(int index) {
      return nvec_.get(index);
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public Builder setNvec(
        int index, double value) {
      ensureNvecIsMutable();
      nvec_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public Builder addNvec(double value) {
      ensureNvecIsMutable();
      nvec_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public Builder addAllNvec(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureNvecIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, nvec_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *未修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvec = 1;</code>
     */
    public Builder clearNvec() {
      nvec_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> mvec_ = java.util.Collections.emptyList();
    private void ensureMvecIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        mvec_ = new java.util.ArrayList<java.lang.Double>(mvec_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public java.util.List<java.lang.Double>
        getMvecList() {
      return java.util.Collections.unmodifiableList(mvec_);
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public int getMvecCount() {
      return mvec_.size();
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public double getMvec(int index) {
      return mvec_.get(index);
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public Builder setMvec(
        int index, double value) {
      ensureMvecIsMutable();
      mvec_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public Builder addMvec(double value) {
      ensureMvecIsMutable();
      mvec_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public Builder addAllMvec(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureMvecIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, mvec_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  未修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvec = 2;</code>
     */
    public Builder clearMvec() {
      mvec_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> nvecM_ = java.util.Collections.emptyList();
    private void ensureNvecMIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        nvecM_ = new java.util.ArrayList<java.lang.Double>(nvecM_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public java.util.List<java.lang.Double>
        getNvecMList() {
      return java.util.Collections.unmodifiableList(nvecM_);
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public int getNvecMCount() {
      return nvecM_.size();
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public double getNvecM(int index) {
      return nvecM_.get(index);
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public Builder setNvecM(
        int index, double value) {
      ensureNvecMIsMutable();
      nvecM_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public Builder addNvecM(double value) {
      ensureNvecMIsMutable();
      nvecM_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public Builder addAllNvecM(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureNvecMIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, nvecM_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *修正的静水剪力 目前返回21个
     * </pre>
     *
     * <code>repeated double nvecM = 3;</code>
     */
    public Builder clearNvecM() {
      nvecM_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> mvecM_ = java.util.Collections.emptyList();
    private void ensureMvecMIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        mvecM_ = new java.util.ArrayList<java.lang.Double>(mvecM_);
        bitField0_ |= 0x00000008;
       }
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public java.util.List<java.lang.Double>
        getMvecMList() {
      return java.util.Collections.unmodifiableList(mvecM_);
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public int getMvecMCount() {
      return mvecM_.size();
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public double getMvecM(int index) {
      return mvecM_.get(index);
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public Builder setMvecM(
        int index, double value) {
      ensureMvecMIsMutable();
      mvecM_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public Builder addMvecM(double value) {
      ensureMvecMIsMutable();
      mvecM_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public Builder addAllMvecM(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureMvecMIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, mvecM_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  修正的弯矩 目前返回21个
     * </pre>
     *
     * <code>repeated double mvecM = 4;</code>
     */
    public Builder clearMvecM() {
      mvecM_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }

    private int code_ ;
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 5;</code>
     */
    public int getCode() {
      return code_;
    }
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 5;</code>
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
     * <code>int32 code = 5;</code>
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.StaticLoadResponse)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.StaticLoadResponse)
  private static final com.iscas.biz.calculation.grpc.StaticLoadResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.StaticLoadResponse();
  }

  public static com.iscas.biz.calculation.grpc.StaticLoadResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StaticLoadResponse>
      PARSER = new com.google.protobuf.AbstractParser<StaticLoadResponse>() {
    public StaticLoadResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new StaticLoadResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StaticLoadResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StaticLoadResponse> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.StaticLoadResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

