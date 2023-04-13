package com.iscas.common.tools.core.valid;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/9/20 8:35
 * @since jdk1.8
 */
public class IpValidUtilsTests {

    @Test
    public void test() {
        String flag1 = IpValidUtils.validIp4Or6("10.2.3.125");
        Assertions.assertEquals("IPv4", flag1);

        String flag2 = IpValidUtils.validIp4Or6("1000.2.3.125");
        Assertions.assertEquals("Neither", flag2);

        String flag3 = IpValidUtils.validIp4Or6("FF01::101");
        Assertions.assertEquals(flag3, "IPv6");
    }
}
