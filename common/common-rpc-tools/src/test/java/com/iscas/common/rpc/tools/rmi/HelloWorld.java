package com.iscas.common.rpc.tools.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI实现类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 18:58
 * @since jdk1.8
 */
public class HelloWorld extends UnicastRemoteObject implements IHelloWorld {
    private static final long serialVersionUID = -3215090123894879218L;


    protected HelloWorld() throws RemoteException {
    }

    @Override
    public void sayHello(String name) throws RemoteException{
        System.out.printf("你好啊,%s\n", name);
    }
}
