package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 13:57
 * 舱壁区间
 */
@Data
public class BulkheadCompartment {
    @TableId(type = IdType.AUTO)
    private Integer compartmentId;

    private Integer projectId;
    /**
     * 横舱壁id
     */
    private Integer bulkheadId;
    /**
     * 距基线高度
     */
    private Double heightAbove;

    /**
     * 带板宽度
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> stripPlateWidth;

    /**
     * 扶强材规格
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> strengthMaterialSpecification;

    /**
     * 是否液舱
     */
    private Boolean liquid;
    /**
     * 板厚
     */
    private Double plateThickness;
    /**
     * 材料
     */
    private Double material;
    /**
     * 板宽
     */
    private Double plateWidth;
    /**
     * 区间
     */
    private Double compartment;

    private Date createTime;

    private Date updateTime;

    /**
     * 带板厚度
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Double> daibanhou;
}
