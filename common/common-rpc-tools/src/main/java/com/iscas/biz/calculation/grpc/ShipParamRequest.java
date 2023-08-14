// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * <pre>
 *船舶参数请求
 * </pre>
 *
 * Protobuf type {@code com.iscas.biz.calculation.grpc.ShipParamRequest}
 */
public  final class ShipParamRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.ShipParamRequest)
    ShipParamRequestOrBuilder {
  // Use ShipParamRequest.newBuilder() to construct.
  private ShipParamRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ShipParamRequest() {
    lWl_ = 0D;
    width_ = 0D;
    depth_ = 0D;
    draught_ = 0D;
    area_ = 0;
    checkType_ = 0;
    displacement_ = 0D;
    portraitGravity_ = 0D;
    principle_ = 0;
    type_ = "";
    vmax_ = 0D;
    speed_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ShipParamRequest(
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

            lWl_ = input.readDouble();
            break;
          }
          case 17: {

            width_ = input.readDouble();
            break;
          }
          case 25: {

            depth_ = input.readDouble();
            break;
          }
          case 33: {

            draught_ = input.readDouble();
            break;
          }
          case 40: {

            area_ = input.readInt32();
            break;
          }
          case 48: {

            checkType_ = input.readInt32();
            break;
          }
          case 57: {

            displacement_ = input.readDouble();
            break;
          }
          case 65: {

            portraitGravity_ = input.readDouble();
            break;
          }
          case 80: {

            principle_ = input.readInt32();
            break;
          }
          case 90: {
            java.lang.String s = input.readStringRequireUtf8();

            type_ = s;
            break;
          }
          case 97: {

            vmax_ = input.readDouble();
            break;
          }
          case 105: {

            speed_ = input.readDouble();
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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShipParamRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShipParamRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.ShipParamRequest.class, com.iscas.biz.calculation.grpc.ShipParamRequest.Builder.class);
  }

  public static final int L_WL_FIELD_NUMBER = 1;
  private double lWl_;
  /**
   * <pre>
   *水线长
   * </pre>
   *
   * <code>double l_wl = 1;</code>
   */
  public double getLWl() {
    return lWl_;
  }

  public static final int WIDTH_FIELD_NUMBER = 2;
  private double width_;
  /**
   * <pre>
   *型宽
   * </pre>
   *
   * <code>double width = 2;</code>
   */
  public double getWidth() {
    return width_;
  }

  public static final int DEPTH_FIELD_NUMBER = 3;
  private double depth_;
  /**
   * <pre>
   *型深
   * </pre>
   *
   * <code>double depth = 3;</code>
   */
  public double getDepth() {
    return depth_;
  }

  public static final int DRAUGHT_FIELD_NUMBER = 4;
  private double draught_;
  /**
   * <pre>
   *吃水
   * </pre>
   *
   * <code>double draught = 4;</code>
   */
  public double getDraught() {
    return draught_;
  }

  public static final int AREA_FIELD_NUMBER = 5;
  private int area_;
  /**
   * <pre>
   *航行区域 0-无限 1-有限
   * </pre>
   *
   * <code>int32 area = 5;</code>
   */
  public int getArea() {
    return area_;
  }

  public static final int CHECKTYPE_FIELD_NUMBER = 6;
  private int checkType_;
  /**
   * <pre>
   *校验方式 0-极限 1-巡航
   * </pre>
   *
   * <code>int32 checkType = 6;</code>
   */
  public int getCheckType() {
    return checkType_;
  }

  public static final int DISPLACEMENT_FIELD_NUMBER = 7;
  private double displacement_;
  /**
   * <pre>
   *排水量
   * </pre>
   *
   * <code>double displacement = 7;</code>
   */
  public double getDisplacement() {
    return displacement_;
  }

  public static final int PORTRAITGRAVITY_FIELD_NUMBER = 8;
  private double portraitGravity_;
  /**
   * <pre>
   *重心纵向位置
   * </pre>
   *
   * <code>double portraitGravity = 8;</code>
   */
  public double getPortraitGravity() {
    return portraitGravity_;
  }

  public static final int PRINCIPLE_FIELD_NUMBER = 10;
  private int principle_;
  /**
   * <pre>
   *校核准则 0-通用 1-大船 2-DQ
   * </pre>
   *
   * <code>int32 principle = 10;</code>
   */
  public int getPrinciple() {
    return principle_;
  }

  public static final int TYPE_FIELD_NUMBER = 11;
  private volatile java.lang.Object type_;
  /**
   * <pre>
   *船舶类型 ZQJ HM
   * </pre>
   *
   * <code>string type = 11;</code>
   */
  public java.lang.String getType() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *船舶类型 ZQJ HM
   * </pre>
   *
   * <code>string type = 11;</code>
   */
  public com.google.protobuf.ByteString
      getTypeBytes() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VMAX_FIELD_NUMBER = 12;
  private double vmax_;
  /**
   * <pre>
   *静水中最大航数[增0728]
   * </pre>
   *
   * <code>double vmax = 12;</code>
   */
  public double getVmax() {
    return vmax_;
  }

  public static final int SPEED_FIELD_NUMBER = 13;
  private double speed_;
  /**
   * <pre>
   *航速[增0728]
   * </pre>
   *
   * <code>double speed = 13;</code>
   */
  public double getSpeed() {
    return speed_;
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
    if (lWl_ != 0D) {
      output.writeDouble(1, lWl_);
    }
    if (width_ != 0D) {
      output.writeDouble(2, width_);
    }
    if (depth_ != 0D) {
      output.writeDouble(3, depth_);
    }
    if (draught_ != 0D) {
      output.writeDouble(4, draught_);
    }
    if (area_ != 0) {
      output.writeInt32(5, area_);
    }
    if (checkType_ != 0) {
      output.writeInt32(6, checkType_);
    }
    if (displacement_ != 0D) {
      output.writeDouble(7, displacement_);
    }
    if (portraitGravity_ != 0D) {
      output.writeDouble(8, portraitGravity_);
    }
    if (principle_ != 0) {
      output.writeInt32(10, principle_);
    }
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 11, type_);
    }
    if (vmax_ != 0D) {
      output.writeDouble(12, vmax_);
    }
    if (speed_ != 0D) {
      output.writeDouble(13, speed_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (lWl_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, lWl_);
    }
    if (width_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, width_);
    }
    if (depth_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, depth_);
    }
    if (draught_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, draught_);
    }
    if (area_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, area_);
    }
    if (checkType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, checkType_);
    }
    if (displacement_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(7, displacement_);
    }
    if (portraitGravity_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(8, portraitGravity_);
    }
    if (principle_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, principle_);
    }
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(11, type_);
    }
    if (vmax_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(12, vmax_);
    }
    if (speed_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(13, speed_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.ShipParamRequest)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.ShipParamRequest other = (com.iscas.biz.calculation.grpc.ShipParamRequest) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getLWl())
        == java.lang.Double.doubleToLongBits(
            other.getLWl()));
    result = result && (
        java.lang.Double.doubleToLongBits(getWidth())
        == java.lang.Double.doubleToLongBits(
            other.getWidth()));
    result = result && (
        java.lang.Double.doubleToLongBits(getDepth())
        == java.lang.Double.doubleToLongBits(
            other.getDepth()));
    result = result && (
        java.lang.Double.doubleToLongBits(getDraught())
        == java.lang.Double.doubleToLongBits(
            other.getDraught()));
    result = result && (getArea()
        == other.getArea());
    result = result && (getCheckType()
        == other.getCheckType());
    result = result && (
        java.lang.Double.doubleToLongBits(getDisplacement())
        == java.lang.Double.doubleToLongBits(
            other.getDisplacement()));
    result = result && (
        java.lang.Double.doubleToLongBits(getPortraitGravity())
        == java.lang.Double.doubleToLongBits(
            other.getPortraitGravity()));
    result = result && (getPrinciple()
        == other.getPrinciple());
    result = result && getType()
        .equals(other.getType());
    result = result && (
        java.lang.Double.doubleToLongBits(getVmax())
        == java.lang.Double.doubleToLongBits(
            other.getVmax()));
    result = result && (
        java.lang.Double.doubleToLongBits(getSpeed())
        == java.lang.Double.doubleToLongBits(
            other.getSpeed()));
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + L_WL_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getLWl()));
    hash = (37 * hash) + WIDTH_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getWidth()));
    hash = (37 * hash) + DEPTH_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDepth()));
    hash = (37 * hash) + DRAUGHT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDraught()));
    hash = (37 * hash) + AREA_FIELD_NUMBER;
    hash = (53 * hash) + getArea();
    hash = (37 * hash) + CHECKTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getCheckType();
    hash = (37 * hash) + DISPLACEMENT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getDisplacement()));
    hash = (37 * hash) + PORTRAITGRAVITY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getPortraitGravity()));
    hash = (37 * hash) + PRINCIPLE_FIELD_NUMBER;
    hash = (53 * hash) + getPrinciple();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    hash = (37 * hash) + VMAX_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getVmax()));
    hash = (37 * hash) + SPEED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getSpeed()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.ShipParamRequest parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.ShipParamRequest prototype) {
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
   *船舶参数请求
   * </pre>
   *
   * Protobuf type {@code com.iscas.biz.calculation.grpc.ShipParamRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.ShipParamRequest)
      com.iscas.biz.calculation.grpc.ShipParamRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShipParamRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShipParamRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.ShipParamRequest.class, com.iscas.biz.calculation.grpc.ShipParamRequest.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.ShipParamRequest.newBuilder()
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
      lWl_ = 0D;

      width_ = 0D;

      depth_ = 0D;

      draught_ = 0D;

      area_ = 0;

      checkType_ = 0;

      displacement_ = 0D;

      portraitGravity_ = 0D;

      principle_ = 0;

      type_ = "";

      vmax_ = 0D;

      speed_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_ShipParamRequest_descriptor;
    }

    public com.iscas.biz.calculation.grpc.ShipParamRequest getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.ShipParamRequest.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.ShipParamRequest build() {
      com.iscas.biz.calculation.grpc.ShipParamRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.ShipParamRequest buildPartial() {
      com.iscas.biz.calculation.grpc.ShipParamRequest result = new com.iscas.biz.calculation.grpc.ShipParamRequest(this);
      result.lWl_ = lWl_;
      result.width_ = width_;
      result.depth_ = depth_;
      result.draught_ = draught_;
      result.area_ = area_;
      result.checkType_ = checkType_;
      result.displacement_ = displacement_;
      result.portraitGravity_ = portraitGravity_;
      result.principle_ = principle_;
      result.type_ = type_;
      result.vmax_ = vmax_;
      result.speed_ = speed_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.ShipParamRequest) {
        return mergeFrom((com.iscas.biz.calculation.grpc.ShipParamRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.ShipParamRequest other) {
      if (other == com.iscas.biz.calculation.grpc.ShipParamRequest.getDefaultInstance()) return this;
      if (other.getLWl() != 0D) {
        setLWl(other.getLWl());
      }
      if (other.getWidth() != 0D) {
        setWidth(other.getWidth());
      }
      if (other.getDepth() != 0D) {
        setDepth(other.getDepth());
      }
      if (other.getDraught() != 0D) {
        setDraught(other.getDraught());
      }
      if (other.getArea() != 0) {
        setArea(other.getArea());
      }
      if (other.getCheckType() != 0) {
        setCheckType(other.getCheckType());
      }
      if (other.getDisplacement() != 0D) {
        setDisplacement(other.getDisplacement());
      }
      if (other.getPortraitGravity() != 0D) {
        setPortraitGravity(other.getPortraitGravity());
      }
      if (other.getPrinciple() != 0) {
        setPrinciple(other.getPrinciple());
      }
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
      if (other.getVmax() != 0D) {
        setVmax(other.getVmax());
      }
      if (other.getSpeed() != 0D) {
        setSpeed(other.getSpeed());
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
      com.iscas.biz.calculation.grpc.ShipParamRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.ShipParamRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double lWl_ ;
    /**
     * <pre>
     *水线长
     * </pre>
     *
     * <code>double l_wl = 1;</code>
     */
    public double getLWl() {
      return lWl_;
    }
    /**
     * <pre>
     *水线长
     * </pre>
     *
     * <code>double l_wl = 1;</code>
     */
    public Builder setLWl(double value) {
      
      lWl_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *水线长
     * </pre>
     *
     * <code>double l_wl = 1;</code>
     */
    public Builder clearLWl() {
      
      lWl_ = 0D;
      onChanged();
      return this;
    }

    private double width_ ;
    /**
     * <pre>
     *型宽
     * </pre>
     *
     * <code>double width = 2;</code>
     */
    public double getWidth() {
      return width_;
    }
    /**
     * <pre>
     *型宽
     * </pre>
     *
     * <code>double width = 2;</code>
     */
    public Builder setWidth(double value) {
      
      width_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *型宽
     * </pre>
     *
     * <code>double width = 2;</code>
     */
    public Builder clearWidth() {
      
      width_ = 0D;
      onChanged();
      return this;
    }

    private double depth_ ;
    /**
     * <pre>
     *型深
     * </pre>
     *
     * <code>double depth = 3;</code>
     */
    public double getDepth() {
      return depth_;
    }
    /**
     * <pre>
     *型深
     * </pre>
     *
     * <code>double depth = 3;</code>
     */
    public Builder setDepth(double value) {
      
      depth_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *型深
     * </pre>
     *
     * <code>double depth = 3;</code>
     */
    public Builder clearDepth() {
      
      depth_ = 0D;
      onChanged();
      return this;
    }

    private double draught_ ;
    /**
     * <pre>
     *吃水
     * </pre>
     *
     * <code>double draught = 4;</code>
     */
    public double getDraught() {
      return draught_;
    }
    /**
     * <pre>
     *吃水
     * </pre>
     *
     * <code>double draught = 4;</code>
     */
    public Builder setDraught(double value) {
      
      draught_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *吃水
     * </pre>
     *
     * <code>double draught = 4;</code>
     */
    public Builder clearDraught() {
      
      draught_ = 0D;
      onChanged();
      return this;
    }

    private int area_ ;
    /**
     * <pre>
     *航行区域 0-无限 1-有限
     * </pre>
     *
     * <code>int32 area = 5;</code>
     */
    public int getArea() {
      return area_;
    }
    /**
     * <pre>
     *航行区域 0-无限 1-有限
     * </pre>
     *
     * <code>int32 area = 5;</code>
     */
    public Builder setArea(int value) {
      
      area_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *航行区域 0-无限 1-有限
     * </pre>
     *
     * <code>int32 area = 5;</code>
     */
    public Builder clearArea() {
      
      area_ = 0;
      onChanged();
      return this;
    }

    private int checkType_ ;
    /**
     * <pre>
     *校验方式 0-极限 1-巡航
     * </pre>
     *
     * <code>int32 checkType = 6;</code>
     */
    public int getCheckType() {
      return checkType_;
    }
    /**
     * <pre>
     *校验方式 0-极限 1-巡航
     * </pre>
     *
     * <code>int32 checkType = 6;</code>
     */
    public Builder setCheckType(int value) {
      
      checkType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *校验方式 0-极限 1-巡航
     * </pre>
     *
     * <code>int32 checkType = 6;</code>
     */
    public Builder clearCheckType() {
      
      checkType_ = 0;
      onChanged();
      return this;
    }

    private double displacement_ ;
    /**
     * <pre>
     *排水量
     * </pre>
     *
     * <code>double displacement = 7;</code>
     */
    public double getDisplacement() {
      return displacement_;
    }
    /**
     * <pre>
     *排水量
     * </pre>
     *
     * <code>double displacement = 7;</code>
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
     * <code>double displacement = 7;</code>
     */
    public Builder clearDisplacement() {
      
      displacement_ = 0D;
      onChanged();
      return this;
    }

    private double portraitGravity_ ;
    /**
     * <pre>
     *重心纵向位置
     * </pre>
     *
     * <code>double portraitGravity = 8;</code>
     */
    public double getPortraitGravity() {
      return portraitGravity_;
    }
    /**
     * <pre>
     *重心纵向位置
     * </pre>
     *
     * <code>double portraitGravity = 8;</code>
     */
    public Builder setPortraitGravity(double value) {
      
      portraitGravity_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *重心纵向位置
     * </pre>
     *
     * <code>double portraitGravity = 8;</code>
     */
    public Builder clearPortraitGravity() {
      
      portraitGravity_ = 0D;
      onChanged();
      return this;
    }

    private int principle_ ;
    /**
     * <pre>
     *校核准则 0-通用 1-大船 2-DQ
     * </pre>
     *
     * <code>int32 principle = 10;</code>
     */
    public int getPrinciple() {
      return principle_;
    }
    /**
     * <pre>
     *校核准则 0-通用 1-大船 2-DQ
     * </pre>
     *
     * <code>int32 principle = 10;</code>
     */
    public Builder setPrinciple(int value) {
      
      principle_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *校核准则 0-通用 1-大船 2-DQ
     * </pre>
     *
     * <code>int32 principle = 10;</code>
     */
    public Builder clearPrinciple() {
      
      principle_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object type_ = "";
    /**
     * <pre>
     *船舶类型 ZQJ HM
     * </pre>
     *
     * <code>string type = 11;</code>
     */
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *船舶类型 ZQJ HM
     * </pre>
     *
     * <code>string type = 11;</code>
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *船舶类型 ZQJ HM
     * </pre>
     *
     * <code>string type = 11;</code>
     */
    public Builder setType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *船舶类型 ZQJ HM
     * </pre>
     *
     * <code>string type = 11;</code>
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *船舶类型 ZQJ HM
     * </pre>
     *
     * <code>string type = 11;</code>
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
    }

    private double vmax_ ;
    /**
     * <pre>
     *静水中最大航数[增0728]
     * </pre>
     *
     * <code>double vmax = 12;</code>
     */
    public double getVmax() {
      return vmax_;
    }
    /**
     * <pre>
     *静水中最大航数[增0728]
     * </pre>
     *
     * <code>double vmax = 12;</code>
     */
    public Builder setVmax(double value) {
      
      vmax_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *静水中最大航数[增0728]
     * </pre>
     *
     * <code>double vmax = 12;</code>
     */
    public Builder clearVmax() {
      
      vmax_ = 0D;
      onChanged();
      return this;
    }

    private double speed_ ;
    /**
     * <pre>
     *航速[增0728]
     * </pre>
     *
     * <code>double speed = 13;</code>
     */
    public double getSpeed() {
      return speed_;
    }
    /**
     * <pre>
     *航速[增0728]
     * </pre>
     *
     * <code>double speed = 13;</code>
     */
    public Builder setSpeed(double value) {
      
      speed_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *航速[增0728]
     * </pre>
     *
     * <code>double speed = 13;</code>
     */
    public Builder clearSpeed() {
      
      speed_ = 0D;
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.ShipParamRequest)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.ShipParamRequest)
  private static final com.iscas.biz.calculation.grpc.ShipParamRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.ShipParamRequest();
  }

  public static com.iscas.biz.calculation.grpc.ShipParamRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ShipParamRequest>
      PARSER = new com.google.protobuf.AbstractParser<ShipParamRequest>() {
    public ShipParamRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ShipParamRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ShipParamRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ShipParamRequest> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.ShipParamRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

