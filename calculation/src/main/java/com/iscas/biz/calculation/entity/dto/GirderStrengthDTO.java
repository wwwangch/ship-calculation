package com.iscas.biz.calculation.entity.dto;

import lombok.Data;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 16:38
 */
@Data
public class GirderStrengthDTO {
    private Integer projectId;

    /**
     * 构件跨距
     */
    private Double kuaChang;

    /**
     * 剖面位置x
     */
    private Double girderDistance;
}
