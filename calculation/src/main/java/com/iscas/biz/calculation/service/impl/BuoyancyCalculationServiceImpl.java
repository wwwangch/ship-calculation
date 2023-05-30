package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.BuoyancyVO;
import com.iscas.biz.calculation.entity.db.BuoyancyParam;
import com.iscas.biz.calculation.entity.db.BuoyancyResult;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.biz.calculation.entity.dto.BuoyancyParamExcel;
import com.iscas.biz.calculation.entity.dto.Buoyant;
import com.iscas.biz.calculation.grpc.*;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.BuoyancyParamMapper;
import com.iscas.biz.calculation.mapper.BuoyancyResultMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.iscas.biz.calculation.service.BuoyancyCalculationService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.templet.exception.ValidDataException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 16:48
 * 浮力计算
 */
@Service
@Slf4j
public class BuoyancyCalculationServiceImpl implements BuoyancyCalculationService {
    private final static String TABLE_IDENTITY = "buoyancy_param";

    private final TableDefinitionService tableDefinitionService;

    private final BuoyancyParamMapper buoyancyParamMapper;

    private final ShipParamMapper shipParamMapper;

    private final BuoyancyResultMapper buoyancyResultMapper;

    private final ProjectMapper projectMapper;

    private final AlgorithmGrpc algorithmGrpc;

    private final GrpcHolder grpcHolder;

    public BuoyancyCalculationServiceImpl(TableDefinitionService tableDefinitionService, BuoyancyParamMapper buoyancyParamMapper, ShipParamMapper shipParamMapper, BuoyancyResultMapper buoyancyResultMapper, ProjectMapper projectMapper, AlgorithmGrpc algorithmGrpc, GrpcHolder grpcHolder) {
        this.tableDefinitionService = tableDefinitionService;
        this.buoyancyParamMapper = buoyancyParamMapper;
        this.shipParamMapper = shipParamMapper;
        this.buoyancyResultMapper = buoyancyResultMapper;
        this.projectMapper = projectMapper;
        this.algorithmGrpc = algorithmGrpc;
        this.grpcHolder = grpcHolder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(Map<String, Object> data) throws ValidDataException {
        Integer projectId = (Integer) data.get("project_id");
        if (null == projectId) {
            throw new RuntimeException("项目id不存在");
        }

        ImmutableMap.Builder<String, Object> forceItemBuilder = ImmutableMap.builder();
        forceItemBuilder.put("create_time", DateSafeUtils.format(new Date()));
        forceItemBuilder.put("update_time", DateSafeUtils.format(new Date()));
        forceItemBuilder.put("project_id", projectId);

        BuoyancyParam buoyancyParam = this.listParamByProjectId(projectId);
        if (null != buoyancyParam) {
            Integer paramId = buoyancyParam.getParamId();
            buoyancyParamMapper.deleteById(paramId);
            forceItemBuilder.put("param_id", paramId);
        }
        tableDefinitionService.saveData(TABLE_IDENTITY, data, true, BuoyancyParam.class, forceItemBuilder.build());
        return 1;
    }

    @Override
    public BuoyancyParam listParamByProjectId(Integer projectId) {
        if (null == projectId) {
            return null;
        }
        QueryWrapper<BuoyancyParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<BuoyancyParam> buoyancyParams = buoyancyParamMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(buoyancyParams)) {
            if (buoyancyParams.size() > 1) {
                for (int i = 1; i < buoyancyParams.size(); i++) {
                    buoyancyParamMapper.deleteById(buoyancyParams.get(i));
                }
            }
            return buoyancyParams.get(0);
        }
        return null;
    }

    @Override
    public int remove(Integer projectId) {
        QueryWrapper<BuoyancyParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        return buoyancyParamMapper.delete(queryWrapper);
    }

