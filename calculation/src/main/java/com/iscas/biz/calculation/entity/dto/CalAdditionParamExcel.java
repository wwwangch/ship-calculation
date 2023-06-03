package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */

@Data
public class CalAdditionParamExcel {


    /**
     *肋位号数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> leiweihaos;

    /**
     *附加压头数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> addyatouh;
}
