package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.Bulkhead;
import com.iscas.biz.calculation.entity.db.BulkheadCompartment;
import com.iscas.biz.calculation.entity.db.Material;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.dto.BuoyancyParamExcel;
import com.iscas.biz.calculation.entity.dto.MaterialDTO;
import com.iscas.biz.calculation.entity.dto.MaterialParamExcel;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.MaterialService;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */
public class MaterialServiceImpl implements MaterialService {

    private final MaterialMapper materialMapper;

    private final BulkheadMapper bulkheadMapper;

    private final ProjectMapper projectMapper;

    private final BulkheadCompartmentMapper bulkheadCompartmentMapper;

    private final AlgorithmGrpc algorithmGrpc;

    public MaterialServiceImpl(MaterialMapper materialMapper, BulkheadMapper bulkheadMapper, ProjectMapper projectMapper, BulkheadCompartmentMapper bulkheadCompartmentMapper, AlgorithmGrpc algorithmGrpc) {
        this.materialMapper = materialMapper;
        this.bulkheadMapper = bulkheadMapper;
        this.projectMapper = projectMapper;
        this.bulkheadCompartmentMapper = bulkheadCompartmentMapper;
        this.algorithmGrpc = algorithmGrpc;
    }


    @Override
    public Material listBybulkheadId(Integer bulkheadId) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Material::getBulkheadId, bulkheadId);
        List<Material> materials = materialMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(materials)) {
            if (materials.size() > 1) {
                for (int i = 1; i < materials.size(); i++) {
                    materialMapper.deleteById(materials.get(i));
                }
            }
            return materials.get(0);
        }
        return null;
    }

    @Override
    public void export(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }

        QueryWrapper<Material> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.lambda().eq(Material::getProjectId, projectId);
        Material material = materialMapper.selectOne(materialQueryWrapper);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();

        MaterialParamExcel paramExcel = new MaterialParamExcel();


        paramExcel.setLowerLoad(material.getLowerLoad());
        paramExcel.setUpperLoad(material.getUpperLoad());
        paramExcel.setZiyouZhongwan(material.getZiyouZhongwan());
        paramExcel.setZiyouShangwan(material.getZiyouShangwan());
        paramExcel.setZiyouXiawan(material.getZiyouXiawan());
        paramExcel.setZiyouShangjian(material.getZiyouShangjian());
        paramExcel.setZiyouXiajian(material.getZiyouXiajian());
        paramExcel.setGangxingShangwan(material.getGangxingShangwan());
        paramExcel.setGangxingXiawan(material.getGangxingXiawan());
        paramExcel.setGangxingShangjian(material.getGangxingShangjian());
        paramExcel.setGangxingXiajian(material.getGangxingXiajian());
        paramExcel.setYingliZhongying(material.getYingliZhongying());
        paramExcel.setYingliShangying(material.getYingliShangying());
        paramExcel.setYingliXiaying(material.getYingliXiaying());
        paramExcel.setYingliXuying(material.getYingliXuying());
        paramExcel.setYingliShangjian(material.getYingliShangjian());
        paramExcel.setYingliXiajian(material.getYingliXiajian());
        paramExcel.setYingliXujian(material.getYingliXujian());

        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();

        WriteTable paramTable = EasyExcel.writerTable(0).head(BuoyancyParamExcel.class).needHead(true).build();
        excelWriter.write(Lists.newArrayList(paramExcel), writeSheet, paramTable);
        excelWriter.finish();
    }

    @Override
    public Material calMaterial(MaterialDTO materialDTO) {

        Integer projectId = materialDTO.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }

        Bulkhead bulkhead = bulkheadMapper.selectById(materialDTO.getBulkheadId());
        Double zonggukuaju = bulkhead.getZonggukuaju();
        materialDTO.setZongguKuaju(zonggukuaju);

        QueryWrapper<BulkheadCompartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id",materialDTO.getBulkheadId());
        queryWrapper.eq("project_id",projectId);
        List<BulkheadCompartment> compartments = bulkheadCompartmentMapper.selectList(queryWrapper);
        List<String> guicaitype = new ArrayList<>();
        List<Double> daibankuan = new ArrayList<>();
        List<Double> daibanhou = new ArrayList<>();
        if (com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(compartments)){
            for (BulkheadCompartment compartment : compartments) {
                if (null != compartment.getStrengthMaterialSpecification() && null != compartment.getDaibanhou() && null != compartment.getStripPlateWidth()){
                    guicaitype.add(compartment.getStrengthMaterialSpecification());
                    daibankuan.add(Double.parseDouble(compartment.getStripPlateWidth()));
                    daibanhou.add(Double.parseDouble(compartment.getDaibanhou()));
                }
            }
        }
        if (!guicaitype.isEmpty() && !daibankuan.isEmpty() && !daibanhou.isEmpty()){
            materialDTO.setGuicaiType(guicaitype);
            materialDTO.setDaibanHou(daibanhou);
            materialDTO.setDaibanKuan(daibankuan);

            Material material = algorithmGrpc.material(materialDTO);

            Material bybulkheadId = listBybulkheadId(material.getBulkheadId());

            if (null != bybulkheadId){
                Integer materialId = bybulkheadId.getMaterialId();
                material.setMaterialId(materialId);
                materialMapper.deleteById(materialId);
            }
            material.setBulkheadId(materialDTO.getBulkheadId());
            materialMapper.insert(material);
            return material;
        }

        return null;
    }


}
