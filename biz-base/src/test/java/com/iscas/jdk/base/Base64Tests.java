package com.iscas.jdk.base;

import org.junit.Test;

import java.util.Base64;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/1 9:52
 * @since jdk1.8
 */
public class Base64Tests {
    @Test
    public void test1() {
        String encode = Base64.getUrlEncoder().encodeToString("http://www.qq.com".getBytes());
        System.out.println(encode);
    }
}
