package com.iscas.common.tools.core.security;


import org.junit.jupiter.api.Test;

/**
 * 编码工具类测试
 * @author zhuquanwen
 **/
public class EscapeUtilsTests {
    private static final String stest = "人生得意需尽欢1234 abcd[]()<+>,.~\"";
    /**
     * 编码
    * */
    @Test
    public void encode(){
        String xx = EscapeUtils.escape(stest);
        System.out.println(xx);
    }
    /**
     * 解码
     * */
    @Test
    public void decode(){
        System.out.println(EscapeUtils.unescape(EscapeUtils.escape(stest)));
    }
}
