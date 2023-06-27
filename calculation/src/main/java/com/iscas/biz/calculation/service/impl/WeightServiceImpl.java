package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.BuoyancyParamExcel;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.iscas.biz.calculation.mapper.WeightMapper;
import com.iscas.biz.calculation.service.WeightService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 15:52
 */
@Service
@Slf4j
public class WeightServiceImpl implements WeightService {
    private final AlgorithmGrpc algorithmGrpc;
    private final WeightMapper weightMapper;

    private final ProjectMapper projectMapper;

    private final ShipParamMapper shipParamMapper;

    public WeightServiceImpl(AlgorithmGrpc algorithmGrpc, WeightMapper weightMapper, ProjectMapper projectMapper, ShipParamMapper shipParamMapper) {
        this.algorithmGrpc = algorithmGrpc;
        this.weightMapper = weightMapper;
        this.projectMapper = projectMapper;
        this.shipParamMapper = shipParamMapper;
    }

    @Override
    @Transactional
    public Weight calculate(WeightDTO weight) {
        Integer projectId = weight.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }
        //首先初始化船舶参数
        QueryWrapper<ShipParam> shipParamQueryWrapper = new QueryWrapper<>();
        shipParamQueryWrapper.eq("project_id", projectId);
        ShipParam shipParam = shipParamMapper.selectOne(shipParamQueryWrapper);

        Weight calledWeight = algorithmGrpc.callWeight(shipParam,weight);
        Weight dbWeight = listByProjectId(projectId);
        if (null != dbWeight) {
            Integer weightId = dbWeight.getWeightId();
            calledWeight.setWeightId(weightId);
            weightMapper.deleteById(weightId);
        }
        weightMapper.insert(calledWeight);
        return calledWeight;
    }

    @Override
    @Transactional
    public Weight listByProjectId(Integer projectId) {
        QueryWrapper<Weight> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<Weight> weights = weightMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(weights)) {
            if (weights.size() > 1) {
                for (int i = 1; i < weights.size(); i++) {
                    weightMapper.deleteById(weights.get(i));
                }
            }
            return weights.get(0);
        }
        return null;
    }

    @Override
    public void export(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<Weight> weightQueryWrapper = new QueryWrapper<>();
        weightQueryWrapper.eq("project_id", projectId);
        Weight weight = weightMapper.selectOne(weightQueryWrapper);
        List<WeightDistribution> weightDistributions = weight.getWeightDistributions();
        weightDistributions = JSON.parseArray(JSON.toJSONString(weightDistributions), WeightDistribution.class);
        List<List<String>> headList = new ArrayList<>();
        List<List<Double>> dataList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(weightDistributions)) {
            boolean mapB = true;
            List<String> head0 = new ArrayList<>();
            head0.add("站号");
            headList.add(head0);
            for (WeightDistribution weightDistribution : weightDistributions) {
                String name = weightDistribution.getName();
                List<String> head = new ArrayList<>();
                head.add(name);
                headList.add(head);
                List<Double> weightItems = weightDistribution.getWeightItems();
                for (int i = 0; i < weightItems.size(); i++) {
                    if (mapB) {
                        List list = new ArrayList<>();
                        list.add(i);
                        list.add(weightItems.get(i));
                        dataList.add(list);
                    } else {
                        List list = dataList.get(i);
                        list.add(weightItems.get(i));
                    }
                }
                mapB = false;
            }
        }

        EasyExcel.write(SpringUtils.getResponse().getOutputStream()).head(headList).sheet("0").doWrite(dataList);
    }
}
