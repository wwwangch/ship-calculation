package com.iscas.jdk.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * String replace与replaceAll的区别，
 * replace替换，支持字符、字符串替换，replaceAll替换所有，正则表达式替换
 *
 * replaceFirst替换第一个
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/25 14:56
 * @since jdk1.8
 */
public class ReplaceStringTests {
    @Test
    public void test1() {
        String str = "zhongguo@riben@meiguo@yindu@bajisitan";
        String newStr = str.replace("@", "&");
        Assert.assertEquals("zhongguo&riben&meiguo&yindu&bajisitan", newStr);
    }

    @Test
    public void test2() {
        String str = "com/iscas/newframe";
        String newStr = str.replaceAll("\\w", ".");
        Assert.assertEquals(".../...../........", newStr);
    }

    @Test
    public void test3() {





        //替换第一个
        String str = "com/iscas/newframe";
        String newStr = str.replaceFirst("\\w", ".");
        Assert.assertEquals(".om/iscas/newframe", newStr);
    }
}
