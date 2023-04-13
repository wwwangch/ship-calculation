package com.iscas.common.rpc.tools.sofa;

import com.iscas.common.rpc.tools.sofa.client.SofaRpcClientUtils;
import com.iscas.common.rpc.tools.sofa.server.SofaRpcServerUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 11:41
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class SofaRpcCallTests {
    @Before
    public void before() {
        SofaRpcServerUtils.simpleExport(HelloService.class, new HelloServiceImpl(), 2345);
    }

    @Test
    public void testCall() {
        HelloService helloService = SofaRpcClientUtils.getSimpleInstance(HelloService.class, "127.0.0.1", 2345);
        String result = helloService.sayHello("zqw");
        System.out.println(result);
    }
}
