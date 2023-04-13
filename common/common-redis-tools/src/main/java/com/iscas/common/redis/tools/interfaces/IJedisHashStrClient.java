package com.iscas.common.redis.tools.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis-hash操作接口，适用于字符串，自动序列化与反序列化
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/17 15:50
 * @since jdk1.8
 */
public interface IJedisHashStrClient {

    /**
     * 存入hash
     *
     * @param key           key
     * @param map           存储的键值对
     * @param cacheSenconds 缓存的时间，0代表永久
     * @return 成功返回true
     * @date 2020/11/17
     * @since jdk1.8
     */
    boolean hmset(String key, Map<String, String> map, int cacheSenconds);

    /**
     * 从hash中获取所有元素存入map
     *
     * @param key key
     * @return Map
     * @date 2020/11/17
     * @since jdk1.8
     */
    Map<String, String> hgetAll(String key);

    /**
     * 从hash中删除元素
     *
     * @param key    key
     * @param fields hash的key
     * @return 删除的元素数量
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hdel(String key, String... fields);

    /**
     * 从hash中删除元素
     *
     * @param key   key
     * @param field hash的key
     * @return boolean
     * @date 2020/11/17
     * @since jdk1.8
     */
    boolean hexists(String key, String field);

    /**
     * 从hash中获取指定key的值
     *
     * @param key   key
     * @param field hash的key
     * @return String
     * @date 2020/11/17
     * @since jdk1.8
     */
    String hget(String key, String field);

    /**
     * 向hash中添加一个键值对
     *
     * @param key   key
     * @param field hash的key
     * @param value hash的value
     * @return long
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hset(String key, String field, String value);

    /**
     * 增加hash一个条目，只有在field不存在时才执行
     *
     * @param key   key
     * @param field hash的key
     * @param value hash的value
     * @return long
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hsetnx(String key, String field, String value);

    /**
     * 获取hash中value的集合
     *
     * @param key key
     * @return List
     * @date 2020/11/17
     * @since jdk1.8
     */
    List<String> hvals(String key);

    /**
     * hash中的字段值加上指定增量值,存入的值要能转为整数
     *
     * @param key   哈希表的键
     * @param field field
     * @param value 要增加的值,可以是负数
     * @return long
     * @date 2020/11/17
     * @since jdk1.8
     */
    @SuppressWarnings("UnusedReturnValue")
    long hincrby(String key, String field, long value);

    /**
     * hash中的字段值加上指定浮点增量值,存入的值要能转为整数
     *
     * @param key   哈希表的键
     * @param field field
     * @param value 要增加的值,可以是负数
     * @return Double
     * @date 2020/11/17
     * @since jdk1.8
     */
    @SuppressWarnings("UnusedReturnValue")
    Double hincrby(String key, String field, double value);

    /**
     * 返回指定hash中的所有存储的field,类似Map中的keySet方法
     *
     * @param key 哈希表的键
     * @return Set
     * @date 2020/11/17
     * @since jdk1.8
     */
    Set<String> hkeys(String key);

    /**
     * 获取hash中存储的个数，类似Map中size方法
     *
     * @param key 哈希表的键
     * @return long
     * @date 2020/11/17
     * @since jdk1.8
     */
    long hlen(String key);

    /**
     * 从hash中，根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
     *
     * @param key    哈希表的键
     * @param fields 哈希表的key
     * @return List
     * @date 2020/11/17
     * @since jdk1.8
     */
    List<String> hmget(String key, String... fields);

}
