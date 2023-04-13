package com.iscas.common.tools.assertion;



import org.junit.jupiter.api.Test;

/**
 * 对象断言测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 17:59
 * @since jdk1.8
 */
public class AssertObjUtilsTests {
    @Test
    public void test111() {
        System.out.println("-------AssertObjUtils对象不能为空断言begin---------");
        try {
            AssertObjUtils.assertNotNull(null, "xxx不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("断言成功");
        }
        System.out.println("-------AssertObjUtils对象不能为空断言end---------");
    }

    @Test
    public void test2222() {
        AssertObjUtils.assertNull(null, "xxx必须为空");
    }

    @Test
    public void test3333() {
        System.out.println("-------AssertObjUtils对象不能为空对象断言begin---------");
        Integer x = null;
        try {
            AssertObjUtils.assertNotEmpty(x, "xxx必须不为空对象");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("断言成功");
        }
        System.out.println("-------AssertObjUtils对象不能为空断言end---------");
    }



}