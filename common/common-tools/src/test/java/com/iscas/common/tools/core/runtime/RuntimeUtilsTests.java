package com.iscas.common.tools.core.runtime;

import org.junit.jupiter.api.Test;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/7/31 16:32
 * @since jdk1.8
 */
public class RuntimeUtilsTests {
    @Test
    public void pidTest() {
        System.out.println(RuntimeUtils.getCurrentPid());
    }
}
