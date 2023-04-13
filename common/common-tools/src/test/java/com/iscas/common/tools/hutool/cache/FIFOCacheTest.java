package com.iscas.common.tools.hutool.cache;


import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * 先进先出缓存测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 14:53
 * @since jdk1.8
 */
public class FIFOCacheTest {
    @Test
    public void test1(){
        Cache<String,String> fifoCache = CacheUtil.newFIFOCache(3);
        fifoCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据FIFO规则，最先放入的对象将被移除
        String value1 = fifoCache.get("key1");
        String value4 = fifoCache.get("key4");
        Assertions.assertTrue(null == value1);
        Assertions.assertEquals("value4", value4);
    }


    @Test
    public void test2() throws InterruptedException {
        Cache<String,String> fifoCache = CacheUtil.newFIFOCache(3);
        fifoCache.put("a", "a", DateUnit.SECOND.getMillis() * 3);
        System.out.println(fifoCache.get("a"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(fifoCache.get("a"));
    }
}
