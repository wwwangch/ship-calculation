package com.iscas.common.ehcache.tools;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.xml.XmlConfiguration;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class EhCacheUtils {
    private static volatile CacheManager cacheManager = null;
    private static volatile Map<String, Cache<?, ?>> CACHE_MAP = new ConcurrentHashMap<>();

    private EhCacheUtils() {
    }

    /**
     * 获取Cache
     *
     * */
    public static <K,V> Cache<K, V> getCache(String cacheName, Class<K> kClass, Class<V> vClass) {
        Cache<K, V> cache = null;
        if (!CACHE_MAP.containsKey(cacheName)) {
            synchronized (EhCacheUtils.class) {
                if (!CACHE_MAP.containsKey(cacheName)) {
                    if (cacheManager == null) {
                        throw new RuntimeException("cacheManager未初始化");
                    }
                    cache = cacheManager.getCache(cacheName, kClass, vClass);
                    if (cache == null) {
                        throw new RuntimeException("获取cache失败," + cacheName + "的配置可能不存在");
                    }
                    CACHE_MAP.put(cacheName, cache);
                }
            }
        }
        return cache != null ? cache : (Cache<K, V>) CACHE_MAP.get(cacheName);
    }

    /**
     * 放入键值对
     * */
    public static <K, V> void put(String cacheName, K key, V value) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), value.getClass());
        cache.put(key, value);
    }

    /**
     * 取出键值对
     * */
    public static <K, V> V get(String cacheName, K key, Class<V> vClass) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), vClass);
        return cache.get(key);
    }

    /**
     * 删除键值对
     * */
    public static <K, V> void remove(String cacheName, K key, Class<V> vClass) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), vClass);
        cache.remove(key);
    }

    /**
     * 判断是否含有某个缓存
     * */
    public static <K, V> boolean containsKey(String cacheName, K key, Class<V> vClass) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), vClass);
        return cache.containsKey(key);
    }

    /**
     * 替换缓存
     * */
    public static <K, V> boolean replace(String cacheName, K key, V oldValue, V newValue) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), oldValue.getClass());
        return cache.replace(key, oldValue, newValue);
    }

    /**
     * 替换缓存
     * */
    public static <K, V> V replace(String cacheName, K key, V value) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), value.getClass());
        return cache.replace(key, value);
    }

    /**
     * 获取多个key的缓存
     * */
    public static <K, V> Map<K, V> getAll(String cacheName, Set<K> keys, Class<V> vClass) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, keys.iterator().next().getClass(), vClass);
        return cache.getAll(keys);
    }

    /**
     * 不存在的时候设置值
     * */
    public static <K, V> V putIfAbsent(String cacheName, K key, V value) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, key.getClass(), value.getClass());
        return cache.putIfAbsent(key, value);
    }

    /**
     * 清除缓存
     * */
    public static <K, V> void clear(String cacheName, Class<K> keyClass, Class<V> valueClass) {
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, keyClass, valueClass);
        cache.clear();
    }

    /**
     * 设置多个值
     * */
    public static <K, V> void putAll(String cacheName, Map<K,V> keyValues) {
        Set<Map.Entry<K, V>> entries = keyValues.entrySet();
        Map.Entry<K, V> entry = entries.iterator().next();
        Cache<K, V> cache = (Cache<K, V>) getCache(cacheName, entry.getKey().getClass(), entry.getValue().getClass());
        cache.putAll(keyValues);
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
                    EhCacheUtils.cacheManager.init();
                }
            }
        }
    }

    /**
     * 初始化一个CacheManager，默认读取resources下的ehcache.xml
     * @param clazz class
     * */
    public static void init(Class<?> clazz) {
        if (cacheManager == null) {
            synchronized (EhCacheUtils.class) {
                if (cacheManager == null) {
                    //从配置文件创建配置对象
                    Configuration xmlConf = new XmlConfiguration(clazz.getResource("/ehcache.xml"));
                    //创建缓存管理器
                    CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConf);
                    EhCacheUtils.cacheManager = cacheManager;
                    EhCacheUtils.cacheManager.init();
                }
            }
        }
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
                    Configuration xmlConf = new XmlConfiguration(clazz.getResource("/" + xmlName));
                    //创建缓存管理器
                    CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConf);
                    EhCacheUtils.cacheManager = cacheManager;
                    EhCacheUtils.cacheManager.init();
                }
            }
        }
    }

    /**
     * 初始化一个CacheManager，读取外部配置的XML配置文件
     * @param xmlFile 外部的XML文件
     * */
    public static void init(File xmlFile) throws MalformedURLException {
        if (cacheManager == null) {
            synchronized (EhCacheUtils.class) {
                if (cacheManager == null) {
                    //从配置文件创建配置对象
                    Configuration xmlConf = new XmlConfiguration(xmlFile.toURI().toURL());
                    //创建缓存管理器
                    CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConf);
                    EhCacheUtils.cacheManager = cacheManager;
                    EhCacheUtils.cacheManager.init();
                }
            }
        }
    }


