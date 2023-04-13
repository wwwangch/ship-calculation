package com.iscas.common.tools.junit5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

/**
 * //超时
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 9:12
 * @since jdk1.8
 */
public class DemoTimeout {

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void failsIfExecutionTimeExceeds100Milliseconds() throws InterruptedException {
        // fails if execution time exceeds 100 milliseconds
        Thread.sleep(110);
    }

}
