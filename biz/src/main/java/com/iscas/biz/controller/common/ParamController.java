package com.iscas.biz.controller.common;

import com.google.common.collect.ImmutableMap;
import com.iscas.base.biz.util.JWTUtils;
import com.iscas.biz.config.log.LogRecord;
import com.iscas.biz.config.log.LogType;
import com.iscas.biz.config.log.OperateType;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.biz.service.common.ParamService;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.AuthenticationRuntimeException;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/26 14:37
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/param")
@Tag(name = "参数管理-ParamController")
@ConditionalOnMybatis
public class ParamController extends BaseController {


    private final static String TABLE_IDENTITY = "param";
    @Autowired
    private TableDefinitionService tableDefinitionService;
    @Autowired
    private ParamService paramService;

    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/getHeader", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(TABLE_IDENTITY);
    }

    @Operation(summary = "查询表格数据", description = "不带表头")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件", content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping(value = "/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(TABLE_IDENTITY, request, null);
    }

    @Operation(summary = "删除参数数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "id的集合", content = @Content(examples = @ExampleObject(value = "[123,124]")))
    @PostMapping("/del")
    @LogRecord(type = LogType.SYSTEM, desc = "删除参数", operateType = OperateType.delete)
    public ResponseEntity deleteData(@RequestBody List<Object> ids) {
        ResponseEntity responseEntity = getResponse();
        boolean ret = paramService.deleteByIds(ids);
        responseEntity.setValue(ret);
        return responseEntity;
    }

    @Operation(summary = "新增参数数据", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据", content = @Content(examples = @ExampleObject(value = "{\"key1\":\"val1\"}")))
    @PostMapping("/data")
    @LogRecord(type = LogType.SYSTEM, desc = "新增参数", operateType = OperateType.add)
    public ResponseEntity saveData(@RequestBody Map<String, Object> data) throws ValidDataException {
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("create_by", getUsername(), "create_time", DateSafeUtils.format(new Date()));
        return tableDefinitionService.saveData(TABLE_IDENTITY, data, false, null, forceItem);
    }

    @Operation(summary = "修改参数数据", description = "更新")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据(未变动的数据也传)", content = @Content(examples = @ExampleObject(value = "{\"key1\":\"val1\"}")))
    @PutMapping("/data")
    @CacheEvict(value = "param", key = "#data.get(\"param_key\")")
    @LogRecord(type = LogType.SYSTEM, desc = "修改参数", operateType = OperateType.update)
    public ResponseEntity editData(@RequestBody Map<String, Object> data)
            throws ValidDataException {
        ImmutableMap<String, Object> forceItem = ImmutableMap.of("update_by", getUsername(), "update_time", DateSafeUtils.format(new Date()));
        return tableDefinitionService.saveData(TABLE_IDENTITY, data, false, null, forceItem);
    }

    @GetMapping("/getParamValue/{key}")
    @Operation(summary = "测试", description = "getParamValue")
    public ResponseEntity getParamValue(@PathVariable String key) {
        ResponseEntity response = getResponse();
        String value = paramService.getParamValue(key);
        response.setValue(value);
        return response;

    }

    private String getUsername() {
        String username;
        try {
            username = JWTUtils.getLoginUsername();
        } catch (AuthenticationRuntimeException e) {
            username = "unknown";
        }
        return username;
    }
}
