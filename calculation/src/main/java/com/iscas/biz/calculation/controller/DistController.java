package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.Dist;
import com.iscas.biz.calculation.service.DistService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaotianci@iscas.ac.cn
 * @date 2023/5/27 23:54
 */

@RestController
@Slf4j
@RequestMapping("/dist")
@Tag(name = "应力分布计算-极限弯矩校核")
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
    @PostMapping(value = "/getData/{projectId}/{sectionId}")
    public TableResponse getData(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
        TableResponse tableResponse = new TableResponse();
        TableResponseData tableResponseData = new TableResponseData();
        Dist tmp = distService.getData(projectId, sectionId);
        List<Dist> distList = new ArrayList<>();
        distList.add(tmp);
        tableResponseData.setRows((long) distList.size());
        tableResponseData.setData(distList);
        tableResponse.setValue(tableResponseData);
        return tableResponse;
    }

    @Operation(summary = "计算", description = "计算,应力分布计算不需要传入参数")
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据")
    @PostMapping("/calculate/{projectId}/{sectionId}")
    public ResponseEntity saveData(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws ValidDataException {
        return ResponseEntity.ok(distService.calculateAndSave(projectId, sectionId));
    }

//    @Operation(summary = "重置", description = "重置")
//    @PostMapping("/reset/{projectId}/{sectionId}")
//    public ResponseEntity reset(@PathVariable Integer projectId, @PathVariable Integer sectionId) {
//        return ResponseEntity.ok(distService.reset(projectId, sectionId));
//    }

    @Operation(summary = "导出", description = "导出")
    @GetMapping("/download/{projectId}/{sectionId}")
    public void export(@PathVariable Integer projectId, @PathVariable Integer sectionId) throws IOException {
        distService.export(projectId, sectionId);
    }
}
