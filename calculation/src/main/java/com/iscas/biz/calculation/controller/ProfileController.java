package com.iscas.biz.calculation.controller;

import com.google.common.collect.ImmutableMap;
import com.iscas.biz.calculation.service.BulbFlatService;
import com.iscas.biz.calculation.service.TProfileService;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/13 20:51
 */
@RestController
@Slf4j
@RequestMapping("/profile")
@Tag(name = "型材库管理")
public class ProfileController {
    private final static String TABLE_IDENTITY = "bulb_flat";

    private final static String T_TABLE_IDENTITY = "t_profile";

    private final TableDefinitionService tableDefinitionService;

    private final TProfileService tProfileService;

    private final BulbFlatService bulbFlatService;

    public ProfileController(TableDefinitionService tableDefinitionService, TProfileService tProfileService, BulbFlatService bulbFlatService) {
        this.tableDefinitionService = tableDefinitionService;
        this.tProfileService = tProfileService;
        this.bulbFlatService = bulbFlatService;
    }

    @Operation(summary = "获取球扁钢型材表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(TABLE_IDENTITY);
    }

    @Operation(summary = "查询球扁钢数据", description = "不带表头")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件",
            content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping(value = "/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(TABLE_IDENTITY, request, null);
    }

    @Operation(summary = "删除球扁钢数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "id的集合",
            content = @Content(examples = @ExampleObject(value = "[123, 124]")))
    @PostMapping("/del")
    public Boolean deleteData(@RequestBody List<Integer> ids) throws BaseException {
        return bulbFlatService.deleteByIds(ids);
    }

    @Operation(summary = "新增球扁钢", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PostMapping("/data")
    public ResponseEntity saveData(@RequestBody Map<String, Object> data) throws ValidDataException {
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("create_time", DateSafeUtils.format(new Date()));
        return tableDefinitionService.saveData(TABLE_IDENTITY, data, false, null, forceItem);
    }

    @Operation(summary = "上传文件新增球扁钢", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据")
    @PostMapping(value = "/data/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer saveDataByFile(MultipartFile file) {
        return bulbFlatService.uploadFile(file);
    }

    @Operation(summary = "修改球扁钢数据", description = "更新")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据(未变动的数据也传)",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PutMapping("/data")
    public ResponseEntity editData(@RequestBody Map<String, Object> data)
            throws ValidDataException {
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("update_time", DateSafeUtils.format(new Date()));
        return tableDefinitionService.saveData(TABLE_IDENTITY, data, false, null, forceItem);
    }

    @Operation(summary = "获取T型材表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/t/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTProfileTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(T_TABLE_IDENTITY);
    }

    @Operation(summary = "查询T型材数据", description = "不带表头")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件",
            content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping(value = "/t/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(T_TABLE_IDENTITY, request, null);
    }

    @Operation(summary = "删除T型材数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "id的集合",
            content = @Content(examples = @ExampleObject(value = "[123, 124]")))
    @PostMapping("/t/del")
    public Boolean deleteTData(@RequestBody List<Integer> ids) throws BaseException {
        return tProfileService.deleteByIds(ids);
    }

    @Operation(summary = "新增T型材", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PostMapping("/t/data")
    public ResponseEntity saveTData(@RequestBody Map<String, Object> data) throws ValidDataException {
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("create_time", DateSafeUtils.format(new Date()));
        return tableDefinitionService.saveData(T_TABLE_IDENTITY, data, false, null, forceItem);
    }

    @Operation(summary = "上传文件新增T型材", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的文件")
    @PostMapping(value = "/t/data/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Integer saveTDataByFile(MultipartFile file) {
        return tProfileService.uploadFile(file);
    }

    @Operation(summary = "修改T型材数据", description = "更新")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据(未变动的数据也传)",
            content = @Content(examples = @ExampleObject(value = "{\"key\":\"val\"}")))
    @PutMapping("/t/data")
    public ResponseEntity editTData(@RequestBody Map<String, Object> data)
            throws ValidDataException {
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("update_time", DateSafeUtils.format(new Date()));
        return tableDefinitionService.saveData(T_TABLE_IDENTITY, data, false, null, forceItem);
    }
}
