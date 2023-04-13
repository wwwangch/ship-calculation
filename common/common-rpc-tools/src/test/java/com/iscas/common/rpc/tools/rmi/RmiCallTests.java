package com.iscas.common.rpc.tools.rmi;

import com.iscas.common.rpc.tools.rmi.client.RmiClientUtils;
import com.iscas.common.rpc.tools.rmi.server.RmiServerUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Rmi调用测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 18:56
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class RmiCallTests {
    @Before
    public void before() throws RemoteException, AlreadyBoundException, MalformedURLException {
        HelloWorld helloWorld = new HelloWorld();
        RmiServerUtils.bind("127.0.0.1", 9898, "hello", helloWorld);
    }

    @Test
    public void test() throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {
        IHelloWorld hello = RmiClientUtils.lookup(IHelloWorld.class, "127.0.0.1", 9898, "hello");
        hello.sayHello("张三");
    }
}
