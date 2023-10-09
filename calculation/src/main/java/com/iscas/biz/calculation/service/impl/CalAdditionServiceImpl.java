package com.iscas.biz.calculation.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.biz.calculation.entity.db.*;
import com.iscas.biz.calculation.entity.dto.CalAdditionDTO;
import com.iscas.biz.calculation.enums.UpBuiltForm;
import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
import com.iscas.biz.calculation.mapper.BulkheadMapper;
import com.iscas.biz.calculation.mapper.CalAdditionMapper;
import com.iscas.biz.calculation.mapper.ProjectMapper;
import com.iscas.biz.calculation.mapper.ShipParamMapper;
import com.iscas.biz.calculation.service.BulkheadCompartmentService;
import com.iscas.biz.calculation.service.CalAdditionService;
import com.iscas.biz.calculation.service.ShipParamService;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CalAdditionServiceImpl implements CalAdditionService {

    private final CalAdditionMapper calAdditionMapper;

    private final BulkheadMapper bulkheadMapper;

    private final BulkheadCompartmentService bulkheadCompartmentService;

    private final ProjectMapper projectMapper;

    private final ShipParamService shipParamService;

    private final ShipParamMapper shipParamMapper;

    private final AlgorithmGrpc algorithmGrpc;

    public CalAdditionServiceImpl(CalAdditionMapper calAdditionMapper, BulkheadMapper bulkheadMapper, BulkheadCompartmentService bulkheadCompartmentService, ProjectMapper projectMapper, ShipParamService shipParamService, ShipParamMapper shipParamMapper, AlgorithmGrpc algorithmGrpc) {
        this.calAdditionMapper = calAdditionMapper;
        this.bulkheadMapper = bulkheadMapper;
        this.bulkheadCompartmentService = bulkheadCompartmentService;
        this.projectMapper = projectMapper;
        this.shipParamService = shipParamService;
        this.shipParamMapper = shipParamMapper;
        this.algorithmGrpc = algorithmGrpc;
    }

    /**
     * 根据舱壁id查询附加压头计算结果
     *
     * @param bulkheadId
     * @return
     */
    @Override
    public CalAddition listByBulkheadId(Integer projectId, Integer bulkheadId) {

        QueryWrapper<CalAddition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bulkhead_id", bulkheadId);
        //增加工况条件
        shipParamService.addCheckTypeCondition(queryWrapper, projectId);

        List<CalAddition> calAdditions = calAdditionMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(calAdditions)) {
            if (calAdditions.size() > 1) {
                for (int i = 1; i < calAdditions.size(); i++) {
                    calAdditionMapper.deleteById(calAdditions.get(i));
                }
            }
            return calAdditions.get(0);
        }
        return null;
    }

    @Override
    public void export(Integer projectId, Integer bulkheadId) throws IOException {
        Project project = projectMapper.selectById(projectId);
        if (null == project) {
            throw new RuntimeException("当前项目不存在!");
        }

        CalAddition calAddition = listByBulkheadId(projectId, bulkheadId);
        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
                .autoTrim(true).build();
        FileDownloadUtils.setResponseHeader(SpringUtils.getRequest(), SpringUtils.getResponse(), "附加压头结果.xlsx");
        if (null != calAddition) {
            // 准备表头数据
            List<List<String>> headList = new ArrayList<>();
            List<String> head0 = new ArrayList<>();
            head0.add("肋位号数组");
            headList.add(head0);
            List<String> head1 = new ArrayList<>();
            head1.add("附加压头数组");
            headList.add(head1);
            List<String> head2 = new ArrayList<>();
            head2.add("甲板名称");
            headList.add(head2);
            List<String> head3 = new ArrayList<>();
            head3.add("甲板破损压头水压值");
            headList.add(head3);

            // 准备数据列表
            List<List<Object>> dataList = new ArrayList<>();
            List<Number> leiweihaos = calAddition.getLeiweihaos();
            List<Number> addyatouh = calAddition.getAddyatous();
            List<String> strDecks = calAddition.getStrDecks();
            List<Number> deckYatous = calAddition.getDeckYatous();

            for (int i = 0; i < Math.max(leiweihaos.size(), Math.max(addyatouh.size(), Math.max(strDecks.size(), deckYatous.size()))); i++) {
                List<Object> rowData = new ArrayList<>();
                if (i < leiweihaos.size()) {
                    rowData.add(leiweihaos.get(i));
                } else {
                    rowData.add("");
                }
                if (i < addyatouh.size()) {
                    rowData.add(addyatouh.get(i));
                } else {
                    rowData.add("");
                }
                if (i < strDecks.size()) {
                    rowData.add(strDecks.get(i));
                } else {
                    rowData.add("");
                }
                if (i < deckYatous.size()) {
                    rowData.add(deckYatous.get(i));
                } else {
                    rowData.add("");
                }
                dataList.add(rowData);
            }

            WriteSheet writeSheet = EasyExcel.writerSheet("sheet0").head(headList).build();
            excelWriter.write(dataList, writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 附加压头计算
     *
     * @param calAdditionDTO
     * @return
     */
    @Override
    public CalAddition calAdditional(CalAdditionDTO calAdditionDTO) {
        Integer projectId = calAdditionDTO.getProjectId();
        Integer bulkheadId = calAdditionDTO.getBulkheadId();
        Project project;
        if (null == projectId || null == (project = projectMapper.selectById(projectId))) {
            return null;
        }

        //查询船舶参数
        ShipParam shipParam = shipParamService.listByProjectId(projectId);

        calAdditionDTO.setUpBuiltForm(Optional.ofNullable(shipParam.getUpBuiltForm()).orElse(UpBuiltForm.Bow).getValue());
        calAdditionDTO.setFreeboard(shipParam.getFreeboard());
        calAdditionDTO.setRibNumber(shipParam.getRibNumber());
        calAdditionDTO.setMidRibNumber(shipParam.getMidRibNumber());
        calAdditionDTO.setRibSpacing(shipParam.getRibSpacing());
        calAdditionDTO.setForeFreeBoard(shipParam.getForeFreeBoard());
        calAdditionDTO.setAfterFreeBoard(shipParam.getAfterFreeBoard());
        calAdditionDTO.setBridgeForePos(shipParam.getBridgeForePos());
        calAdditionDTO.setBridgeForeHeight(shipParam.getBridgeForeHeight());
        calAdditionDTO.setBridgeAftPos(shipParam.getBridgeAftPos());
        calAdditionDTO.setBridgeAftHeight(shipParam.getBridgeAftHeight());
        calAdditionDTO.setDraugthnoraml(shipParam.getDesignedDraft());
        calAdditionDTO.setAirguanyatou(shipParam.getAirguanyatou());

        Bulkhead bulkhead = bulkheadMapper.selectById(bulkheadId);
        calAdditionDTO.setShuidongyali(bulkhead.getShuidongyali());
        calAdditionDTO.setLeiweihao(bulkhead.getLeiweihao());
        calAdditionDTO.setCollisionBulkhead(bulkhead.getCollisionBulkhead());

        List<String> deckName = Lists.newArrayList();
        List<Double> deckHeight = Lists.newArrayList();
        List<Boolean> liquidTanks = Lists.newArrayList();
        List<BulkheadCompartment> bulkheadCompartments = bulkheadCompartmentService.listByBulkHeadId(bulkheadId);

        for (BulkheadCompartment bulkheadCompartment : bulkheadCompartments) {
            deckName.add(Optional.ofNullable(bulkheadCompartment.getName()).orElse(""));
            deckHeight.add(Optional.ofNullable(bulkheadCompartment.getHeightAbove()).orElse(0D));
            liquidTanks.add(Optional.ofNullable(bulkheadCompartment.getLiquid()).orElse(false));
        }
        calAdditionDTO.setDeckName(bulkhead.getDeckNames());
        calAdditionDTO.setDeckHeight(bulkhead.getDeckHeights());
        calAdditionDTO.setLiquidTanks(liquidTanks);

        CalAddition calAddition = algorithmGrpc.calAddition(calAdditionDTO);

        //填充所属工况
        calAddition.setCheckType(shipParam.getCurrentType());

        CalAddition dbCalAddition = listByBulkheadId(projectId, calAddition.getBulkheadId());

        if (null != dbCalAddition) {
            Integer additionId = dbCalAddition.getCalAdditionId();
            calAddition.setCalAdditionId(additionId);
            calAdditionMapper.deleteById(additionId);
        }
        calAddition.setBulkheadId(bulkheadId);
        calAdditionMapper.insert(calAddition);
        return calAddition;
    }
}
