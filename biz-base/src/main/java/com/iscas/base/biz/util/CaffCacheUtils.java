package com.iscas.base.biz.util;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.PolyNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * 咖啡因缓存工具类
 *
 * @author zhuquanwen
 * @date 2018/3/21 15:09
 **/
@SuppressWarnings("unused")
public class CaffCacheUtils {
    private CaffCacheUtils() {
    }

    private static final Logger log = LoggerFactory.getLogger(CaffCacheUtils.class);
    /**
     * 初始化
     */
    private static final int INITIAL_CAPCITY = 100;
    /**
     * 最大容量
     */
    private static final int MAXIMUM_SIZE = 10000;

    private static volatile LoadingCache<String, Object> localCache = null;

    public static LoadingCache<String, Object> getLocalCache() {
        if (localCache == null) {
            synchronized (CaffCacheUtils.class) {
                localCache = Caffeine.newBuilder()
                        .expireAfter(new MyExpiry())
                        .initialCapacity(INITIAL_CAPCITY)
                        .maximumSize(MAXIMUM_SIZE)
                        .build(s -> null);
                // 动态调整缓存容量
                localCache.policy().eviction().ifPresent(eviction -> eviction.setMaximum(2 * eviction.getMaximum()));
            }
        }
        return localCache;
    }

    /**
     * 设置缓存
     *
     * @param key   key
     * @param value value
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void set(String key, Object value) {
        getLocalCache().put(key, value);
    }

    /**
     * 设置缓存，并带失效时间，如果超时时间为0，永不失效
     *
     * @param key          key
     * @param value        value
     * @param cacheSeconds 失效时间，单位秒
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void set(String key, Object value, int cacheSeconds) {
        if (Objects.equals(0, cacheSeconds)) {
            set(key, value);
        } else {
            getLocalCache().policy().expireVariably().ifPresent(e -> e.put(key, value, cacheSeconds, TimeUnit.SECONDS));
        }
    }

    /**
     * 设置缓存，并带失效时间，如果超时时间为0，永不失效。如果缓存的key不存在才添加
     *
     * @param key          key
     * @param value        value
     * @param cacheSeconds 失效时间，单位秒
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void setnx(String key, Object value, int cacheSeconds) {
        long cache = cacheSeconds;
        if (cacheSeconds == 0) {
            cache = Long.MAX_VALUE;
        }
        long finalCacheTime = cache;
        getLocalCache().policy().expireVariably().ifPresent(e -> e.putIfAbsent(key, value, finalCacheTime, TimeUnit.SECONDS));
    }

    /**
     * 为缓存的某个key设置失效时间
     *
     * @param key          key
     * @param cacheSeconds 失效时间，单位秒
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void expire(String key, int cacheSeconds) {
        long cache = cacheSeconds;
        if (cacheSeconds == 0) {
            cache = Long.MAX_VALUE;
        }
        long finalCacheTime = cache;
        getLocalCache().policy().expireVariably().ifPresent(e -> e.setExpiresAfter(key, finalCacheTime, TimeUnit.SECONDS));
    }

    /**
     * compute
     *
     * @param key          key
     * @param cacheSeconds 失效时间
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void compute(String key, int cacheSeconds, BiFunction<String, Object, Object> remappingFunction) {
        getLocalCache().policy().expireVariably().ifPresent(e -> e.compute(key, remappingFunction, Duration.ofSeconds(cacheSeconds)));
    }


    public synchronized static void safeSet(String key, Object value) {
        set(key, value);
    }

    /**
     * 清空所有缓存
     *
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void cleanup() {
        getLocalCache().invalidateAll();
    }

    /**
     * 获取缓存
     *
     * @param key 缓存的key
     * @return 缓存的值
     * @date 2022/12/1
     * @since jdk1.8
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static Object get(String key) {
        Object value;
        try {
            value = getLocalCache().get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            log.error("localCache get error", e);
        }
        return null;
    }

    /**
     * 通过多个key获取
     * @since jdk1.8
     * @date 2022/12/1
     * @param keys 多个key的集合
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> getAll(Iterable<String> keys) {
        return getLocalCache().getAll(keys);
    }

    /**
     * 获取所有缓存
     * @since jdk1.8
     * @date 2022/12/1
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> getAll() {
        return getLocalCache().asMap();
    }

    public synchronized static Object safeGet(String key) {
        return get(key);
    }

    /**
     * 删除缓存
     *
     * @param key 缓存的key
     * @date 2022/12/1
     * @since jdk1.8
     */
    public static void remove(String key) {
        if (key != null) {
            getLocalCache().invalidate(key);
        }
    }

    public synchronized static void safeRemove(String key) {
        remove(key);
    }

    private static class MyExpiry implements Expiry<String, Object> {

        @Override
        public long expireAfterCreate(String key, Object value, long currentTime) {
            return Long.MAX_VALUE;
        }

        @Override
        public long expireAfterUpdate(String key, Object value, long currentTime, @NonNegative long currentDuration) {
            return currentDuration;
        }

        @Override
        public long expireAfterRead(String key, Object value, long currentTime, @NonNegative long currentDuration) {
            return currentDuration;
        }

    }


}
