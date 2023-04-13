package com.iscas.common.tools.hutool.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 最少使用缓存策略测试
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 15:00
 * @since jdk1.8
 */
public class LFUCacheTest {
    @Test
    public void test(){
        Cache<String, String> lfuCache = CacheUtil.newLFUCache(3);
        lfuCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        //使用次数+1
        lfuCache.get("key1");
        lfuCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2,3被移除）
        String value2 = lfuCache.get("key2");
        String value3 = lfuCache.get("key3");
        Assertions.assertTrue(null == value2);
        Assertions.assertTrue(null == value3);
    }
}
