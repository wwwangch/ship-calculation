package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.CalSection;
import com.iscas.biz.calculation.entity.dto.CalSectionDTO;
import com.iscas.biz.calculation.service.CalSectionService;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/25 16:54
 */
@RestController
@Slf4j
@RequestMapping("/calSection")
@Tag(name = "剖面计算")
public class CalSectionController {


    private CalSectionService calSectionService;

    public CalSectionController(CalSectionService calSectionService) {
        this.calSectionService = calSectionService;
    }


    @Operation(summary = "查询表格数据,仅一条", description = "传剖面id，带计算结果返回")
    @PostMapping(value = "/getData/{projectId}/{sectionId}")
    public CalSection getData(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
        return calSectionService.listBySectionIdId(projectId, sectionId);
    }

    @Operation(summary = "计算", description = "计算,传入参数")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"projectId\":1,\"sectionId\":1,\"profileFilePath\":\"xx\",\"profileFileName\":\"xxx\"}")))
    @PostMapping(value = "/calculate")
    public ResponseEntity saveData(@RequestBody CalSectionDTO calSectionDTO) {
        return ResponseEntity.ok(calSectionService.calculate(calSectionDTO));
    }

//    @Operation(summary = "导出", description = "导出")
//    @GetMapping("/download/{projectId}")
//    public void export(@PathVariable() Integer projectId) throws IOException {
//        calSectionService.export(projectId);
//    }
}