//    public static void main(String[] args) {
//        //获取到管理cache的CacheManager，
//        // 并且初识化了一个名为preConfigured的cache，
//        // 该cache里存储的entity的key类型为Long , value类型为String,
//        //该cache最多只能缓存100个entity
//        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("preConfigured",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                        ResourcePoolsBuilder.heap(100))
//                                .build())
//                .build(true);
//        //通过CacheManager获取到名为preConfigured的cache
//        Cache<Long, String> preConfigured
//                = cacheManager.getCache("preConfigured", Long.class, String.class);
//        //通过CacheManager根据特定配置再创建一个cache，名为myCache，该cache由cacheManager这个实例管理
//        Cache<Long, String> myCache = cacheManager.createCache("myCache",
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                        ResourcePoolsBuilder.heap(100)).build());
//        //放入一个entity到myCache里，类似于Map的存储方式
//        myCache.put(1L, "da one!");
//        //从名为myCache的cache里获取key为1的value值
//        String value = myCache.get(1L);
//        //打印获取到的值
//        System.out.println(value);
//        //关闭CacheManager，这个方法会自动关闭该CacheManager管理的所有cache及service
//        cacheManager.close();
//
//    }

//    public static void main(String[] args) {
//        // 缓存的时间监听
//        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
//                // CacheEventListenerConfiguration使用构建器创建一个指示侦听器和要接收的事件（在这种情况下，创建和更新事件）
//                .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED)
//                // 可选地指示交付模式 - 默认值是异步的和无序的（出于性能原因）
//                .unordered().asynchronous();
//
//        PersistentCacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .with(CacheManagerBuilder.persistence(new File("d:/tmp/ehcache", "myData")))
//                .using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
//                        .pool("defaultEventPool", 1, 3)
//                        .pool("cache2Pool", 2, 2)
//                        .build())
//                .withDefaultEventListenersThreadPool("defaultEventPool")
//                .withCache("cache1",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(10, EntryUnit.ENTRIES))
//                                .add(CacheEventListenerConfigurationBuilder
//                                        .newEventListenerConfiguration(new CacheLogListener(), EventType.CREATED, EventType.UPDATED))
//                                .withDispatcherConcurrency(10) // 指出所需的并发级别
//                                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS)))) // 设置过期时间
//                .withCache("cache",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
//                                        ResourcePoolsBuilder.newResourcePoolsBuilder()
//                                                .heap(10, EntryUnit.ENTRIES)
//                                                .offheap(1, MemoryUnit.MB)
//                                                .disk(20, MemoryUnit.MB, true))
//                                .add(cacheEventListenerConfiguration)
//                                .withDispatcherConcurrency(10) // 指出所需的并发级别
//                                .withDiskStoreThreadPool("cache2Pool", 2)
//                ).build(true);
//
//
//        Cache<Long, String> threeTieredCache =cacheManager.getCache("cache", Long.class, String.class);
////        threeTieredCache.put(1L, "stillAvailableAfterRestart");
//        System.out.println("args = [" +   threeTieredCache.get(1L) + "]");
//        cacheManager.close();
//    }

    private static class CacheLogListener implements CacheEventListener {

        @Override
        public void onEvent(CacheEvent event) {

        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        //从配置文件创建配置对象
//        Configuration xmlConf = new XmlConfiguration(EhCacheUtils.class.getResource("/ehcache.xml"));
//        //创建缓存管理器
//        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(xmlConf);
//        cacheManager.init();
//        //从缓存管理器中获取缓存
//        Cache<String, String> mycache3 = cacheManager.getCache("myCache3", String.class, String.class);
//        //使用缓存
//        mycache3.put("1", "Hello world!");
//        System.out.println(mycache3.get("1"));
//        TimeUnit.SECONDS.sleep(11);
//        System.out.println(mycache3.get("1"));
//        //清空缓存，关闭缓存管理器
//        mycache3.clear();
//        cacheManager.close();
//    }
}
