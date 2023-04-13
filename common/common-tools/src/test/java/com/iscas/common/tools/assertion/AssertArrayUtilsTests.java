package com.iscas.common.tools.assertion;


import org.junit.jupiter.api.Test;

/**
 * 数组断言测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 17:59
 * @since jdk1.8
 */
public class AssertArrayUtilsTests {
    @Test
    public void test5() {
        System.out.println("-------AssertArrayUtils数组必须为空断言begin---------");
        Integer[] array = null;
        AssertArrayUtils.assertArrayNull(array, "数组必须为空");
        System.out.println("-------AssertArrayUtils数组必须为空断言end---------");
    }

    @Test
    public void test6() {
        System.out.println("-------AssertArrayUtils数组不能为空断言begin---------");
        Integer[] array = null;
        try {
            AssertArrayUtils.assertArrayNotNull(array, "数组不能为空");
        } catch (Exception e) {
            System.out.println("断言成功");
        }
        System.out.println("-------AssertArrayUtils数组不能为空断言end---------");
    }
}
