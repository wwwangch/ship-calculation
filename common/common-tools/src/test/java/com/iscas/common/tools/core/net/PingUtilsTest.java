package com.iscas.common.tools.core.net;

import org.junit.jupiter.api.Test;

/**
 * 健康检测工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/26 15:56
 * @since jdk1.8
 */
public class PingUtilsTest {
    @Test
    public void testHostConnectable() {
        boolean hostConnectable = PingUtils.isHostConnectable("192.168.100.88", 7901, 200);
        System.out.println(hostConnectable);
    }

    @Test
    public void testHostReachable() {
        boolean isHostReachable = PingUtils.isHostReachable("192.168.100.88", 1000);
        System.out.println(isHostReachable);
    }

}