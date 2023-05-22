package com.iscas.biz.calculation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.iscas.biz.calculation.entity.db.BuoyancyResult;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/15 11:29
 */
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BuoyancyVO {
    @TableId(type = IdType.AUTO)
    private Integer paramId;

    private Integer projectId;

    /**
     * 静水力文件
     */
    private String buoyancyCurveFileName;

    private String buoyancyCurveFilePath;


    /**
     * 邦戎曲线Excel文件
     */
    private String bonjungCurveFileName;

    private String bonjungCurveFilePath;

    // 艏吃水（m）
    private double draftForward;

    // 艉吃水（m）
    private double draftAft;

    // 平均吃水（m）
    private double draftMean;

    //排水量精度
    private Double precisionDisplacement;

    //重心纵向精度
    private Double precisionGravity;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private BuoyancyResult buoyancyResult;
}
