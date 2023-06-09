// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface SubGravityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.SubGravity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *专业名-项目名
   * </pre>
   *
   * <code>bytes name = 1;</code>
   */
  com.google.protobuf.ByteString getName();

  /**
   * <pre>
   *排水量
   * </pre>
   *
   * <code>double displacement = 2;</code>
   */
  double getDisplacement();

  /**
   * <pre>
   *重心纵向坐标
   * </pre>
   *
   * <code>double xg = 3;</code>
   */
  double getXg();
}
