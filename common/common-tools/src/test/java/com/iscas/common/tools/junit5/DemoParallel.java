package com.iscas.common.tools.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * //并行执行
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/20 9:24
 * @since jdk1.8
 */
@Slf4j
public class DemoParallel {
    @Order(1)
    @Execution(ExecutionMode.SAME_THREAD)
    @DisplayName("单线程执行10次")
    @RepeatedTest(value = 10, name="完成度：{currentRepetition}/{totalRepetitions}")
    void sameThreadTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        log.info("测试方法 [{}]，当前第[{}]次，共[{}]次",
                testInfo.getTestMethod().get().getName(),
                repetitionInfo.getCurrentRepetition(),
                repetitionInfo.getTotalRepetitions());
    }

    @Order(2)
    @Execution(ExecutionMode.CONCURRENT)
    @DisplayName("单线程执行10次")
    @RepeatedTest(value = 10, name="完成度：{currentRepetition}/{totalRepetitions}")
    void coTest(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        log.info("测试方法 [{}]，当前第[{}]次，共[{}]次",
                testInfo.getTestMethod().get().getName(),
                repetitionInfo.getCurrentRepetition(),
                repetitionInfo.getTotalRepetitions());
    }
}
