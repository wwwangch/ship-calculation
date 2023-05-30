package com.iscas.biz.calculation.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 13:57
 * 剖面-用于总纵强度计算
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
     * 带板宽度
     */
    private String stripPlateWidth;

    /**
     * 扶强材规格
     */
    private String strengthMaterialSpecification;

    /**
     * 是否液舱
     */
    private String liquid;
    /**
     * 板厚
     */
    private String plateThickness;
    /**
     * 材料
     */
    private String material;
    /**
     * 板宽
     */
    private String plateWidth;
    /**
     * 区间
     */
    private String compartment;

    private Date createTime;

    private Date updateTime;
}
