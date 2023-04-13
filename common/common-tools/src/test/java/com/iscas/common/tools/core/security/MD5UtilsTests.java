package com.iscas.common.tools.core.security;


import com.iscas.common.tools.core.reflect.reflectTest.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类测试
 * @author zhuquanwen
 **/

public class MD5UtilsTests {
    /**
     * 普通加密
     * */
    @Test
    public void MD51() throws NoSuchAlgorithmException {
        String sec = MD5Utils.md5("apply");
        Assertions.assertNotNull(sec);
    }
    /**
     * 加盐加密
     * */
    @Test
    public void MD52() throws NoSuchAlgorithmException {
        String sec = MD5Utils.saltMD5("admin");
        System.out.println(sec);
        Assertions.assertNotNull(sec);
    }
    /**
     * 加盐加密的校验
     * */
    @Test
    public void MD53() throws NoSuchAlgorithmException {
        boolean sec = MD5Utils.saltVerify("admin"
                ,"f2020e118a4aa8fd39a4560c79b16b30024fb29b64242b5e");
        Assertions.assertEquals(true, sec);
    }

    /**
     * hmac加密
     * */
    @Test
    public void MD54() {
        String iscas = MD5Utils.hmacMd5("iscas", "123456");
        System.out.println(iscas);
        Assertions.assertNotNull(iscas);
    }

    /**
     * hmac校验
     * */
    @Test
    public void MD55() {
        String data = MD5Utils.hmacMd5("iscas", "123456");
        System.out.println(data);
        boolean res = MD5Utils.hmacVerify("iscas", "123456", data);
        Assertions.assertTrue(res);
    }

    /**
     * 获取大文件MD5码
     * */
    @Test
    @Disabled
    public void testFileMD5() throws FileNotFoundException {
        long start = System.currentTimeMillis();
        String fileMD5 = MD5Utils.getFileMD5(new FileInputStream("G:/cs-video/dist/fe8fc15215877f8422c43dfa408c1518.store"));
        Assertions.assertNotNull(fileMD5);
        System.out.println(fileMD5);
        long end = System.currentTimeMillis();
        System.out.printf("耗时：%dms", (end - start));
    }
}
