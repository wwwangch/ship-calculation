// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

/**
 * <pre>
 *剖面计算输出
 * </pre>
 *
 * Protobuf type {@code com.iscas.biz.calculation.grpc.SectionResponse}
 */
public  final class SectionResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.iscas.biz.calculation.grpc.SectionResponse)
    SectionResponseOrBuilder {
  // Use SectionResponse.newBuilder() to construct.
  private SectionResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SectionResponse() {
    firstMoment0_ = 0D;
    interia0_ = 0D;
    zaxis0_ = 0D;
    area_ = 0D;
    moduleUppper_ = 0D;
    moduleLower_ = 0D;
    profileFilePath_ = "";
    code_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private SectionResponse(
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

            firstMoment0_ = input.readDouble();
            break;
          }
          case 17: {

            interia0_ = input.readDouble();
            break;
          }
          case 25: {

            zaxis0_ = input.readDouble();
            break;
          }
          case 33: {

            area_ = input.readDouble();
            break;
          }
          case 41: {

            moduleUppper_ = input.readDouble();
            break;
          }
          case 49: {

            moduleLower_ = input.readDouble();
            break;
          }
          case 58: {
            java.lang.String s = input.readStringRequireUtf8();

            profileFilePath_ = s;
            break;
          }
          case 64: {

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
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionResponse_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.iscas.biz.calculation.grpc.SectionResponse.class, com.iscas.biz.calculation.grpc.SectionResponse.Builder.class);
  }

  public static final int FIRSTMOMENT0_FIELD_NUMBER = 1;
  private double firstMoment0_;
  /**
   * <pre>
   *初始静矩
   * </pre>
   *
   * <code>double firstMoment0 = 1;</code>
   */
  public double getFirstMoment0() {
    return firstMoment0_;
  }

  public static final int INTERIA0_FIELD_NUMBER = 2;
  private double interia0_;
  /**
   * <pre>
   *初始惯性矩
   * </pre>
   *
   * <code>double interia0 = 2;</code>
   */
  public double getInteria0() {
    return interia0_;
  }

  public static final int ZAXIS0_FIELD_NUMBER = 3;
  private double zaxis0_;
  /**
   * <pre>
   *初始中和轴
   * </pre>
   *
   * <code>double zaxis0 = 3;</code>
   */
  public double getZaxis0() {
    return zaxis0_;
  }

  public static final int AREA_FIELD_NUMBER = 4;
  private double area_;
  /**
   * <pre>
   *修改0614 去掉中拱中垂输出，增加面积和底部上甲板模数输出
   *剖面面积
   * </pre>
   *
   * <code>double area = 4;</code>
   */
  public double getArea() {
    return area_;
  }

  public static final int MODULE_UPPPER_FIELD_NUMBER = 5;
  private double moduleUppper_;
  /**
   * <pre>
   *上甲板模数
   * </pre>
   *
   * <code>double module_uppper = 5;</code>
   */
  public double getModuleUppper() {
    return moduleUppper_;
  }

  public static final int MODULE_LOWER_FIELD_NUMBER = 6;
  private double moduleLower_;
  /**
   * <pre>
   *底部模数
   * </pre>
   *
   * <code>double module_lower = 6;</code>
   */
  public double getModuleLower() {
    return moduleLower_;
  }

  public static final int PROFILEFILEPATH_FIELD_NUMBER = 7;
  private volatile java.lang.Object profileFilePath_;
  /**
   * <pre>
   *解析后的剖面文件路径
   * </pre>
   *
   * <code>string profileFilePath = 7;</code>
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
   *解析后的剖面文件路径
   * </pre>
   *
   * <code>string profileFilePath = 7;</code>
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

  public static final int CODE_FIELD_NUMBER = 8;
  private int code_;
  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 8;</code>
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
    if (firstMoment0_ != 0D) {
      output.writeDouble(1, firstMoment0_);
    }
    if (interia0_ != 0D) {
      output.writeDouble(2, interia0_);
    }
    if (zaxis0_ != 0D) {
      output.writeDouble(3, zaxis0_);
    }
    if (area_ != 0D) {
      output.writeDouble(4, area_);
    }
    if (moduleUppper_ != 0D) {
      output.writeDouble(5, moduleUppper_);
    }
    if (moduleLower_ != 0D) {
      output.writeDouble(6, moduleLower_);
    }
    if (!getProfileFilePathBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, profileFilePath_);
    }
    if (code_ != 0) {
      output.writeInt32(8, code_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (firstMoment0_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, firstMoment0_);
    }
    if (interia0_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, interia0_);
    }
    if (zaxis0_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, zaxis0_);
    }
    if (area_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, area_);
    }
    if (moduleUppper_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, moduleUppper_);
    }
    if (moduleLower_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(6, moduleLower_);
    }
    if (!getProfileFilePathBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, profileFilePath_);
    }
    if (code_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, code_);
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
    if (!(obj instanceof com.iscas.biz.calculation.grpc.SectionResponse)) {
      return super.equals(obj);
    }
    com.iscas.biz.calculation.grpc.SectionResponse other = (com.iscas.biz.calculation.grpc.SectionResponse) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getFirstMoment0())
        == java.lang.Double.doubleToLongBits(
            other.getFirstMoment0()));
    result = result && (
        java.lang.Double.doubleToLongBits(getInteria0())
        == java.lang.Double.doubleToLongBits(
            other.getInteria0()));
    result = result && (
        java.lang.Double.doubleToLongBits(getZaxis0())
        == java.lang.Double.doubleToLongBits(
            other.getZaxis0()));
    result = result && (
        java.lang.Double.doubleToLongBits(getArea())
        == java.lang.Double.doubleToLongBits(
            other.getArea()));
    result = result && (
        java.lang.Double.doubleToLongBits(getModuleUppper())
        == java.lang.Double.doubleToLongBits(
            other.getModuleUppper()));
    result = result && (
        java.lang.Double.doubleToLongBits(getModuleLower())
        == java.lang.Double.doubleToLongBits(
            other.getModuleLower()));
    result = result && getProfileFilePath()
        .equals(other.getProfileFilePath());
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
    hash = (37 * hash) + FIRSTMOMENT0_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getFirstMoment0()));
    hash = (37 * hash) + INTERIA0_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getInteria0()));
    hash = (37 * hash) + ZAXIS0_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getZaxis0()));
    hash = (37 * hash) + AREA_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getArea()));
    hash = (37 * hash) + MODULE_UPPPER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getModuleUppper()));
    hash = (37 * hash) + MODULE_LOWER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getModuleLower()));
    hash = (37 * hash) + PROFILEFILEPATH_FIELD_NUMBER;
    hash = (53 * hash) + getProfileFilePath().hashCode();
    hash = (37 * hash) + CODE_FIELD_NUMBER;
    hash = (53 * hash) + getCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.iscas.biz.calculation.grpc.SectionResponse parseFrom(
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
  public static Builder newBuilder(com.iscas.biz.calculation.grpc.SectionResponse prototype) {
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
   *剖面计算输出
   * </pre>
   *
   * Protobuf type {@code com.iscas.biz.calculation.grpc.SectionResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.iscas.biz.calculation.grpc.SectionResponse)
      com.iscas.biz.calculation.grpc.SectionResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.iscas.biz.calculation.grpc.SectionResponse.class, com.iscas.biz.calculation.grpc.SectionResponse.Builder.class);
    }

    // Construct using com.iscas.biz.calculation.grpc.SectionResponse.newBuilder()
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
      firstMoment0_ = 0D;

      interia0_ = 0D;

      zaxis0_ = 0D;

      area_ = 0D;

      moduleUppper_ = 0D;

      moduleLower_ = 0D;

      profileFilePath_ = "";

      code_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.iscas.biz.calculation.grpc.CalculationProto.internal_static_com_iscas_biz_calculation_grpc_SectionResponse_descriptor;
    }

    public com.iscas.biz.calculation.grpc.SectionResponse getDefaultInstanceForType() {
      return com.iscas.biz.calculation.grpc.SectionResponse.getDefaultInstance();
    }

    public com.iscas.biz.calculation.grpc.SectionResponse build() {
      com.iscas.biz.calculation.grpc.SectionResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.iscas.biz.calculation.grpc.SectionResponse buildPartial() {
      com.iscas.biz.calculation.grpc.SectionResponse result = new com.iscas.biz.calculation.grpc.SectionResponse(this);
      result.firstMoment0_ = firstMoment0_;
      result.interia0_ = interia0_;
      result.zaxis0_ = zaxis0_;
      result.area_ = area_;
      result.moduleUppper_ = moduleUppper_;
      result.moduleLower_ = moduleLower_;
      result.profileFilePath_ = profileFilePath_;
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
      if (other instanceof com.iscas.biz.calculation.grpc.SectionResponse) {
        return mergeFrom((com.iscas.biz.calculation.grpc.SectionResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.iscas.biz.calculation.grpc.SectionResponse other) {
      if (other == com.iscas.biz.calculation.grpc.SectionResponse.getDefaultInstance()) return this;
      if (other.getFirstMoment0() != 0D) {
        setFirstMoment0(other.getFirstMoment0());
      }
      if (other.getInteria0() != 0D) {
        setInteria0(other.getInteria0());
      }
      if (other.getZaxis0() != 0D) {
        setZaxis0(other.getZaxis0());
      }
      if (other.getArea() != 0D) {
        setArea(other.getArea());
      }
      if (other.getModuleUppper() != 0D) {
        setModuleUppper(other.getModuleUppper());
      }
      if (other.getModuleLower() != 0D) {
        setModuleLower(other.getModuleLower());
      }
      if (!other.getProfileFilePath().isEmpty()) {
        profileFilePath_ = other.profileFilePath_;
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
      com.iscas.biz.calculation.grpc.SectionResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.iscas.biz.calculation.grpc.SectionResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double firstMoment0_ ;
    /**
     * <pre>
     *初始静矩
     * </pre>
     *
     * <code>double firstMoment0 = 1;</code>
     */
    public double getFirstMoment0() {
      return firstMoment0_;
    }
    /**
     * <pre>
     *初始静矩
     * </pre>
     *
     * <code>double firstMoment0 = 1;</code>
     */
    public Builder setFirstMoment0(double value) {
      
      firstMoment0_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *初始静矩
     * </pre>
     *
     * <code>double firstMoment0 = 1;</code>
     */
    public Builder clearFirstMoment0() {
      
      firstMoment0_ = 0D;
      onChanged();
      return this;
    }

    private double interia0_ ;
    /**
     * <pre>
     *初始惯性矩
     * </pre>
     *
     * <code>double interia0 = 2;</code>
     */
    public double getInteria0() {
      return interia0_;
    }
    /**
     * <pre>
     *初始惯性矩
     * </pre>
     *
     * <code>double interia0 = 2;</code>
     */
    public Builder setInteria0(double value) {
      
      interia0_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *初始惯性矩
     * </pre>
     *
     * <code>double interia0 = 2;</code>
     */
    public Builder clearInteria0() {
      
      interia0_ = 0D;
      onChanged();
      return this;
    }

    private double zaxis0_ ;
    /**
     * <pre>
     *初始中和轴
     * </pre>
     *
     * <code>double zaxis0 = 3;</code>
     */
    public double getZaxis0() {
      return zaxis0_;
    }
    /**
     * <pre>
     *初始中和轴
     * </pre>
     *
     * <code>double zaxis0 = 3;</code>
     */
    public Builder setZaxis0(double value) {
      
      zaxis0_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *初始中和轴
     * </pre>
     *
     * <code>double zaxis0 = 3;</code>
     */
    public Builder clearZaxis0() {
      
      zaxis0_ = 0D;
      onChanged();
      return this;
    }

    private double area_ ;
    /**
     * <pre>
     *修改0614 去掉中拱中垂输出，增加面积和底部上甲板模数输出
     *剖面面积
     * </pre>
     *
     * <code>double area = 4;</code>
     */
    public double getArea() {
      return area_;
    }
    /**
     * <pre>
     *修改0614 去掉中拱中垂输出，增加面积和底部上甲板模数输出
     *剖面面积
     * </pre>
     *
     * <code>double area = 4;</code>
     */
    public Builder setArea(double value) {
      
      area_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *修改0614 去掉中拱中垂输出，增加面积和底部上甲板模数输出
     *剖面面积
     * </pre>
     *
     * <code>double area = 4;</code>
     */
    public Builder clearArea() {
      
      area_ = 0D;
      onChanged();
      return this;
    }

    private double moduleUppper_ ;
    /**
     * <pre>
     *上甲板模数
     * </pre>
     *
     * <code>double module_uppper = 5;</code>
     */
    public double getModuleUppper() {
      return moduleUppper_;
    }
    /**
     * <pre>
     *上甲板模数
     * </pre>
     *
     * <code>double module_uppper = 5;</code>
     */
    public Builder setModuleUppper(double value) {
      
      moduleUppper_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *上甲板模数
     * </pre>
     *
     * <code>double module_uppper = 5;</code>
     */
    public Builder clearModuleUppper() {
      
      moduleUppper_ = 0D;
      onChanged();
      return this;
    }

    private double moduleLower_ ;
    /**
     * <pre>
     *底部模数
     * </pre>
     *
     * <code>double module_lower = 6;</code>
     */
    public double getModuleLower() {
      return moduleLower_;
    }
    /**
     * <pre>
     *底部模数
     * </pre>
     *
     * <code>double module_lower = 6;</code>
     */
    public Builder setModuleLower(double value) {
      
      moduleLower_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *底部模数
     * </pre>
     *
     * <code>double module_lower = 6;</code>
     */
    public Builder clearModuleLower() {
      
      moduleLower_ = 0D;
      onChanged();
      return this;
    }

    private java.lang.Object profileFilePath_ = "";
    /**
     * <pre>
     *解析后的剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 7;</code>
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
     *解析后的剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 7;</code>
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
     *解析后的剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 7;</code>
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
     *解析后的剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 7;</code>
     */
    public Builder clearProfileFilePath() {
      
      profileFilePath_ = getDefaultInstance().getProfileFilePath();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *解析后的剖面文件路径
     * </pre>
     *
     * <code>string profileFilePath = 7;</code>
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

    private int code_ ;
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 8;</code>
     */
    public int getCode() {
      return code_;
    }
    /**
     * <pre>
     *0-正常 1-异常
     * </pre>
     *
     * <code>int32 code = 8;</code>
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
     * <code>int32 code = 8;</code>
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


    // @@protoc_insertion_point(builder_scope:com.iscas.biz.calculation.grpc.SectionResponse)
  }

  // @@protoc_insertion_point(class_scope:com.iscas.biz.calculation.grpc.SectionResponse)
  private static final com.iscas.biz.calculation.grpc.SectionResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.iscas.biz.calculation.grpc.SectionResponse();
  }

  public static com.iscas.biz.calculation.grpc.SectionResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SectionResponse>
      PARSER = new com.google.protobuf.AbstractParser<SectionResponse>() {
    public SectionResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SectionResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SectionResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SectionResponse> getParserForType() {
    return PARSER;
  }

  public com.iscas.biz.calculation.grpc.SectionResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

