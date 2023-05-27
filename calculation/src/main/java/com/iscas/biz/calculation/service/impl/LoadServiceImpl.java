package com.iscas.biz.calculation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.biz.calculation.entity.db.SlamLoad;
import com.iscas.biz.calculation.entity.db.StaticLoad;
import com.iscas.biz.calculation.entity.db.WaveLoad;
import com.iscas.biz.calculation.entity.dto.SlamLoadDTO;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.entity.dto.WaveLoadDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.SlamLoadMapper;
import com.iscas.biz.calculation.mapper.StaticLoadMapper;
import com.iscas.biz.calculation.mapper.WaveLoadMapper;
import com.iscas.biz.calculation.service.LoadService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/27 23:16
 */
@Service
public class LoadServiceImpl implements LoadService {
    private final StaticLoadMapper staticLoadMapper;
    private final WaveLoadMapper waveLoadMapper;
    private final SlamLoadMapper slamLoadMapper;

    private final AlgorithmGrpc algorithmGrpc;

    public LoadServiceImpl(StaticLoadMapper staticLoadMapper, WaveLoadMapper waveLoadMapper, SlamLoadMapper slamLoadMapper, AlgorithmGrpc algorithmGrpc) {
        this.staticLoadMapper = staticLoadMapper;
        this.waveLoadMapper = waveLoadMapper;
        this.slamLoadMapper = slamLoadMapper;
        this.algorithmGrpc = algorithmGrpc;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StaticLoad getStaticData(Integer projectId) {
        QueryWrapper<StaticLoad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<StaticLoad> staticLoads = staticLoadMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(staticLoads)) {
            if (staticLoads.size() > 1) {
                for (int i = 1; i < staticLoads.size(); i++) {
                    staticLoadMapper.deleteById(staticLoads.get(i));
                }
            }
            return staticLoads.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WaveLoad getWaveData(Integer projectId) {
        QueryWrapper<WaveLoad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<WaveLoad> waveLoads = waveLoadMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(waveLoads)) {
            if (waveLoads.size() > 1) {
                for (int i = 1; i < waveLoads.size(); i++) {
                    waveLoadMapper.deleteById(waveLoads.get(i));
                }
            }
            return waveLoads.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SlamLoad getSlamData(Integer projectId) {
        QueryWrapper<SlamLoad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<SlamLoad> slamLoads = slamLoadMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(slamLoads)) {
            if (slamLoads.size() > 1) {
                for (int i = 1; i < slamLoads.size(); i++) {
                    slamLoadMapper.deleteById(slamLoads.get(i));
                }
            }
            return slamLoads.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StaticLoad calStaticLoad(StaticLoadDTO staticLoadDTO) {
        Integer projectId = staticLoadDTO.getProjectId();
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        StaticLoad calStaticLoad = algorithmGrpc.calStaticLoad(staticLoadDTO);
        StaticLoad dbStaticLoad = getStaticData(projectId);
        if (null != dbStaticLoad) {
            Integer staticLoadId = dbStaticLoad.getStaticLoadId();
            calStaticLoad.setStaticLoadId(staticLoadId);
            staticLoadMapper.deleteById(staticLoadId);
        }
        staticLoadMapper.insert(calStaticLoad);
        return calStaticLoad;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WaveLoad calWaveLoad(WaveLoadDTO waveLoadDTO) {
        Integer projectId = waveLoadDTO.getProjectId();
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        WaveLoad calWaveLoad = algorithmGrpc.calWaveLoad(waveLoadDTO);
        WaveLoad dbWaveLoad = getWaveData(projectId);
        if (null != dbWaveLoad) {
            Integer waveLoadId = dbWaveLoad.getWaveLoadId();
            calWaveLoad.setWaveLoadId(waveLoadId);
            waveLoadMapper.deleteById(waveLoadId);
        }
        waveLoadMapper.insert(calWaveLoad);
        return calWaveLoad;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SlamLoad calSlamLoad(SlamLoadDTO slamLoadDTO) {
        Integer projectId = slamLoadDTO.getProjectId();
        if (null == projectId) {
            throw new RuntimeException("参数[projectId]不可为空");
        }
        SlamLoad calSlamLoad = algorithmGrpc.calSlamLoad(slamLoadDTO);
        SlamLoad dbSlamLoad = getSlamData(projectId);
        if (null != dbSlamLoad) {
            Integer slamLoadId = dbSlamLoad.getSlamLoadId();
            dbSlamLoad.setSlamLoadId(slamLoadId);
            slamLoadMapper.deleteById(slamLoadId);
        }
        slamLoadMapper.insert(calSlamLoad);
        return calSlamLoad;
    }
}
