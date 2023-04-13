package com.iscas.common.redis.tools.impl;


import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.IJedisStrClient;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.helper.MyStringHelper;
import com.iscas.common.redis.tools.impl.cluster.JedisClusterConnection;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import redis.clients.jedis.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * JedisClient
 *
 * 将原来的{@link IJedisStrClient} 中<br/>
 * 存储字符串的方式单独拆分出来，以免造成对象存储与字符串存储调用时混淆
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/15
 * @since jdk1.8
 */
@SuppressWarnings({"unchecked", "rawtypes", "deprecation"})
public class JedisStrClient extends JedisCommonClient implements IJedisStrClient {

    public JedisStrClient(JedisConnection jedisConnection, ConfigInfo configInfo) {
        this.jedisConnection = jedisConnection;
        jedisConnection.initConfig(configInfo);
        jedisPool = jedisConnection.getPool();
    }


    /*========================通用 begin=================================*/

    @Override
    public void pipelineClusterBatch(Consumer<RedisAdvancedClusterCommands<String, String>> consumer) {
        JedisClusterConnection jedisClusterConnection = (JedisClusterConnection) jedisConnection;
        RedisClusterClient lettuceClusterClient = jedisClusterConnection.getLettuceClusterClient();
        StatefulRedisClusterConnection<String, String> connect = lettuceClusterClient.connect();
        RedisAdvancedClusterCommands<String, String> commands = connect.sync();
        consumer.accept(commands);
        connect.close();
    }
    /**
     * 删除缓存
     * @param key 键
     * @return long
     */
    @SuppressWarnings("deprecation")
    @Override
    public long del(String key) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.del(key);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.del(key);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.del(key);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    /**
     * 缓存是否存在
     * @param key 键
     * @return boolean
     */
    @SuppressWarnings("deprecation")
    @Override
    public boolean exists(String key) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.exists(key);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.exists(key);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.exists(key);
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings({"deprecation", "DuplicatedCode"})
    @Override
    public void deleteByPattern(String pattern) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                BaseOperate.del(pattern, (Jedis) jedis);
            } else if (jedis instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持pattern删除");
            } else if (jedis instanceof JedisCluster) {
                throw new RuntimeException("JedisCluster 暂不支持pattern删除");
            }
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void expire(String key, long milliseconds) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (milliseconds <= 0) {
                return;
            }
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                jd.pexpire(key, milliseconds);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                shardedJedis.pexpire(key, milliseconds);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                jedisCluster.pexpire(key, milliseconds);
            }
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public void putDelayQueue(String task, long timeout, TimeUnit timeUnit, Consumer<String> consumer) {
        //使用默认key
        String hostAddress = null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostAddress = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert hostAddress != null;
        putDelayQueue(DELAY_QUEUE_DEFUALT_KEY.concat(hostAddress), task, timeout, timeUnit, consumer);
    }

    @Override
    public void putDelayQueue(String key, String task, long timeout, TimeUnit timeUnit, Consumer<String> consumer) {
        long l = System.currentTimeMillis();
        long x = timeUnit.toMillis(timeout);
        long targetScore = l + x;
        Map<String, Double> map = new HashMap(2);
        map.put(task, Double.valueOf(String.valueOf(targetScore)));
        zadd(key, map);
        MAP_DELAY.put(task, consumer);
        delayTaskHandler(key);
    }

    private void delayTaskHandler(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (MAP_DELAY_EXECUTE.get(key) == null) {
                synchronized (key.intern()) {
                    if (MAP_DELAY_EXECUTE.get(key) == null) {
                        MAP_DELAY_EXECUTE.put(key, true);
                        while (true) {
                            Map<String, Double> zSet = zrangeWithScoresToMap(key, 0, -1);
                            if (zSet == null || zSet.size() == 0) {
                                break;
                            }
                            for (Map.Entry<String, Double> entry : zSet.entrySet()) {
                                String storeTask = entry.getKey();
                                Double score = entry.getValue();
                                if (System.currentTimeMillis() - score > 0) {
                                    //开始执行任务
                                    MAP_DELAY.get(storeTask).accept(storeTask);
                                    //删除这个值
                                    String script = "return redis.call('zrem', KEYS[1], ARGV[1])";
                                    jedisCommandsBytesLuaEvalSha(jedis, script, Collections.singletonList(key), Collections.singletonList(storeTask));
                                }
                            }
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } finally {
            returnResource(jedis);
        }

    }

    /*========================通用 end  =================================*/



    /*=============================set begin===============================================*/

    @Override
    public long sadd(String key, String... value) {
        long result = 0;
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Long jedis = BaseOperate.sadd(key, jc, value);
            if (jedis != null) {
                return jedis;
            }
        } finally {
            returnResource(jc);
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    @Override
    public long scard(String key) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.scard(key);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis jedis = (ShardedJedis) jc;
                return jedis.scard(key);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedis = (JedisCluster) jc;
                return jedis.scard(key);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<String> sdiff(String... keys) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sdiff(keys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sdiff");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sdiff(keys);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public long sdiffStore(String newkey, String... keys) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sdiffstore(newkey, keys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sdiff");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sdiffstore(newkey, keys);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<String> sinter(String... keys) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sinter(keys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sinter");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sinter(keys);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public long sinterStore(String newKey, String... keys) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sinterstore(newKey, keys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sinter");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sinterstore(newKey, keys);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public boolean sismember(String key, String member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Boolean jedis = BaseOperate.sismember(key, member, jc);
            if (jedis != null) {
                return jedis;
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<String> smembers(String key) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.smembers(key);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis jedis = (ShardedJedis) jc;
                return jedis.smembers(key);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedis = (JedisCluster) jc;
                return jedis.smembers(key);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public long smove(String srckey, String dstkey, String member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.smove(srckey, dstkey, member);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持smove");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.smove(srckey, dstkey, member);
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public String spop(String key) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.spop(key);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.spop(key);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.spop(key);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<String> spop(String key, long count) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.spop(key, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.spop(key, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.spop(key, count);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long srem(String key, String... member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            return BaseOperate.srem(key, jc, member);
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Set<String> sunion(String... keys) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sunion(keys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sunion");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sunion(keys);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    /*=============================set end  ===============================================*/

    /*==============================sort set begin=====================================================*/

    @SuppressWarnings("deprecation")
    @Override
    public long zadd(String key, double score, String member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zadd(key, score, member);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zadd(key, score, member);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zadd(key, score, member);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public long zadd(String key, Map<String, Double> valueScoreMap, int cacheSeconds) {
        long result = 0;
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (valueScoreMap == null || valueScoreMap.size() == 0 ) {
                throw new RuntimeException("集合不能为空");
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                if (jedis.exists(key)) {
                    jedis.del(key);
                }
                result = jedis.zadd(key, valueScoreMap);
                if (cacheSeconds != 0) {
                    jedis.expire(key, cacheSeconds);
                }
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis jedis = (ShardedJedis) jc;
                if (jedis.exists(key)) {
                    jedis.del(key);
                }
                result = jedis.zadd(key, valueScoreMap);
                if (cacheSeconds != 0) {
                    jedis.expire(key, cacheSeconds);
                }
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedis = (JedisCluster) jc;
                if (jedis.exists(key)) {
                    jedis.del(key);
                }
                result = jedis.zadd(key, valueScoreMap);
                if (cacheSeconds != 0) {
                    jedis.expire(key, cacheSeconds);
                }
            }

        } finally {
            returnResource(jc);
        }
        return result;
    }

    @Override
    public long zadd(String key, Map<String, Double> valueScoreMap) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zadd(key, valueScoreMap);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zadd(key, valueScoreMap);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zadd(key, valueScoreMap);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zcard(String key) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zcard(key);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zcard(key);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zcard(key);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zcount(String key, double min, double max) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zcount(key, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zcount(key, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zcount(key, min, max);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public double zincrby(String key, double score, String member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zincrby(key, score, member);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zincrby(key, score, member);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zincrby(key, score, member);
            }
            return 0.0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrange(key, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrange(key, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrange(key, start, end);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Map<String, Double> zrangeWithScoresToMap(String key, long start, long end) {
        Set<Tuple> tuples = zrangeWithScores(key, start, end);
        Map<String, Double> result = new HashMap<>();
        if (tuples != null) {
            for (Tuple tuple : tuples) {
                result.put(tuple.getElement(), tuple.getScore());
            }
        }
        return result;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeWithScores(key, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeWithScores(key, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeWithScores(key, start, end);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeByScore(key, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeByScore(key, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeByScore(key, min, max);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeByScore(key, min, max, offset, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeByScore(key, min, max, offset, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeByScore(key, min, max, offset, count);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeByScoreWithScores(key, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeByScoreWithScores(key, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeByScoreWithScores(key, min, max);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Map<String, Double> zrangeByScoreWithScoresToMap(String key, double min, double max) {
        Set<Tuple> tuples = zrangeByScoreWithScores(key, min, max);
        Map<String, Double> result = new HashMap<>();
        if (tuples != null) {
            for (Tuple tuple : tuples) {
                result.put(tuple.getElement(), tuple.getScore());
            }
        }
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeByScoreWithScores(key, min, max, offset, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Map<String, Double> zrangeByScoreWithScoresToMap(String key, double min, double max, int offset, int count) {
        Set<Tuple> tuples = zrangeByScoreWithScores(key, min, max, offset, count);
        Map<String, Double> result = new HashMap<>();
        if (tuples != null) {
            for (Tuple tuple : tuples) {
                result.put(tuple.getElement(), tuple.getScore());
            }
        }
        return result;
    }

    @Override
    public long zrank(String key, String member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrank(key, member);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrank(key, member);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrank(key, member);
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zrevrank(String key, String member) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrevrank(key, member);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrevrank(key, member);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrevrank(key, member);
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zrem(String key, String... members) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrem(key, members);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrem(key, members);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrem(key, members);
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zremrangeByRank(String key, int start, int end) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zremrangeByRank(key, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zremrangeByRank(key, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zremrangeByRank(key, start, end);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zremrangeByScore(String key, double min, double max) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zremrangeByScore(key, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zremrangeByScore(key, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zremrangeByScore(key, min, max);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Double zscore(String key, String memeber) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zscore(key, memeber);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zscore(key, memeber);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zscore(key, memeber);
            }
            return 0.0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeByLex(key, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeByLex(key, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeByLex(key, min, max);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrangeByLex(key, min, max, offset, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zrangeByLex(key, min, max, offset, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrangeByLex(key, min, max, offset, count);
            }
            return new HashSet<>();
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zremrangeByLex(String key, String min, String max) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zremrangeByLex(key, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.zremrangeByLex(key, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zremrangeByLex(key, min, max);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zinterstore(String dstKey, String... keys) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.zinterstore(dstKey, keys);
            } else if (jedis instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持zinterstore");
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.zinterstore(dstKey, keys);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long zunionstore(String dstKey, String... keys) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.zunionstore(dstKey, keys);
            } else if (jedis instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持zinterstore");
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.zunionstore(dstKey, keys);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    /*==============================sort set end  =====================================================*/


    /*==============================hash begin  =====================================================*/

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean hmset(String key, Map<String, String> map, int cacheSenconds) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            String result = null;
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                result = jd.hmset(key, map);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                result = shardedJedis.hmset(key, map);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                result = jedisCluster.hmset(key, map);
            }
            if ("ok".equalsIgnoreCase(result)) {
                if (!Objects.equals(0, cacheSenconds)) {
                    expire(key, cacheSenconds * 1000L);
                }
                return true;
            }
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hgetAll(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hgetAll(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hgetAll(key);
            }
            return new HashMap<>();
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hdel(String key, String... fields) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hdel(key, fields);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hdel(key, fields);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hdel(key, fields);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public boolean hexists(String key, String field) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hexists(key, field);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hexists(key, field);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hexists(key, field);
            }
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String hget(String key, String field) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hget(key, field);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hget(key, field);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hget(key, field);
            }
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hset(String key, String field, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hset(key, field, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hset(key, field, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hset(key, field, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hsetnx(String key, String field, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hsetnx(key, field, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hsetnx(key, field, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hsetnx(key, field, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public List<String> hvals(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hvals(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hvals(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hvals(key);
            }
            return new ArrayList<>();
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long hincrby(String key, String field, long value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hincrBy(key, field, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hincrBy(key, field, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hincrBy(key, field, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public Double hincrby(String key, String field, double value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hincrByFloat(key, field, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hincrByFloat(key, field, value);
            } else if (jedis instanceof JedisCluster) {
                throw new UnsupportedOperationException("jedisCluster暂不支持此操作");
            }
            return 0.0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public Set<String> hkeys(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hkeys(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hkeys(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hkeys(key);
            }
            return new HashSet<>();
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hlen(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hlen(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hlen(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hlen(key);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.hmget(key, fields);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.hmget(key, fields);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.hmget(key, fields);
            }
            return new ArrayList<>();
        } finally {
            returnResource(jedis);
        }
    }

    /*==============================hash end=====================================================*/


    /*==============================string begin=====================================================*/
    /**
     * 设置数据，字符串数据
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return boolean
     */
    @Override
    public boolean set(String key, String value, int cacheSeconds) {
        Object jedis = null;
        try {
            String result = null;
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                result = jd.set(key, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                result = shardedJedis.set(key, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                result = jedisCluster.set(key, value);
            }
            if (cacheSeconds != 0) {
                expire(key, cacheSeconds * 1000L);
            }
            return "OK".equals(result);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 获取数据，获取字符串数据
     * @param key 键
     * @return 值
     */
    @Override
    public String get(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            String value = null;
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                value = jd.get(key);
                value = MyStringHelper.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                value = shardedJedis.get(key);
                value = MyStringHelper.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                value = jedisCluster.get(key);
                value = MyStringHelper.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
            return value;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long setnx(String key, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.setnx(key, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.setnx(key, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.setnx(key, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long setrange(String key, long offset, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.setrange(key, offset, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.setrange(key, offset, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.setrange(key, offset, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long append(String key, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.append(key, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.append(key, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.append(key, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long decrBy(String key, long number) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.decrBy(key, number);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.decrBy(key, number);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.decrBy(key, number);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long incrBy(String key, long number) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.incrBy(key, number);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.incrBy(key, number);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.incrBy(key, number);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String getrange(String key, long startOffset, long endOffset) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.getrange(key, startOffset, endOffset);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.getrange(key, startOffset, endOffset);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.getrange(key, startOffset, endOffset);
            }
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String getSet(String key, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.getSet(key, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.getSet(key, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.getSet(key, value);
            }
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public List<String> mget(String... keys) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.mget(keys);
            } else if (jc instanceof ShardedJedis) {
                throw new UnsupportedOperationException("sharedJedis不支持mget操作");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.mget(keys);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public boolean mset(String... keysvalues) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return "OK".equalsIgnoreCase(jedis.mset(keysvalues));
            } else if (jc instanceof ShardedJedis) {
                throw new UnsupportedOperationException("sharedJedis不支持mset操作");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return "OK".equalsIgnoreCase(jedisCluster.mset(keysvalues));
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long strlen(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.strlen(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.strlen(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.strlen(key);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }
    /*==============================string end=====================================================*/


    /*==============================list begin=====================================================*/
    @SuppressWarnings("DuplicatedCode")
    @Override
    public long rpush(String key, String... value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.rpush(key, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.rpush(key, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.rpush(key, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long lpush(String key, String... value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.lpush(key, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.lpush(key, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.lpush(key, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long llen(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.llen(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.llen(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.llen(key);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public boolean lset(String key, int index, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return "OK".equalsIgnoreCase(jd.lset(key, index, value));
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return "OK".equalsIgnoreCase(shardedJedis.lset(key, index, value));
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return "OK".equalsIgnoreCase(jedisCluster.lset(key, index, value));
            }
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long linsert(String key, ListPosition where, String pivot, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.linsert(key, where, pivot, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.linsert(key, where, pivot, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.linsert(key, where, pivot, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String lindex(String key, long index) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.lindex(key, index);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.lindex(key, index);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.lindex(key, index);
            }
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String lpop(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.lpop(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.lpop(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.lpop(key);
            }
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public String rpop(String key) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.rpop(key);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.rpop(key);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.rpop(key);
            }
            return null;
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.lrange(key, start, end);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.lrange(key, start, end);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.lrange(key, start, end);
            }
            return new ArrayList<>();
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public long lrem(String key, int count, String value) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return jd.lrem(key, count, value);
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return shardedJedis.lrem(key, count, value);
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return jedisCluster.lrem(key, count, value);
            }
            return 0;
        } finally {
            returnResource(jedis);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean ltrim(String key, long start, long end) {
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedis instanceof Jedis) {
                Jedis jd = (Jedis) jedis;
                return "OK".equalsIgnoreCase(jd.ltrim(key, start, end));
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                return "OK".equalsIgnoreCase(shardedJedis.ltrim(key, start, end));
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                return "OK".equalsIgnoreCase(jedisCluster.ltrim(key, start, end));
            }
            return false;
        } finally {
            returnResource(jedis);
        }
    }
    /*==============================list end=======================================================*/




}
