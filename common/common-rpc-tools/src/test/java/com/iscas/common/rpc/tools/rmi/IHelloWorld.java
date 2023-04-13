package com.iscas.common.rpc.tools.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI接口定义
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 18:57
 * @since jdk1.8
 */
public interface IHelloWorld extends Remote {
    void sayHello(String name) throws RemoteException;
}
