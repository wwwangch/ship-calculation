package com.iscas.common.tools.core.security;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * AES加解密测试类
 * @author zhuquanwen
 **/
public class AesUtilsTests {

    /**
     * 测试AES 默认加密
     * */
    @Test
    public void aesEncrpty() throws Exception {
        String sec = AesUtils.aesEncrypt("USER2");
        System.out.println(sec);
        Assertions.assertNotNull(sec);
    }

    /**
     *
     * 测试AES默认解密
     * */
    @Test
    public void aesDecrpty() throws Exception {
        String ori = AesUtils.aesDecrypt("Cg2jBQvUGJJUMfalO+HF5g==");
        Assertions.assertEquals("gfdx", ori);
    }

    /**
     * 测试AES 带KEY加密
    * */
    @Test
    public void aesEncrptyWithKey() throws Exception {
        String k = "6x9o67h5BO205Cfv";
        String sec = AesUtils.aesEncrypt("admin", k);
        Assertions.assertNotNull(sec);
    }

    /**
     * 测试AES 带KEY解密
     * */
    @Test
    public void aesDecrptyWithKey() throws Exception {
        String k = "6x9o67h5BO205Cfv";
        String ori = AesUtils.aesDecrypt("yij1zaxI6X10t7v6OpW7gw==", k);
        Assertions.assertEquals("admin", ori);
    }

}
