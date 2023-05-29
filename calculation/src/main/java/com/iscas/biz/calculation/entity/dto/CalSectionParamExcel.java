package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 15:31
 */
@Data
public class CalSectionParamExcel {
    @ExcelProperty("计算规范")
    private String calculationSpecification;
    @ExcelProperty("载重文件")
    private String profileFileName;
    // 初始静矩
    @ExcelProperty("初始静矩")
    private double firstMoment0;

    // 惯性矩
    @ExcelProperty("惯性矩")
    private double interia0;

    // 中拱时的中和轴
    @ExcelProperty("中拱时的中和轴")
    private double zaxisH;

    //中拱时静矩
    @ExcelProperty("中拱时静矩")
    private Double firstMomH;

    //中拱时惯性矩
    @ExcelProperty("中拱时惯性矩")
    private Double interiaH;

    //中垂时的中和轴
    @ExcelProperty("中垂时的中和轴")
    private Double zaxisS;

    //中垂时静矩
    @ExcelProperty("中垂时静矩")
    private Double firstMomS;


    //中垂时惯性矩
    @ExcelProperty("中垂时惯性矩")
    private Double interiaS;

}
