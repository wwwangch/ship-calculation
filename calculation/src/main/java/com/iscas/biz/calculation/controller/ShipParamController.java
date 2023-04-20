package com.iscas.biz.calculation.controller;

import com.iscas.biz.calculation.entity.db.ShipParam;
import com.iscas.biz.calculation.service.ShipParamService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.ComboboxData;
import com.iscas.templet.view.table.TableSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/11 15:01
 */
@RestController
@Slf4j
@RequestMapping("/ship")
@Tag(name = "船舶参数")
public class ShipParamController {
    private final static String TABLE_IDENTITY = "ship_param";

    private final ShipParamService shipParamService;

    private final TableDefinitionService tableDefinitionService;

    public ShipParamController(ShipParamService shipParamService, TableDefinitionService tableDefinitionService) {
        this.shipParamService = shipParamService;
        this.tableDefinitionService = tableDefinitionService;
    }

    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(TABLE_IDENTITY);
    }

    @Operation(summary = "查询表格数据", description = "不带表头")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件",
            content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping(value = "/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(TABLE_IDENTITY, request, null);
    }

    @Operation(summary = "删除参数数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "id的集合",
            content = @Content(examples = @ExampleObject(value = "[123, 124]")))
    @PostMapping("/del")
    public Boolean deleteData(@RequestBody List<Integer> ids) throws BaseException {
        return shipParamService.deleteByIds(ids);
    }

    @Operation(summary = "新增", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PostMapping("/data")
    public Integer saveData(@RequestBody Map<String, Object> data) throws ValidDataException {
        return shipParamService.save(data);
    }

    @Operation(summary = "上传文件新增", description = "通过上传配置来新增数据,只传项目id和文件")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据,只传项目id和文件",
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
    @PostMapping(value = "/data/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer saveDataWithFile(ShipParam shipParam) {
        return shipParamService.saveWithFile(shipParam);
    }

    @Operation(summary = "修改数据", description = "更新")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据(未变动的数据也传)",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PutMapping("/data")
    public Integer editData(@RequestBody Map<String, Object> data) throws ValidDataException {
        return shipParamService.updateById(data);
    }

    @Operation(summary = "获取航行区域下拉框", description = "获取航行区域下拉框")
    @GetMapping("/combobox/area")
    public List<ComboboxData> listNavigationAreaCombobox() {
        return shipParamService.listNavigationAreaCombobox();
    }
}
