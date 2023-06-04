package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.CalAddition;
import com.iscas.biz.calculation.entity.db.Material;
import com.iscas.biz.calculation.entity.dto.CalAdditionDTO;
import com.iscas.biz.calculation.entity.dto.MaterialDTO;

import java.io.IOException;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */
public interface MaterialService {

    Material listBybulkheadId(Integer bulkheadId);

    void export(MaterialDTO materialDTO) throws IOException;

    Material calMaterial(MaterialDTO materialDTO);
}
