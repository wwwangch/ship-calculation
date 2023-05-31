package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.SlamLoadDTO;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.entity.dto.WaveLoadDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.SlamLoadMapper;
import com.iscas.biz.calculation.mapper.StaticLoadMapper;
import com.iscas.biz.calculation.mapper.WaveLoadMapper;
import com.iscas.biz.calculation.service.LoadService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
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
    private final ProjectMapper projectMapper;

    private final AlgorithmGrpc algorithmGrpc;

    public LoadServiceImpl(StaticLoadMapper staticLoadMapper, WaveLoadMapper waveLoadMapper, SlamLoadMapper slamLoadMapper, ProjectMapper projectMapper, AlgorithmGrpc algorithmGrpc) {
        this.staticLoadMapper = staticLoadMapper;
        this.waveLoadMapper = waveLoadMapper;
        this.slamLoadMapper = slamLoadMapper;
        this.projectMapper = projectMapper;
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

    @Override
    public void staticExport(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<StaticLoad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        StaticLoad staticLoad = staticLoadMapper.selectOne(queryWrapper);
        List<List<String>> headList = new ArrayList<>();
        if (staticLoad != null) {
            List<String> head0 = new ArrayList<>();
            head0.add("站号");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("未修正的静水剪力");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("修正的静水剪力");
            headList.add(head2);
            List<String> head3 = new ArrayList<>();
            head3.add("未修正的弯矩");
            headList.add(head3);
            List<String> head4 = new ArrayList<>();
            head4.add("修正的弯矩");
            headList.add(head4);
        }
        List<List<Object>> dataList = new ArrayList<>();
        List<Double> nvec = staticLoad.getNvec();
        List<Double> nvecM = staticLoad.getNvecM();
        List<Double> mvec = staticLoad.getMvec();
        List<Double> mvecM = staticLoad.getMvecM();
        if (CollectionUtils.isNotEmpty(nvec)) {
            for (int i = 0; i < nvec.size(); i++) {
                List<Object> data = new ArrayList<>();
                data.add(i);
                data.add(nvec.get(i));
                data.add(nvecM.get(i));
                data.add(mvec.get(i));
                data.add(mvecM.get(i));
                dataList.add(data);
            }
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream()).head(headList).sheet("0").doWrite(dataList);
    }

    @Override
    public void waveExport(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<WaveLoad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        WaveLoad waveLoad = waveLoadMapper.selectOne(queryWrapper);
        List<List<String>> headList = new ArrayList<>();
        if (waveLoad != null) {
            List<String> head0 = new ArrayList<>();
            head0.add("站号");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("波峰未修正的静水剪力");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("波峰修正的静水剪力");
            headList.add(head2);
            List<String> head3 = new ArrayList<>();
            head3.add("波谷未修正的静水剪力");
            headList.add(head3);
            List<String> head4 = new ArrayList<>();
            head4.add("波谷修正的静水剪力");
            headList.add(head4);
            List<String> head5 = new ArrayList<>();
            head5.add("波峰未修正的弯矩");
            headList.add(head5);
            List<String> head6 = new ArrayList<>();
            head6.add("波峰修正的弯矩");
            headList.add(head6);
            List<String> head7 = new ArrayList<>();
            head7.add("波谷未修正的弯矩");
            headList.add(head7);
            List<String> head8 = new ArrayList<>();
            head8.add("波谷修正的弯矩");
            headList.add(head8);
            List<String> head9 = new ArrayList<>();
            head9.add("中拱附加浮力");
            headList.add(head9);
            List<String> head10 = new ArrayList<>();
            head10.add("中垂附加浮力");
            headList.add(head10);
        }
        List<List<Object>> dataList = new ArrayList<>();
        List<Double> nwvecH = waveLoad.getNwvecH();
        List<Double> nwvecMH = waveLoad.getNwvecMH();
        List<Double> nwvecS = waveLoad.getNwvecS();
        List<Double> nwvecMS = waveLoad.getNwvecMS();
        List<Double> mwvecH = waveLoad.getMwvecH();
        List<Double> mwvecMH = waveLoad.getMwvecMH();
        List<Double> mwvecS = waveLoad.getMwvecS();
        List<Double> mwvecMS = waveLoad.getMwvecMS();
        List<Double> mbb = waveLoad.getMbb();
        List<Double> bdeltaS = waveLoad.getBdeltaS();
        if (CollectionUtils.isNotEmpty(nwvecH)) {
            for (int i = 0; i < nwvecH.size(); i++) {
                List<Object> data = new ArrayList<>();
                data.add(i);
                data.add(nwvecH.get(i));
                data.add(nwvecMH.get(i));
                data.add(nwvecS.get(i));
                data.add(nwvecMS.get(i));
                data.add(mwvecH.get(i));
                data.add(mwvecMH.get(i));
                data.add(mwvecS.get(i));
                data.add(mwvecMS.get(i));
                data.add(mbb.get(i));
                data.add(bdeltaS.get(i));
                dataList.add(data);
            }
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream()).head(headList).sheet("0").doWrite(dataList);
    }

    @Override
    public void slamExport(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<SlamLoad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        SlamLoad slamLoad = slamLoadMapper.selectOne(queryWrapper);
        List<List<String>> headList = new ArrayList<>();
        if (slamLoad != null) {
            List<String> head0 = new ArrayList<>();
            head0.add("站号");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("波峰抨击弯矩");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("波谷抨击弯矩");
            headList.add(head2);
        }
        List<List<Object>> dataList = new ArrayList<>();
        List<Double> pwbm = slamLoad.getPwbm();
        List<Double> nwb = slamLoad.getNwb();
        if (CollectionUtils.isNotEmpty(pwbm)) {
            for (int i = 0; i < pwbm.size(); i++) {
                List<Object> data = new ArrayList<>();
                data.add(i);
                data.add(pwbm.get(i));
                data.add(nwb.get(i));
                dataList.add(data);
            }
        }
        EasyExcel.write(SpringUtils.getResponse().getOutputStream()).head(headList).sheet("0").doWrite(dataList);
    }
}
