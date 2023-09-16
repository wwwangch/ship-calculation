package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.MaterialDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.BulkheadCompartmentMapper;
import com.iscas.biz.calculation.mapper.BulkheadMapper;
import com.iscas.biz.calculation.mapper.MaterialMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.BulkheadCompartmentService;
import com.iscas.biz.calculation.service.MaterialService;
import com.iscas.biz.calculation.service.ShipParamService;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialMapper materialMapper;

    private final BulkheadMapper bulkheadMapper;

    private final ProjectMapper projectMapper;

    private final ShipParamService shipParamService;

    private final BulkheadCompartmentMapper bulkheadCompartmentMapper;

    private final BulkheadCompartmentService bulkheadCompartmentService;

    private final AlgorithmGrpc algorithmGrpc;

    public MaterialServiceImpl(MaterialMapper materialMapper, BulkheadMapper bulkheadMapper, ProjectMapper projectMapper, ShipParamService shipParamService, BulkheadCompartmentMapper bulkheadCompartmentMapper, BulkheadCompartmentService bulkheadCompartmentService, AlgorithmGrpc algorithmGrpc) {
        this.materialMapper = materialMapper;
        this.bulkheadMapper = bulkheadMapper;
        this.projectMapper = projectMapper;
        this.shipParamService = shipParamService;
        this.bulkheadCompartmentMapper = bulkheadCompartmentMapper;
        this.bulkheadCompartmentService = bulkheadCompartmentService;
        this.algorithmGrpc = algorithmGrpc;
    }


    @Override
    public Material listBybulkheadId(Integer projectId, Integer bulkheadId) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id", bulkheadId);

        //增加工况条件
        shipParamService.addCheckTypeCondition(queryWrapper, projectId);

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
    public void export(Integer projectId, Integer bulkheadId) throws IOException {

        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }

        QueryWrapper<Material> materialQueryWrapper = new QueryWrapper<>();
        materialQueryWrapper.eq("bulkhead_id", bulkheadId);
        shipParamService.addCheckTypeCondition(materialQueryWrapper, project.getProjectId());

        Material material = materialMapper.selectOne(materialQueryWrapper);
        FileDownloadUtils.setResponseHeader(SpringUtils.getRequest(), SpringUtils.getResponse(), "扶强材计算结果.xlsx");
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();
        if (null != material) {
            // 准备表头数据
            List<List<String>> headList = new ArrayList<>();
            headList.add(List.of("上端载荷"));
            headList.add(List.of("下端载荷"));
            headList.add(List.of("自由支持中部弯矩"));
            headList.add(List.of("自由支持上部弯矩"));
            headList.add(List.of("自由支持下部弯矩"));
            headList.add(List.of("自由支持上部剪力"));
            headList.add(List.of("自由支持下部剪力"));
            headList.add(List.of("刚性固定上部弯矩"));
            headList.add(List.of("刚性固定下部弯矩"));
            headList.add(List.of("刚性固定上部剪力"));
            headList.add(List.of("刚性固定下部剪力"));
            headList.add(List.of("应力值中部应力"));
            headList.add(List.of("应力值上部应力"));
            headList.add(List.of("应力值下部应力"));
            headList.add(List.of("应力值许用应力"));
            headList.add(List.of("应力值上部剪力"));
            headList.add(List.of("应力值下部剪力"));
            headList.add(List.of("应力值许用剪力"));
            headList.add(List.of("弹性连续梁最大弯矩"));
            headList.add(List.of("弹性连续梁最大支撑力"));
            headList.add(List.of("弹性连续梁最大正应力"));
            headList.add(List.of("弹性连续梁最大剪切力"));

            // 准备数据列表
            List<List<Object>> dataList = new ArrayList<>();
            List<Number> upperLoad = material.getUpperLoad();
            List<Number> lowerLoad = material.getLowerLoad();
            List<Number> ziyouZhongwan = material.getZiyouZhongwan();
            List<Number> ziyouShangwan = material.getZiyouShangwan();
            List<Number> ziyouXiawan = material.getZiyouXiawan();
            List<Number> ziyouShangjian = material.getZiyouShangjian();
            List<Number> ziyouXiajian = material.getZiyouXiajian();
            List<Number> gangxingShangwan = material.getGangxingShangwan();
            List<Number> gangxingXiawan = material.getGangxingXiawan();
            List<Number> gangxingShangjian = material.getGangxingShangjian();
            List<Number> gangxingXiajian = material.getGangxingXiajian();
            List<Number> yingliZhongying = material.getYingliZhongying();
            List<Number> yingliShangying = material.getYingliShangying();
            List<Number> yingliXiaying = material.getYingliXiaying();
            List<Number> yingliXuying = material.getYingliXuying();
            List<Number> yingliShangjian = material.getYingliShangjian();
            List<Number> yingliXiajian = material.getYingliXiajian();
            List<Number> yingliXujian = material.getYingliXujian();
            List<Number> mMaxEl = material.getMMaxEl();
            List<Number> nMaxEl = material.getNMaxEl();
            List<Number> stressMaxEl = material.getStressMaxEl();
            List<Number> shearMaxEl = material.getShearMaxEl();

            int maxListSize = Math.max(upperLoad.size(), Math.max(lowerLoad.size(), Math.max(ziyouZhongwan.size(),
                    Math.max(ziyouShangwan.size(), Math.max(ziyouXiawan.size(), Math.max(ziyouShangjian.size(),
                            Math.max(ziyouXiajian.size(), Math.max(gangxingShangwan.size(), Math.max(gangxingXiawan.size(),
                                    Math.max(gangxingShangjian.size(), Math.max(gangxingXiajian.size(),
                                            Math.max(yingliZhongying.size(), Math.max(yingliShangying.size(),
                                                    Math.max(yingliXiaying.size(), Math.max(yingliXuying.size(),
                                                            Math.max(yingliShangjian.size(), Math.max(yingliXiajian.size(),
                                                                    Math.max(yingliXujian.size(), Math.max(mMaxEl.size(),
                                                                            Math.max(nMaxEl.size(), Math.max(stressMaxEl.size(),
                                                                                    shearMaxEl.size())))))))))))))))))))));
            for (int i = 0; i < maxListSize; i++) {
                List<Object> rowData = new ArrayList<>();
                if (i < upperLoad.size()) {
                    rowData.add(upperLoad.get(i));
                } else {
                    rowData.add("");
                }
                if (i < lowerLoad.size()) {
                    rowData.add(lowerLoad.get(i));
                } else {
                    rowData.add("");
                }
                if (i < ziyouZhongwan.size()) {
                    rowData.add(ziyouZhongwan.get(i));
                } else {
                    rowData.add("");
                }
                if (i < ziyouShangwan.size()) {
                    rowData.add(ziyouShangwan.get(i));
                } else {
                    rowData.add("");
                }
                if (i < ziyouXiawan.size()) {
                    rowData.add(ziyouXiawan.get(i));
                } else {
                    rowData.add("");
                }
                if (i < ziyouShangjian.size()) {
                    rowData.add(ziyouShangjian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < ziyouXiajian.size()) {
                    rowData.add(ziyouXiajian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < gangxingShangwan.size()) {
                    rowData.add(gangxingShangwan.get(i));
                } else {
                    rowData.add("");
                }
                if (i < gangxingXiawan.size()) {
                    rowData.add(gangxingXiawan.get(i));
                } else {
                    rowData.add("");
                }
                if (i < gangxingShangjian.size()) {
                    rowData.add(gangxingShangjian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < gangxingXiajian.size()) {
                    rowData.add(gangxingXiajian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliZhongying.size()) {
                    rowData.add(yingliZhongying.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliShangying.size()) {
                    rowData.add(yingliShangying.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliXiaying.size()) {
                    rowData.add(yingliXiaying.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliXuying.size()) {
                    rowData.add(yingliXuying.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliShangjian.size()) {
                    rowData.add(yingliShangjian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliXiajian.size()) {
                    rowData.add(yingliXiajian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < yingliXujian.size()) {
                    rowData.add(yingliXujian.get(i));
                } else {
                    rowData.add("");
                }
                if (i < mMaxEl.size()) {
                    rowData.add(mMaxEl.get(i));
                } else {
                    rowData.add("");
                }
                if (i < nMaxEl.size()) {
                    rowData.add(nMaxEl.get(i));
                } else {
                    rowData.add("");
                }
                if (i < stressMaxEl.size()) {
                    rowData.add(stressMaxEl.get(i));
                } else {
                    rowData.add("");
                }
                if (i < shearMaxEl.size()) {
                    rowData.add(shearMaxEl.get(i));
                } else {
                    rowData.add("");
                }
                dataList.add(rowData);
            }

            // 导出数据到Excel
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet0").head(headList).build();
            excelWriter.write(dataList, writeSheet);
        }
        excelWriter.finish();

    }

    @Override
    public Material calMaterial(MaterialDTO materialDTO) {

        Integer projectId = materialDTO.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }
        List<BulkheadCompartment> bulkheadCompartments = bulkheadCompartmentService.listByBulkHeadId(materialDTO.getBulkheadId());
        if (CollectionUtils.isEmpty(bulkheadCompartments)) {
            throw new RuntimeException("当前舱壁区间为空!");
        }

        Bulkhead bulkhead = bulkheadMapper.selectById(materialDTO.getBulkheadId());
        Double zonggukuaju = bulkhead.getZonggukuaju();
        materialDTO.setZongguKuaju(zonggukuaju);

        List<String> guicaitype = new ArrayList<>();
        List<Double> daibankuan = new ArrayList<>();
        List<Double> daibanhou = new ArrayList<>();
        List<String> guicaiTypeUppers = new ArrayList<>();
        List<String> guicaiTypeLowers = new ArrayList<>();
        List<Double> fuQiangCaiYieldLimits = new ArrayList<>();

        for (BulkheadCompartment compartment : bulkheadCompartments) {
            guicaitype.add(Optional.ofNullable(compartment.getStrengthMaterialSpecification()).orElse(""));
            daibankuan.add(Optional.ofNullable(compartment.getStripPlateWidth()).orElse(0D));
            daibanhou.add(Optional.ofNullable(compartment.getDaibanhou()).orElse(0D));
            guicaiTypeUppers.add(Optional.ofNullable(compartment.getGuicaiTypeUpper()).orElse(""));
            guicaiTypeLowers.add(Optional.ofNullable(compartment.getGuicaiTypeLower()).orElse(""));
            fuQiangCaiYieldLimits.add(Double.parseDouble(Optional.ofNullable(compartment.getMaterial()).orElse("595")));
        }


        materialDTO.setGuicaiType(guicaitype);
        materialDTO.setDaibanHou(daibanhou);
        materialDTO.setDaibanKuan(daibankuan);
        materialDTO.setGuicaiTypeUppers(guicaiTypeUppers);
        materialDTO.setGuicaiTypeLowers(guicaiTypeLowers);
        materialDTO.setFuQiangCaiYieldLimits(fuQiangCaiYieldLimits);

        Material material = algorithmGrpc.material(materialDTO);
        //填充所属工况
        ShipParam shipParam = shipParamService.listByProjectId(projectId);
        material.setCheckType(shipParam.getCurrentType());

        Material bybulkheadId = listBybulkheadId(projectId, material.getBulkheadId());

        if (null != bybulkheadId) {
            Integer materialId = bybulkheadId.getMaterialId();
            material.setMaterialId(materialId);
            materialMapper.deleteById(materialId);
        }
        material.setBulkheadId(materialDTO.getBulkheadId());
        materialMapper.insert(material);
        return material;
    }
}
