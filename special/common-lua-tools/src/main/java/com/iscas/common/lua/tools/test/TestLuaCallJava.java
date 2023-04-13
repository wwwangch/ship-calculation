package com.iscas.common.lua.tools.test;

/**
 * 测试Lua脚本调用java
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/2 18:22
 * @since jdk1.8
 */
public class TestLuaCallJava {

    public static String test(String arg1) {
//        System.out.println(arg1);
        return "传参为:" + arg1;
    }

    public String test2(String arg1) {
//        System.out.println(arg1);
        return "传参为:" + arg1;
    }
}
