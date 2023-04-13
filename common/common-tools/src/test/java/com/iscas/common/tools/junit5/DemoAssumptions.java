package com.iscas.common.tools.junit5;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

/**
 * //Assumptions测试
 *
 * @author yuemingyang
 * @version 1.0
 * @date 2021/2/19 16:52
 * @since jdk1.8
 */
public class DemoAssumptions {

    @Test
    public void testOnlyOnCiServer() {
        Assumptions.assumeTrue("CI".equals(System.getenv("ENV")));
        // remainder of test
    }

    @Test
    public void testOnlyOnDeveloperWorkstation() {
        Assumptions.assumeTrue("DEV".equals(System.getenv("ENV")),
                () -> "Aborting test: not on developer workstation");
        // remainder of test
    }

    @Test
    public void testInAllEnvironments() {
        Assumptions.assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                   Assertions.assertEquals(2, 4 / 2);
                });

        // perform these assertions in all environments
        Assertions.assertEquals(42, 6 * 7);
    }

}
