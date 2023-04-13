package com.iscas.common.redis.tools.interfaces;

import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * set 相关操作接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/16 15:14
 * @since jdk1.8
 */
public interface IJedisSortSetClient {
    /**
     * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重
     * @since jdk1.8
     * @date 2020/11/16
     * @param key 缓存的key
     * @param score 权重
     * @param member 缓存的成员
     * @throws IOException io异常
     * @return long
     */
    long zadd(String key, double score, Object member) throws IOException;

    /**
     * 设置zSet缓存
     *
     * @param key           键
     * @param valueScoreMap (value和score)
     * @param cacheSeconds  超时时间，0为不超时
     * @throws IOException io异常
     * @return long
     */
    long zadd(String key, Map<?, Double> valueScoreMap, int cacheSeconds) throws IOException;

    /**
     * 向ZSet缓存中添加值
     *
     * @param key           键
     * @param valueScoreMap (value和score)
     * @return long
     * @throws IOException io异常
     */
    long zadd(String key, Map<?, Double> valueScoreMap) throws IOException;

    /**
     * 获取zset中元素的个数
     * @param key 键
     * @return long
     * @throws IOException IO异常
     */
    long zcard(String key) throws IOException;

    /**
     * 获取zset中指定权重区间内元素的数量
     * @param key 键
     * @param min 最小值
     * @param max 最大值
     * @return long
     * @throws IOException IO异常
     */
    long zcount(String key, double min, double max) throws IOException;

    /**
     * 如果给定的member已存在， zset中某个元素的权重增加给定值，如果不存在插入这个元素和对应的权重
     *
     * @param key 键
     * @param score  要增的权重
     * @param member 元素
     * @return double 增后的权重
     * @throws IOException io异常
     */
    double zincrby(String key, double score, Object member) throws IOException;

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
     *
     * @param tClass  返回对象的泛型
     * @param key    键
     * @param start  开始位置(包含)
     * @param end 结束位置(包含)
     * @return Set
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     **/
    <T> Set<T> zrange(Class<T> tClass, String key, long start, long end) throws IOException, ClassNotFoundException;

    /**
     * 读取制定范围的zset中的元素并返回Map
     *
     * @param tClass 返回对象的泛型
     * @param key 键
     * @param start  开始位置(包含)
     * @param end 结束位置(包含)
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    <T> Map<T, Double> zrangeWithScoresToMap(Class<T> tClass, String key, long start, long end) throws IOException, ClassNotFoundException;

    /**
     * 读取制定范围的zset中的元素并返回Map,
     * @param key 键
     * @param start  开始位置(包含)
     * @param end 结束位置(包含)
     * @return 值
     * @throws IOException io异常
     */
    Set<Tuple> zrangeWithScores(String key, long start, long end) throws IOException;

    /**
     * 按照权重值查找zset中的元素
     *
     * @param tClass 返回对象的泛型
     * @param key 键
     * @param min  权重最小值
     * @param max  权重最大值
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    <T> Set<T> zrangeByScore(Class<T> tClass, String key, double min, double max) throws IOException, ClassNotFoundException;

    /**
     * 按照权重值查找zset中的元素, 带偏移量
     *
     * @param tClass 返回对象的泛型
     * @param key 键
     * @param min  权重最小值
     * @param max  权重最大值
     * @param offset  偏移量
     * @param count  数量
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    <T> Set<T> zrangeByScore(Class<T> tClass, String key, double min, double max, int offset, int count) throws IOException, ClassNotFoundException;

    /**
     * 读取指定得分范围的zset中的元素
     *
     * @param key 键
     * @param min  开始位置(包含)
     * @param max 结束位置(包含)
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) throws IOException, ClassNotFoundException;

    /**
     * 读取指定得分范围的zset中的元素, 返回Map
     * @param tClass 返回对象的泛型
     * @param key 键
     * @param min  开始位置(包含)
     * @param max 结束位置(包含)
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    <T> Map<T, Double> zrangeByScoreWithScoresToMap(Class<T> tClass, String key, double min, double max) throws IOException, ClassNotFoundException;

    /**
     * 读取指定得分范围的zset中的元素, 带偏移量
     *
     * @param key 键
     * @param min  开始位置(包含)
     * @param max 结束位置(包含)
     * @param offset 偏移量
     * @param count 数量
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) throws IOException, ClassNotFoundException;

    /**
     * 读取指定得分范围的zset中的元素, 返回Map, 带偏移量
     * @param tClass 返回对象的泛型
     * @param key 键
     * @param min  开始位置(包含)
     * @param max 结束位置(包含)
     * @param offset 偏移量
     * @param count 数量
     * @return 值
     * @throws IOException io异常
     * @throws ClassNotFoundException 找不到类异常
     */
    <T> Map<T, Double> zrangeByScoreWithScoresToMap(Class<T> tClass, String key, double min, double max, int offset, int count) throws IOException, ClassNotFoundException;

    /**
     * 获取指定值在zset中的位置，集合排序从低到高
     *
     * @param key 键
     * @param member  成员
     * @return 值
     * @throws IOException io异常
     */
    long zrank(String key, Object member) throws IOException;

    /**
     * 获取指定值在zset中的位置，集合排序从高到低
     *
     * @param key 键
     * @param member  成员
     * @return 值
     * @throws IOException io异常
     */
    long zrevrank(String key, Object member) throws IOException;

    /**
     * 从zset中删除成员
     *
     * @param key 键
     * @param members 成员
     * @return 值
     * @throws IOException io异常
     */
    long zrem(String key, Object... members) throws IOException;

    /**
     * 删除zset中给定位置区间的元素
     *
     * @param key 键
     * @param start 开始位置(包含)
     * @param end   结束位置(包含)
     * @return 值
     * @throws IOException io异常
     */
    long zremrangeByRank(String key, int start, int end) throws IOException;

    /**
     * 删除zset中给定权重区间的元素
     *
     * @param key 键
     * @param min 最小权重(包含)
     * @param max 最大权重(包含)
     * @return 删除的数量
     * @throws IOException io异常
     */
    long zremrangeByScore(String key, double min, double max) throws IOException;

    /**
     * 获取给定值在zset中的权重
     *
     * @param key 键
     * @param memeber 元素
     * @return 权重，没找到返回null
     * @throws IOException io异常
     */
    Double zscore(String key, Object memeber) throws IOException;

    /**
     * 通过字典区间返回zset的成员
     *
     * @param key 键
     * @param min 字典中排序位置较小的成员,必须以"["开头,或者以"("开头,可使用"-"代替
     * @param max 字典中排序位置较大的成员,必须以"["开头,或者以"("开头,可使用"+"代替
     * @return 元素集合
     */
//    <T> Set<T> zrangeByLex(Class<T> tClass, String key, String min, String max) throws IOException, ClassNotFoundException;

    /**
     * 计算给定的一个或多个zset的交集，并存储在新的 key 中
     *
     * @param dstKey 目标zset的key
     * @param keys 取交集的zset的key
     * @return long 交集后的数目
     * @throws IOException io异常
     */
    long zinterstore(String dstKey, String... keys) throws IOException;

    /**
     * 计算给定的一个或多个zset的并集，并存储在新的 key 中
     *
     * @param dstKey 目标zset的key
     * @param keys 取交集的zset的key
     * @return long 交集后的数目
     * @throws IOException io异常
     */
    long zunionstore(String dstKey, String... keys) throws IOException;

}
