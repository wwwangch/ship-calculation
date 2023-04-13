package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.util.LogLevelUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.common.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/11 17:51
 */
@RestController
@RequestMapping("/test/log")
@Slf4j
public class LogControllerTest {

    @GetMapping
    public ResponseEntity t1 () {
        log.debug("test-debug");
        return ResponseEntity.ok(null);
    }

    /**
     * 调整日志级别
     * */
    @GetMapping("/t2")
    public ResponseEntity t2 () {
        LogLevelUtils.updateLevel("com.iscas.base.biz.test.controller", LogLevel.INFO);
        return ResponseEntity.ok(null);
    }

    /**
     * 测试子线程打印traceId
     * */
    @GetMapping("/t3")
    public ResponseEntity t3() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtils.getBean("asyncExecutor");
        threadPoolTaskExecutor.execute(() -> {
            log.info("测试子线程打印traceId");
        });
        return ResponseEntity.ok(null);
    }

}
