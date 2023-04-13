package com.iscas.common.jredisearch.tools;


import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.impl.standalone.JedisStandAloneConnection;
import io.redisearch.client.Client;
import redis.clients.jedis.JedisPool;

/**
 * 全文检索工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/4 10:18
 * @since jdk1.8
 */
public class JredisearchUtils {
    private JredisearchUtils() {}

    private static volatile Client client;

    public static Client initJredisearchClient(JedisConnection jedisConnection, ConfigInfo configInfo, String indexName) {

        if (client == null) {
            synchronized (JredisearchUtils.class) {
                if (client == null) {
                    if (!(jedisConnection instanceof JedisStandAloneConnection)) {
                        throw new UnsupportedOperationException("当前只支持JedisStandAloneConnection类型");
                    }
                    jedisConnection.initConfig(configInfo);
                    JedisPool pool = (JedisPool) jedisConnection.getPool();
                    client = new Client(indexName, pool);
                }
            }
        }
        return client;
    }
}
