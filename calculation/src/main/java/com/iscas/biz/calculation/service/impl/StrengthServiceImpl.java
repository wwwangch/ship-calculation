package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.sigma.Sigma1DTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.StrengthService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public StrengthServiceImpl(Sigma1Mapper sigma1Mapper, Sigma2Mapper sigma2Mapper, Sigma3Mapper sigma3Mapper, Sigma4Mapper sigma4Mapper, ShearingStressMapper shearingStressMapper, AlgorithmGrpc algorithmGrpc) {
        this.sigma1Mapper = sigma1Mapper;
        this.sigma2Mapper = sigma2Mapper;
        this.sigma3Mapper = sigma3Mapper;
        this.sigma4Mapper = sigma4Mapper;
        this.shearingStressMapper = shearingStressMapper;
        this.algorithmGrpc = algorithmGrpc;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Sigma1 getSigma1(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma1> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma1> sigma1List = sigma1Mapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(sigma1List)) {
            if (sigma1List.size() > 1) {
                for(int i = 1; i < sigma1List.size(); i++) {
                    sigma1Mapper.deleteById(sigma1List.get(i));
                }
            }
            return sigma1List.get(0);
        }
        return null;
    }

    @Override
    public Sigma2 getSigma2(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma2> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma2> sigma2List = sigma2Mapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(sigma2List)) {
            if (sigma2List.size() > 1) {
                for(int i = 1; i < sigma2List.size(); i++) {
                    sigma2Mapper.deleteById(sigma2List.get(i));
                }
            }
            return sigma2List.get(0);
        }
        return null;
    }

    @Override
    public Sigma3 getSigma3(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma3> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma3> sigma3List = sigma3Mapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(sigma3List)) {
            if (sigma3List.size() > 1) {
                for(int i = 1; i < sigma3List.size(); i++) {
                    sigma3Mapper.deleteById(sigma3List.get(i));
                }
            }
            return sigma3List.get(0);
        }
        return null;
    }

    @Override
    public Sigma4 getSigma4(Integer projectId, Integer sectionId) {
        QueryWrapper<Sigma4> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<Sigma4> sigma4List = sigma4Mapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(sigma4List)) {
            if (sigma4List.size() > 1) {
                for(int i = 1; i < sigma4List.size(); i++) {
                    sigma4Mapper.deleteById(sigma4List.get(i));
                }
            }
            return sigma4List.get(0);
        }
        return null;
    }

    @Override
    public ShearingStress getShearingStress(Integer projectId, Integer sectionId) {
        QueryWrapper<ShearingStress> queryWrapper = new QueryWrapper();
        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq("section_id", sectionId);
        List<ShearingStress> shearingStressList = shearingStressMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(shearingStressList)) {
            if (shearingStressList.size() > 1) {
                for(int i = 1; i < shearingStressList.size(); i++) {
                    shearingStressMapper.deleteById(shearingStressList.get(i));
                }
            }
            return shearingStressList.get(0);
        }
        return null;
    }

    @Override
    public Sigma1 calSigma1(Sigma1DTO sigma1DTO) {
        Integer projectId = sigma1DTO.getProjectId();
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        Integer sectionId = sigma1DTO.getSectionId();
        if (null == sectionId) {
            throw new RuntimeException("参数[sectionId]不可为空");
        }
//        Sigma1 calSigma1 = algorithmGrpc.calSigma1(sigma1DTO);
    return null;
    }
}
