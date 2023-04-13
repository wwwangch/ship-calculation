package com.iscas.base.biz.controller.common;

import com.iscas.base.biz.util.AuthContextHolder;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.AuthenticationRuntimeException;
import com.iscas.templet.exception.Exceptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.undertow.server.handlers.form.MultiPartParserDefinition;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuquanwen
 */
@SuppressWarnings({"unused", "rawtypes"})
@RestController
@Tag(name = "全局异常处理")
public class ErrorPageController {
    @Value("${spring.servlet.multipart.max-file-size:10485760000}")
    private String maxFileSize;

    @RequestMapping(value = "/401", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity to401() {
        AuthContextHolder.removeContext();
        return new ResponseEntity(401, "未登录");
    }

    @RequestMapping(value = "/403", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity to403() {
        AuthContextHolder.removeContext();
        return new ResponseEntity(403, "没有权限");
    }

    @RequestMapping(value = "/404", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity to404() {
        AuthContextHolder.removeContext();
        return new ResponseEntity(404, "找不到资源");
    }

    @RequestMapping(value = "/502", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity to502() {
        AuthContextHolder.removeContext();
        return new ResponseEntity(502, "网关错误");
    }

    @RequestMapping(value = "/400", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity to400() {
        AuthContextHolder.removeContext();
        return new ResponseEntity(400, "请求无效");
    }

    @RequestMapping(value = "/500", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity to500() throws Throwable {
        AuthContextHolder.removeContext();
        HttpServletRequest request = SpringUtils.getRequest();
        Object attribute = request.getAttribute("jakarta.servlet.error.exception");
        //如果是文件太大了异常 抛处错误给前台
        if (attribute instanceof IllegalStateException) {
            IllegalStateException exception = (IllegalStateException) attribute;
            Throwable cause = exception.getCause();
            //TODO 这里耦合的Undertow的包，如果以后替为tomcat这段要删除或作其他处理
            if (cause != null && cause.getClass().getName().equals("io.undertow.server.handlers.form.MultiPartParserDefinition$FileTooLargeException")) {
                throw Exceptions.baseException("文件大小超过限制，最大限制" + maxFileSize, ((Exception) attribute).getMessage());
            }
        } else if (attribute instanceof AuthenticationRuntimeException) {
            throw (AuthenticationRuntimeException) attribute;
        }
        throw (Throwable) attribute;
    }

}
