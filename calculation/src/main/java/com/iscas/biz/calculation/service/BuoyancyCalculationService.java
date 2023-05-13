package com.iscas.biz.calculation.service;

import com.iscas.biz.calculation.entity.db.BuoyancyParam;
import com.iscas.biz.calculation.entity.db.BuoyancyResult;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.biz.calculation.grpc.BuoyancyResponse;
import com.iscas.biz.calculation.grpc.ShipParamResponse;
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

    ShipParamResponse callShipParam(Project project, ShipParam shipParam);

    BuoyancyResponse callBuoyancy(ShipParam shipParam, BuoyancyParam buoyancyParam);

    Boolean reset(Integer projectId);

    void export(Integer projectId) throws IOException;

    BuoyancyParam listParamByProjectId(Integer projectId);

    BuoyancyResult listResultByParamId(Integer paramId);

    BuoyancyParam getData(Integer projectId);

}
