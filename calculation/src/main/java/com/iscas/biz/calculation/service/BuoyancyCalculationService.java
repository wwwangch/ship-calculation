package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.BuoyancyVO;
import com.iscas.biz.calculation.entity.db.BuoyancyParam;
import com.iscas.biz.calculation.entity.db.BuoyancyResult;
import com.iscas.templet.exception.ValidDataException;

import java.io.IOException;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 16:48
 */
public interface BuoyancyCalculationService {

    Integer save(Map<String, Object> data) throws ValidDataException;

    int remove(Integer projectId);

    BuoyancyResult saveAndCalculate(Map<String, Object> data) throws ValidDataException;

    Boolean reset(Integer projectId);

    void export(Integer projectId) throws IOException;

    BuoyancyParam listParamByProjectId(Integer projectId);

    BuoyancyResult listResultByParamId(Integer paramId);

    BuoyancyVO getData(Integer projectId);

}
