// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma2Request}
 */
public  final class Sigma2Request extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.Sigma2Request)
    Sigma2RequestOrBuilder {
  // Use Sigma2Request.newBuilder() to construct.
  private Sigma2Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Sigma2Request() {
    kuaChang_ = java.util.Collections.emptyList();
    girderDistance_ = 0D;
    frDistance_ = 0D;
    frGuige_ = java.util.Collections.emptyList();
    plateThick_ = java.util.Collections.emptyList();
    deviceWeight_ = 0D;
    girderWidth_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Sigma2Request(
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
              kuaChang_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000001;
            }
            kuaChang_.add(input.readDouble());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
              kuaChang_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              kuaChang_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 17: {

            girderDistance_ = input.readDouble();
            break;
          }
          case 25: {

            frDistance_ = input.readDouble();
            break;
          }
          case 33: {
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
              frGuige_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000008;
            }
            frGuige_.add(input.readDouble());
            break;
          }
          case 34: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000008) == 0x00000008) && input.getBytesUntilLimit() > 0) {
              frGuige_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000008;
            }
            while (input.getBytesUntilLimit() > 0) {
              frGuige_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 41: {
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
              plateThick_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000010;
            }
            plateThick_.add(input.readDouble());
            break;
          }
          case 42: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000010) == 0x00000010) && input.getBytesUntilLimit() > 0) {
              plateThick_ = new java.util.ArrayList<java.lang.Double>();
              mutable_bitField0_ |= 0x00000010;
            }
            while (input.getBytesUntilLimit() > 0) {
              plateThick_.add(input.readDouble());
            }
            input.popLimit(limit);
            break;
          }
          case 49: {

            deviceWeight_ = input.readDouble();
            break;
          }
          case 57: {

            girderWidth_ = input.readDouble();
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
        kuaChang_ = java.util.Collections.unmodifiableList(kuaChang_);
      }
      if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
        frGuige_ = java.util.Collections.unmodifiableList(frGuige_);
      }
      if (((mutable_bitField0_ & 0x00000010) == 0x00000010)) {
        plateThick_ = java.util.Collections.unmodifiableList(plateThick_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma2Request_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma2Request_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.Sigma2Request.class, com.iscas.biz.calculation.grpc.Sigma2Request.Builder.class);
  }

  private int bitField0_;
  public static final int KUACHANG_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Double> kuaChang_;
  /**
   * <pre>
   *构件跨距-龙骨跨距 每个的跨距
   * </pre>
   *
   * <code>repeated double kuaChang = 1;</code>
   */
  public java.util.List<java.lang.Double>
      getKuaChangList() {
    return kuaChang_;
  }
  /**
   * <pre>
   *构件跨距-龙骨跨距 每个的跨距
   * </pre>
   *
   * <code>repeated double kuaChang = 1;</code>
   */
  public int getKuaChangCount() {
    return kuaChang_.size();
  }
  /**
   * <pre>
   *构件跨距-龙骨跨距 每个的跨距
   * </pre>
   *
   * <code>repeated double kuaChang = 1;</code>
   */
  public double getKuaChang(int index) {
    return kuaChang_.get(index);
  }
  private int kuaChangMemoizedSerializedSize = -1;

  public static final int GIRDERDISTANCE_FIELD_NUMBER = 2;
  private double girderDistance_;
  /**
   * <pre>
   *剖面位置x-衡量间距
   * </pre>
   *
   * <code>double girderDistance = 2;</code>
   */
  public double getGirderDistance() {
    return girderDistance_;
  }

  public static final int FRDISTANCE_FIELD_NUMBER = 3;
  private double frDistance_;
  /**
   * <pre>
   *纵骨间距
   * </pre>
   *
   * <code>double frDistance = 3;</code>
   */
  public double getFrDistance() {
    return frDistance_;
  }

  public static final int FRGUIGE_FIELD_NUMBER = 4;
  private java.util.List<java.lang.Double> frGuige_;
  /**
   * <pre>
   *纵骨规格 每个的规格
   * </pre>
   *
   * <code>repeated double frGuige = 4;</code>
   */
  public java.util.List<java.lang.Double>
      getFrGuigeList() {
    return frGuige_;
  }
  /**
   * <pre>
   *纵骨规格 每个的规格
   * </pre>
   *
   * <code>repeated double frGuige = 4;</code>
   */
  public int getFrGuigeCount() {
    return frGuige_.size();
  }
  /**
   * <pre>
   *纵骨规格 每个的规格
   * </pre>
   *
   * <code>repeated double frGuige = 4;</code>
   */
  public double getFrGuige(int index) {
    return frGuige_.get(index);
  }
  private int frGuigeMemoizedSerializedSize = -1;

  public static final int PLATETHICK_FIELD_NUMBER = 5;
  private java.util.List<java.lang.Double> plateThick_;
  /**
   * <pre>
   *板各厚度 每个板材的厚度
   * </pre>
   *
   * <code>repeated double plateThick = 5;</code>
   */
  public java.util.List<java.lang.Double>
      getPlateThickList() {
    return plateThick_;
  }
  /**
   * <pre>
   *板各厚度 每个板材的厚度
   * </pre>
   *
   * <code>repeated double plateThick = 5;</code>
   */
  public int getPlateThickCount() {
    return plateThick_.size();
  }
  /**
   * <pre>
   *板各厚度 每个板材的厚度
   * </pre>
   *
   * <code>repeated double plateThick = 5;</code>
   */
  public double getPlateThick(int index) {
    return plateThick_.get(index);
  }
  private int plateThickMemoizedSerializedSize = -1;

  public static final int DEVICEWEIGHT_FIELD_NUMBER = 6;
  private double deviceWeight_;
  /**
   * <pre>
   *设备重量(t)
   * </pre>
   *
   * <code>double deviceWeight = 6;</code>
   */
  public double getDeviceWeight() {
    return deviceWeight_;
  }

  public static final int GIRDERWIDTH_FIELD_NUMBER = 7;
  private double girderWidth_;
  /**
   * <pre>
   *版格宽度
   * </pre>
   *
   * <code>double girderWidth = 7;</code>
   */
  public double getGirderWidth() {
    return girderWidth_;
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
    if (getKuaChangList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(kuaChangMemoizedSerializedSize);
    }
    for (int i = 0; i < kuaChang_.size(); i++) {
      output.writeDoubleNoTag(kuaChang_.get(i));
    }
    if (girderDistance_ != 0D) {
      output.writeDouble(2, girderDistance_);
    }
    if (frDistance_ != 0D) {
      output.writeDouble(3, frDistance_);
    }
    if (getFrGuigeList().size() > 0) {
      output.writeUInt32NoTag(34);
      output.writeUInt32NoTag(frGuigeMemoizedSerializedSize);
    }
    for (int i = 0; i < frGuige_.size(); i++) {
      output.writeDoubleNoTag(frGuige_.get(i));
    }
    if (getPlateThickList().size() > 0) {
      output.writeUInt32NoTag(42);
      output.writeUInt32NoTag(plateThickMemoizedSerializedSize);
    }
    for (int i = 0; i < plateThick_.size(); i++) {
      output.writeDoubleNoTag(plateThick_.get(i));
    }
    if (deviceWeight_ != 0D) {
      output.writeDouble(6, deviceWeight_);
    }
    if (girderWidth_ != 0D) {
      output.writeDouble(7, girderWidth_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      dataSize = 8 * getKuaChangList().size();
      size += dataSize;
      if (!getKuaChangList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      kuaChangMemoizedSerializedSize = dataSize;
    }
    if (girderDistance_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, girderDistance_);
    }
    if (frDistance_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, frDistance_);
    }
    {
      int dataSize = 0;
      dataSize = 8 * getFrGuigeList().size();
      size += dataSize;
      if (!getFrGuigeList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      frGuigeMemoizedSerializedSize = dataSize;
    }
    {
      int dataSize = 0;
      dataSize = 8 * getPlateThickList().size();
      size += dataSize;
      if (!getPlateThickList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      plateThickMemoizedSerializedSize = dataSize;
    }
    if (deviceWeight_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(6, deviceWeight_);
    }
    if (girderWidth_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(7, girderWidth_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.Sigma2Request)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.Sigma2Request other = (com.iscas.biz.calculation.grpc.Sigma2Request) obj;

    boolean result = true;
    result = result && getKuaChangList()
        .equals(other.getKuaChangList());
    result = result && (
        java.lang.Double.doubleToLongBits(getGirderDistance())
        == java.lang.Double.doubleToLongBits(
            other.getGirderDistance()));
    result = result && (
        java.lang.Double.doubleToLongBits(getFrDistance())
        == java.lang.Double.doubleToLongBits(
            other.getFrDistance()));
    result = result && getFrGuigeList()
        .equals(other.getFrGuigeList());
    result = result && getPlateThickList()
        .equals(other.getPlateThickList());
    result = result && (
        java.lang.Double.doubleToLongBits(getDeviceWeight())
        == java.lang.Double.doubleToLongBits(
            other.getDeviceWeight()));
    result = result && (
        java.lang.Double.doubleToLongBits(getGirderWidth())
        == java.lang.Double.doubleToLongBits(
            other.getGirderWidth()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getKuaChangCount() > 0) {
      hash = (37 * hash) + KUACHANG_FIELD_NUMBER;
      hash = (53 * hash) + getKuaChangList().hashCode();
    }
    hash = (37 * hash) + GIRDERDISTANCE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getGirderDistance()));
    hash = (37 * hash) + FRDISTANCE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getFrDistance()));
    if (getFrGuigeCount() > 0) {
      hash = (37 * hash) + FRGUIGE_FIELD_NUMBER;
      hash = (53 * hash) + getFrGuigeList().hashCode();
    }
    if (getPlateThickCount() > 0) {
      hash = (37 * hash) + PLATETHICK_FIELD_NUMBER;
      hash = (53 * hash) + getPlateThickList().hashCode();
    }
    hash = (37 * hash) + DEVICEWEIGHT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDeviceWeight()));
    hash = (37 * hash) + GIRDERWIDTH_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getGirderWidth()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.Sigma2Request parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.Sigma2Request prototype) {
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
   * Protobuf type {@code com.iscas.biz.calculation.grpc.Sigma2Request}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.Sigma2Request)
      com.iscas.biz.calculation.grpc.Sigma2RequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma2Request_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma2Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.Sigma2Request.class, com.iscas.biz.calculation.grpc.Sigma2Request.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.Sigma2Request.newBuilder()
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
      kuaChang_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      girderDistance_ = 0D;

      frDistance_ = 0D;

      frGuige_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      plateThick_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000010);
      deviceWeight_ = 0D;

      girderWidth_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_Sigma2Request_descriptor;
    }

    public com.iscas.biz.calculation.grpc.Sigma2Request getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.Sigma2Request.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.Sigma2Request build() {
      com.iscas.biz.calculation.grpc.Sigma2Request result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.Sigma2Request buildPartial() {
      com.iscas.biz.calculation.grpc.Sigma2Request result = new com.iscas.biz.calculation.grpc.Sigma2Request(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        kuaChang_ = java.util.Collections.unmodifiableList(kuaChang_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.kuaChang_ = kuaChang_;
      result.girderDistance_ = girderDistance_;
      result.frDistance_ = frDistance_;
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        frGuige_ = java.util.Collections.unmodifiableList(frGuige_);
        bitField0_ = (bitField0_ & ~0x00000008);
      }
      result.frGuige_ = frGuige_;
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        plateThick_ = java.util.Collections.unmodifiableList(plateThick_);
        bitField0_ = (bitField0_ & ~0x00000010);
      }
      result.plateThick_ = plateThick_;
      result.deviceWeight_ = deviceWeight_;
      result.girderWidth_ = girderWidth_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.Sigma2Request) {
        return mergeFrom((com.iscas.biz.calculation.grpc.Sigma2Request)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.Sigma2Request other) {
      if (other == com.iscas.biz.calculation.grpc.Sigma2Request.getDefaultInstance()) return this;
      if (!other.kuaChang_.isEmpty()) {
        if (kuaChang_.isEmpty()) {
          kuaChang_ = other.kuaChang_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureKuaChangIsMutable();
          kuaChang_.addAll(other.kuaChang_);
        }
        onChanged();
      }
      if (other.getGirderDistance() != 0D) {
        setGirderDistance(other.getGirderDistance());
      }
      if (other.getFrDistance() != 0D) {
        setFrDistance(other.getFrDistance());
      }
      if (!other.frGuige_.isEmpty()) {
        if (frGuige_.isEmpty()) {
          frGuige_ = other.frGuige_;
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          ensureFrGuigeIsMutable();
          frGuige_.addAll(other.frGuige_);
        }
        onChanged();
      }
      if (!other.plateThick_.isEmpty()) {
        if (plateThick_.isEmpty()) {
          plateThick_ = other.plateThick_;
          bitField0_ = (bitField0_ & ~0x00000010);
        } else {
          ensurePlateThickIsMutable();
          plateThick_.addAll(other.plateThick_);
        }
        onChanged();
      }
      if (other.getDeviceWeight() != 0D) {
        setDeviceWeight(other.getDeviceWeight());
      }
      if (other.getGirderWidth() != 0D) {
        setGirderWidth(other.getGirderWidth());
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
      com.iscas.biz.calculation.grpc.Sigma2Request parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.Sigma2Request) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Double> kuaChang_ = java.util.Collections.emptyList();
    private void ensureKuaChangIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        kuaChang_ = new java.util.ArrayList<java.lang.Double>(kuaChang_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public java.util.List<java.lang.Double>
        getKuaChangList() {
      return java.util.Collections.unmodifiableList(kuaChang_);
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public int getKuaChangCount() {
      return kuaChang_.size();
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public double getKuaChang(int index) {
      return kuaChang_.get(index);
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public Builder setKuaChang(
        int index, double value) {
      ensureKuaChangIsMutable();
      kuaChang_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public Builder addKuaChang(double value) {
      ensureKuaChangIsMutable();
      kuaChang_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public Builder addAllKuaChang(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureKuaChangIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, kuaChang_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *构件跨距-龙骨跨距 每个的跨距
     * </pre>
     *
     * <code>repeated double kuaChang = 1;</code>
     */
    public Builder clearKuaChang() {
      kuaChang_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private double girderDistance_ ;
    /**
     * <pre>
     *剖面位置x-衡量间距
     * </pre>
     *
     * <code>double girderDistance = 2;</code>
     */
    public double getGirderDistance() {
      return girderDistance_;
    }
    /**
     * <pre>
     *剖面位置x-衡量间距
     * </pre>
     *
     * <code>double girderDistance = 2;</code>
     */
    public Builder setGirderDistance(double value) {
      
      girderDistance_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *剖面位置x-衡量间距
     * </pre>
     *
     * <code>double girderDistance = 2;</code>
     */
    public Builder clearGirderDistance() {
      
      girderDistance_ = 0D;
      onChanged();
      return this;
    }

    private double frDistance_ ;
    /**
     * <pre>
     *纵骨间距
     * </pre>
     *
     * <code>double frDistance = 3;</code>
     */
    public double getFrDistance() {
      return frDistance_;
    }
    /**
     * <pre>
     *纵骨间距
     * </pre>
     *
     * <code>double frDistance = 3;</code>
     */
    public Builder setFrDistance(double value) {
      
      frDistance_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *纵骨间距
     * </pre>
     *
     * <code>double frDistance = 3;</code>
     */
    public Builder clearFrDistance() {
      
      frDistance_ = 0D;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> frGuige_ = java.util.Collections.emptyList();
    private void ensureFrGuigeIsMutable() {
      if (!((bitField0_ & 0x00000008) == 0x00000008)) {
        frGuige_ = new java.util.ArrayList<java.lang.Double>(frGuige_);
        bitField0_ |= 0x00000008;
       }
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public java.util.List<java.lang.Double>
        getFrGuigeList() {
      return java.util.Collections.unmodifiableList(frGuige_);
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public int getFrGuigeCount() {
      return frGuige_.size();
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public double getFrGuige(int index) {
      return frGuige_.get(index);
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public Builder setFrGuige(
        int index, double value) {
      ensureFrGuigeIsMutable();
      frGuige_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public Builder addFrGuige(double value) {
      ensureFrGuigeIsMutable();
      frGuige_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public Builder addAllFrGuige(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensureFrGuigeIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, frGuige_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *纵骨规格 每个的规格
     * </pre>
     *
     * <code>repeated double frGuige = 4;</code>
     */
    public Builder clearFrGuige() {
      frGuige_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Double> plateThick_ = java.util.Collections.emptyList();
    private void ensurePlateThickIsMutable() {
      if (!((bitField0_ & 0x00000010) == 0x00000010)) {
        plateThick_ = new java.util.ArrayList<java.lang.Double>(plateThick_);
        bitField0_ |= 0x00000010;
       }
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public java.util.List<java.lang.Double>
        getPlateThickList() {
      return java.util.Collections.unmodifiableList(plateThick_);
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public int getPlateThickCount() {
      return plateThick_.size();
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public double getPlateThick(int index) {
      return plateThick_.get(index);
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public Builder setPlateThick(
        int index, double value) {
      ensurePlateThickIsMutable();
      plateThick_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public Builder addPlateThick(double value) {
      ensurePlateThickIsMutable();
      plateThick_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public Builder addAllPlateThick(
        java.lang.Iterable<? extends java.lang.Double> values) {
      ensurePlateThickIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, plateThick_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *板各厚度 每个板材的厚度
     * </pre>
     *
     * <code>repeated double plateThick = 5;</code>
     */
    public Builder clearPlateThick() {
      plateThick_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }

    private double deviceWeight_ ;
    /**
     * <pre>
     *设备重量(t)
     * </pre>
     *
     * <code>double deviceWeight = 6;</code>
     */
    public double getDeviceWeight() {
      return deviceWeight_;
    }
    /**
     * <pre>
     *设备重量(t)
     * </pre>
     *
     * <code>double deviceWeight = 6;</code>
     */
    public Builder setDeviceWeight(double value) {
      
      deviceWeight_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *设备重量(t)
     * </pre>
     *
     * <code>double deviceWeight = 6;</code>
     */
    public Builder clearDeviceWeight() {
      
      deviceWeight_ = 0D;
      onChanged();
      return this;
    }

    private double girderWidth_ ;
    /**
     * <pre>
     *版格宽度
     * </pre>
     *
     * <code>double girderWidth = 7;</code>
     */
    public double getGirderWidth() {
      return girderWidth_;
    }
    /**
     * <pre>
     *版格宽度
     * </pre>
     *
     * <code>double girderWidth = 7;</code>
     */
    public Builder setGirderWidth(double value) {
      
      girderWidth_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *版格宽度
     * </pre>
     *
     * <code>double girderWidth = 7;</code>
     */
    public Builder clearGirderWidth() {
      
      girderWidth_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.Sigma2Request)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.Sigma2Request)
  private static final com.iscas.biz.calculation.grpc.Sigma2Request DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.Sigma2Request();
  }

  public static com.iscas.biz.calculation.grpc.Sigma2Request getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Sigma2Request>
      PARSER = new com.google.protobuf.AbstractParser<Sigma2Request>() {
    public Sigma2Request parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Sigma2Request(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Sigma2Request> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Sigma2Request> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.Sigma2Request getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
