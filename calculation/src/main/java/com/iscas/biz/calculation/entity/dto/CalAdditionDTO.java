package com.iscas.biz.calculation.entity.dto;

import lombok.Data;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */

@Data
public class CalAdditionDTO {

    private Integer projectId;

    private Integer bulkheadId;

    private Double freeboard;

    private Double leiweihao;

    private Double cangbiWeizhi;

    private Boolean isCollision;

    private Double shuidongYali;
}
