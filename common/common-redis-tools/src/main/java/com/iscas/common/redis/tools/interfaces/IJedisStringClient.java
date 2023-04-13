package com.iscas.common.redis.tools.interfaces;

import java.io.IOException;
import java.util.List;

/**
 * string 相关操作接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/18 20:25
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public interface IJedisStringClient {

    /**
     * 设置缓存
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return boolean
     * @throws IOException io异常
     */
    boolean set(String key, Object value, int cacheSeconds) throws IOException;

    /**
     * 获取缓存
     *
     * @param tClass 对象泛型
     * @param key    键
     * @return 值
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类找不到异常
     */
    <T> T get(Class<T> tClass, String key) throws IOException, ClassNotFoundException;

    /**
     * 添加一条记录，仅当给定的key不存在时才插入
     *
     * @param key   key
     * @param value 存入的元素
     * @return long 状态码，1插入成功且key不存在，0未插入，key存在
     * @throws IOException IO异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    long setnx(String key, Object value) throws IOException;

    /**
     * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
     * 例:String str="123456789";<br/>
     * 对str操作后setRange(key,2,000000)，str1="120000009"
     *
     * @param key    key
     * @param offset 字符串偏移量
     * @param value  偏移量后面要设置的元素
     * @return long 替换后存储的元素的长度
     * @throws IOException IO异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    long setrange(String key, long offset, Object value) throws IOException;

    /**
     * 在指定的key中追加value
     *
     * @param key   key
     * @param value 要追加的元素
     * @return long 追加后存储的元素的长度
     * @date 2020/11/18
     * @since jdk1.8
     */
    long append(String key, String value);

    /**
     * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
     *
     * @param key    key
     * @param number 要减去的数字
     * @return long 减后的值
     * @throws IOException io异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    long decrBy(String key, long number) throws IOException;

    /**
     * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
     *
     * @param key    key
     * @param number 要加上的数字
     * @return long 加上的值
     * @throws IOException IO异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    long incrBy(String key, long number) throws IOException;

    /**
     * 对指定key对应的value进行截取
     *
     * @param tClass      返回值得类型
     * @param key         key
     * @param startOffset 开始位置(包含)
     * @param endOffset   结束位置(包含)
     * @return 截取后的值
     * @date 2020/11/18
     * @since jdk1.8
     */
    <T> T getrange(Class<T> tClass, String key, long startOffset, long endOffset);

    /**
     * 获取并设置指定key对应的value<br/>
     * 如果key存在返回之前的value,否则返回null
     *
     * @param tClass 返回值得类型
     * @param key    key
     * @param value  要设置的值
     * @return 旧的值
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类找不到异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    <T> T getSet(Class<T> tClass, String key, T value) throws IOException, ClassNotFoundException;

    /**
     * 批量获取记录,如果指定的key不存在返回List的对应位置将是null
     *
     * @param tClass 返回值得类型
     * @param keys   keys
     * @return List<T>
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类找不到异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    <T> List<T> mget(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException;

    /**
     * 批量存储记录
     *
     * @param keysvalues 例:keysvalues="key1","value1","key2","value2";
     * @return boolean
     * @throws IOException IO异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    boolean mset(Object... keysvalues) throws IOException;

    /**
     * 获取key对应的值的长度
     *
     * @param key key
     * @return long
     * @throws IOException IO异常
     * @date 2020/11/18
     * @since jdk1.8
     */
    long strlen(String key) throws IOException;

}
