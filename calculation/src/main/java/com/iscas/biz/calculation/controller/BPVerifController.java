package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.BulkheadCheckResult;
import com.iscas.biz.calculation.entity.dto.BulkheadDTO;
import com.iscas.biz.calculation.service.BulkheadCompartmentService;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/6/4 11:44
 */
@RestController
@RequestMapping("/bpverif")
@Slf4j
@Tag(name = "舱壁板校核")
public class BPVerifController {

    private final BulkheadCompartmentService compartmentService;

    public BPVerifController(BulkheadCompartmentService compartmentService) {
        this.compartmentService = compartmentService;
    }


    @Operation(summary = "舱壁板材校核结果查询,仅一条", description = "传舱壁id，仅返回计算结果")
    @GetMapping(value = "/getData/{bulkheadId}")
    public BulkheadCheckResult getCheckData(@PathVariable Integer bulkheadId) {
        return compartmentService.listResultByBulkheadId(bulkheadId);
    }

    @Operation(summary = "舱壁板材校核", description = "舱壁板材校核")
    @PostMapping(value = "/check")
    public ResponseEntity checkBulkheadPlate(@RequestBody BulkheadDTO bulkheadDTO) {
        return ResponseEntity.ok(compartmentService.checkBulkhead(bulkheadDTO));
    }

}
