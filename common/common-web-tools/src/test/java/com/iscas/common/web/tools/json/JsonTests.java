package com.iscas.common.web.tools.json;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/9 14:36
 * @since jdk1.8
 */

public class JsonTests {

    public JsonObject getSimpleJsonObject() {
        JsonObject jsonObject = JsonUtils.createJsonObject();
        int age = 18;
        jsonObject.set("name", "张三").set("age", age);
        return jsonObject;
    }


    /**
    * 测试最简单的JsonObject
    * */
    @Test
    public void testSimpleJsonObject() {
        JsonObject simpleJsonObject = getSimpleJsonObject();
        System.out.println(simpleJsonObject.toMap());
        System.out.println(simpleJsonObject.toJson());
    }

    /**
     * 测试最简单的JsonArray
     * */
    @Test
    public void testSimpleJsonArray() {
        JsonArray jsonArray = JsonUtils.createJsonArray();
        jsonArray.add("a").add("b");
        System.out.println(jsonArray.toList());
        System.out.println(jsonArray.toJson());
    }

    /**
     * 测试嵌套
     *
     */
    @Test
    public void testNested1() {
        JsonObject jsonObejct = JsonUtils.createJsonObject();
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("age", 18);
        jsonObejct.set("person", map);
        System.out.println(jsonObejct.toJson());
    }

    /**
     * 测试嵌套2
     *
     */
    @Test
    public void testNested2() {
        JsonObject jsonObject = JsonUtils.createJsonObject();
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("age", 18);
        map.put("number", Arrays.asList(1,2,2,4));
        jsonObject.set("person", map);
        System.out.println(jsonObject.toJson());
    }

    /**
     * 测试嵌套3
     *
     */
    @Test
    public void testNested3() {
        JsonObject jsonObejct = JsonUtils.createJsonObject();
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("age", 18);
        map.put("number", Arrays.asList(1,2,2,4));
        jsonObejct.set("person1", map)
                .set("person2", getSimpleJsonObject());
        System.out.println(jsonObejct.toJson());
        System.out.println(jsonObejct.fromJson(Map.class));
    }

    /**
     * 测试嵌套4
     *
     */
    @Test
    public void testNested4() {
        JsonArray jsonArray = JsonUtils.createJsonArray();
        JsonObject jsonObejct = JsonUtils.createJsonObject();
        Map map = new HashMap();
        map.put("name", "zhangsan");
        map.put("age", 18);
        map.put("number", Arrays.asList(1,2,2,4));
        jsonObejct.set("person1", map)
                .set("person2", getSimpleJsonObject());
        JsonArray jsonArray2 = JsonUtils.createJsonArray();
        jsonArray2.add("3").add("5").add("6");
        jsonArray.add(jsonObejct).add(jsonArray2);
        System.out.println(jsonArray.toJson());
    }
}
