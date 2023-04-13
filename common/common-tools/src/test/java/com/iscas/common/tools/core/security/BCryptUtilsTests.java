package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

/**
 * BcryptUtils测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/14 13:03
 * @since jdk1.8
 */
public class BCryptUtilsTests {

    /**
     * 测试Bcrypt加解密
     * */
    @Test
    public void testBcrypt() {
        //10是不确定的  此处只是个例子
        String salt = BCryptUtils.gensalt(10, new SecureRandom());
        String str = BCryptUtils.hashpw("123456", salt);
        boolean result = BCryptUtils.checkpw("123456", str);
        Assertions.assertEquals(result, true);

    }
}
