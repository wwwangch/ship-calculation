package com.iscas.common.tools.core.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/14 13:37
 * @since jdk1.8
 */
public class Pbkdf2Sha256UtilsTests {
    @Test
    public void test() {
        String encode = Pbkdf2Sha256Utils.encode("123456");
        Assertions.assertNotNull(encode);
        boolean verification = Pbkdf2Sha256Utils.verification("123456", encode);
        Assertions.assertTrue(verification);
    }
}
