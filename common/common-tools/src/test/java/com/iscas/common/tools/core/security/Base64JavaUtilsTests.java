package com.iscas.common.tools.core.security;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 *
 * <b>这里只测试了部分函数，还有些函数的使用见{@link Base64Utils}<b/>
 *
 * @author: zhuquanwen
 * @date: 2018/7/13 16:37
 **/
public class Base64JavaUtilsTests {
    /**
    * 判断是不是base64编码
    * */
    @Test
    public void isBase64_1() throws IOException {
        //字符串
        String str1 = "111";
        boolean flag1 = Base64Utils.isBase64(str1);
        String str2 = "d3dlZ3dlZ3dlZw==";
        boolean flag2 = Base64Utils.isBase64(str2);
        Assertions.assertEquals(flag1,false);
        Assertions.assertEquals(flag2,true);

        //字符串带编码格式
        String str11 = "111";
        boolean flag11 = Base64Utils.isBase64(str11, "utf-8");
        String str21 = "d3dlZ3dlZ3dlZw==";
        boolean flag21 = Base64Utils.isBase64(str21,"utf-8");
        Assertions.assertEquals(flag11,false);
        Assertions.assertEquals(flag21,true);

    }

    /**
     * 字符串编码
     * */
    @Test
    public void encode(){
        String encodeStr = "wwegwegweg";
        String result = Base64Utils.encode(encodeStr);
        Assertions.assertEquals("d3dlZ3dlZ3dlZw==", result);
    }

    /**
     * 字符串解码
     * */
    @Test
    public void decode(){
        String decodeStr = "d3dlZ3dlZ3dlZw==";
        String result = Base64Utils.decode(decodeStr);
        Assertions.assertEquals("wwegwegweg", result);
    }

}
