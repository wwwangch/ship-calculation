package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 15:31
 */
@Data
public class BuoyancyParamExcel {
    @ExcelProperty("计算规范")
    private String calculationSpecification;
    @ExcelProperty("静水力文件")
    private String buoyancyCurveFileName;
    @ExcelProperty("邦戎曲线文件")
    private String bonjungCurveFileName;
    // 艏吃水（m）
    @ExcelProperty("艏吃水（m）")
    private double draftForward;

    // 艉吃水（m）
    @ExcelProperty("艉吃水（m）")
    private double draftAft;

    // 平均吃水（m）
    @ExcelProperty("平均吃水（m）")
    private double draftMean;

    //排水量精度
    @ExcelProperty("排水量精度")
    private Double precisionDisplacement;

    //重心纵向精度
    @ExcelProperty("重心纵向精度")
    private Double precisionGravity;

    public String getCalculationSpecification() {
        return calculationSpecification;
    }

    public String getBuoyancyCurveFileName() {
        return buoyancyCurveFileName;
    }

    public String getBonjungCurveFileName() {
        return bonjungCurveFileName;
    }

    public double getDraftForward() {
        return draftForward;
    }

    public double getDraftAft() {
        return draftAft;
    }

    public double getDraftMean() {
        return draftMean;
    }

    public Double getPrecisionDisplacement() {
        return precisionDisplacement;
    }

    public Double getPrecisionGravity() {
        return precisionGravity;
    }
}
