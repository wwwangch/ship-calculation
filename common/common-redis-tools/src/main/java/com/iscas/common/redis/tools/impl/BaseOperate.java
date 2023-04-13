package com.iscas.common.redis.tools.impl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import java.util.Set;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/7 13:13
 * @since jdk11
 */
@SuppressWarnings("deprecation")
public class BaseOperate {
    private BaseOperate() {}

    public static void del(String pattern, Jedis jedis) {
        Set<String> keys = jedis.keys(pattern);
        if(keys != null && !keys.isEmpty()) {
            String[] keyArr = new String[keys.size()];
            jedis.del(keys.toArray(keyArr));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public static Long sadd(String key, Object jc, String[] value) {
        if (jc instanceof Jedis) {
            Jedis jedis = (Jedis) jc;
            return jedis.sadd(key, value);
        } else if (jc instanceof ShardedJedis) {
            ShardedJedis jedis = (ShardedJedis) jc;
            return jedis.sadd(key, value);
        } else if (jc instanceof JedisCluster) {
            JedisCluster jedis = (JedisCluster) jc;
            return jedis.sadd(key, value);
        }
        return null;
    }

    @SuppressWarnings("DuplicatedCode")
    public static Boolean sismember(String key, String member, Object jc) {
        if (jc instanceof Jedis) {
            Jedis jedis = (Jedis) jc;
            return jedis.sismember(key, member);
        } else if (jc instanceof ShardedJedis) {
            ShardedJedis jedis = (ShardedJedis) jc;
            return jedis.sismember(key, member);
        } else if (jc instanceof JedisCluster) {
            JedisCluster jedis = (JedisCluster) jc;
            return jedis.sismember(key, member);
        }
        return null;
    }

    public static long srem(String key, Object jc, String[] member) {
        long res = 0;
        if (jc instanceof Jedis) {
            Jedis jedis = (Jedis) jc;
            res = jedis.srem(key, member);
        } else if (jc instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jc;
            res = shardedJedis.srem(key, member);
        } else if (jc instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jc;
            res = jedisCluster.srem(key, member);
        }
        return res;
    }
}
