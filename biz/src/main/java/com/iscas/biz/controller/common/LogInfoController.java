package com.iscas.biz.controller.common;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.biz.service.common.LogInfoService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/2/20 18:48
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@RestController
@RequestMapping("/logInfo")
@Tag(name = "访问日志-LogInfoController")
@ConditionalOnMybatis
public class LogInfoController extends BaseController {

    private final static String TABLE_IDENTITY = "log_info";
    @Autowired
    private TableDefinitionService tableDefinitionService;
    @Autowired
    private LogInfoService logInfoService;

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


    @Operation(summary = "删除数据", description = "删除某个时间点之前的数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "保存周期，保存多久的数据，例如，'period':'1s'（1秒）、1m（1分钟）、1h（1小时）、1d（1天） 等",
            content = @Content(examples = @ExampleObject(value="{\"key\":\"value\"}")))
    @PostMapping(value = "/deleteData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteData(@RequestBody Map requestParam) {

        ResponseEntity response = getResponse();
        Object period = requestParam.get("period");
        if (period == null){
            response.setMessage("参数为空，不执行任何操作");
            return response;
        }
        logInfoService.deleteDataByTime(period.toString());

        return response;

    }

}
