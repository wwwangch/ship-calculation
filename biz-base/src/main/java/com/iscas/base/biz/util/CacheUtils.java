package com.iscas.base.biz.util;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * @author lirenshen
 * @version 1.0
 * @date 2021/3/1 10:41
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class CacheUtils {
    private CacheUtils() {
    }

    private static volatile CacheManager cacheManager;

    public static CacheManager getCacheManager() {
        // 获取CacheManager 改为单例 update by zqw 2023-02-22
        if (cacheManager == null) {
            synchronized (CacheUtils.class) {
                if (cacheManager == null) {
                    cacheManager = SpringUtils.getBean(CacheManager.class);
                }
            }
        }
        return cacheManager;
    }

    /**
     * 清理缓存
     *
     * @param cacheName cacheName需与配置文件中配置的缓存名称一致
     */
    public static void evictCache(String cacheName) {
        Cache cache = getCacheManager().getCache(cacheName);
        Optional.ofNullable(cache).ifPresent(Cache::invalidate);
    }

    /**
     * 根据cacheKeys清理缓存
     *
     * @param cacheName cacheName需与配置文件中配置的缓存名称一致
     * @param cacheKeys 缓存的key
     */
    public static void evictCache(String cacheName, Collection cacheKeys) {
        Cache cache = getCacheManager().getCache(cacheName);
        Optional.ofNullable(cache).flatMap(a -> Optional.ofNullable(cacheKeys)).ifPresent(keys -> keys.forEach(cache::evict));
    }

    /**
     * 根据cacheKeys清理缓存
     *
     * @param cacheName cacheName需与配置文件中配置的缓存名称一致
     * @param cacheKeys 缓存的key
     */
    public static void evictCache(String cacheName, Object... cacheKeys) {
        Cache cache = getCacheManager().getCache(cacheName);
        Optional.ofNullable(cache).flatMap(a -> Optional.ofNullable(cacheKeys))
                .ifPresent(keys -> Arrays.stream(keys).forEach(cache::evict));
    }

    /**
     * 缓存存在时，加入缓存
     *
     * @param cacheName cacheName需与配置文件中配置的缓存名称一致
     * @param key       缓存的key
     * @param value     缓存的value
     */
    public static void putCache(String cacheName, Object key, Object value) {
        Cache cache = getCacheManager().getCache(cacheName);
        Optional.ofNullable(cache).ifPresent(c -> c.put(key, value));
    }

    /**
     * 获取缓存
     *
     * @param cacheName cacheName需与配置文件中配置的缓存名称一致
     * @param key       缓存的key
     * @return Object     缓存的value
     */
    public static <T> T getCache(String cacheName, Object key, Class<T> tClass) {
        Cache cache = getCacheManager().getCache(cacheName);
        return (T) Optional.ofNullable(cache).flatMap(c -> Optional.ofNullable(c.get(key))
                        .map(Cache.ValueWrapper::get))
                .orElse(null);
    }
}
