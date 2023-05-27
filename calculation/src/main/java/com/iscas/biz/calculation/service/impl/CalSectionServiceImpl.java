package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.calculation.entity.db.CalSection;
import com.iscas.biz.calculation.entity.db.Weight;
import com.iscas.biz.calculation.entity.dto.CalSectionDTO;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.CalSectionMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.WeightMapper;
import com.iscas.biz.calculation.service.CalSectionService;
import com.iscas.biz.calculation.service.WeightService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 15:52
 */
@Service
@Slf4j
public class CalSectionServiceImpl implements CalSectionService {
    private final AlgorithmGrpc algorithmGrpc;
    private final CalSectionMapper calSectionMapper;

    private final ProjectMapper projectMapper;

    public CalSectionServiceImpl(AlgorithmGrpc algorithmGrpc, CalSectionMapper calSectionMapper, ProjectMapper projectMapper) {
        this.algorithmGrpc = algorithmGrpc;
        this.calSectionMapper = calSectionMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public CalSection calculate(CalSectionDTO calSectionDTO) {
        Integer projectId = calSectionDTO.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }
        CalSection calSection = algorithmGrpc.calSection(calSectionDTO);
        CalSection section = listByProjectId(projectId);
        if (null != section) {
            Integer sectionId = section.getCalSectionId();
            calSection.setCalSectionId(sectionId);
            calSectionMapper.deleteById(sectionId);
        }
        calSectionMapper.insert(calSection);
        return calSection;
    }

    @Override
    public CalSection listByProjectId(Integer projectId) {
        QueryWrapper<CalSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<CalSection> calSections = calSectionMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(calSections)) {
            if (calSections.size() > 1) {
                for (int i = 1; i < calSections.size(); i++) {
                    calSectionMapper.deleteById(calSections.get(i));
                }
            }
            return calSections.get(0);
        }
        return null;
    }
}
