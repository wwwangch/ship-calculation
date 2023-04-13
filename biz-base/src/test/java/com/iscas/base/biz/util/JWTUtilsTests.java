package com.iscas.base.biz.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * JWT测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/17 19:18
 * @since jdk1.8
 */
public class JWTUtilsTests {
    @Test
    public void test() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String name = JWTUtils.createToken("zqw11", 11111111);
        System.out.println(name);
        Assert.assertNotNull(name);
    }
}
