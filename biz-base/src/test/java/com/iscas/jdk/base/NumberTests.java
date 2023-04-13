package com.iscas.jdk.base;

import org.junit.Test;

/**
 * 测试Number类型
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/25 10:30
 * @since jdk1.8
 */
public class NumberTests {
    @Test
    public void test1() {
        Number number1 = 45.7;
        Number number2 = 45;
        Number number3 = Float.valueOf(2.5f);
        Number number4 = (byte) 3;

        System.out.println(number1);
        System.out.println(number2);
        System.out.println(number3);
        System.out.println(number4);
    }
}
