--在Lua中直接创建Java类的实例的方法：

function testCallJava1(str)
--使用luajava创建java类的实例（对象）
local testLuaCallJava = luajava.newInstance("com.iscas.common.lua.tools.test.TestLuaCallJava")
--调用对象方法
testLuaCallJava:test(str)
end


--在Lua中绑定Java类的方法：
function testCallJava2(str)
--使用luajava绑定一个java类
local testLuaCallJava = luajava.bindClass("com.iscas.common.lua.tools.test.TestLuaCallJava");
--调用类的静态方法/变量
testLuaCallJava:test(str)
-- 使用绑定类创建类的实例（对象）
local testLuaCallJava_instance = luajava.new(testLuaCallJava)
-- 调用对象方法
local result = testLuaCallJava_instance:test2(str .. 'world')
return result
end