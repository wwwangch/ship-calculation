package com.iscas.common.redis.tools.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis-hash操作接口，适用于对象，自动序列化与反序列化
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/17 15:50
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused"})
public interface IJedisHashClient {

    /**
     * 存入hash
     *
     * @param key           key
     * @param map           存储的键值对
     * @param cacheSenconds 缓存的时间，0代表永久
     * @return 成功返回true
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    boolean hmset(String key, Map map, int cacheSenconds) throws IOException;

    /**
     * 从hash中获取所有元素存入map
     *
     * @param keyClass key 的class
     * @param valClass val 的
     * @param key      key
     * @return Map
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    <K, V> Map<K, V> hgetAll(Class<K> keyClass, Class<V> valClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 从hash中删除元素
     *
     * @param key    key
     * @param fields fields
     * @return 删除的元素数量
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hdel(String key, Object... fields) throws IOException;

    /**
     * 从hash中删除元素
     *
     * @param key   key
     * @param field hash的key
     * @return boolean
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    boolean hexists(String key, Object field) throws IOException;

    /**
     * 从hash中获取指定key的值
     *
     * @param tClass 返回元素的泛型
     * @param key    key
     * @param field  hash的key
     * @return T
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    <T> T hget(Class<T> tClass, String key, String field) throws IOException, ClassNotFoundException;

    /**
     * 向hash中添加一个键值对
     *
     * @param key   key
     * @param field hash的key
     * @param value hash的value
     * @return long
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hset(String key, Object field, Object value) throws IOException;

    /**
     * 增加hash一个条目，只有在field不存在时才执行
     *
     * @param key   key
     * @param field hash的key
     * @param value hash的value
     * @return long
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hsetnx(String key, Object field, Object value) throws IOException;

    /**
     * 获取hash中value的集合
     *
     * @param tClass 返回对象的泛型
     * @param key    key
     * @return List
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    <T> List<T> hvals(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * hash中的字段值加上指定增量值,存入的值要能转为整数
     *
     * @param key   哈希表的键
     * @param field field
     * @param value 要增加的值,可以是负数
     * @return long
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hincrby(String key, String field, long value) throws IOException;

    /**
     * hash中的字段值加上指定浮点增量值,存入的值要能转为整数
     *
     * @param key   哈希表的键
     * @param field field
     * @param value 要增加的值,可以是负数
     * @return Double
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    Double hincrby(String key, String field, double value) throws IOException;

    /**
     * 返回指定hash中的所有field,类似Map中的keySet方法
     *
     * @param tClass field的泛型
     * @param key    哈希表的键
     * @return Set
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    <T> Set<T> hkeys(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 获取hash中存储的个数，类似Map中size方法
     *
     * @param key 哈希表的键
     * @return long
     * @throws IOException io异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hlen(String key) throws IOException;

    /**
     * 从hash中，根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
     *
     * @param tClass 返回值的泛型
     * @param key    哈希表的键
     * @param fields 哈希表的key
     * @return List
     * @throws IOException io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/17
     * @since jdk1.8
     */
    <T> List<T> hmget(Class<T> tClass, String key, Object... fields) throws IOException, ClassNotFoundException;

}
