package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.property.ColumnWidthProperty;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.Moment;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.db.Section;
import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.sigma.Sigma1DTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.StrengthService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/6/1 9:08
 */
@Service
public class StrengthServiceImpl implements StrengthService {
    private final Sigma1Mapper sigma1Mapper;

    private final Sigma2Mapper sigma2Mapper;

    private final Sigma3Mapper sigma3Mapper;

    private final Sigma4Mapper sigma4Mapper;

    private final ShearingStressMapper shearingStressMapper;

    private final AlgorithmGrpc algorithmGrpc;

    private final SectionMapper sectionMapper;

    private final MomentMapper momentMapper;

    public StrengthServiceImpl(Sigma1Mapper sigma1Mapper, Sigma2Mapper sigma2Mapper, Sigma3Mapper sigma3Mapper, Sigma4Mapper sigma4Mapper, ShearingStressMapper shearingStressMapper, AlgorithmGrpc algorithmGrpc, SectionMapper sectionMapper, MomentMapper momentMapper) {
        this.sigma1Mapper = sigma1Mapper;
        this.sigma2Mapper = sigma2Mapper;
        this.sigma3Mapper = sigma3Mapper;
        this.sigma4Mapper = sigma4Mapper;
        this.shearingStressMapper = shearingStressMapper;
        this.algorithmGrpc = algorithmGrpc;
        this.sectionMapper = sectionMapper;
        this.momentMapper = momentMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Sigma1> getSigma1(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma1> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma1> sigma1List = sigma1Mapper.selectList(queryWrapper);
        return sigma1List;
    }

    @Override
    public List<Sigma2> getSigma2(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma2> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma2> sigma2List = sigma2Mapper.selectList(queryWrapper);
        return sigma2List;
    }

    @Override
    public List<Sigma3> getSigma3(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma3> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma3> sigma3List = sigma3Mapper.selectList(queryWrapper);
        return sigma3List;
    }

    @Override
    public List<Sigma4> getSigma4(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma4> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma4> sigma4List = sigma4Mapper.selectList(queryWrapper);
        return sigma4List;
    }

    @Override
    public List<ShearingStress> getShearingStress(Integer projectId, Integer sectionId) {
        QueryWrapper<ShearingStress> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<ShearingStress> shearingStressList = shearingStressMapper.selectList(queryWrapper);
        return shearingStressList;
    }

    @Override
    public List<Sigma1> calSigma1(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        QueryWrapper<Section> sectionQueryWrapper = new QueryWrapper();
        sectionQueryWrapper.eq("project_id", projectId);
        sectionQueryWrapper.eq("section_id", sectionId);
        Section section = sectionMapper.selectOne(sectionQueryWrapper);
        Sigma1DTO sigma1DTO = new Sigma1DTO();
        QueryWrapper<Moment> momentQueryWrapper = new QueryWrapper<>();
        momentQueryWrapper.eq("project_id", projectId);
        momentQueryWrapper.eq("section_id", sectionId);
        Moment moment = momentMapper.selectOne(momentQueryWrapper);
        //复制属性
        copyFields(section, sigma1DTO);
        copyFields(moment, sigma1DTO);
        List<Sigma1> calSigma1List = algorithmGrpc.calSigma1(sigma1DTO);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma1> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma1Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma1List.size(); i++) {
            sigma1Mapper.insert(calSigma1List.get(i));
        }
        return calSigma1List;
    }


    @Override
    public List<Sigma2> calSigma2(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<Sigma2> calSigma2List = algorithmGrpc.calSigma2(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma2> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma2Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma2List.size(); i++) {
            sigma2Mapper.insert(calSigma2List.get(i));
        }
        return calSigma2List;
    }

    @Override
    public List<Sigma3> calSigma3(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<Sigma3> calSigma3List = algorithmGrpc.calSigma3(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma3> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma3Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma3List.size(); i++) {
            sigma3Mapper.insert(calSigma3List.get(i));
        }
        return calSigma3List;
    }

