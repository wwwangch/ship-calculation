// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface Sigma2RequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.Sigma2Request)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *用户输入自定义标志[增0728]
   * </pre>
   *
   * <code>bool userfined = 1;</code>
   */
  boolean getUserfined();

  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma2Entity sigma2 = 2;</code>
   */
  java.util.List<com.iscas.biz.calculation.grpc.Sigma2Entity> 
      getSigma2List();
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma2Entity sigma2 = 2;</code>
   */
  com.iscas.biz.calculation.grpc.Sigma2Entity getSigma2(int index);
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma2Entity sigma2 = 2;</code>
   */
  int getSigma2Count();
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma2Entity sigma2 = 2;</code>
   */
  java.util.List<? extends com.iscas.biz.calculation.grpc.Sigma2EntityOrBuilder> 
      getSigma2OrBuilderList();
  /**
   * <code>repeated .com.iscas.biz.calculation.grpc.Sigma2Entity sigma2 = 2;</code>
   */
  com.iscas.biz.calculation.grpc.Sigma2EntityOrBuilder getSigma2OrBuilder(
      int index);
}
