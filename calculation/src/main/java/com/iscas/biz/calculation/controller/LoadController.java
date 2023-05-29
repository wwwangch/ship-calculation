package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.SlamLoad;
import com.iscas.biz.calculation.entity.db.StaticLoad;
import com.iscas.biz.calculation.entity.db.WaveLoad;
import com.iscas.biz.calculation.entity.db.Weight;
import com.iscas.biz.calculation.entity.dto.SlamLoadDTO;
import com.iscas.biz.calculation.entity.dto.StaticLoadDTO;
import com.iscas.biz.calculation.entity.dto.WaveLoadDTO;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.service.LoadService;
import com.iscas.biz.calculation.service.WeightService;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 16:54
 */
@RestController
@Slf4j
@RequestMapping("/load")
@Tag(name = "外力-载荷计算")
public class LoadController {


    private LoadService loadService;

    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }


    @Operation(summary = "静水载荷查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @GetMapping(value = "/static/getData/{projectId}")
    public StaticLoad getStaticData(@PathVariable Integer projectId) {
        return loadService.getStaticData(projectId);
    }

    @Operation(summary = "波浪载荷查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @GetMapping(value = "/wave/getData/{projectId}")
    public WaveLoad getWaveData(@PathVariable Integer projectId) {
        return loadService.getWaveData(projectId);
    }

    @Operation(summary = "抨击载荷查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @GetMapping(value = "/slam/getData/{projectId}")
    public SlamLoad getSlamData(@PathVariable Integer projectId) {
        return loadService.getSlamData(projectId);
    }

    @Operation(summary = "计算静水载荷", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1}")))
    @PostMapping(value = "/static/calculate")
    public ResponseEntity saveStaticData(@RequestBody StaticLoadDTO staticLoadDTO) {
        return ResponseEntity.ok(loadService.calStaticLoad(staticLoadDTO));
    }

    @Operation(summary = "计算波浪载荷", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"waveHeight\":25}")))
    @PostMapping(value = "/wave/calculate")
    public ResponseEntity saveWaveData(@RequestBody WaveLoadDTO waveLoadDTO) {
        return ResponseEntity.ok(loadService.calWaveLoad(waveLoadDTO));
    }
    @Operation(summary = "计算抨击载荷", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"speed\":25}")))
    @PostMapping(value = "/slam/calculate")
    public ResponseEntity saveSlamData(@RequestBody SlamLoadDTO slamLoadDTO) {
        return ResponseEntity.ok(loadService.calSlamLoad(slamLoadDTO));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}")
    public void export(@PathVariable() Integer projectId) throws IOException {
        return;
    }
}
