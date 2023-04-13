package com.iscas.common.rpc.tools.sofa;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 11:06
 * @since jdk1.8
 */
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "hello " + name;
    }
}
