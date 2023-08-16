package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.*;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.CalSectionService;
import com.iscas.biz.calculation.service.ShipParamService;
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

    private final ShipParamMapper shipParamMapper;
    private final ShipParamService shipParamService;

    public CalSectionServiceImpl(AlgorithmGrpc algorithmGrpc, CalSectionMapper calSectionMapper,
                                 TProfileMapper tProfileMapper, BulbFlatMapper bulbFlatMapper,
                                 ProjectMapper projectMapper, SectionMapper sectionMapper,
                                 ShipParamMapper shipParamMapper ,ShipParamService shipParamService) {
        this.algorithmGrpc = algorithmGrpc;
        this.calSectionMapper = calSectionMapper;
        this.tProfileMapper = tProfileMapper;
        this.bulbFlatMapper = bulbFlatMapper;
        this.projectMapper = projectMapper;
        this.sectionMapper = sectionMapper;
        this.shipParamMapper = shipParamMapper;
        this.shipParamService = shipParamService;
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
        //填充所属工况
        ShipParam shipParam = shipParamMapper.selectById(projectId);
        calSection.setCheckType(shipParam.getCurrentType());
        //查询当前工况下有无数据
        CalSection section = listBySectionIdId(calSectionDTO.getSectionId());
        //删除当前工况下数据
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
        queryWrapper.eq("section_id",sectionId);
        CalSection searchProjectId = calSectionMapper.selectById(queryWrapper);
        //增加工况查询条件
        shipParamService.addCheckTypeCondition(queryWrapper, searchProjectId.getProjectId());
        List<CalSection> calSections =calSectionMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(calSections)){
            return null;
        }
        //提取出第一个数据放回显示，其余的都删除，此处已经做了工况筛选的处理
        CalSection calSection = calSections.remove(0);
        if(CollectionUtils.isNotEmpty(calSections)){
            calSectionMapper.deleteBatchIds(calSections.stream().map(CalSection::getCalSectionId).toList());
        }
        return calSection;
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
