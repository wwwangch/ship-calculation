package com.iscas.common.rpc.tools.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 18:53
 * @since jdk1.8
 */
public class RmiClientUtils {
    private RmiClientUtils() {}

    @SuppressWarnings({"unused", "unchecked"})
    public static <T extends Remote> T lookup(Class<T> tClass, String host, int port, String publishName) throws RemoteException, NotBoundException, MalformedURLException {
        return (T) Naming.lookup("rmi://" + host + ":" + port + "/" + publishName);
    }
}
