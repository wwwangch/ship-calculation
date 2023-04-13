package com.iscas.common.lua.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

/**
 * 测试Lua调用Java
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/2 18:26
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class LuaCallJavaTests {
    @Test
    public void test() {
        String filePath = "D:\\ideaProjects\\newframe-branch-v3\\special\\common-lua-tools\\luaCallJava.lua";
        Globals globals = LuaJUtils.loadScriptFromFile(filePath);

        //测试无参
        LuaJUtils.callFunction(globals, "testCallJava1", LuaValue.valueOf("hello"));

        LuaValue luaValue = LuaJUtils.callFunction(globals, "testCallJava2", LuaValue.valueOf("hello"));
        System.out.println(luaValue.toString());
    }
}
