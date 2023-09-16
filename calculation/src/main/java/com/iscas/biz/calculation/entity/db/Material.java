package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.iscas.biz.calculation.enums.CheckType;
import lombok.Data;

import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote 扶强材计算
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

    //工况类型
    private CheckType checkType;

    /**
     * 舱壁ID
     */
    private Integer bulkheadId;

    /**
     * 上端载荷
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> upperLoad;

    /**
     * 下端载荷
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> lowerLoad;

    /**
     * 自由支持中部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> ziyouZhongwan;

    /**
     * 自由支持上部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> ziyouShangwan;


    /**
     * 自由支持下部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> ziyouXiawan;

    /**
     * 自由支持上部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> ziyouShangjian;


    /**
     * 自由支持下部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> ziyouXiajian;

    /**
     * 刚性固定上部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> gangxingShangwan;


    /**
     * 刚性固定下部弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> gangxingXiawan;

    /**
     * 刚性固定上部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> gangxingShangjian;

    /**
     * 刚性固定下部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> gangxingXiajian;

    /**
     * 应力值中部应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliZhongying;

    /**
     * 应力值上部应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliShangying;

    /**
     * 应力值下部应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliXiaying;

    /**
     * 应力值许用应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliXuying;

    /**
     * 应力值上部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliShangjian;

    /**
     * 应力值下部剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliXiajian;

    /**
     * 应力值许用剪力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> yingliXujian;
    /**
     * 弹性连续梁最大弯矩
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> mMaxEl;
    /**
     * 弹性连续梁最大支撑力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> nMaxEl;
    /**
     * 弹性连续梁最大正应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> stressMaxEl;
    /**
     * 弹性连续梁最大剪切力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Number> shearMaxEl;
}
