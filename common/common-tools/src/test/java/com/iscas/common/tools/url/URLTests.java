package com.iscas.common.tools.url;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/26 17:17
 * @since jdk1.8
 */
public class URLTests {
    @Test
    @Disabled
    public void test1() {
        try {
            //获取文件
            String file = URLUtils.getFile("file:F:\\项目\\上光\\CMI-Demo-web\\index.html");
            System.out.println(file);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Test
    @Disabled
    public void test2() {
        try {
            //获取协议
            String protocol = URLUtils.getProtocol("file:F:\\项目\\上光\\CMI-Demo-web\\index.html");
            System.out.println(protocol);
            String protocol2 = URLUtils.getProtocol("https://www.baidu.com");
            System.out.println(protocol2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    public void test3() {
        try {
            //获取主机
            String host = URLUtils.getHost("file:F:\\项目\\上光\\CMI-Demo-web\\index.html");
            System.out.println(host);
            String host2 = URLUtils.getHost("https://www.baidu.com");
            System.out.println(host2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    public void test4() {
        try {
            //获取路径
            String path = URLUtils.getPath("file:F:\\项目\\上光\\CMI-Demo-web\\index.html");
            System.out.println(path);
            String path2 = URLUtils.getPath("https://www.baidu.com");
            System.out.println(path2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    public void test5() {
        try {
            //获取端口
            int port = URLUtils.getPort("file:F:\\项目\\上光\\CMI-Demo-web\\index.html");
            System.out.println(port);
            int port2 = URLUtils.getPort("https://www.baidu.com");
            System.out.println(port2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Disabled
    public void test6() {
        try {
            //获取默认端口
            int port = URLUtils.getDefaultPort("file:F:\\项目\\上光\\CMI-Demo-web\\index.html");
            System.out.println(port);
            int port2 = URLUtils.getDefaultPort("https://www.baidu.com");
            System.out.println(port2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
