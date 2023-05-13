package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.BuoyancyParam;
import com.iscas.biz.calculation.service.BuoyancyCalculationService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 16:54
 */
@RestController
@Slf4j
@RequestMapping("/buoyancy")
@Tag(name = "外力-浮力分布计算")
public class BuoyancyController {
    private final static String TABLE_IDENTITY = "buoyancy_param";

    private TableDefinitionService tableDefinitionService;

    private BuoyancyCalculationService buoyancyCalculationService;

    public BuoyancyController(BuoyancyCalculationService buoyancyCalculationService, TableDefinitionService tableDefinitionService) {
        this.buoyancyCalculationService = buoyancyCalculationService;
        this.tableDefinitionService = tableDefinitionService;
    }

    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(TABLE_IDENTITY);
    }

    @Operation(summary = "查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @PostMapping(value = "/getData/{projectId}")
    public BuoyancyParam getData(@PathVariable Integer projectId) {
        return buoyancyCalculationService.getData(projectId);
    }

    @Operation(summary = "计算", description = "计算,传入浮力计算参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PostMapping("/calculate")
    public ResponseEntity saveData(@RequestBody Map<String, Object> data) throws ValidDataException {
        return ResponseEntity.ok(buoyancyCalculationService.saveAndCalculate(data));
    }

    @Operation(summary = "重置", description = "重置")
    @PostMapping("/reset/{projectId}")
    public ResponseEntity reset(@PathVariable() Integer projectId) {
        return ResponseEntity.ok(buoyancyCalculationService.reset(projectId));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}")
    public void export(@PathVariable() Integer projectId) throws IOException {
        buoyancyCalculationService.export(projectId);
    }
}