    @Override
    public List<Sigma4> calSigma4(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<Sigma4> calSigma4List = algorithmGrpc.calSigma4(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<Sigma4> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        sigma4Mapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calSigma4List.size(); i++) {
            sigma4Mapper.insert(calSigma4List.get(i));
        }
        return calSigma4List;
    }

    @Override
    public List<ShearingStress> calShearingStress(Integer projectId, Integer sectionId) throws IllegalAccessException {
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
        List<ShearingStress> calShearingStressList = algorithmGrpc.calShearingStress(projectId, sectionId);
        //清空该项目该剖面历史数据
        UpdateWrapper<ShearingStress> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.eq("section_id", sectionId);
        shearingStressMapper.delete(updateWrapper);
        //插入新数据
        for (int i = 0; i < calShearingStressList.size(); i++) {
            shearingStressMapper.insert(calShearingStressList.get(i));
        }
        return calShearingStressList;
    }

    public static <T, U> void copyFields(T sourceObj, U destObj) throws IllegalAccessException {
        Field[] sourceFields = sourceObj.getClass().getDeclaredFields();
        Field[] destFields = destObj.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            String sourceFieldName = sourceField.getName();
            Object sourceFieldValue = sourceField.get(sourceObj);

            for (Field destField : destFields) {
                destField.setAccessible(true);
                String destFieldName = destField.getName();
                Object destFieldValue = destField.get(destObj);

                if (sourceFieldName.equals(destFieldName) &&
                        sourceFieldValue != null &&
                        sourceFieldValue.getClass().equals(destField.getType())) {
                    destField.set(destObj, sourceFieldValue);
                }
            }
        }
    }


    @Override
    public void sigma1Export(Integer projectId, Integer sectionId) throws IOException {
        QueryWrapper<Sigma1> sigma1QueryWrapper = new QueryWrapper<>();
        sigma1QueryWrapper.eq("project_id", projectId);
        sigma1QueryWrapper.eq("section_id", sectionId);
        List<Sigma1> sigma1List = sigma1Mapper.selectList(sigma1QueryWrapper);
        if (null == sigma1List || sigma1List.size() == 0) {
            throw new RuntimeException("当前数据不存在!");
        }
        List<List<String>> headList = new ArrayList<>();
        if (sigma1List.size() > 0) {
            List<String> head0 = new ArrayList<>();
            head0.add("");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("中拱");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("中垂");
            headList.add(head2);
        }

        List<Sigma1Export> sigma1ExportList = new ArrayList<>();
        for (int i = 0; i < sigma1List.size(); i++) {
            int number = i + 1;
            Sigma1Export sigma1Export1 = new Sigma1Export();
            sigma1Export1.setName("龙骨" + number + "上纤维（σ1）");
            sigma1Export1.setZhonggong(sigma1List.get(i).getSigma1HUp());
            sigma1Export1.setZhongchui(sigma1List.get(i).getSigma1SUp());
            Sigma1Export sigma1Export2 = new Sigma1Export();
            sigma1Export2.setName("龙骨" + number + "下纤维（σ1）");
            sigma1Export2.setZhonggong(sigma1List.get(i).getSigma1Down());
            sigma1Export2.setZhongchui(sigma1List.get(i).getSigma1SDown());
            sigma1ExportList.add(sigma1Export1);
            sigma1ExportList.add(sigma1Export2);
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream(), Sigma1Export.class)
                .head(headList).sheet("0").doWrite(sigma1ExportList);
    }

    private static class Sigma1Export {
        @ExcelProperty("")
        private String name;
        @ExcelProperty("中拱")
        private Double zhonggong;
        @ExcelProperty("中垂")
        private Double zhongchui;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getZhonggong() {
            return zhonggong;
        }

        public void setZhonggong(Double zhonggong) {
            this.zhonggong = zhonggong;
        }

        public Double getZhongchui() {
            return zhongchui;
        }

        public void setZhongchui(Double zhongchui) {
            this.zhongchui = zhongchui;
        }
    }
}
