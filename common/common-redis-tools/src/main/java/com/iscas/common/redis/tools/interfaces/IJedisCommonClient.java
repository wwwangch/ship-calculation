package com.iscas.common.redis.tools.interfaces;

import redis.clients.jedis.PipelineBase;

import java.util.function.Consumer;

/**
 * Jedis通用接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 19:38
 * @since jdk1.8
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface IJedisCommonClient {


    /**
     * 获取分布式锁
     *
     * @param lockName        锁key
     * @param lockTimeoutInMS 锁超时时间
     * @return 锁标识
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    String acquireLock(String lockName, long lockTimeoutInMS);

    /**
     * 释放分布式锁
     *
     * @param lockName   锁key
     * @param identifier 锁标识
     * @return boolean
     */
    boolean releaseLock(String lockName, String identifier);


    /**
     * 对IP进行限流
     *
     * @param ip      ip
     * @param timeout 超时时间
     * @param limit   限制
     * @return boolean
     */
    boolean accessLimit(String ip, int timeout, int limit);

    /**
     * 获取Pipeline
     *
     * @param jedisResource Jedis 或SharedJedis 或JedisCluster
     * @return PipelineBase PipelineBase
     * @date 2020/11/20
     * @since jdk1.8
     */
    PipelineBase getPipeline(Object jedisResource);

    /**
     * 使用pipeline批量执行，jedisCluster暂不支持
     *
     * @param consumer 消费者
     * @date 2020/11/20
     * @since jdk1.8
     */
    void pipelineBatch(Consumer<PipelineBase> consumer);

}
