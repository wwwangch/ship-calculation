package com.iscas.biz.calculation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iscas.biz.calculation.entity.db.BulkheadCheckResult;
import com.iscas.biz.calculation.entity.db.BulkheadCompartment;
import com.iscas.biz.calculation.entity.dto.BulkheadDTO;
import com.iscas.templet.view.table.ComboboxData;

import java.io.IOException;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
public interface BulkheadCompartmentService extends IService<BulkheadCompartment> {

    Boolean deleteByIds(List<Integer> ids);

    Integer update(BulkheadCompartment compartment) throws IOException;

    List<ComboboxData> getCascader();

    BulkheadCheckResult checkBulkhead(BulkheadDTO bulkheadDTO);

    BulkheadCheckResult listResultByBulkheadId(Integer bulkheadId);
}
