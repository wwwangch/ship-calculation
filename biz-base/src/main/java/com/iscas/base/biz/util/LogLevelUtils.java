package com.iscas.base.biz.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggerConfiguration;
import org.springframework.boot.logging.LoggingSystem;

import java.util.Set;

/**
 * 日志工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/11 17:39
 */
@Slf4j
public class LogLevelUtils {
    private LogLevelUtils() {
    }


    public static void updateLevel(String loggerName, LogLevel level) {
        LoggingSystem loggingSystem = SpringUtils.getBean(LoggingSystem.class);
        LoggerConfiguration loggerConfiguration = loggingSystem.getLoggerConfiguration(loggerName);
        if (loggerConfiguration == null) {
            log.error("[动态日志]没有获取到[{}]日志配置", loggerName);
            return;
        }
        Set<LogLevel> supportedLogLevels = loggingSystem.getSupportedLogLevels();
        if (!supportedLogLevels.contains(level)) {
            log.error("[动态日志]不支持配置的[{}]日志级别", level);
            return;
        }
        if (loggerConfiguration.getEffectiveLevel() != level) {
            loggingSystem.setLogLevel(loggerName, level);
        } else {
            log.info("[动态日志]当前已经是配置的[{}]日志级别", level);
        }
    }

}
