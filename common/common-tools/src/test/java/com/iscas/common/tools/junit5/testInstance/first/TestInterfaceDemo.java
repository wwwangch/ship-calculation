package com.iscas.common.tools.junit5.testInstance.first;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * //TODO
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 17:44
 * @since jdk1.8
 */
public class TestInterfaceDemo implements TestLifecycleLogger,
        TimeExecutionLogger, TestInterfaceDynamicTestsDemo {
    @Test
    void isEqualValue() {
        assertEquals(1, "a".length(), "is always equal");
    }
}
