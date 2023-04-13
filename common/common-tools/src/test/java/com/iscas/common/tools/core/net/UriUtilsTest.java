package com.iscas.common.tools.core.net;

import org.junit.jupiter.api.Test;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/27 9:49
 * @since jdk1.8
 */
public class UriUtilsTest {
    @Test
    public void test() {
        UriUtils.UriInfo hostPort = UriUtils.getHostPort("http://www.baidu.com/a/b/c");
        System.out.println(hostPort);
    }
}