package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.BulkheadDTO;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.*;
import com.iscas.biz.calculation.service.BulkheadCompartmentService;
import com.iscas.biz.calculation.service.ShipParamService;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import com.iscas.datasong.connector.util.CollectionUtils;
import com.iscas.templet.view.table.ComboboxData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/20 14:09
 */
@Service
public class BulkheadCompartmentServiceImpl extends ServiceImpl<BulkheadCompartmentMapper, BulkheadCompartment> implements BulkheadCompartmentService {

    private final ProjectMapper projectMapper;

    private final ShipParamMapper shipParamMapper;
    private final ShipParamService shipParamService;
    private final BulbFlatMapper bulbFlatMapper;
    private final TProfileMapper tProfileMapper;

    private final AlgorithmGrpc algorithmGrpc;

    private final BulkheadCheckResultMapper bulkheadCheckResultMapper;

    public BulkheadCompartmentServiceImpl(ProjectMapper projectMapper, ShipParamMapper shipParamMapper, ShipParamService shipParamService, BulbFlatMapper bulbFlatMapper, TProfileMapper tProfileMapper, AlgorithmGrpc algorithmGrpc, BulkheadCheckResultMapper bulkheadCheckResultMapper) {
        this.projectMapper = projectMapper;
        this.shipParamMapper = shipParamMapper;
        this.shipParamService = shipParamService;
        this.bulbFlatMapper = bulbFlatMapper;
        this.tProfileMapper = tProfileMapper;
        this.algorithmGrpc = algorithmGrpc;
        this.bulkheadCheckResultMapper = bulkheadCheckResultMapper;
    }

    @Override
    public Integer update(BulkheadCompartment compartment) {
        Integer projectId = compartment.getProjectId();
        if (null == projectMapper.selectById(projectId)) {
            throw new RuntimeException(String.format("项目:[%s]不存在", projectId));
        }
        return this.updateById(compartment) ? 1 : 0;
    }

    @Override
    public List<ComboboxData> getCascader() {
        List<BulbFlat> bulbFlats = bulbFlatMapper.selectList(null);
        List<TProfile> tProfiles = tProfileMapper.selectList(null);
        List<ComboboxData> comboboxData = new ArrayList<>();
        ComboboxData qData = new ComboboxData<>();
        qData.setLabel("球扁钢");
        qData.setValue("q");
        List<ComboboxData> qDataList = new ArrayList<>();
        for (BulbFlat bulbFlat : bulbFlats) {
            String label = bulbFlat.getModel();
            String value = bulbFlat.getProfileId().toString();
            ComboboxData data = new ComboboxData();
            data.setValue(label);
            data.setLabel(label);
            qDataList.add(data);
        }
        qData.setChildren(qDataList);
        comboboxData.add(qData);
        ComboboxData tData = new ComboboxData<>();
        tData.setLabel("T型材");
        tData.setValue("t");
        List<ComboboxData> tDataList = new ArrayList<>();
        for (TProfile tProfile : tProfiles) {
            String label = tProfile.getModel();
            String value = tProfile.getProfileId().toString();
            ComboboxData data = new ComboboxData();
            data.setValue(label);
            data.setLabel(label);
            tDataList.add(data);
        }
        tData.setChildren(tDataList);
        comboboxData.add(tData);
        return comboboxData;
    }

