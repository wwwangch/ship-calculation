// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface TProfileResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.TProfileResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *T型材属性计算[增0728]
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.TProfile tProfiles = 1;</code>
   */
  java.util.List<com.iscas.biz.calculation.grpc.TProfile> 
      getTProfilesList();
  /**
   * <pre>
   *T型材属性计算[增0728]
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.TProfile tProfiles = 1;</code>
   */
  com.iscas.biz.calculation.grpc.TProfile getTProfiles(int index);
  /**
   * <pre>
   *T型材属性计算[增0728]
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.TProfile tProfiles = 1;</code>
   */
  int getTProfilesCount();
  /**
   * <pre>
   *T型材属性计算[增0728]
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.TProfile tProfiles = 1;</code>
   */
  java.util.List<? extends com.iscas.biz.calculation.grpc.TProfileOrBuilder> 
      getTProfilesOrBuilderList();
  /**
   * <pre>
   *T型材属性计算[增0728]
   * </pre>
   *
   * <code>repeated .com.iscas.biz.calculation.grpc.TProfile tProfiles = 1;</code>
   */
  com.iscas.biz.calculation.grpc.TProfileOrBuilder getTProfilesOrBuilder(
      int index);

  /**
   * <pre>
   *0-正常 1-异常[增0731]
   * </pre>
   *
   * <code>int32 code = 2;</code>
   */
  int getCode();
}