// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface SlamLoadResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.SlamLoadResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *波峰抨击弯矩  数组  21个
   * </pre>
   *
   * <code>repeated double pwbm = 1;</code>
   */
  java.util.List<java.lang.Double> getPwbmList();
  /**
   * <pre>
   *波峰抨击弯矩  数组  21个
   * </pre>
   *
   * <code>repeated double pwbm = 1;</code>
   */
  int getPwbmCount();
  /**
   * <pre>
   *波峰抨击弯矩  数组  21个
   * </pre>
   *
   * <code>repeated double pwbm = 1;</code>
   */
  double getPwbm(int index);

  /**
   * <pre>
   *波谷抨击弯矩  数组  21个
   * </pre>
   *
   * <code>repeated double nwb = 2;</code>
   */
  java.util.List<java.lang.Double> getNwbList();
  /**
   * <pre>
   *波谷抨击弯矩  数组  21个
   * </pre>
   *
   * <code>repeated double nwb = 2;</code>
   */
  int getNwbCount();
  /**
   * <pre>
   *波谷抨击弯矩  数组  21个
   * </pre>
   *
   * <code>repeated double nwb = 2;</code>
   */
  double getNwb(int index);
}
