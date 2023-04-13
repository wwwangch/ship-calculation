package com.iscas.common.redis.tools.interfaces;

import redis.clients.jedis.ListPosition;

import java.io.IOException;
import java.util.List;

/**
 * List(链表) 相关操作接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/16 11:13
 * @since jdk1.8
 */
public interface IJedisListClient {
    /**
     * 向list中尾部追加元素
     *
     * @param key   key
     * @param value 元素
     * @return 追加元素后链表的长度
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    long rpush(String key, Object... value) throws IOException;

    /**
     * 向list中头部追加元素
     *
     * @param key   key
     * @param value 元素
     * @return 追加元素后链表的长度
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    long lpush(String key, Object... value) throws IOException;

    /**
     * 获取list的长度
     *
     * @param key key
     * @return 追加元素后链表的长度
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    long llen(String key) throws IOException;

    /**
     * 覆盖List中指定位置的值
     *
     * @param key   key
     * @param index 下标
     * @param value 值
     * @return 追加元素后链表的长度
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    boolean lset(String key, int index, Object value) throws IOException;

    /**
     * 在value的相对位置插入记录
     *
     * @param key   key
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 追加元素后链表的长度
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    long linsert(String key, ListPosition where, Object pivot, Object value) throws IOException;

    /**
     * 获取List中指定位置的值
     *
     * @param tClass 返回值的泛型
     * @param key    key
     * @param index  位置下标
     * @return T
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    <T> T lindex(Class<T> tClass, String key, long index) throws IOException, ClassNotFoundException;

    /**
     * 将List中的第一条记录移出List
     *
     * @param tClass 返回值的泛型
     * @param key    key
     * @return T
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    <T> T lpop(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 将List中的最后一条记录移出List
     *
     * @param tClass 返回值的泛型
     * @param key    key
     * @return T
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    <T> T rpop(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param tClass 返回的泛型
     * @param key    key
     * @param start  开始位置
     * @param end    结束位置
     * @return 操作后list的长度
     * @throws IOException            io异常
     * @throws ClassNotFoundException 类不存在异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    <T> List<T> lrange(Class<T> tClass, String key, long start, long end) throws IOException, ClassNotFoundException;

    /**
     * 删除List中count条记录，被删除的记录值为value
     *
     * @param key   key
     * @param count 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除的记录数
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    long lrem(String key, int count, Object value) throws IOException;

    /**
     * 删除记录，只保留start与end之间的记录
     *
     * @param key   key
     * @param start 开始位置
     * @param end   结束位置
     * @return boolean
     * @throws IOException io异常
     * @date 2020/11/19
     * @since jdk1.8
     */
    boolean ltrim(String key, long start, long end) throws IOException;


}