    @Override
    public Boolean deleteByIds(List<Integer> ids) {
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                return this.removeByIds(ids);
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("删除剖面数据时异常", e);
        }
    }

    @Override
    public BulkheadCheckResult checkBulkhead(BulkheadDTO bulkheadDTO) {
        //根据项目Id查询船舶参数
        Integer projectId = bulkheadDTO.getProjectId();
        QueryWrapper<ShipParam> shipParamQueryWrapper = new QueryWrapper<>();
        shipParamQueryWrapper.eq("project_id", projectId);
        List<ShipParam> shipParams = shipParamMapper.selectList(shipParamQueryWrapper);
        if (CollectionUtils.isEmpty(shipParams)) {
            throw new RuntimeException("当前项目船舶参数为空");
        }
        ShipParam shipParam = shipParams.get(0);

        //根据区间id获取区间数据
        List<BulkheadCompartment> bulkheadCompartments = listByBulkHeadId(bulkheadDTO.getBulkheadId());
        if (CollectionUtils.isEmpty(bulkheadCompartments)) {
            throw new RuntimeException("当前舱壁区间数据为空");
        }

        BulkheadCheckResult calBulkheadCheckResult = algorithmGrpc.calBulkheadCheck(bulkheadDTO.getBulkheadId(), shipParam, bulkheadCompartments);
        calBulkheadCheckResult.setCheckType(shipParam.getCurrentType());

        BulkheadCheckResult dbBulkheadCheckResult = listResultByBulkheadId(projectId, bulkheadDTO.getBulkheadId());
        if (null != dbBulkheadCheckResult) {
            Integer bulkheadResultId = dbBulkheadCheckResult.getBulkheadResultId();
            calBulkheadCheckResult.setBulkheadResultId(bulkheadResultId);
            bulkheadCheckResultMapper.deleteById(bulkheadResultId);
        }
        bulkheadCheckResultMapper.insert(calBulkheadCheckResult);
        return calBulkheadCheckResult;
    }

    @Override
    public BulkheadCheckResult listResultByBulkheadId(Integer projectId, Integer bulkheadId) {
        QueryWrapper<BulkheadCheckResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id", bulkheadId);
        shipParamService.addCheckTypeCondition(queryWrapper, projectId);
        List<BulkheadCheckResult> bulkheadCheckResults = bulkheadCheckResultMapper.selectList(queryWrapper);
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(bulkheadCheckResults)) {
            if (bulkheadCheckResults.size() > 1) {
                for (int i = 1; i < bulkheadCheckResults.size(); i++) {
                    bulkheadCheckResultMapper.deleteById(bulkheadCheckResults.get(i));
                }
            }
            return bulkheadCheckResults.get(0);
        }
        return null;
    }

    @Override
    public List<BulkheadCompartment> listByBulkHeadId(Integer bulkHeadId) {
        List<BulkheadCompartment> result = Lists.newArrayList();
        QueryWrapper<BulkheadCompartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id", bulkHeadId);
        List<BulkheadCompartment> list = this.list(queryWrapper);
        result.addAll(list);
        return result;
    }

    @Override
    public void export(Integer projectId, Integer bulkheadId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }

        BulkheadCheckResult bulkheadCheckResult = listResultByBulkheadId(projectId, bulkheadId);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();
        FileDownloadUtils.setResponseHeader(SpringUtils.getRequest(), SpringUtils.getResponse(), "舱壁板材校核计算结果.xlsx");

        if (null != bulkheadCheckResult) {
            // 准备表头数据
            List<List<String>> headList = new ArrayList<>();
            List<String> head0 = new ArrayList<>();
            head0.add("层间名称");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("均布载荷");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("LGV");
            headList.add(head2);
            List<String> head3 = new ArrayList<>();
            head3.add("U输出");
            headList.add(head3);
            List<String> head4 = new ArrayList<>();
            head4.add("Chi1输出");
            headList.add(head4);
            List<String> head5 = new ArrayList<>();
            head5.add("Chi2输出");
            headList.add(head5);
            List<String> head6 = new ArrayList<>();
            head6.add("悬链应力");
            headList.add(head6);
            List<String> head7 = new ArrayList<>();
            head7.add("跨中应力");
            headList.add(head7);
            List<String> head8 = new ArrayList<>();
            head8.add("支座应力");
            headList.add(head8);
            List<String> head9 = new ArrayList<>();
            head9.add("许用剪力");
            headList.add(head9);

            // 准备数据列表
            List<List<Object>> dataList = new ArrayList<>();
            List<String> strdeckdistrict = bulkheadCheckResult.getStrdeckdistrict();
            List<Number> disload = bulkheadCheckResult.getDisload();
            List<Number> lgvList = bulkheadCheckResult.getLgvList();
            List<Number> uList = bulkheadCheckResult.getUList();
            List<Number> chi1List = bulkheadCheckResult.getChi1List();
            List<Number> chi2List = bulkheadCheckResult.getChi2List();
            List<Number> stressXlList = bulkheadCheckResult.getStressXlList();
            List<Number> stressKuozhong = bulkheadCheckResult.getStressKuozhong();
            List<Number> stressZhizuo = bulkheadCheckResult.getStressZhizuo();
            List<Number> shearAllow = bulkheadCheckResult.getShearAllow();

            int maxListSize = Math.max(strdeckdistrict.size(), Math.max(disload.size(), Math.max(lgvList.size(),
                    Math.max(uList.size(), Math.max(chi1List.size(), Math.max(chi2List.size(), Math.max(stressXlList.size(),
                            Math.max(stressKuozhong.size(), Math.max(stressZhizuo.size(), shearAllow.size())))))))));
            for (int i = 0; i < maxListSize; i++) {
                List<Object> rowData = new ArrayList<>();
                if (i < strdeckdistrict.size()) {
                    rowData.add(strdeckdistrict.get(i));
                } else {
                    rowData.add("");
                }
                if (i < disload.size()) {
                    rowData.add(disload.get(i));
                } else {
                    rowData.add("");
                }
                if (i < lgvList.size()) {
                    rowData.add(lgvList.get(i));
                } else {
                    rowData.add("");
                }
                if (i < uList.size()) {
                    rowData.add(uList.get(i));
                } else {
                    rowData.add("");
                }
                if (i < chi1List.size()) {
                    rowData.add(chi1List.get(i));
                } else {
                    rowData.add("");
                }
                if (i < chi2List.size()) {
                    rowData.add(chi2List.get(i));
                } else {
                    rowData.add("");
                }
                if (i < stressXlList.size()) {
                    rowData.add(stressXlList.get(i));
                } else {
                    rowData.add("");
                }
                if (i < stressKuozhong.size()) {
                    rowData.add(stressKuozhong.get(i));
                } else {
                    rowData.add("");
                }
                if (i < stressZhizuo.size()) {
                    rowData.add(stressZhizuo.get(i));
                } else {
                    rowData.add("");
                }
                if (i < shearAllow.size()) {
                    rowData.add(shearAllow.get(i));
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
}
