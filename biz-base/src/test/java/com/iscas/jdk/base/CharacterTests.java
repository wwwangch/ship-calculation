package com.iscas.jdk.base;


import org.junit.Assert;
import org.junit.Test;

/**
 * Character工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/25 13:55
 * @since jdk1.8
 */
public class CharacterTests {
    @Test
    public void test(){
        //判断是不是一个字母
        boolean b1 = Character.isLetter('t');
        Assert.assertTrue(b1);

        //判断是不是数字
        boolean b2 = Character.isDigit('1');
        Assert.assertTrue(b2);

        //判断是不是大写字母
        boolean b3 = Character.isUpperCase('A');
        Assert.assertTrue(b3);

        //判断是不是小写字母
        boolean b4 = Character.isLowerCase('a');
        Assert.assertTrue(b4);

        //判断是不是空格
        boolean b5 = Character.isWhitespace(' ');
        Assert.assertTrue(b5);
    }
}
