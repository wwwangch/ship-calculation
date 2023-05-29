package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.GirderStrength;
import com.iscas.biz.calculation.entity.db.Weight;
import com.iscas.biz.calculation.entity.dto.GirderStrengthDTO;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.service.GirderStrengthService;
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
@RequestMapping("/girderStrength")
@Tag(name = "板件弯矩应力计算  对应总纵强度校核")
public class GirderStrengthController {


    private GirderStrengthService girderStrengthService;

    public GirderStrengthController(GirderStrengthService girderStrengthService) {
        this.girderStrengthService = girderStrengthService;
    }


    @Operation(summary = "查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @PostMapping(value = "/getData/{projectId}")
    public GirderStrength getData(@PathVariable Integer projectId) {
        return girderStrengthService.listByProjectId(projectId);
    }

    @Operation(summary = "计算", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"kuaChang\":\"xx\",\"girderDistance\":\"xxx\"}")))
    @PostMapping(value = "/calculate")
    public ResponseEntity saveData(@RequestBody GirderStrengthDTO girderStrengthDTO) {
        return ResponseEntity.ok(girderStrengthService.calculate(girderStrengthDTO));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}")
    public void export(@PathVariable() Integer projectId) throws IOException {
        girderStrengthService.export(projectId);
    }
}
