// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface SupportingMaterialStrengthRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.SupportingMaterialStrengthRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *扶强材型号
   * </pre>
   *
   * <code>repeated string guicaiType = 1;</code>
   */
  java.util.List<java.lang.String>
      getGuicaiTypeList();
  /**
   * <pre>
   *扶强材型号
   * </pre>
   *
   * <code>repeated string guicaiType = 1;</code>
   */
  int getGuicaiTypeCount();
  /**
   * <pre>
   *扶强材型号
   * </pre>
   *
   * <code>repeated string guicaiType = 1;</code>
   */
  java.lang.String getGuicaiType(int index);
  /**
   * <pre>
   *扶强材型号
   * </pre>
   *
   * <code>repeated string guicaiType = 1;</code>
   */
  com.google.protobuf.ByteString
      getGuicaiTypeBytes(int index);

  /**
   * <pre>
   *带板宽
   * </pre>
   *
   * <code>repeated double daibanKuan = 2;</code>
   */
  java.util.List<java.lang.Double> getDaibanKuanList();
  /**
   * <pre>
   *带板宽
   * </pre>
   *
   * <code>repeated double daibanKuan = 2;</code>
   */
  int getDaibanKuanCount();
  /**
   * <pre>
   *带板宽
   * </pre>
   *
   * <code>repeated double daibanKuan = 2;</code>
   */
  double getDaibanKuan(int index);

  /**
   * <pre>
   *上带板厚
   * </pre>
   *
   * <code>repeated double daibanHouUpper = 3;</code>
   */
  java.util.List<java.lang.Double> getDaibanHouUpperList();
  /**
   * <pre>
   *上带板厚
   * </pre>
   *
   * <code>repeated double daibanHouUpper = 3;</code>
   */
  int getDaibanHouUpperCount();
  /**
   * <pre>
   *上带板厚
   * </pre>
   *
   * <code>repeated double daibanHouUpper = 3;</code>
   */
  double getDaibanHouUpper(int index);

  /**
   * <pre>
   *下带板厚
   * </pre>
   *
   * <code>repeated double daibanHouLower = 4;</code>
   */
  java.util.List<java.lang.Double> getDaibanHouLowerList();
  /**
   * <pre>
   *下带板厚
   * </pre>
   *
   * <code>repeated double daibanHouLower = 4;</code>
   */
  int getDaibanHouLowerCount();
  /**
   * <pre>
   *下带板厚
   * </pre>
   *
   * <code>repeated double daibanHouLower = 4;</code>
   */
  double getDaibanHouLower(int index);

  /**
   * <pre>
   *甲板纵骨跨距
   * </pre>
   *
   * <code>double zongguKuaju = 5;</code>
   */
  double getZongguKuaju();

  /**
   * <pre>
   *new614----------------
   *上部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_upper = 6;</code>
   */
  java.util.List<java.lang.String>
      getGuicaiTypeUpperList();
  /**
   * <pre>
   *new614----------------
   *上部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_upper = 6;</code>
   */
  int getGuicaiTypeUpperCount();
  /**
   * <pre>
   *new614----------------
   *上部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_upper = 6;</code>
   */
  java.lang.String getGuicaiTypeUpper(int index);
  /**
   * <pre>
   *new614----------------
   *上部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_upper = 6;</code>
   */
  com.google.protobuf.ByteString
      getGuicaiTypeUpperBytes(int index);

  /**
   * <pre>
   *下部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_lower = 7;</code>
   */
  java.util.List<java.lang.String>
      getGuicaiTypeLowerList();
  /**
   * <pre>
   *下部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_lower = 7;</code>
   */
  int getGuicaiTypeLowerCount();
  /**
   * <pre>
   *下部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_lower = 7;</code>
   */
  java.lang.String getGuicaiTypeLower(int index);
  /**
   * <pre>
   *下部支撑纵骨规格
   * </pre>
   *
   * <code>repeated string guicaiType_lower = 7;</code>
   */
  com.google.protobuf.ByteString
      getGuicaiTypeLowerBytes(int index);

  /**
   * <pre>
   *扶强材屈服极限
   * </pre>
   *
   * <code>repeated double fuQiangCaiYieldLimit = 8;</code>
   */
  java.util.List<java.lang.Double> getFuQiangCaiYieldLimitList();
  /**
   * <pre>
   *扶强材屈服极限
   * </pre>
   *
   * <code>repeated double fuQiangCaiYieldLimit = 8;</code>
   */
  int getFuQiangCaiYieldLimitCount();
  /**
   * <pre>
   *扶强材屈服极限
   * </pre>
   *
   * <code>repeated double fuQiangCaiYieldLimit = 8;</code>
   */
  double getFuQiangCaiYieldLimit(int index);
}
