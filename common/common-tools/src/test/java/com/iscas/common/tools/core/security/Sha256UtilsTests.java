package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/14 13:36
 * @since jdk1.8
 */
public class Sha256UtilsTests {

    @Test
    public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String sha256Str = Sha256Utils.encrypt("123456");
        System.out.println(sha256Str);
    }


    @Test
    public void test2() {
        String iscas = Sha256Utils.hmacEncrypt("iscas", "123456");
        System.out.println(iscas);
        Assertions.assertNotNull(iscas);
    }

    @Test
    public void test3() {
        String sha = Sha256Utils.hmacEncrypt("iscas", "123456");
        System.out.println(sha);
        boolean res = Sha256Utils.hmacVerify("iscas", "123456", sha);
        Assertions.assertTrue(res);
    }
}
