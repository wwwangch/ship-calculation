package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.StaticLoad;
import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.service.StrengthService;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/5/31 17:14
 */
@RestController
@Slf4j
@RequestMapping("/strength")
@Tag(name = "总纵强度校核")
public class StrengthController {
    //todo getdata  calcalate export

    private StrengthService strengthService;

    public StrengthController(StrengthService strengthService) {
        this.strengthService = strengthService;
    }

//    @Operation(summary = "sigma1查询表格数据,多条", description = "传项目id,剖面id，带计算结果返回")
//    @GetMapping(value = "/sigma1/getData/{projectId}/{sectionId}")
//    public TableResponse getSigma1Data(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
//        TableResponse tableResponse = new TableResponse();
//        TableResponseData tableResponseData = new TableResponseData();
//        List<Sigma1> sigma1List = strengthService.getSigma1(projectId, sectionId);
//        tableResponseData.setRows((long) sigma1List.size());
//        tableResponseData.setData(sigma1List);
//        tableResponse.setValue(tableResponseData);
//        return tableResponse;
//    }

    @Operation(summary = "sigma2查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma2/getData/{projectId}/{sectionId}")
    public TableResponse getSigma2Data(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
        TableResponse tableResponse = new TableResponse();
        TableResponseData tableResponseData = new TableResponseData();
        List<Sigma2> sigma2List = strengthService.getSigma2(projectId, sectionId);
        tableResponseData.setRows((long) sigma2List.size());
        tableResponseData.setData(sigma2List);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }

    @Operation(summary = "sigma3查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma3/getData/{projectId}/{sectionId}")
    public TableResponse getSigma3Data(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
        TableResponse tableResponse = new TableResponse();
        TableResponseData tableResponseData = new TableResponseData();
        List<Sigma3> sigma3List = strengthService.getSigma3(projectId, sectionId);
        tableResponseData.setRows((long) sigma3List.size());
        tableResponseData.setData(sigma3List);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }

    @Operation(summary = "sigma4查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma4/getData/{projectId}/{sectionId}")
    public TableResponse getSigma4Data(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
        TableResponse tableResponse = new TableResponse();
        TableResponseData tableResponseData = new TableResponseData();
        List<Sigma4> sigma4List = strengthService.getSigma4(projectId, sectionId);
        tableResponseData.setRows((long) sigma4List.size());
        tableResponseData.setData(sigma4List);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }

    @Operation(summary = "ShearingStress查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/shearingStress/getData/{projectId}/{sectionId}")
    public TableResponse getShearingStresssData(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
        TableResponse tableResponse = new TableResponse();
        TableResponseData tableResponseData = new TableResponseData();
        List<ShearingStress> shearingStressList = strengthService.getShearingStress(projectId, sectionId);
        tableResponseData.setRows((long) shearingStressList.size());
        tableResponseData.setData(shearingStressList);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }

    @Operation(summary = "计算Sigma1", description = "计算,传入参数")
    @GetMapping(value = "/sigma1/calculate/{projectId}/{sectionId}")
    public ResponseEntity calSigma1(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IllegalAccessException {
        strengthService.calSigma1(projectId,sectionId);
        return ResponseEntity.ok(strengthService.calSigma1(projectId,sectionId));
    }

    @Operation(summary = "计算Sigma2", description = "计算,传入参数")
    @GetMapping(value = "/sigma2/calculate/{projectId}/{sectionId}")
    public ResponseEntity calSigma2(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IllegalAccessException {
        return ResponseEntity.ok(strengthService.calSigma2(projectId,sectionId));
    }

    @Operation(summary = "计算Sigma3", description = "计算,传入参数")
    @GetMapping(value = "/sigma3/calculate/{projectId}/{sectionId}")
    public ResponseEntity calSigma3(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IllegalAccessException {
        return ResponseEntity.ok(strengthService.calSigma3(projectId,sectionId));
    }

    @Operation(summary = "计算Sigma4", description = "计算,传入参数")
    @GetMapping(value = "/sigma4/calculate/{projectId}/{sectionId}")
    public ResponseEntity calSigma4(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IllegalAccessException {
        return ResponseEntity.ok(strengthService.calSigma4(projectId,sectionId));
    }

    @Operation(summary = "计算ShearingStress", description = "计算,传入参数")
    @GetMapping(value = "/ShearingStress/calculate/{projectId}/{sectionId}")
    public ResponseEntity calShearingStress(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IllegalAccessException {
        return ResponseEntity.ok(strengthService.calShearingStress(projectId,sectionId));
    }
//
//    @Operation(summary = "sigma1导出", description = "导出")
//    @GetMapping("/sigma1/download/{projectId}/{sectionId}")
//    public void exportSigma1(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IOException {
//        strengthService.sigma1Export(projectId,sectionId);
//    }

//    @Operation(summary = "sigma2导出", description = "导出")
//    @GetMapping("/sigma2/download/{projectId}/{sectionId}")
//    public void exportSigma2(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IOException {
//        strengthService.sigma2Export(projectId,sectionId);
//    }
//
//    @Operation(summary = "sigma3导出", description = "导出")
//    @GetMapping("/sigma3/download/{projectId}/{sectionId}")
//    public void exportSigma3(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IOException {
//        strengthService.sigma3Export(projectId,sectionId);
//    }
//
//    @Operation(summary = "sigma4导出", description = "导出")
//    @GetMapping("/sigma4/download/{projectId}/{sectionId}")
//    public void exportSigma4(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IOException {
//        strengthService.sigma4Export(projectId,sectionId);
//    }

    @Operation(summary = "shearingStress导出", description = "导出")
    @GetMapping("/shearingStress/download/{projectId}/{sectionId}")
    public void exportShearingStress(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IOException {
        strengthService.shearingStressExport(projectId,sectionId);
    }


}
