package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/23 13:34
 */
class Sm3UtilsTest {
    /**
     * 测试普通sm3加密
     */
    @Test
    public void testEncode() {
        System.out.println(Sm3Utils.encrypt("123456"));
    }

    /**
     * 测试使用密钥hmac加密
     */
    @Test
    public void testEncode2() {
        System.out.println(Sm3Utils.hmacEncrypt("iscas", "123456"));
        System.out.println(Sm3Utils.hmacEncrypt("ISCAS", "123456"));
    }

    /**
     * 测试普通加密校验
     */
    @Test
    public void testVerify() {
        System.out.println(Sm3Utils.verify("123456", "207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb"));
    }

    /**
     * 测试HMAC加密校验
     */
    @Test
    public void testHmacVerify() {
        System.out.println(Sm3Utils.hmacVerify("iscas", "123456", "a0bc6c6ac39712c81f805c8a80407892b1006ee27a04c2b0a15fec84b5065225"));
    }

}