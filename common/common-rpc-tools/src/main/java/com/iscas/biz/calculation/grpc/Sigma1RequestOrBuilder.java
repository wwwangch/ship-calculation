// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface Sigma1RequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.Sigma1Request)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *构件跨距-龙骨跨距 每个的跨距
   * </pre>
   *
   * <code>repeated double kuaChang = 1;</code>
   */
  java.util.List<java.lang.Double> getKuaChangList();
  /**
   * <pre>
   *构件跨距-龙骨跨距 每个的跨距
   * </pre>
   *
   * <code>repeated double kuaChang = 1;</code>
   */
  int getKuaChangCount();
  /**
   * <pre>
   *构件跨距-龙骨跨距 每个的跨距
   * </pre>
   *
   * <code>repeated double kuaChang = 1;</code>
   */
  double getKuaChang(int index);

  /**
   * <pre>
   *剖面位置x-衡量间距
   * </pre>
   *
   * <code>double girderDistance = 2;</code>
   */
  double getGirderDistance();

  /**
   * <pre>
   *纵骨间距
   * </pre>
   *
   * <code>double frDistance = 3;</code>
   */
  double getFrDistance();

  /**
   * <pre>
   *纵骨规格 每个的规格
   * </pre>
   *
   * <code>repeated double frGuige = 4;</code>
   */
  java.util.List<java.lang.Double> getFrGuigeList();
  /**
   * <pre>
   *纵骨规格 每个的规格
   * </pre>
   *
   * <code>repeated double frGuige = 4;</code>
   */
  int getFrGuigeCount();
  /**
   * <pre>
   *纵骨规格 每个的规格
   * </pre>
   *
   * <code>repeated double frGuige = 4;</code>
   */
  double getFrGuige(int index);

  /**
   * <pre>
   *板各厚度 每个板材的厚度
   * </pre>
   *
   * <code>repeated double plateThick = 5;</code>
   */
  java.util.List<java.lang.Double> getPlateThickList();
  /**
   * <pre>
   *板各厚度 每个板材的厚度
   * </pre>
   *
   * <code>repeated double plateThick = 5;</code>
   */
  int getPlateThickCount();
  /**
   * <pre>
   *板各厚度 每个板材的厚度
   * </pre>
   *
   * <code>repeated double plateThick = 5;</code>
   */
  double getPlateThick(int index);

  /**
   * <pre>
   *设备重量(t)
   * </pre>
   *
   * <code>double deviceWeight = 6;</code>
   */
  double getDeviceWeight();

  /**
   * <pre>
   *版格宽度
   * </pre>
   *
   * <code>double girderWidth = 7;</code>
   */
  double getGirderWidth();

  /**
   * <pre>
   *材料类型
   * </pre>
   *
   * <code>double materialType = 8;</code>
   */
  double getMaterialType();
}
