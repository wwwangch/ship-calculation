package com.iscas.biz.controller.common;

import com.iscas.biz.service.common.SystemLogService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.view.tree.TreeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 系统日志控制器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/25 14:30
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Tag(name = "系统日志-SystemLogController")
@RestController
@RequestMapping("/system/log")
@Validated
public class SystemLogController extends BaseController {
    private final SystemLogService systemLogService;

    public SystemLogController(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @GetMapping("/tree")
    @Operation(summary="获取日志树-create by zqw 2021-02-25")
    public TreeResponse getTree() throws BaseException {
        return systemLogService.getTree();
    }

    @Operation(summary="获取日志-create by zqw 2021-02-25")
    @Parameters(
            {
                    @Parameter(name = "filePath", description = "日志文件路径，在树上可以获取", required = true),
                    @Parameter(name = "lines", description = "行数", required = true)
            }
    )
    @GetMapping("/view")
    public ResponseEntity viewLog(@NotBlank(message = "日志文件路径不能为空") String filePath, @Min(value = 1, message = "行数必须大于0") int lines) throws BaseException {
        try {
            return getResponse().setValue(systemLogService.viewLog(filePath, lines));
        } catch (IOException e) {
            throw Exceptions.baseException("读取日志数据出错", e);
        }
    }

}
