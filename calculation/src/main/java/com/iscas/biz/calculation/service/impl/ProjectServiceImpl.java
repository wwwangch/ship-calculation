package com.iscas.biz.calculation.service.impl;

import com.google.common.collect.Lists;
import com.iscas.biz.calculation.enums.CalculationSpecification;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.ProjectService;
import com.iscas.templet.view.table.ComboboxData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 16:28
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public boolean deleteByIds(List<Integer> ids) {
        try {
            projectMapper.deleteBatchIds(ids);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除项目数据时异常", e);
        }
    }

    @Override
    public List<ComboboxData> listCalculationSpecificationCombobox() {
        List<ComboboxData> result = Lists.newArrayList();
        CalculationSpecification[] calculationSpecifications = CalculationSpecification.values();
        for (CalculationSpecification calculationSpecification : calculationSpecifications) {
            ComboboxData comboboxData = new ComboboxData();
            comboboxData.setLabel(calculationSpecification.getDescCH());
            comboboxData.setValue(calculationSpecification.getValue());
            result.add(comboboxData);
        }
        return result;
    }
}
