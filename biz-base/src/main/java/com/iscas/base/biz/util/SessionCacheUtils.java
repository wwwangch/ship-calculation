package com.iscas.base.biz.util;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateUnit;

import java.util.Map;

/**
 * session-cache工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/9 16:56
 * @since jdk1.8
 */
@SuppressWarnings("unchecked")
public class SessionCacheUtils {
    /**超时时间秒*/
    private static final long TIMEOUT = 36000;
    private static final Cache<String,Object> FIFO_CACHE = CacheUtil.newFIFOCache(2000);

    public static void put(String sessionId, Object obj) {
        //超时时间十个小时
        FIFO_CACHE.put(sessionId, obj, DateUnit.SECOND.getMillis() * TIMEOUT);
    }
    public static Map<String, Object> get(String sessionId) {
        return (Map<String, Object>) FIFO_CACHE.get(sessionId);
    }

    public static void remove(String sessionId) {
        FIFO_CACHE.remove(sessionId);
    }
}
