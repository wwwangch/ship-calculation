package com.iscas.biz.calculation.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */
@Data
public class MaterialDTO {

    private Integer projectId;

    private Integer bulkheadId;

    /**
     * 扶强材型号
     */
    private List<String> guicaiType;

    /**
     * 带板宽
     */
    private List<Double> stripPlateWidth;

    /**
     * 上带板厚度
     */
    private List<Double> stripPlateThicknessUpper;

    /**
     * 下带板厚度
     */
    private List<Double> stripPlateThicknessLower;

    /**
     * 甲板纵骨跨距
     */
    private Double zongguKuaju;

    private List<String> guicaiTypeUppers;
    private List<String> guicaiTypeLowers;
    private List<Double> fuQiangCaiYieldLimits;

}
