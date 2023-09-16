package com.iscas.biz.calculation.entity.db;

import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 16:44
 */
@Data
public class WeightDistribution {
    private String name;

    private List<Number> weightItems;
}
