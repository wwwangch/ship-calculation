package com.iscas.common.tools.core.collection;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/19 14:58
 * @since jdk1.8
 */
public class CollectionRaiseUtilsTests {
    @Test
    public void test() {
        System.out.println("-------CollectionRaiseUtils#isEmpty() begin---------");
        ArrayList<Object> collection1 = CollectionUtil.newArrayList(null, null);
        boolean empty = CollectionRaiseUtils.isEmpty(collection1);
        Assertions.assertTrue(empty);

        ArrayList<Object> collection2 = CollectionUtil.newArrayList("{test", null);
        boolean empty2 = CollectionRaiseUtils.isEmpty(collection2);
        Assertions.assertFalse(empty2);
        System.out.println("-------CollectionRaiseUtils#isEmpty() end---------");
    }

    @Test
    public void test2() {
        System.out.println("-------MapRaiseUtils#isEmpty() begin---------");
        Map<Object, Object> map1 = MapUtil.builder().put(null, null).build();
        boolean empty = MapRaiseUtils.isEmpty(map1);
        Assertions.assertTrue(empty);

        Map<Object, Object> map2 = MapUtil.builder().put("key", "val").build();
        boolean empty2 = MapRaiseUtils.isEmpty(map2);
        Assertions.assertFalse(empty2);
        System.out.println("-------MapRaiseUtils#isEmpty() end---------");
    }

    /**
     * 测试remove空值
     * */
    @Test
    public void test3() {
        System.out.println("-------MapRaiseUtils.removeNullValue(map) begin---------");
        Map<Object, Object> map = MapUtil.builder().put("name", "zhangsan")
                .put("age", 18)
                .put("lalala", null)
                .build();
        MapRaiseUtils.removeNullValue(map);
        Assertions.assertEquals("{name=zhangsan, age=18}", map.toString());
        System.out.println(map);
        System.out.println("-------MapRaiseUtils.removeNullValue(map) end---------");
    }
}
