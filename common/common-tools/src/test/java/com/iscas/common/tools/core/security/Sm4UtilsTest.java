package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/23 17:16
 */
class Sm4UtilsTest {

    /**
     * 默认秘钥加密
     * */
    @Test
    void encrypt() throws Exception {
        String encrypt = Sm4Utils.encrypt("123456");
        System.out.println(encrypt);
        Assertions.assertNotNull(encrypt);
    }

    /**
     * 默认秘钥解密
     * */
    @Test
    void decrypt() throws Exception {
        String encrypt = Sm4Utils.encrypt("123456");
        String decrypt = Sm4Utils.decrypt(encrypt);
        Assertions.assertEquals(decrypt, "123456");
    }

    /**
     * 自定义秘钥加解密
     * */
    @Test
    void test3() throws Exception {
        String enKey = Sm4Utils.generateSm4Key();
        String encrypt = Sm4Utils.encrypt("123456", enKey);
        System.out.println(encrypt);
        String decrypt = Sm4Utils.decrypt(encrypt, enKey);
        Assertions.assertEquals(decrypt, "123456");
    }
}