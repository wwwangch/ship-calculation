package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

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
     * 甲板名称
     */
    private String name;
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
    private Double stripPlateWidth;

    /**
     * 上带板厚度
     */
    private Double stripPlateThicknessUpper;

    /**
     * 下带板厚度
     */
    private Double stripPlateThicknessLower;

    /**
     * 扶强材规格
     */
    private String strengthMaterialSpecification;

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
    private String material;
    /**
     * 板宽
     */
    private Double plateWidth;
    /**
     * 区间
     */
    private String compartment;

    private Date createTime;

    private Date updateTime;



    /**
     * 上部支撑纵骨规格
     */
    private String guicaiTypeUpper;

    /**
     * 下部支撑纵骨规格
     */
    private String guicaiTypeLower;
}
