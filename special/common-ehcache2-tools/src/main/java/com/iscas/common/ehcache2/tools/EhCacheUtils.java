package com.iscas.common.ehcache2.tools;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * EhCache工具类
 *
 * @author zhuquanwen
 * @date 2023/02/24 15:12
 **/
public class EhCacheUtils {
    private static volatile CacheManager cacheManager = null;
    private static volatile Map<String, Cache> CACHE_MAP = new ConcurrentHashMap<>();

    private EhCacheUtils() {
    }

    public static CacheManager getManager() {
        return cacheManager;
    }

    /**
     * 获取Cache
     *
     * */
    public static Cache getCache(String cacheName) {
        Cache cache = null;
        if (!CACHE_MAP.containsKey(cacheName)) {
            synchronized (EhCacheUtils.class) {
                if (!CACHE_MAP.containsKey(cacheName)) {
                    if (cacheManager == null) {
                        throw new RuntimeException("cacheManager未初始化");
                    }
                    cache = cacheManager.getCache(cacheName);
                    if (cache == null) {
                        throw new RuntimeException("获取cache失败," + cacheName + "的配置可能不存在");
                    }
                    CACHE_MAP.put(cacheName, cache);
                }
            }
        }
        return cache != null ? cache : CACHE_MAP.get(cacheName);
    }


    public static Serializable cacheable(String cacheName, Serializable key, Supplier<Serializable> supplier) {
        Serializable value = get(cacheName, key);
        if (Objects.isNull(value)) {
            value = supplier.get();
            put(cacheName, key, value);
        }
        return value;
    }

    /**
     * 放入键值对
     * */
    public static void put(String cacheName, Serializable key, Serializable value) {
        Cache cache =  getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
        cache.flush();
    }

    /**
     * 取出键值对
     * */
    public static Serializable get(String cacheName, Serializable key) {
        Cache cache = getCache(cacheName);
        return (Serializable) Optional.ofNullable(cache.get(key)).map(Element::getObjectValue)
                .orElse(null);
    }

    /**
     * 删除键值对
     * */
    public static void remove(String cacheName, Serializable key) {
        Cache cache =  getCache(cacheName);
        cache.remove(key);
        cache.flush();
    }

    /**
     * 判断是否含有某个缓存
     * */
    public static boolean containsKey(String cacheName, Serializable key) {
        Cache cache = getCache(cacheName);
        return cache.get(key) != null;
    }

    /**
     * 替换缓存
     * */
    public static boolean replace(String cacheName, Serializable key, Serializable oldValue, Serializable newValue) {
        Cache cache = getCache(cacheName);
        boolean replace = cache.replace(new Element(key, oldValue), new Element(key, newValue));
        cache.flush();
        return replace;
    }

    /**
     * 替换缓存
     * */
    public static Element replace(String cacheName, Serializable key, Serializable value) {
        Cache cache = getCache(cacheName);
        Element replace = cache.replace(new Element(key, value));
        cache.flush();
        return replace;
    }

    /**
     * 获取多个key的缓存
     * */
    public static Map<Object, Element> getAll(String cacheName, Set<? extends Serializable> keys) {
        Cache cache = getCache(cacheName);
        return cache.getAll(keys);
    }

    /**
     * 不存在的时候设置值
     * */
    public static Element putIfAbsent(String cacheName, Serializable key, Serializable value) {
        Cache cache = getCache(cacheName);
        Element element = cache.putIfAbsent(new Element(key, value));
        cache.flush();
        return element;
    }

    /**
     * 清除缓存
     * */
    public static void clear(String cacheName) {
        Cache cache = getCache(cacheName);
        cache.removeAll();
    }

    /**
     * 设置多个值
     * */
    public static void putAll(String cacheName, Map<Serializable, Serializable> keyValues) {
        Cache cache = getCache(cacheName);
        List<Element> list = new ArrayList<>();
        keyValues.forEach((k, v) -> list.add(new Element(k, v)));
        cache.putAll(list);
        cache.flush();
    }

    /**
     * 初始化一个CacheManager，直接用代码构建一个CacheManager
     * @param cacheManager 缓存管理器
     * */
    public static void init(CacheManager cacheManager) {
        if (cacheManager == null) {
            synchronized (EhCacheUtils.class) {
                if (cacheManager == null) {
                    EhCacheUtils.cacheManager = cacheManager;
                }
            }
        }
    }

    /**
     * 初始化一个CacheManager，默认读取resources下的ehcache.xml
     * */
    public static void init() {
        init(EhCacheUtils.class);
    }

    /**
     * 初始化一个CacheManager，默认读取resources下的ehcache.xml
     * @param clazz class
     * */
    public static void init(Class<?> clazz) {
        init(clazz, "ehcache.xml");
    }

    /**
     * 初始化一个CacheManager，读取resources下的配置XML文件
     * @param xmlName xml文件名
     * @param clazz class
     * */
    public static void init(Class<?> clazz, String xmlName) {
        if (cacheManager == null) {
            synchronized (EhCacheUtils.class) {
                if (cacheManager == null) {
                    //从配置文件创建配置对象
                    InputStream resourceAsStream = clazz.getResourceAsStream("/" + xmlName);
                    //创建缓存管理器
                    EhCacheUtils.cacheManager = CacheManager.create(resourceAsStream);
                }
            }
        }
    }

    /**
     * 初始化一个CacheManager，读取外部配置的XML配置文件
     * @param is 文件输入流
     * */
    public static void init(InputStream is) throws MalformedURLException {
        if (cacheManager == null) {
            synchronized (EhCacheUtils.class) {
                if (cacheManager == null) {
                    //创建缓存管理器
                    EhCacheUtils.cacheManager = CacheManager.create(is);
                }
            }
        }
    }

}