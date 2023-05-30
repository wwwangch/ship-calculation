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
import com.iscas.biz.calculation.mapper.GirderStrengthMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.WeightMapper;
import com.iscas.biz.calculation.service.GirderStrengthService;
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
public class GirderStrengthServiceImpl implements GirderStrengthService {
    private final AlgorithmGrpc algorithmGrpc;
    private final GirderStrengthMapper girderStrengthMapper;

    private final ProjectMapper projectMapper;

    public GirderStrengthServiceImpl(AlgorithmGrpc algorithmGrpc, GirderStrengthMapper girderStrengthMapper, ProjectMapper projectMapper) {
        this.algorithmGrpc = algorithmGrpc;
        this.girderStrengthMapper = girderStrengthMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public GirderStrength calculate(GirderStrengthDTO girderStrengthDTO) {
        Integer projectId = girderStrengthDTO.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }
        GirderStrength girderStrength = algorithmGrpc.calGirderStrength(girderStrengthDTO);
        GirderStrength dbGirderStrength = listByProjectId(projectId);
        if (null != dbGirderStrength) {
            Integer girderStrengthId = dbGirderStrength.getGirderStrengthId();
            girderStrength.setGirderStrengthId(girderStrengthId);
            girderStrengthMapper.deleteById(girderStrengthId);
        }
        girderStrengthMapper.insert(girderStrength);
        return girderStrength;
    }

    @Override
    public GirderStrength listByProjectId(Integer projectId) {
        QueryWrapper<GirderStrength> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<GirderStrength> girderStrengths = girderStrengthMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(girderStrengths)) {
            if (girderStrengths.size() > 1) {
                for (int i = 1; i < girderStrengths.size(); i++) {
                    girderStrengthMapper.deleteById(girderStrengths.get(i));
                }
            }
            return girderStrengths.get(0);
        }
        return null;
    }

    @Override
    public void export(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<GirderStrength> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        GirderStrength girderStrength = girderStrengthMapper.selectOne(queryWrapper);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();
        GirderStrengthExcel girderStrengthExcel = new GirderStrengthExcel();
        girderStrengthExcel.setCalculationSpecification(project.getCalculationSpecification().getDescCH());
        girderStrengthExcel.setKuaChang(girderStrength.getKuaChang());
        girderStrengthExcel.setGirderDistance(girderStrength.getGirderDistance());
        girderStrengthExcel.setSigma1SH(girderStrength.getSigma1SH());
        girderStrengthExcel.setSigma1MidH(girderStrength.getSigma1MidH());
        girderStrengthExcel.setSigma1SS(girderStrength.getSigma1SS());
        girderStrengthExcel.setSigma1MidS(girderStrength.getSigma1MidS());
        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();
        WriteTable paramTable = EasyExcel.writerTable(0).head(BuoyancyParamExcel.class).needHead(true).build();
        excelWriter.write(Lists.newArrayList(girderStrengthExcel), writeSheet, paramTable);
        excelWriter.finish();
    }
}
