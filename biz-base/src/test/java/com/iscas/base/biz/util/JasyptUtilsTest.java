package com.iscas.base.biz.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/24 21:51
 * @since jdk1.8
 */
public class JasyptUtilsTest {
    @Test
    public void test1() {
        String str = JasyptUtils.encrypt("123456");
        Assertions.assertNotNull(str);
    }

    @Test
    public void test2() {
        String str = JasyptUtils.decrypt("0UHSqmE9LuNVXf/cf64ruQ==");
        Assertions.assertNotNull(str);
    }
}