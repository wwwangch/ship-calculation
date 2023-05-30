package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.*;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.CalSectionMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.WeightMapper;
import com.iscas.biz.calculation.service.CalSectionService;
import com.iscas.biz.calculation.service.WeightService;
import com.iscas.common.web.tools.json.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    @Override
    public void export(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<CalSection> calSectionQueryWrapper = new QueryWrapper<>();
        calSectionQueryWrapper.lambda().eq(CalSection::getProjectId, projectId);
        CalSection calSection = calSectionMapper.selectOne(calSectionQueryWrapper);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();

        CalSectionParamExcel calSectionParamExcel = new CalSectionParamExcel();
        calSectionParamExcel.setCalculationSpecification(project.getCalculationSpecification().getDescCH());
        calSectionParamExcel.setProfileFileName(calSection.getProfileFileName());
        calSectionParamExcel.setFirstMoment0(calSection.getFirstMoment0());
        calSectionParamExcel.setInteria0(calSection.getInteria0());
        calSectionParamExcel.setZaxisH(calSection.getZaxisH());
        calSectionParamExcel.setFirstMomH(calSection.getFirstMomH());
        calSectionParamExcel.setInteriaH(calSection.getInteriaH());
        calSectionParamExcel.setZaxisS(calSection.getZaxisS());
        calSectionParamExcel.setFirstMomS(calSection.getFirstMomS());
        calSectionParamExcel.setInteriaS(calSection.getInteriaS());

        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();

        WriteTable paramTable = EasyExcel.writerTable(0).head(BuoyancyParamExcel.class).needHead(true).build();
        excelWriter.write(Lists.newArrayList(calSectionParamExcel), writeSheet, paramTable);
        excelWriter.finish();
    }
}
