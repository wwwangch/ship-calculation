// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface StaticLoadResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.StaticLoadResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *未修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvec = 1;</code>
   */
  java.util.List<java.lang.Double> getNvecList();
  /**
   * <pre>
   *未修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvec = 1;</code>
   */
  int getNvecCount();
  /**
   * <pre>
   *未修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvec = 1;</code>
   */
  double getNvec(int index);

  /**
   * <pre>
   *  未修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvec = 2;</code>
   */
  java.util.List<java.lang.Double> getMvecList();
  /**
   * <pre>
   *  未修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvec = 2;</code>
   */
  int getMvecCount();
  /**
   * <pre>
   *  未修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvec = 2;</code>
   */
  double getMvec(int index);

  /**
   * <pre>
   *修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvecM = 3;</code>
   */
  java.util.List<java.lang.Double> getNvecMList();
  /**
   * <pre>
   *修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvecM = 3;</code>
   */
  int getNvecMCount();
  /**
   * <pre>
   *修正的静水剪力 目前返回21个
   * </pre>
   *
   * <code>repeated double nvecM = 3;</code>
   */
  double getNvecM(int index);

  /**
   * <pre>
   *  修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvecM = 4;</code>
   */
  java.util.List<java.lang.Double> getMvecMList();
  /**
   * <pre>
   *  修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvecM = 4;</code>
   */
  int getMvecMCount();
  /**
   * <pre>
   *  修正的弯矩 目前返回21个
   * </pre>
   *
   * <code>repeated double mvecM = 4;</code>
   */
  double getMvecM(int index);

  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 5;</code>
   */
  int getCode();
}
