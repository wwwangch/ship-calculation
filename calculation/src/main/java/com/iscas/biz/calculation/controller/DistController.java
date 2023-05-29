package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.Dist;
import com.iscas.biz.calculation.service.DistService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/27 23:54
 */

@RestController
@Slf4j
@RequestMapping("/dist")
@Tag(name = "应力分布计算")
public class DistController {

    private final static String TABLE_IDENTITY = "dist";

    private DistService distService;

    private TableDefinitionService tableDefinitionService;

    public DistController(DistService distService, TableDefinitionService tableDefinitionService) {
        this.distService = distService;
        this.tableDefinitionService = tableDefinitionService;
    }

    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(TABLE_IDENTITY);
    }

    @Operation(summary = "查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @PostMapping(value = "/getData/{projectId}")
    public Dist getData(@PathVariable Integer projectId) {
        return distService.getData(projectId);
    }

    @Operation(summary = "计算", description = "计算,应力分布计算不需要传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PostMapping("/calculate/{projectId}")
    public ResponseEntity saveData(@PathVariable Integer projectId) throws ValidDataException {
        return ResponseEntity.ok(distService.calculateAndSave(projectId));
    }

    @Operation(summary = "重置", description = "重置")
    @PostMapping("/reset/{projectId}")
    public ResponseEntity reset(@PathVariable() Integer projectId) {
        return ResponseEntity.ok(distService.reset(projectId));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}")
    public void export(@PathVariable() Integer projectId) throws IOException {
        distService.export(projectId);
    }
}
