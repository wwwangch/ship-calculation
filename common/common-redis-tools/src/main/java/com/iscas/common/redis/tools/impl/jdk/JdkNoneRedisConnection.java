package com.iscas.common.redis.tools.impl.jdk;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;

/**
 * JDK连接，不使用Redis,为了和其他的方式统一，也写了空的Connection
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/8 15:13
 * @since jdk1.8
 */
public class JdkNoneRedisConnection implements JedisConnection {
    private static final long MAX_TIMEOUT = 20L * 365 * 24 * 3600 * 1000;

    /**存储分布式锁对象*/
    public TimedCache<String, String> acquireLockCache = null;

    /**对象缓存对象*/
    public TimedCache<String, Object> objectCache = null;


    /**初始化*/
    public void init() {

        acquireLockCache = CacheUtil.newTimedCache(MAX_TIMEOUT);
        //启动定时任务，每5毫秒秒检查一次过期
        acquireLockCache.schedulePrune(5);

        objectCache = CacheUtil.newTimedCache(MAX_TIMEOUT);
        //启动定时任务，每5毫秒秒检查一次过期
        objectCache.schedulePrune(5);
    }

    @Override
    public Object getPool() {
        return null;
    }

    @Override
    public void initConfig(ConfigInfo configInfo) {

    }

    @Override
    public void close() {

    }
}