    @Override
    public BuoyancyResult saveAndCalculate(Map<String, Object> data) throws ValidDataException {
        this.save(data);
        Integer projectId = (Integer) data.get("project_id");
        QueryWrapper<BuoyancyParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        BuoyancyParam buoyancyParam = buoyancyParamMapper.selectOne(queryWrapper);

        //首先初始化船舶参数
        QueryWrapper<ShipParam> shipParamQueryWrapper = new QueryWrapper<>();
        shipParamQueryWrapper.eq("project_id", projectId);
        ShipParam shipParam = shipParamMapper.selectOne(shipParamQueryWrapper);

        BuoyancyResponse buoyancyResponse = algorithmGrpc.callBuoyancy(shipParam, buoyancyParam);
        if (0 != buoyancyResponse.getCode()) {
            throw new RuntimeException("浮力计算失败" + buoyancyResponse.getMessage());
        }

        BuoyancyResult buoyancyResult = new BuoyancyResult();
        buoyancyResult.setParamId(buoyancyParam.getParamId());
        buoyancyResult.setBlist(buoyancyResponse.getBlistList());
        List<Buoyant> buoyants = Lists.newArrayList();
        List<com.iscas.biz.calculation.grpc.Buoyant> calrstList = buoyancyResponse.getCalrstList();
        if (CollectionUtils.isNotEmpty(calrstList)) {
            for (com.iscas.biz.calculation.grpc.Buoyant buoyant : calrstList) {
                Buoyant dbBuoyant = new Buoyant();
                BeanUtils.copyProperties(buoyant, dbBuoyant);
                buoyants.add(dbBuoyant);
            }
        }
        buoyancyResult.setCalrst(buoyants);

        QueryWrapper<BuoyancyResult> buoyancyResultQueryWrapper = new QueryWrapper<>();
        buoyancyResultQueryWrapper.eq("param_id", buoyancyParam.getParamId());
        buoyancyResultMapper.delete(buoyancyResultQueryWrapper);

        buoyancyResultMapper.insert(buoyancyResult);
        return buoyancyResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reset(Integer projectId) {
        QueryWrapper<BuoyancyParam> buoyancyParamQueryWrapper = new QueryWrapper<>();
        buoyancyParamQueryWrapper.eq("project_id", projectId);
        List<BuoyancyParam> buoyancyParams = buoyancyParamMapper.selectList(buoyancyParamQueryWrapper);
        if (CollectionUtils.isNotEmpty(buoyancyParams)) {
            buoyancyParamMapper.delete(buoyancyParamQueryWrapper);
            Set<Integer> paramIdSet = buoyancyParams.stream().map(BuoyancyParam::getParamId).collect(Collectors.toSet());
            QueryWrapper<BuoyancyResult> buoyancyResultQueryWrapper = new QueryWrapper<>();
            buoyancyResultQueryWrapper.in("param_id", paramIdSet);
            buoyancyParamMapper.delete(buoyancyParamQueryWrapper);
        }
        return true;
    }

    @Override
    public void export(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<BuoyancyParam> buoyancyParamQueryWrapper = new QueryWrapper<>();
        buoyancyParamQueryWrapper.eq("project_id", projectId);
        BuoyancyParam buoyancyParam = buoyancyParamMapper.selectOne(buoyancyParamQueryWrapper);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();
        QueryWrapper<BuoyancyResult> buoyancyResultQueryWrapper = new QueryWrapper<>();
        buoyancyResultQueryWrapper.eq("param_id", buoyancyParam.getParamId());
        BuoyancyResult buoyancyResult = buoyancyResultMapper.selectOne(buoyancyResultQueryWrapper);
        List<BuoyancyParamExcel> dataList = new ArrayList<>();
        if (buoyancyResult != null) {
            List<Double> blist = buoyancyResult.getBlist();
            if (blist != null) {
                for (int i = 0; i < blist.size(); i++) {
                    BuoyancyParamExcel buoyancyParamExcel = new BuoyancyParamExcel();
                    buoyancyParamExcel.setBlist(blist.get(i));
                    buoyancyParamExcel.setCode(i);
                    dataList.add(buoyancyParamExcel);
                }
            }
        }
        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();
        WriteTable paramTable = EasyExcel.writerTable(0).head(BuoyancyParamExcel.class).needHead(true).build();
        excelWriter.write(dataList, writeSheet, paramTable);
        excelWriter.finish();
    }

    @Override
    public BuoyancyResult listResultByParamId(Integer paramId) {
        if (null == paramId) {
            return null;
        }

        QueryWrapper<BuoyancyResult> buoyancyResultQueryWrapper = new QueryWrapper<>();
        buoyancyResultQueryWrapper.eq("param_id", paramId);
        BuoyancyResult buoyancyResult = buoyancyResultMapper.selectOne(buoyancyResultQueryWrapper);
        return buoyancyResult;
    }

    @Override
    public BuoyancyVO getData(Integer projectId) {
        if (null == projectId) {
            return null;
        }
        QueryWrapper<BuoyancyParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        BuoyancyParam buoyancyParam = buoyancyParamMapper.selectOne(queryWrapper);
        if (null != buoyancyParam) {
            BuoyancyVO buoyancyVO = new BuoyancyVO();
            BeanUtils.copyProperties(buoyancyParam, buoyancyVO);
            buoyancyVO.setBuoyancyResult(listResultByParamId(buoyancyParam.getParamId()));
            return buoyancyVO;
        }
        return null;
    }
}
