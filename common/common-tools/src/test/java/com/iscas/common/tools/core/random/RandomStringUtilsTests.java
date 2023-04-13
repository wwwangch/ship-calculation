package com.iscas.common.tools.core.random;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 随机字符串工具类
 * @author zhuquanwen
 * @date 2018/7/13 18:01
 **/
public class RandomStringUtilsTests {
    public RandomStringUtilsTests(){}

    /**
    * 测试获取随机数字字母字符串
    * */
    @Test
    public void  randomStr(){
        System.out.println("--------随机字符串测试 begin---------");
        int length = 16;
        String result = RandomStringUtils.randomStr(length);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(16, result.length());
        System.out.println("--------随机字符串测试 end---------");
    }
}
