package com.iscas.common.redis.tools.interfaces;

import java.util.Set;

/**
 * set 相关操作接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/16 11:13
 * @since jdk1.8
 */
public interface IJedisSetStrClient {

    /**
     * 向Set缓存中添加值
     * @param key 键
     * @param value 值
     * @return long
     */
    long sadd(String key, String... value);

    /**
     * 获取集合中元素的个数
     * @since jdk1.8
     * @date 2020/11/2
     * @param key 集合的key
     * @return long
     */
    long scard(String key);

    /**
     * 返回从第一个Key和其他key的集合之间的差异的成员，如果没有差异，返回空集合
     * @since jdk1.8
     * @date 2020/11/4
     * @param keys 集合的keys
     * @return Set 差集
     */
    Set<String> sdiff(String... keys);


    /**
     * 这个命令等于{@link #sdiff},但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     * @since jdk1.8
     * @date 2020/11/6
     * @param newkey 存储的心集合
     * @param keys 要取差集的集合的key
     * @return long 新集合中的记录数
     */
    long sdiffStore(String newkey, String... keys);

    /**
     * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set
     * @since jdk1.8
     * @date 2020/11/6
     * @param keys 要取差集的集合的key
     * @return Set<String> 交集的集合
     */
    Set<String> sinter(String... keys);


    /**
     * 个命令等于{@link #sinter},但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     * @since jdk1.8
     * @date 2020/11/6
     * @param newKey 新的key
     * @param keys 要取差集的集合的key
     * @return long 交集的条目
     */
    long sinterStore(String newKey, String... keys);


    /**
     * 判断一个给定的值在集合中是否存在
     * @since jdk1.8
     * @date 2020/11/6
     * @param key 集合的key
     * @param member 成员
     * @return boolean true:存在 false:不存在
     */
    boolean sismember(String key, String member);

    /**
     * 返回集合中的所有成员，成员为字符串
     * @since jdk1.8
     * @date 2020/11/6
     * @param key 集合的key
     * @return Set<String> 集合中的所有值
     */
    Set<String> smembers(String key);

    /**
     * 将成员从源集合移出放入目标集合 </br>
     * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0 </br>
     * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除
     * @since jdk1.8
     * @date 2020/11/11
     * @param srckey 源集合的key
     * @param dstkey 目标集合的key
     * @param member 源集合中的元素
     * @return 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0
     */
    long smove(String srckey, String dstkey, String member);


    /**
     * 从集合中移除成员并返回
     * @since jdk1.8
     * @date 2020/11/11
     * @param key 集合的key
     * @return String
     */
    String spop(String key);

    /**
     * 从集合中移除成员并返回
     * @since jdk1.8
     * @date 2020/11/11
     * @param key 集合的key
     * @param count 移除集合的数目
     * @return Set
     */
    Set<String> spop(String key, long count);

    /**
     * 从集合中移除某个成员，成功返回1，成员不存在返回0,移除多个元素时，如果移除了其中一个就返回1
     * @since jdk1.8
     * @date 2020/11/15
     * @param key key
     * @param member 成员
     * @return long
     */
    long srem(String key, String... member);

    /**
     * 合并多个集合并返回合并后的结果
     * @since jdk1.8
     * @date 2020/11/16
     * @param keys 集合的key
     * @return Set
     */
    Set<String> sunion(String... keys);
}
