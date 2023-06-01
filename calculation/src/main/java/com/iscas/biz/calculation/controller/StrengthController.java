package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.StaticLoad;
import com.iscas.biz.calculation.entity.db.sigma.*;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.service.StrengthService;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "sigma1查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma1/getData/{projectId}/{sectionId}")
    public Sigma1 getSigma1Data(@PathVariable Integer projectId, Integer sectionId) {
        return strengthService.getSigma1(projectId, sectionId);
    }

    @Operation(summary = "sigma2查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma2/getData/{projectId}/{sectionId}")
    public Sigma2 getSigma2Data(@PathVariable Integer projectId, Integer sectionId) {
        return strengthService.getSigma2(projectId, sectionId);
    }

    @Operation(summary = "sigma3查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma3/getData/{projectId}/{sectionId}")
    public Sigma3 getSigma3Data(@PathVariable Integer projectId, Integer sectionId) {
        return strengthService.getSigma3(projectId, sectionId);
    }

    @Operation(summary = "sigma4查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/sigma4/getData/{projectId}/{sectionId}")
    public Sigma4 getSigma4Data(@PathVariable Integer projectId, Integer sectionId) {
        return strengthService.getSigma4(projectId, sectionId);
    }

    @Operation(summary = "ShearingStress查询表格数据,仅一条", description = "传项目id,剖面id，带计算结果返回")
    @GetMapping(value = "/shearingStress/getData/{projectId}/{sectionId}")
    public ShearingStress getShearingStresssData(@PathVariable Integer projectId, Integer sectionId) {
        return strengthService.getShearingStress(projectId, sectionId);
    }

    @Operation(summary = "计算Sigma1", description = "计算,传入参数")
    @GetMapping(value = "/sigma3/calculate/{projectId}/{sectionId}")
    public ResponseEntity calSigma1(@PathVariable Integer projectId, Integer sectionId) throws IllegalAccessException {
        return ResponseEntity.ok(strengthService.calSigma1(projectId,sectionId));
    }

}
