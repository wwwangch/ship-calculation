package com.iscas.biz.controller.common;

import com.iscas.biz.service.common.MonitorService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/2 13:19
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/monitor")
@Tag(name = "监控管理-MonitorController")
public class MonitorController extends BaseController {

    @Autowired
    private MonitorService monitorService;

    @Operation(summary = "获取系统监控数据", description = "获取系统监控数据")
    @GetMapping(value = "/system/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getSystemData() throws BaseException {
        ResponseEntity response = getResponse();
        try {
            Object result = monitorService.getPhysicalData();
            response.setValue(result);

        } catch (Exception e) {
            throw Exceptions.baseException("获取监控数据出错", e);
        }
        return response;
    }

    @Operation(summary = "获取JVM监控数据", description = "获取JVM监控数据")
    @GetMapping(value = "/jvm/getData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getJvmData() throws BaseException {
        ResponseEntity response = getResponse();
        try {
            Object result = monitorService.getJvmData();
            response.setValue(result);

        } catch (Exception e) {
            throw Exceptions.baseException("获取监控数据出错", e);
        }
        return response;
    }
}
