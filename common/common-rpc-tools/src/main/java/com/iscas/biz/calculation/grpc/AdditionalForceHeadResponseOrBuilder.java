// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface AdditionalForceHeadResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.AdditionalForceHeadResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *肋位号  数组
   * </pre>
   *
   * <code>repeated double leiweihao = 1;</code>
   */
  java.util.List<java.lang.Double> getLeiweihaoList();
  /**
   * <pre>
   *肋位号  数组
   * </pre>
   *
   * <code>repeated double leiweihao = 1;</code>
   */
  int getLeiweihaoCount();
  /**
   * <pre>
   *肋位号  数组
   * </pre>
   *
   * <code>repeated double leiweihao = 1;</code>
   */
  double getLeiweihao(int index);

  /**
   * <pre>
   *附加压头  数组
   * </pre>
   *
   * <code>repeated double addyatouh = 2;</code>
   */
  java.util.List<java.lang.Double> getAddyatouhList();
  /**
   * <pre>
   *附加压头  数组
   * </pre>
   *
   * <code>repeated double addyatouh = 2;</code>
   */
  int getAddyatouhCount();
  /**
   * <pre>
   *附加压头  数组
   * </pre>
   *
   * <code>repeated double addyatouh = 2;</code>
   */
  double getAddyatouh(int index);

  /**
   * <pre>
   *甲板名称
   * </pre>
   *
   * <code>repeated string strdeck = 3;</code>
   */
  java.util.List<java.lang.String>
      getStrdeckList();
  /**
   * <pre>
   *甲板名称
   * </pre>
   *
   * <code>repeated string strdeck = 3;</code>
   */
  int getStrdeckCount();
  /**
   * <pre>
   *甲板名称
   * </pre>
   *
   * <code>repeated string strdeck = 3;</code>
   */
  java.lang.String getStrdeck(int index);
  /**
   * <pre>
   *甲板名称
   * </pre>
   *
   * <code>repeated string strdeck = 3;</code>
   */
  com.google.protobuf.ByteString
      getStrdeckBytes(int index);

  /**
   * <pre>
   *甲板破损压头水压值
   * </pre>
   *
   * <code>repeated double deckyatou = 4;</code>
   */
  java.util.List<java.lang.Double> getDeckyatouList();
  /**
   * <pre>
   *甲板破损压头水压值
   * </pre>
   *
   * <code>repeated double deckyatou = 4;</code>
   */
  int getDeckyatouCount();
  /**
   * <pre>
   *甲板破损压头水压值
   * </pre>
   *
   * <code>repeated double deckyatou = 4;</code>
   */
  double getDeckyatou(int index);

  /**
   * <pre>
   *0-正常 1-异常
   * </pre>
   *
   * <code>int32 code = 5;</code>
   */
  int getCode();
}
