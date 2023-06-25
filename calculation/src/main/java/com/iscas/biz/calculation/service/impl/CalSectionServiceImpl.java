package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.*;
import com.iscas.biz.calculation.grpc.SectionRequest;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.CalSectionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
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
    private final TProfileMapper tProfileMapper;
    private final BulbFlatMapper bulbFlatMapper;
    private final ProjectMapper projectMapper;
    private final SectionMapper sectionMapper;

    public CalSectionServiceImpl(AlgorithmGrpc algorithmGrpc, CalSectionMapper calSectionMapper,
                                 TProfileMapper tProfileMapper, BulbFlatMapper bulbFlatMapper,
                                 ProjectMapper projectMapper, SectionMapper sectionMapper) {
        this.algorithmGrpc = algorithmGrpc;
        this.calSectionMapper = calSectionMapper;
        this.tProfileMapper = tProfileMapper;
        this.bulbFlatMapper = bulbFlatMapper;
        this.projectMapper = projectMapper;
        this.sectionMapper = sectionMapper;
    }

    @Override
    @Transactional
    public CalSection calculate(CalSectionDTO calSectionDTO) {
        Integer projectId = calSectionDTO.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }
        Section selectById = sectionMapper.selectById(calSectionDTO.getSectionId());
        calSectionDTO.setProfileFilePathOld(selectById.getSectionFilePath());
        calSectionDTO.setProfileFileName(selectById.getSectionFileName());
        calSectionDTO.setRibNumber(selectById.getRibNumber());
        String isHalfProfile = selectById.getIsHalfProfile();
        if (isHalfProfile == null) {
            return null;
        }
        if ("1".equals(isHalfProfile)) {
            calSectionDTO.setHalfProfile(true);
        } else {
            calSectionDTO.setHalfProfile(false);
        }
        List<TProfile> tProfiles = tProfileMapper.selectList(null);
        List<BulbFlat> bulbFlats = bulbFlatMapper.selectList(null);
        List<com.iscas.biz.calculation.grpc.TProfile> toTProfiles = new ArrayList<>();
        for (TProfile tProfile : tProfiles) {
            toTProfiles.add(com.iscas.biz.calculation.grpc.TProfile
                    .newBuilder()
                    .setModel(tProfile.getModel())
                    .setKeelHeight(tProfile.getKeelHeight())
                    .setKeelThickness(tProfile.getKeelThickness())
                    .setDeckWidth(tProfile.getDeckWidth())
                    .setDeckThickness(tProfile.getDeckThickness())
                    .setSectionalArea(tProfile.getSectionalArea())
                    .setMomentOfInertia(tProfile.getMomentOfInertia())
                    .setCentroidPosition(tProfile.getCentroidPosition())
                    .build());
        }
        List<com.iscas.biz.calculation.grpc.BulbFlat> toBulbFlats = new ArrayList<>();
        for (BulbFlat bulbFlat : bulbFlats) {
            toBulbFlats.add(com.iscas.biz.calculation.grpc.BulbFlat
                    .newBuilder()
                    .setModel(bulbFlat.getModel())
                    .setHeight(bulbFlat.getHeight())
                    .setWidth(bulbFlat.getWidth())
                    .setThickness(bulbFlat.getThickness())
                    .setSectionalArea(bulbFlat.getSectionalArea())
                    .setMomentOfInertia(bulbFlat.getMomentOfInertia())
                    .setCentroidPosition(bulbFlat.getCentroidPosition())
                    .build());
        }
        calSectionDTO.setBulbFlats(toBulbFlats);
        calSectionDTO.setTProfiles(toTProfiles);
        CalSection calSection = algorithmGrpc.calSection(calSectionDTO);
        CalSection section = listBySectionIdId(calSectionDTO.getSectionId());
        if (null != section) {
            Integer sectionId = section.getCalSectionId();
            calSection.setCalSectionId(sectionId);
            calSectionMapper.deleteById(sectionId);
        }
        calSection.setSectionId(calSectionDTO.getSectionId());
        calSectionMapper.insert(calSection);
        return calSection;
    }

    @Override
    public CalSection listBySectionIdId(Integer sectionId) {
        QueryWrapper<CalSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CalSection::getSectionId, sectionId);
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

//    @Override
//    public void export(Integer projectId) throws IOException {
//        Project project = projectMapper.selectById(projectId);
//        if (null == project) {
//            throw new RuntimeException("当前项目不存在!");
//        }
//        QueryWrapper<CalSection> calSectionQueryWrapper = new QueryWrapper<>();
//        calSectionQueryWrapper.lambda().eq(CalSection::getProjectId, projectId);
//        CalSection calSection = calSectionMapper.selectOne(calSectionQueryWrapper);
//        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
//                .autoTrim(true).build();
//
//        CalSectionParamExcel calSectionParamExcel = new CalSectionParamExcel();
//        calSectionParamExcel.setCalculationSpecification(project.getCalculationSpecification().getDescCH());
//        calSectionParamExcel.setProfileFileName(calSection.getProfileFileName());
//        calSectionParamExcel.setFirstMoment0(calSection.getFirstMoment0());
//        calSectionParamExcel.setInteria0(calSection.getInteria0());
//        calSectionParamExcel.setZaxisH(calSection.getZaxisH());
//        calSectionParamExcel.setFirstMomH(calSection.getFirstMomH());
//        calSectionParamExcel.setInteriaH(calSection.getInteriaH());
//        calSectionParamExcel.setZaxisS(calSection.getZaxisS());
//        calSectionParamExcel.setFirstMomS(calSection.getFirstMomS());
//        calSectionParamExcel.setInteriaS(calSection.getInteriaS());
//
//        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();
//
//        WriteTable paramTable = EasyExcel.writerTable(0).head(BuoyancyParamExcel.class).needHead(true).build();
//        excelWriter.write(Lists.newArrayList(calSectionParamExcel), writeSheet, paramTable);
//        excelWriter.finish();
//    }
}
