package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 10:57
 */
@Data
public class Buoyant {
    @ExcelProperty("第几次计算")
    private Integer index;
    //浮力
    @ExcelProperty("浮力")
    private double floatforce;
    //浮力位置
    @ExcelProperty("浮力位置")
    private double floatxpos;
    //船尾吃水
    @ExcelProperty("船艉吃水")
    private double da;
    //船中吃水
    @ExcelProperty("船中吃水")
    private double dm;
    //船首吃水
    @ExcelProperty("船艏吃水")
    private double df;
    //浮力偏差 重心平衡精度
    @ExcelProperty("浮力偏差-重量平衡精度")
    private double biasf;
    //浮力位置偏差
    @ExcelProperty("浮力位置偏差-重心平衡精度")
    private double biasx;
}
