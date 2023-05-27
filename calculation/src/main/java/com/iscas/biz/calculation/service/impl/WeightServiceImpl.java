package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.calculation.entity.db.Weight;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.WeightMapper;
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
public class WeightServiceImpl implements WeightService {
    private final AlgorithmGrpc algorithmGrpc;
    private final WeightMapper weightMapper;

    private final ProjectMapper projectMapper;

    public WeightServiceImpl(AlgorithmGrpc algorithmGrpc, WeightMapper weightMapper, ProjectMapper projectMapper) {
        this.algorithmGrpc = algorithmGrpc;
        this.weightMapper = weightMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    @Transactional
    public Weight calculate(WeightDTO weight) {
        Integer projectId = weight.getProjectId();
        if (null == projectId || null == projectMapper.selectById(projectId)) {
            return null;
        }
        Weight calledWeight = algorithmGrpc.callWeight(weight);
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
}
