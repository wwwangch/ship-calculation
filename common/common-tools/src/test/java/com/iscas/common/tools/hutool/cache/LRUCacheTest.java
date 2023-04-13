package com.iscas.common.tools.hutool.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 最近最久未使用缓存策略测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 15:05
 * @since jdk1.8
 */
public class LRUCacheTest {
    @Test
    public void test(){
        Cache<String, String> lruCache = CacheUtil.newLRUCache(3);
        //通过实例化对象创建
//		LRUCache<String, String> lruCache = new LRUCache<String, String>(3);
        lruCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        //使用时间推近
        lruCache.get("key1");
        lruCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2被移除）
        String value2 = lruCache.get("key2");
        Assertions.assertTrue(null == value2);
    }
}
