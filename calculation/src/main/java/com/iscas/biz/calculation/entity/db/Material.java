package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 * 扶强材计算
 */

@Data
@TableName(value = "material", autoResultMap = true)
public class Material {

    @TableId(type = IdType.AUTO)
    private Integer materialId;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 舱壁ID
     */
    private Integer bulkheadId;

    /**
     * 扶强材型号
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> guicaiType;

    /**
     * 带板宽
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> daibanKuan;

    /**
     * 带板厚
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> daibanHou;

    /**
     * 甲板纵骨跨距
     */
    private Double zongguKuaju;

    /**
     * 上端载荷
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> upperLoad;

    /**
     * 下端载荷
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> lowerLoad;

    /**
     * 自由支持中部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> ziyouZhongwan;

    /**
     * 自由支持上部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> ziyouShangwan;


    /**
     * 自由支持下部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> ziyouXiawan;

    /**
     * 自由支持上部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> ziyouShangjian;


    /**
     * 自由支持下部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> ziyouXiajian;

    /**
     * 刚性固定上部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> gangxingShangwan;


    /**
     * 刚性固定下部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> gangxingXiawan;

    /**
     * 刚性固定上部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> gangxingShangjian;

    /**
     * 刚性固定下部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> gangxingXiajian;

    /**
     * 应力值中部应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliZhongying;

    /**
     * 应力值上部应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliShangying;

    /**
     * 应力值下部应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliXiaying;

    /**
     * 应力值许用应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliXuying;

    /**
     * 应力值上部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliShangjian;

    /**
     * 应力值下部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliXiajian;

    /**
     * 应力值许用剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> yingliXujian;



}
