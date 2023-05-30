package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iscas.biz.calculation.entity.db.BulbFlat;
import com.iscas.biz.calculation.entity.db.BulkheadCompartment;
import com.iscas.biz.calculation.entity.db.TProfile;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.BulkheadCompartmentService;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.view.table.ComboboxData;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
@Service
public class BulkheadCompartmentServiceImpl extends ServiceImpl<BulkheadCompartmentMapper, BulkheadCompartment> implements BulkheadCompartmentService {

    private final ProjectMapper projectMapper;
    private final BulbFlatMapper bulbFlatMapper;
    private final TProfileMapper tProfileMapper;

    public BulkheadCompartmentServiceImpl(ProjectMapper projectMapper, BulbFlatMapper bulbFlatMapper, TProfileMapper tProfileMapper) {
        this.projectMapper = projectMapper;
        this.bulbFlatMapper = bulbFlatMapper;
        this.tProfileMapper = tProfileMapper;
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
}
