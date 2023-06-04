package com.iscas.biz.calculation.entity.dto;

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
public class MaterialDTO {

    private Integer projectId;

    private Integer bulkheadId;

    /**
     * 扶强材型号
     */
//    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> guicaiType;

    /**
     * 带板宽
     */
//    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> daibanKuan;

    /**
     * 带板厚
     */
//    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> daibanHou;

    /**
     * 甲板纵骨跨距
     */
    private Double zongguKuaju;

}
