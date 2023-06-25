//package com.iscas.biz.calculation.service.impl;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.write.metadata.WriteSheet;
//import com.alibaba.excel.write.metadata.WriteTable;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.google.common.collect.Lists;
//import com.iscas.base.biz.util.SpringUtils;
//import com.iscas.biz.calculation.entity.db.Bulkhead;
//import com.iscas.biz.calculation.entity.db.CalAddition;
//import com.iscas.biz.calculation.entity.db.Project;
//import com.iscas.biz.calculation.entity.db.ShipParam;
//import com.iscas.biz.calculation.entity.dto.BuoyancyParamExcel;
//import com.iscas.biz.calculation.entity.dto.CalAdditionDTO;
//import com.iscas.biz.calculation.entity.dto.CalAdditionParamExcel;
//import com.iscas.biz.calculation.grpc.service.AlgorithmGrpc;
//import com.iscas.biz.calculation.mapper.BulkheadMapper;
//import com.iscas.biz.calculation.mapper.CalAdditionMapper;
//import com.iscas.biz.calculation.mapper.ProjectMapper;
//import com.iscas.biz.calculation.mapper.ShipParamMapper;
//import com.iscas.biz.calculation.service.CalAdditionService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author wujiyue
// * @date 2023-06-03
// * @apiNote
// */
//
//@Service
//@Slf4j
//public class CalAdditionServiceImpl implements CalAdditionService {
//
//    private final CalAdditionMapper calAdditionMapper;
//
//    private final BulkheadMapper bulkheadMapper;
//
//    private final ProjectMapper projectMapper;
//
//    private final ShipParamMapper shipParamMapper;
//
//    private final AlgorithmGrpc algorithmGrpc;
//
//    public CalAdditionServiceImpl(CalAdditionMapper calAdditionMapper, BulkheadMapper bulkheadMapper, ProjectMapper projectMapper, ShipParamMapper shipParamMapper, AlgorithmGrpc algorithmGrpc) {
//        this.calAdditionMapper = calAdditionMapper;
//        this.bulkheadMapper = bulkheadMapper;
//        this.projectMapper = projectMapper;
//        this.shipParamMapper = shipParamMapper;
//        this.algorithmGrpc = algorithmGrpc;
//    }
//
//    @Override
//    public CalAddition listBybulkheadId(Integer bulkheadId) {
//
//        QueryWrapper<CalAddition> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(CalAddition::getBulkheadId, bulkheadId);
//        List<CalAddition> calAdditions = calAdditionMapper.selectList(queryWrapper);
//        if (CollectionUtils.isNotEmpty(calAdditions)) {
//            if (calAdditions.size() > 1) {
//                for (int i = 1; i < calAdditions.size(); i++) {
//                    calAdditionMapper.deleteById(calAdditions.get(i));
//                }
//            }
//            return calAdditions.get(0);
//        }
//        return null;
//    }
//
//    @Override
//    public void export(Integer projectId) throws IOException {
//        Project project = projectMapper.selectById(projectId);
//        if (null == project) {
//            throw new RuntimeException("当前项目不存在!");
//        }
//
//        QueryWrapper<CalAddition> calAdditionQueryWrapper = new QueryWrapper<>();
//        calAdditionQueryWrapper.lambda().eq(CalAddition::getProjectId, projectId);
//        CalAddition calAddition = calAdditionMapper.selectOne(calAdditionQueryWrapper);
//        ExcelWriter excelWriter = EasyExcel.write(SpringUtils.getResponse().getOutputStream())
//                .autoTrim(true).build();
//
//        CalAdditionParamExcel calAdditionParamExcel = new CalAdditionParamExcel();
//
//        calAdditionParamExcel.setLeiweihaos(calAddition.getLeiweihaos());
//        calAdditionParamExcel.setAddyatouh(calAddition.getAddyatouh());
//
//        WriteSheet writeSheet = EasyExcel.writerSheet(0).needHead(false).build();
//
//        WriteTable paramTable = EasyExcel.writerTable(0).head(BuoyancyParamExcel.class).needHead(true).build();
//        excelWriter.write(Lists.newArrayList(calAdditionParamExcel), writeSheet, paramTable);
//        excelWriter.finish();
//
//    }
//
//    @Override
//    public CalAddition calAdditional(CalAdditionDTO calAdditionDTO) {
//        Integer projectId = calAdditionDTO.getProjectId();
//        if (null == projectId || null == projectMapper.selectById(projectId)) {
//            return null;
//        }
//
//        QueryWrapper<ShipParam> shipParamQueryWrapper = new QueryWrapper<>();
//        shipParamQueryWrapper.lambda().eq(ShipParam::getProjectId, projectId);
//        ShipParam shipParam = shipParamMapper.selectOne(shipParamQueryWrapper);
//
//        calAdditionDTO.setFreeboard(shipParam.getFreeboard());
//
//        Bulkhead bulkhead = bulkheadMapper.selectById(calAdditionDTO.getBulkheadId());
//        calAdditionDTO.setLeiweihao(bulkhead.getLeiweihao());
//        calAdditionDTO.setCangbiWeizhi(bulkhead.getCangbiweizhi());
//        calAdditionDTO.setIsCollision(bulkhead.getCollisionBulkhead());
//        calAdditionDTO.setShuidongYali(bulkhead.getShuidongyali());
//
//        CalAddition calAddition = algorithmGrpc.calAddition(calAdditionDTO);
//
//        CalAddition addition = listBybulkheadId(calAddition.getBulkheadId());
//
//        if (null != addition){
//            Integer additionId = addition.getCalAdditionId();
//            calAddition.setCalAdditionId(additionId);
//            calAdditionMapper.deleteById(additionId);
//        }
//        calAddition.setBulkheadId(calAdditionDTO.getBulkheadId());
//        calAdditionMapper.insert(calAddition);
//        return calAddition;
//    }
//}
