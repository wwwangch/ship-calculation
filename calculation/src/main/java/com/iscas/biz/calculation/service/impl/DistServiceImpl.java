package com.iscas.biz.calculation.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.Dist;
import com.iscas.biz.calculation.entity.db.Project;
import com.iscas.biz.calculation.entity.dto.DistExcel;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.DistMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.service.DistService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/28 0:38
 */

@Service
@Slf4j
public class DistServiceImpl implements DistService {

    private final DistMapper distMapper;

    private final ProjectMapper projectMapper;

    private final AlgorithmGrpc algorithmGrpc;

    public DistServiceImpl(DistMapper distMapper, ProjectMapper projectMapper, AlgorithmGrpc algorithmGrpc) {
        this.distMapper = distMapper;
        this.projectMapper = projectMapper;
        this.algorithmGrpc = algorithmGrpc;
    }

    @Override
    public int remove(Integer projectId) {
        QueryWrapper<Dist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        return distMapper.delete(queryWrapper);
    }

    @Override
    public Dist calculateAndSave(Integer projectId) {

        Dist dist = algorithmGrpc.calDist(projectId);
        if (null != dist) {
            distMapper.insert(dist);
        }
        return dist;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reset(Integer projectId) {
        QueryWrapper<Dist> distQueryWrapper = new QueryWrapper<>();
        distQueryWrapper.eq("project_id", projectId);
        List<Dist> dists = distMapper.selectList(distQueryWrapper);
        if (CollectionUtils.isNotEmpty(dists)) {
            distMapper.delete(distQueryWrapper);
            Set<Integer> distIdSet = dists.stream().map(Dist::getDistId).collect(Collectors.toSet());
            QueryWrapper<Dist> distQueryWrapper1 = new QueryWrapper<>();
            distQueryWrapper1.in("dist_id", distIdSet);
            distMapper.delete(distQueryWrapper1);
        }
        return true;
    }

    @Override
    public void export(Integer projectId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }
        QueryWrapper<Dist> distQueryWrapper = new QueryWrapper<>();
        distQueryWrapper.eq("project_id", projectId);
        Dist dist = distMapper.selectOne(distQueryWrapper);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();

        DistExcel distExcel = new DistExcel();
        distExcel.setCalculationSpecification(project.getCalculationSpecification().getDescCH());
        distExcel.setExtermeH(dist.getExtremeH());
        distExcel.setExtermeS(dist.getExtremeS());

        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();

        WriteTable distTable = EasyExcel.writerTable(0).head(DistExcel.class).needHead(true).build();

        excelWriter.write(Lists.newArrayList(distExcel), writeSheet, distTable);

        excelWriter.finish();
    }

    @Override
    public Dist getData(Integer projectId) {
        if (null == projectId) {
            return null;
        }
        QueryWrapper<Dist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        Dist dist = distMapper.selectOne(queryWrapper);
        if (null != dist) {

            return dist;
        }
        return null;
    }
}
