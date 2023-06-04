package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.BulkheadDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.BulkheadCompartmentService;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.templet.view.table.ComboboxData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
@Service
public class BulkheadCompartmentServiceImpl extends ServiceImpl<BulkheadCompartmentMapper, BulkheadCompartment> implements BulkheadCompartmentService {

    private final ProjectMapper projectMapper;

    private final ShipParamMapper shipParamMapper;
    private final BulbFlatMapper bulbFlatMapper;
    private final TProfileMapper tProfileMapper;

    private final AlgorithmGrpc algorithmGrpc;

    private final BulkheadCheckResultMapper bulkheadCheckResultMapper;

    public BulkheadCompartmentServiceImpl(ProjectMapper projectMapper, ShipParamMapper shipParamMapper, BulbFlatMapper bulbFlatMapper, TProfileMapper tProfileMapper, AlgorithmGrpc algorithmGrpc, BulkheadCheckResultMapper bulkheadCheckResultMapper) {
        this.projectMapper = projectMapper;
        this.shipParamMapper = shipParamMapper;
        this.bulbFlatMapper = bulbFlatMapper;
        this.tProfileMapper = tProfileMapper;
        this.algorithmGrpc = algorithmGrpc;
        this.bulkheadCheckResultMapper = bulkheadCheckResultMapper;
    }

    @Override
    public Integer update(BulkheadCompartment compartment) {
        Integer projectId = compartment.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", projectId));
        }
        return this.updateById(compartment) ? 1 : 0;
    }

    @Override
    public List<ComboboxData> getCascader() {
        List<BulbFlat> bulbFlats = bulbFlatMapper.selectList(null);
        List<TProfile> tProfiles = tProfileMapper.selectList(null);
        List<ComboboxData> comboboxData = new ArrayList<>();
        ComboboxData qData = new ComboboxData<>();
        comboboxData.add(qData);
        qData.setLabel("球扁钢");
        qData.setValue("q");
        List<ComboboxData> qDataList = new ArrayList<>();
        qData.setChildren(qDataList);
        for (BulbFlat bulbFlat : bulbFlats) {
            String label = bulbFlat.getModel();
            String value = bulbFlat.getProfileId().toString();
            ComboboxData data = new ComboboxData();
            data.setValue(value);
            data.setLabel(label);
            qDataList.add(data);
        }
        ComboboxData tData = new ComboboxData<>();
        comboboxData.add(qData);
        qData.setLabel("T型材");
        qData.setValue("t");
        List<ComboboxData> tDataList = new ArrayList<>();
        for (TProfile tProfile : tProfiles) {
            String label = tProfile.getModel();
            String value = tProfile.getProfileId().toString();
            ComboboxData data = new ComboboxData();
            data.setValue(value);
            data.setLabel(label);
            tDataList.add(data);
        }
        comboboxData.add(tData);
        return comboboxData;
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                return this.removeByIds(ids);
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("删除剖面数据时异常", e);
        }
    }

    @Override
    public BulkheadCheckResult checkBulkhead(BulkheadDTO bulkheadDTO) {
        //根据项目Id查询船舶参数
        Integer projectId = bulkheadDTO.getProjectId();
        QueryWrapper<ShipParam> shipParamQueryWrapper = new QueryWrapper<>();
        shipParamQueryWrapper.eq("project_id", projectId);
        List<ShipParam> shipParams = shipParamMapper.selectList(shipParamQueryWrapper);
        if (CollectionUtils.isEmpty(shipParams)) {
            throw new RuntimeException("当前项目船舶参数为空");
        }
        ShipParam shipParam = shipParams.get(0);

        //根据区间id获取区间数据
        QueryWrapper<BulkheadCompartment> bulkheadCompartmentQueryWrapper = new QueryWrapper<>();
        bulkheadCompartmentQueryWrapper.eq("bulkhead_id", bulkheadDTO.getBulkheadId());
        List<BulkheadCompartment> bulkheadCompartments = this.list(bulkheadCompartmentQueryWrapper);
        if (CollectionUtils.isEmpty(bulkheadCompartments)) {
            throw new RuntimeException("当前舱壁区间数据为空");
        }

        BulkheadCheckResult calBulkheadCheckResult = algorithmGrpc.calBulkheadCheck(bulkheadDTO.getBulkheadId(), shipParam, bulkheadCompartments);
        BulkheadCheckResult dbBulkheadCheckResult = listResultByBulkheadId(bulkheadDTO.getBulkheadId());
        if (null != dbBulkheadCheckResult) {
            Integer bulkheadResultId = dbBulkheadCheckResult.getBulkheadResultId();
            calBulkheadCheckResult.setBulkheadResultId(bulkheadResultId);
            bulkheadCheckResultMapper.deleteById(bulkheadResultId);
        }
        bulkheadCheckResultMapper.insert(calBulkheadCheckResult);
        return calBulkheadCheckResult;
    }

    @Override
    public BulkheadCheckResult listResultByBulkheadId(Integer bulkheadId) {
        QueryWrapper<BulkheadCheckResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id", bulkheadId);
        List<BulkheadCheckResult> bulkheadCheckResults = bulkheadCheckResultMapper.selectList(queryWrapper);
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(bulkheadCheckResults)) {
            if (bulkheadCheckResults.size() > 1) {
                for (int i = 1; i < bulkheadCheckResults.size(); i++) {
                    bulkheadCheckResultMapper.deleteById(bulkheadCheckResults.get(i));
                }
            }
            return bulkheadCheckResults.get(0);
        }
        return null;
    }
}
