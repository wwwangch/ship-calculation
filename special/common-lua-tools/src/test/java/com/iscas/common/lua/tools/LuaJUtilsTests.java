package com.iscas.common.lua.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

/**
 * LuaJ测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/2 18:01
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class LuaJUtilsTests {
    @Test
    public void test() {
        String filePath = "C:\\Users\\m1531\\IdeaProjects\\newframe\\special\\common-lua-tools\\test.lua";
        Globals globals = LuaJUtils.loadScriptFromFile(filePath);

        //测试无参
        LuaJUtils.callFunction(globals, "hello", null);

        //测试字符串传参
        LuaValue luaValue = LuaJUtils.callFunction(globals, "test", LuaValue.valueOf("测试字符串传参"));
        String s = LuaJUtils.fromResult(luaValue, String.class);
        System.out.println(s);


        //测试数组传参
        LuaTable luaTable = new LuaTable();
        luaTable.set(1, "测试数组传参");
        LuaValue luaValue2 = LuaJUtils.callFunction(globals, "test2", luaTable);
        String s2 = LuaJUtils.fromResult(luaValue2, String.class);
        System.out.println(s2);

    }
}
