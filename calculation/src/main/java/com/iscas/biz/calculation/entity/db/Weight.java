package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 15:40
 */
@Data
@TableName(value = "weight", autoResultMap = true)
public class Weight {

    @TableId(type = IdType.AUTO)
    private Integer weightId;

    private Integer projectId;

    /**
     * 载重文件名称
     */
    private String loadingFileName;

    /**
     * 载重文件路径
     */
    private String loadingFilePath;

    private Integer code;

    private String message;

    /**
     * 各个分项得重量分布
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<WeightDistribution> weightDistributions;

    /**
     * 当前校核工况下的站间重量分布
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> allDirs;

    /**
     * 总体重量分布 排水量和重心纵向坐标
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Gravity allRst;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<SubGravity> subGravities;
}
