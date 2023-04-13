package com.iscas.common.redis.tools.interfaces;

import java.io.IOException;
import java.util.Set;

/**
 * set 相关操作接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/16 11:13
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
public interface IJedisSetClient {
    /**
     * 设置Set缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return long
     * @throws IOException io异常
     */
    long sadd(String key, Set value, int cacheSeconds) throws IOException;

    /**
     * 向Set缓存中添加值
     *
     * @param key   键
     * @param value 值
     * @return long
     * @throws IOException io异常
     */
    long sadd(String key, Object... value) throws IOException;

    /**
     * 获取集合中对象元素的个数
     *
     * @param key 集合的key
     * @return long
     * @throws IOException io异常
     * @date 2020/11/2
     * @since jdk1.8
     */
    long scard(String key) throws IOException;

    /**
     * 返回从第一个Key和其他key的集合之间的差异的成员，返回结果为反序列的对象，放入时也必须是非字符串的对象。如果没有差异，返回空集合
     *
     * @param tClass 返回对象的泛型
     * @param keys   集合的keys
     * @return Set 差集
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/4
     * @since jdk1.8
     */
    <T> Set<T> sdiff(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException;

    /**
     * 这个命令等于{@link #sdiff},但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     *
     * @param newkey 存储的心集合
     * @param keys   要取差集的集合的key
     * @return long 新集合中的记录数
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/6
     * @since jdk1.8
     */
    long sdiffStore(String newkey, String... keys) throws IOException, ClassNotFoundException;

    /**
     * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set,适用于存放对象的集合
     *
     * @param tClass 返回对象的泛型
     * @param keys   要取差集的集合的key
     * @return Set<Object> 交集的集合
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/6
     * @since jdk1.8
     */
    <T> Set<T> sinter(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException;

    /**
     * 个命令等于{@link #sinter},但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖。
     *
     * @param newKey 新的key
     * @param keys   要取差集的集合的key
     * @return long 交集的条目
     * @throws IOException io异常
     * @date 2020/11/6
     * @since jdk1.8
     */
    long sinterStore(String newKey, String... keys) throws IOException;

    /**
     * 判断一个给定的值在集合中是否存在,集合中存储的为对象
     *
     * @param key    集合的key
     * @param member 成员
     * @return boolean true:存在 false:不存在
     * @throws IOException io异常
     * @date 2020/11/6
     * @since jdk1.8
     */
    boolean sismember(String key, Object member) throws IOException;

    /**
     * 返回集合中的所有成员, 成员为对象
     *
     * @param tClass 返回值的泛型
     * @param key    集合的key
     * @return Set<String> 集合中的所有值
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/6
     * @since jdk1.8
     */
    <T> Set<T> smembers(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 将对象成员从源集合移出放入目标集合 </br>
     * 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0 </br>
     * 否则该成员从源集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在源集合进行删除
     *
     * @param srckey 源集合的key
     * @param dstkey 目标集合的key
     * @param member 源集合中的元素
     * @return 如果源集合不存在或不包哈指定成员，不进行任何操作，返回0
     * @throws IOException io异常
     * @date 2020/11/11
     * @since jdk1.8
     */
    long smove(String srckey, String dstkey, Object member) throws IOException;

    /**
     * 从集合中移除对象成员并返回
     *
     * @param tClass 返回对象的泛型
     * @param key    集合的key
     * @return T
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/11
     * @since jdk1.8
     */
    <T> T spop(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 从集合中移除对象成员并返回
     *
     * @param tClass 返回对象的泛型
     * @param key    集合的key
     * @param count  移除集合的数目
     * @return Set
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/11
     * @since jdk1.8
     */
    <T> Set<T> spop(Class<T> tClass, String key, long count) throws IOException, ClassNotFoundException;

    /**
     * 从集合中移除某个成员，成功返回1，成员不存在返回0,移除多个元素时，如果移除了其中一个就返回1
     *
     * @param key    key
     * @param member 成员
     * @return long
     * @throws IOException io异常
     * @date 2020/11/15
     * @since jdk1.8
     */
    long srem(String key, Object... member) throws IOException;

    /**
     * 合并多个集合并返回合并后的结果
     *
     * @param tClass 返回的泛型
     * @param keys   集合的key
     * @return Set<String> 取并集的结果
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/16
     * @since jdk1.8
     */
    <T> Set<T> sunion(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException;
}
