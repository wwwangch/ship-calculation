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
 * @author ch w
 * @version 1.0
 * @since 2023/5/27 22:45
 */
@Data
@TableName(value = "wave_load",autoResultMap = true)
public class WaveLoad {
    @TableId(type = IdType.AUTO)
    private Integer waveLoadId;

    private Integer projectId;

    /**
     * 所属校验类型
     */
    private CheckType checkType;

    private Double waveHeight;

    //中拱附加浮力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> mbb;

    //波峰未修正的静水剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> nwvecH;

    //波峰未修正的弯矩
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> mwvecH;

    //波峰修正的静水剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> nwvecMH;

    //波峰修正的弯矩
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> mwvecMH;

    //中垂附加浮力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> bdeltaS;

    //波谷未修正的静水剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> nwvecS;

    //波谷未修正的弯矩
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> mwvecS;
    //波谷修正的静水剪力
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> nwvecMS;

    //波谷修正的弯矩
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> mwvecMS;
}
