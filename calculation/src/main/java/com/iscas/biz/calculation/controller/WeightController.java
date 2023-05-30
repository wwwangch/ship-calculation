package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.Weight;
import com.iscas.biz.calculation.entity.dto.WeightDTO;
import com.iscas.biz.calculation.service.WeightService;
import com.iscas.templet.common.ResponseEntity;
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
@RequestMapping("/weight")
@Tag(name = "外力-重量分布计算")
public class WeightController {


    private WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }


    @Operation(summary = "查询表格数据,仅一条", description = "传项目id，带计算结果返回")
    @PostMapping(value = "/getData/{projectId}")
    public Weight getData(@PathVariable Integer projectId) {
        return weightService.listByProjectId(projectId);
    }

    @Operation(summary = "计算", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"loadingFilePath\":\"xx\",\"loadingFileName\":\"xxx\"}")))
    @PostMapping(value = "/calculate")
    public ResponseEntity saveData(@RequestBody WeightDTO weightDTO) {
        return ResponseEntity.ok(weightService.calculate(weightDTO));
    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}")
    public void export(@PathVariable() Integer projectId) throws IOException {
        weightService.export(projectId);
    }
}
