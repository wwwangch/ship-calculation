package com.iscas.base.biz.controller.common;

import com.iscas.base.biz.util.LogLevelUtils;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.boot.logging.LogLevel;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志级别管理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/12 10:32
 */
@RestController
@RequestMapping("/log/level")
public class LogLevelController {
    @PutMapping
    public ResponseEntity changeLevel(@RequestParam("logName") String logName, @RequestParam("level") String level) {
        LogLevel logLevel = null;
        for (LogLevel value : LogLevel.values()) {
            if (level.equalsIgnoreCase(value.toString())) {
                logLevel = value;
                break;
            }
        }
        LogLevelUtils.updateLevel(logName, logLevel);
        return ResponseEntity.ok(String.format("[%s]的日志级别修改为:[%s]", logName, level));
    }
}
