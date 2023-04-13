package com.iscas.jdk.base;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/26 16:47
 * @since jdk1.8
 */
public class IpTests {
    @Test
    public void test() {
        //根据网址获得IP地址
        try {
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        //获取本地IP
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
