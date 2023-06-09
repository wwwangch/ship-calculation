// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface WeightResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.WeightResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 1;</code>
   */
  int getCode();

  /**
   * <pre>
   *异常信息
   * </pre>
   *
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   *异常信息
   * </pre>
   *
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   *各个分项得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.WeightDistribution weightDistributions = 3;</code>
   */
  java.util.List<com.iscas.biz.calculation.grpc.WeightDistribution> 
      getWeightDistributionsList();
  /**
   * <pre>
   *各个分项得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.WeightDistribution weightDistributions = 3;</code>
   */
  com.iscas.biz.calculation.grpc.WeightDistribution getWeightDistributions(int index);
  /**
   * <pre>
   *各个分项得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.WeightDistribution weightDistributions = 3;</code>
   */
  int getWeightDistributionsCount();
  /**
   * <pre>
   *各个分项得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.WeightDistribution weightDistributions = 3;</code>
   */
  java.util.List<? extends com.iscas.biz.calculation.grpc.WeightDistributionOrBuilder> 
      getWeightDistributionsOrBuilderList();
  /**
   * <pre>
   *各个分项得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.WeightDistribution weightDistributions = 3;</code>
   */
  com.iscas.biz.calculation.grpc.WeightDistributionOrBuilder getWeightDistributionsOrBuilder(
      int index);

  /**
   * <pre>
   *整体重量分布
   * </pre>
   *
   * <code>repeated double allDis = 4;</code>
   */
  java.util.List<java.lang.Double> getAllDisList();
  /**
   * <pre>
   *整体重量分布
   * </pre>
   *
   * <code>repeated double allDis = 4;</code>
   */
  int getAllDisCount();
  /**
   * <pre>
   *整体重量分布
   * </pre>
   *
   * <code>repeated double allDis = 4;</code>
   */
  double getAllDis(int index);

  /**
   * <pre>
   *总体重量分布
   * </pre>
   *
   * <code>.com.iscas.biz.calculation.grpc.Gravity allRst = 5;</code>
   */
  boolean hasAllRst();
  /**
   * <pre>
   *总体重量分布
   * </pre>
   *
   * <code>.com.iscas.biz.calculation.grpc.Gravity allRst = 5;</code>
   */
  com.iscas.biz.calculation.grpc.Gravity getAllRst();
  /**
   * <pre>
   *总体重量分布
   * </pre>
   *
   * <code>.com.iscas.biz.calculation.grpc.Gravity allRst = 5;</code>
   */
  com.iscas.biz.calculation.grpc.GravityOrBuilder getAllRstOrBuilder();

  /**
   * <pre>
   *每个专业得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.SubGravity subGravities = 6;</code>
   */
  java.util.List<com.iscas.biz.calculation.grpc.SubGravity> 
      getSubGravitiesList();
  /**
   * <pre>
   *每个专业得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.SubGravity subGravities = 6;</code>
   */
  com.iscas.biz.calculation.grpc.SubGravity getSubGravities(int index);
  /**
   * <pre>
   *每个专业得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.SubGravity subGravities = 6;</code>
   */
  int getSubGravitiesCount();
  /**
   * <pre>
   *每个专业得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.SubGravity subGravities = 6;</code>
   */
  java.util.List<? extends com.iscas.biz.calculation.grpc.SubGravityOrBuilder> 
      getSubGravitiesOrBuilderList();
  /**
   * <pre>
   *每个专业得重量分布
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.SubGravity subGravities = 6;</code>
   */
  com.iscas.biz.calculation.grpc.SubGravityOrBuilder getSubGravitiesOrBuilder(
      int index);
}
