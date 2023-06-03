// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculation.proto

package com.iscas.biz.calculation.grpc;

public interface SupportingMaterialStrengthResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.iscas.biz.calculation.grpc.SupportingMaterialStrengthResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *上端载荷
   * </pre>
   *
   * <code>repeated double upperLoad = 1;</code>
   */
  java.util.List<java.lang.Double> getUpperLoadList();
  /**
   * <pre>
   *上端载荷
   * </pre>
   *
   * <code>repeated double upperLoad = 1;</code>
   */
  int getUpperLoadCount();
  /**
   * <pre>
   *上端载荷
   * </pre>
   *
   * <code>repeated double upperLoad = 1;</code>
   */
  double getUpperLoad(int index);

  /**
   * <pre>
   *下端载荷
   * </pre>
   *
   * <code>repeated double lowerLoad = 2;</code>
   */
  java.util.List<java.lang.Double> getLowerLoadList();
  /**
   * <pre>
   *下端载荷
   * </pre>
   *
   * <code>repeated double lowerLoad = 2;</code>
   */
  int getLowerLoadCount();
  /**
   * <pre>
   *下端载荷
   * </pre>
   *
   * <code>repeated double lowerLoad = 2;</code>
   */
  double getLowerLoad(int index);

  /**
   * <pre>
   *自由支持中部弯矩
   * </pre>
   *
   * <code>repeated double ziyouZhongwan = 3;</code>
   */
  java.util.List<java.lang.Double> getZiyouZhongwanList();
  /**
   * <pre>
   *自由支持中部弯矩
   * </pre>
   *
   * <code>repeated double ziyouZhongwan = 3;</code>
   */
  int getZiyouZhongwanCount();
  /**
   * <pre>
   *自由支持中部弯矩
   * </pre>
   *
   * <code>repeated double ziyouZhongwan = 3;</code>
   */
  double getZiyouZhongwan(int index);

  /**
   * <pre>
   *自由支持上部弯矩
   * </pre>
   *
   * <code>repeated double ziyouShangwan = 4;</code>
   */
  java.util.List<java.lang.Double> getZiyouShangwanList();
  /**
   * <pre>
   *自由支持上部弯矩
   * </pre>
   *
   * <code>repeated double ziyouShangwan = 4;</code>
   */
  int getZiyouShangwanCount();
  /**
   * <pre>
   *自由支持上部弯矩
   * </pre>
   *
   * <code>repeated double ziyouShangwan = 4;</code>
   */
  double getZiyouShangwan(int index);

  /**
   * <pre>
   *自由支持下部弯矩
   * </pre>
   *
   * <code>repeated double ziyouXiawan = 5;</code>
   */
  java.util.List<java.lang.Double> getZiyouXiawanList();
  /**
   * <pre>
   *自由支持下部弯矩
   * </pre>
   *
   * <code>repeated double ziyouXiawan = 5;</code>
   */
  int getZiyouXiawanCount();
  /**
   * <pre>
   *自由支持下部弯矩
   * </pre>
   *
   * <code>repeated double ziyouXiawan = 5;</code>
   */
  double getZiyouXiawan(int index);

  /**
   * <pre>
   *自由支持上部剪力
   * </pre>
   *
   * <code>repeated double ziyouShangjian = 6;</code>
   */
  java.util.List<java.lang.Double> getZiyouShangjianList();
  /**
   * <pre>
   *自由支持上部剪力
   * </pre>
   *
   * <code>repeated double ziyouShangjian = 6;</code>
   */
  int getZiyouShangjianCount();
  /**
   * <pre>
   *自由支持上部剪力
   * </pre>
   *
   * <code>repeated double ziyouShangjian = 6;</code>
   */
  double getZiyouShangjian(int index);

  /**
   * <pre>
   *自由支持下部剪力
   * </pre>
   *
   * <code>repeated double ziyouXiajian = 7;</code>
   */
  java.util.List<java.lang.Double> getZiyouXiajianList();
  /**
   * <pre>
   *自由支持下部剪力
   * </pre>
   *
   * <code>repeated double ziyouXiajian = 7;</code>
   */
  int getZiyouXiajianCount();
  /**
   * <pre>
   *自由支持下部剪力
   * </pre>
   *
   * <code>repeated double ziyouXiajian = 7;</code>
   */
  double getZiyouXiajian(int index);

  /**
   * <pre>
   *刚性固定上部弯矩
   * </pre>
   *
   * <code>repeated double gangxingShangwan = 8;</code>
   */
  java.util.List<java.lang.Double> getGangxingShangwanList();
  /**
   * <pre>
   *刚性固定上部弯矩
   * </pre>
   *
   * <code>repeated double gangxingShangwan = 8;</code>
   */
  int getGangxingShangwanCount();
  /**
   * <pre>
   *刚性固定上部弯矩
   * </pre>
   *
   * <code>repeated double gangxingShangwan = 8;</code>
   */
  double getGangxingShangwan(int index);

  /**
   * <pre>
   *刚性固定下部弯矩
   * </pre>
   *
   * <code>repeated double gangxingXiawan = 9;</code>
   */
  java.util.List<java.lang.Double> getGangxingXiawanList();
  /**
   * <pre>
   *刚性固定下部弯矩
   * </pre>
   *
   * <code>repeated double gangxingXiawan = 9;</code>
   */
  int getGangxingXiawanCount();
  /**
   * <pre>
   *刚性固定下部弯矩
   * </pre>
   *
   * <code>repeated double gangxingXiawan = 9;</code>
   */
  double getGangxingXiawan(int index);

  /**
   * <pre>
   *刚性固定上部剪力
   * </pre>
   *
   * <code>repeated double gangxingShangjian = 10;</code>
   */
  java.util.List<java.lang.Double> getGangxingShangjianList();
  /**
   * <pre>
   *刚性固定上部剪力
   * </pre>
   *
   * <code>repeated double gangxingShangjian = 10;</code>
   */
  int getGangxingShangjianCount();
  /**
   * <pre>
   *刚性固定上部剪力
   * </pre>
   *
   * <code>repeated double gangxingShangjian = 10;</code>
   */
  double getGangxingShangjian(int index);

  /**
   * <pre>
   *刚性固定下部剪力
   * </pre>
   *
   * <code>repeated double gangxingXiajian = 11;</code>
   */
  java.util.List<java.lang.Double> getGangxingXiajianList();
  /**
   * <pre>
   *刚性固定下部剪力
   * </pre>
   *
   * <code>repeated double gangxingXiajian = 11;</code>
   */
  int getGangxingXiajianCount();
  /**
   * <pre>
   *刚性固定下部剪力
   * </pre>
   *
   * <code>repeated double gangxingXiajian = 11;</code>
   */
  double getGangxingXiajian(int index);

  /**
   * <pre>
   *应力值中部应力
   * </pre>
   *
   * <code>repeated double yingliZhongying = 12;</code>
   */
  java.util.List<java.lang.Double> getYingliZhongyingList();
  /**
   * <pre>
   *应力值中部应力
   * </pre>
   *
   * <code>repeated double yingliZhongying = 12;</code>
   */
  int getYingliZhongyingCount();
  /**
   * <pre>
   *应力值中部应力
   * </pre>
   *
   * <code>repeated double yingliZhongying = 12;</code>
   */
  double getYingliZhongying(int index);

  /**
   * <pre>
   *应力值上部应力
   * </pre>
   *
   * <code>repeated double yingliShangying = 13;</code>
   */
  java.util.List<java.lang.Double> getYingliShangyingList();
  /**
   * <pre>
   *应力值上部应力
   * </pre>
   *
   * <code>repeated double yingliShangying = 13;</code>
   */
  int getYingliShangyingCount();
  /**
   * <pre>
   *应力值上部应力
   * </pre>
   *
   * <code>repeated double yingliShangying = 13;</code>
   */
  double getYingliShangying(int index);

  /**
   * <pre>
   *应力值下部应力
   * </pre>
   *
   * <code>repeated double yingliXiaying = 14;</code>
   */
  java.util.List<java.lang.Double> getYingliXiayingList();
  /**
   * <pre>
   *应力值下部应力
   * </pre>
   *
   * <code>repeated double yingliXiaying = 14;</code>
   */
  int getYingliXiayingCount();
  /**
   * <pre>
   *应力值下部应力
   * </pre>
   *
   * <code>repeated double yingliXiaying = 14;</code>
   */
  double getYingliXiaying(int index);

  /**
   * <pre>
   *应力值许用应力
   * </pre>
   *
   * <code>repeated double yingliXuying = 15;</code>
   */
  java.util.List<java.lang.Double> getYingliXuyingList();
  /**
   * <pre>
   *应力值许用应力
   * </pre>
   *
   * <code>repeated double yingliXuying = 15;</code>
   */
  int getYingliXuyingCount();
  /**
   * <pre>
   *应力值许用应力
   * </pre>
   *
   * <code>repeated double yingliXuying = 15;</code>
   */
  double getYingliXuying(int index);

  /**
   * <pre>
   *应力值上部剪力
   * </pre>
   *
   * <code>repeated double yingliShangjian = 16;</code>
   */
  java.util.List<java.lang.Double> getYingliShangjianList();
  /**
   * <pre>
   *应力值上部剪力
   * </pre>
   *
   * <code>repeated double yingliShangjian = 16;</code>
   */
  int getYingliShangjianCount();
  /**
   * <pre>
   *应力值上部剪力
   * </pre>
   *
   * <code>repeated double yingliShangjian = 16;</code>
   */
  double getYingliShangjian(int index);

  /**
   * <pre>
   *应力值下部剪力
   * </pre>
   *
   * <code>repeated double yingliXiajian = 17;</code>
   */
  java.util.List<java.lang.Double> getYingliXiajianList();
  /**
   * <pre>
   *应力值下部剪力
   * </pre>
   *
   * <code>repeated double yingliXiajian = 17;</code>
   */
  int getYingliXiajianCount();
  /**
   * <pre>
   *应力值下部剪力
   * </pre>
   *
   * <code>repeated double yingliXiajian = 17;</code>
   */
  double getYingliXiajian(int index);

  /**
   * <pre>
   *应力值许用剪力
   * </pre>
   *
   * <code>repeated double yingliXujian = 18;</code>
   */
  java.util.List<java.lang.Double> getYingliXujianList();
  /**
   * <pre>
   *应力值许用剪力
   * </pre>
   *
   * <code>repeated double yingliXujian = 18;</code>
   */
  int getYingliXujianCount();
  /**
   * <pre>
   *应力值许用剪力
   * </pre>
   *
   * <code>repeated double yingliXujian = 18;</code>
   */
  double getYingliXujian(int index);
}
