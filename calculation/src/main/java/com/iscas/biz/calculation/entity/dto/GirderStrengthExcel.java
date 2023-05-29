package com.iscas.biz.calculation.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/12 15:31
 */
@Data
public class GirderStrengthExcel {
    @ExcelProperty("计算规范")
    private String calculationSpecification;

    /**
     * 构件跨距
     */
    @ExcelProperty("构件跨距")
    private Double kuaChang;

    /**
     * 剖面位置x
     */
    @ExcelProperty("剖面位置x")
    private Double girderDistance;


    /**
     * 中拱支座处
     */
    @ExcelProperty("中拱支座处")
    private Double sigma1SH;
    /**
     * 中拱跨中处
     */
    @ExcelProperty("中拱跨中处")
    private Double sigma1MidH;
    /**
     * 中垂支座处
     */
    @ExcelProperty("中垂支座处")
    private Double sigma1SS;
    /**
     * 中垂跨中处
     */
    @ExcelProperty("中垂跨中处")
    private Double sigma1MidS;
    /**
     * 中拱不同桁材处的支座处的sigma2
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress2SH;
    /**
     * 中拱不同桁材处的跨中处的sigma2
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress2MidH;

    /**
     * 中拱骨材上纤维处的应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress3UpH;

    /**
     * 中拱骨材下纤维处的应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress3DownH;

    /**
     * 中拱板格的上表面
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress4UpH;
    /**
     * 中拱板格的下表面
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress4downH;
    /**
     * 中垂不同桁材处的支座处的sigma2
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress2SS;
    /**
     * 中垂不同桁材处的跨中处的sigma2
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress2MidS;
    /**
     * 中垂骨材上纤维处的应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress3UpS;
    /**
     * 中垂骨材下纤维处的应力
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress3DownS;
    /**
     * 中垂板格的上表面
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress4UpS;
    /**
     * 中垂板格的下表面
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stress4downS;
}
