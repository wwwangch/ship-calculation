package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/28 1:16
 */

@Data
public class DistExcel {

    @ExcelProperty("计算规范")
    private String calculationSpecification;

    @ExcelProperty("剖面文件名称")
    private String sectionFileName;

    @ExcelProperty("中拱极限弯矩")
    private Double extermeH;

    @ExcelProperty("中垂极限弯矩")
    private Double extermeS;
}
