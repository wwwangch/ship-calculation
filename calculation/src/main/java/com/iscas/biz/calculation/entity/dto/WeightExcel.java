package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 15:31
 */
@Data
public class WeightExcel {
    @ExcelProperty("站号")
    private Integer code;
    @ExcelProperty("校核b(x)")
    private Double blist;
}
