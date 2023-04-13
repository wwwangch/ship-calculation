package com.iscas.common.tools.hutool.cache;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 超时缓存
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/8 15:08
 * @since jdk1.8
 */
public class TimeCacheTest {
    @Test
    public void test(){
        TimedCache<String, String> timedCache = CacheUtil.newTimedCache(4);
//		TimedCache<String, String> timedCache = new TimedCache<String, String>(DateUnit.SECOND.getMillis() * 3);
        timedCache.put("key1", "value1", 1);//1毫秒过期
        timedCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 5);//5秒过期
        timedCache.put("key3", "value3");//默认过期(4毫秒)

        //启动定时任务，每5毫秒秒检查一次过期
        timedCache.schedulePrune(5);
        //等待5毫秒
        ThreadUtil.sleep(5);

        //5毫秒后由于value2设置了5毫秒过期，因此只有value2被保留下来
        String value1 = timedCache.get("key1");
        Assertions.assertTrue(null == value1);
        String value2 = timedCache.get("key2");
        Assertions.assertFalse(null == value2);
        //5毫秒后，由于设置了默认过期，key3只被保留4毫秒，因此为null
        String value3 = timedCache.get("key3");
        Assertions.assertTrue(null == value3);

        //取消定时清理
        timedCache.cancelPruneSchedule();

    }
}
