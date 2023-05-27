package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.Weight;
import com.iscas.biz.calculation.entity.dto.WeightDTO;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 15:52
 */
public interface WeightService {
    Weight calculate(WeightDTO weight);
    Weight listByProjectId(Integer projectId);
}