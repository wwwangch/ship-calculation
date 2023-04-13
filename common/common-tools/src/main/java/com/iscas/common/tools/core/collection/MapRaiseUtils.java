package com.iscas.common.tools.core.collection;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Map扩展工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/26 15:55
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
public class MapRaiseUtils {
    private MapRaiseUtils() {}

    /**
     * 删除map中多个对象
     * @since jdk1.8
     * @date 2021/1/6
     * @param map map
     * @param keys 待删除的key
     */
    public static void remove(Map map, Object... keys) {
        Assert.notNull(map, "map不能为空");
        Assert.notNull(keys, "要删除的key不能为空");
        for (Object key : keys) {
            map.remove(key);
        }
    }


    /**
     * 判断Map是不是为空，如果Map里全都是null也判断
     * @since jdk1.8
     * @date 2021/1/6
     * @param map map
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Map map) {
        boolean flag = map == null || map.size() <= 0;
        if (!flag) {
            Set<Map.Entry> entrySet = map.entrySet();
            boolean notnullFlag = false;
            for (Map.Entry entry: entrySet) {
                if (entry.getKey() != null) {
                    notnullFlag = true;
                    break;
                }
            }
            flag = !notnullFlag;
        }
        return flag;
    }

    /**
     * 判断Map是不是不为空，如果Map里全都是null也判断
     * @since jdk1.8
     * @date 2021/1/6
     * @param map map
     * @return boolean
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 移除Map中的null值
     * @since jdk1.8
     * @date 2021/1/6
     * @param map map
     */
    public static void removeNullValue(Map<Object, Object> map) {
        Assert.notNull(map, "map不能为空");
        List<Object> keys = new ArrayList<>();
        for (Map.Entry<Object, Object> entry: map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                keys.add(key);
            }
        }
        if (CollectionUtils.isNotEmpty(keys)) {
            Object[] keyArray = keys.toArray(new Object[0]);
            remove(map, keyArray);
        }
    }

    /**
     * 将Map的Key转为驼峰的
     * @since jdk1.8
     * @date 2021/1/6
     * @param map map
     * @return java.util.Map<K,V>
     */
    public static <K, V> Map<K, V> convertToHump(Map<K, V> map) {
        return MapUtil.toCamelCaseMap(map);
    }
}
