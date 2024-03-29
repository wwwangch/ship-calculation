package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.iscas.biz.calculation.enums.CheckType;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 16:41
 */
@Data
public class BuoyancyParam {
    @TableId(type = IdType.AUTO)
    private Integer paramId;

    private Integer projectId;

    /**
     * 所属校验类型
     */
    private CheckType checkType;

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
