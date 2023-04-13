package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/23 18:00
 */
class DesUtilsTest {

    @Test
    public void test1() throws Exception {
        String encrypt = DesUtils.encrypt("123456");
        System.out.println(encrypt);
        Assertions.assertNotNull(encrypt);
    }

    @Test
    public void test2() throws Exception {
        String decrypt = DesUtils.decrypt("yGj4sGSmFxs=");
        System.out.println(decrypt);
        Assertions.assertNotNull(decrypt);
    }

    /**
    * DES模式
    * */
    @Test
    public void test3() throws Exception {
        String encrypt = DesUtils.encrypt("123456", DesUtils.Mode.DES, "abcdefgh");
        System.out.println(encrypt);
        String data = DesUtils.decrypt(encrypt, DesUtils.Mode.DES, "abcdefgh");
        System.out.println(data);
    }

    /**
     * CBC模式
     * */
    @Test
    public void test4() throws Exception {
        String encrypt = DesUtils.encrypt("123456", DesUtils.Mode.CBC, "abcdefgh");
        System.out.println(encrypt);
        String data = DesUtils.decrypt(encrypt, DesUtils.Mode.CBC, "abcdefgh");
        System.out.println(data);
    }

    /**
     * ECB模式
     * */
    @Test
    public void test5() throws Exception {
        String encrypt = DesUtils.encrypt("123456", DesUtils.Mode.ECB, "abcdefgh");
        System.out.println(encrypt);
        String data = DesUtils.decrypt(encrypt, DesUtils.Mode.ECB, "abcdefgh");
        System.out.println(data);
    }

    /**
     * CFB模式
     * */
    @Test
    public void test6() throws Exception {
        String encrypt = DesUtils.encrypt("123456", DesUtils.Mode.CFB, "abcdefgh");
        System.out.println(encrypt);
        String data = DesUtils.decrypt(encrypt, DesUtils.Mode.CFB, "abcdefgh");
        System.out.println(data);
    }

    /**
     * OFB模式
     * */
    @Test
    public void test7() throws Exception {
        String encrypt = DesUtils.encrypt("123456", DesUtils.Mode.OFB, "abcdefgh");
        System.out.println(encrypt);
        String data = DesUtils.decrypt(encrypt, DesUtils.Mode.OFB, "abcdefgh");
        System.out.println(data);
    }

}