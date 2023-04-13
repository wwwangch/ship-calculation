package com.iscas.base.biz.config.failure;

import com.iscas.base.biz.config.stomp.StompRegistryException;
import com.iscas.common.tools.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * 服务启动错误解析
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/10 11:24
 * @since jdk1.8
 */
@Slf4j
public class StompRegistryFailureAnalyzer extends AbstractFailureAnalyzer<StompRegistryException> {

    @SuppressWarnings("FieldCanBeLocal")
    private final String action = "注册websocket-stomp过程中出错";

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, StompRegistryException cause) {
        log.error(action, cause);
        return new FailureAnalysis(ExceptionUtils.getExceptionInfo(cause), action, cause);
    }
}
