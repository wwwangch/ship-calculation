package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.CalAddition;
import com.iscas.biz.calculation.entity.dto.CalAdditionDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */
public interface CalAdditionService {

    CalAddition listByBulkheadId(Integer projectId, Integer bulkheadId);

    void export(Integer projectId, Integer bulkheadId) throws IOException;

    CalAddition calAdditional(CalAdditionDTO calAdditionDTO);
}
