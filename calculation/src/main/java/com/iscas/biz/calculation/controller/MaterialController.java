package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.CalAddition;
import com.iscas.biz.calculation.entity.db.Material;
import com.iscas.biz.calculation.entity.dto.CalAdditionDTO;
import com.iscas.biz.calculation.entity.dto.MaterialDTO;
import com.iscas.biz.calculation.service.MaterialService;
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
@RequestMapping("/material")
@Tag(name = "扶强材计算")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @Operation(summary = "查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @PostMapping(value = "/getData/{bulkheadId}")
    public Material getData(@PathVariable Integer bulkheadId) {
        return materialService.listBybulkheadId(bulkheadId);
    }

    @Operation(summary = "计算", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"bulkheadId\":1}")))
    @PostMapping(value = "/calMaterial")
    public ResponseEntity saveData(@RequestBody MaterialDTO materialDTO) {
        return ResponseEntity.ok(materialService.calMaterial(materialDTO));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}")
    public void export(@PathVariable() Integer projectId) throws IOException {
        materialService.export(projectId);
    }
}
