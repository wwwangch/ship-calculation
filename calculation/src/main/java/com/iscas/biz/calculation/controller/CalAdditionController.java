package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.CalAddition;
import com.iscas.biz.calculation.entity.dto.CalAdditionDTO;
import com.iscas.biz.calculation.service.CalAdditionService;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author wujiyue
 * @date 2023-06-03
 * @apiNote
 */

@RestController
@Slf4j
@RequestMapping("/calAddition")
@Tag(name = "附加压头计算")
public class CalAdditionController {

    private final CalAdditionService calAdditionService;

    public CalAdditionController(CalAdditionService calAdditionService) {
        this.calAdditionService = calAdditionService;
    }

    @Operation(summary = "查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @PostMapping(value = "/getData/{projectId}/{bulkheadId}")
    public CalAddition getData(@PathVariable Integer projectId, @PathVariable Integer bulkheadId) {
        return calAdditionService.listByBulkheadId(projectId,bulkheadId);
    }

    @Operation(summary = "计算", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"bulkheadId\":1}")))
    @PostMapping(value = "/calAdditional")
    public ResponseEntity saveData(@RequestBody CalAdditionDTO calAdditionDTO) {
        return ResponseEntity.ok(calAdditionService.calAdditional(calAdditionDTO));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}/{bulkheadId}")
    public void export(@PathVariable() Integer projectId, @PathVariable Integer bulkheadId) throws IOException {
        calAdditionService.export(projectId,bulkheadId);
    }
}
