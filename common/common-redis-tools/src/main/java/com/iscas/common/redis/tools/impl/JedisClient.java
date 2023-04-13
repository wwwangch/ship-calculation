package com.iscas.common.redis.tools.impl;


import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.IJedisClient;
import com.iscas.common.redis.tools.JedisConnection;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.*;

/**
 * JedisClient
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/5 14:46
 * @since jdk1.8
 */
@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
public class JedisClient extends JedisCommonClient implements IJedisClient {

    public JedisClient(JedisConnection jedisConnection, ConfigInfo configInfo) {
        this.jedisConnection = jedisConnection;
        jedisConnection.initConfig(configInfo);
        jedisPool = jedisConnection.getPool();
    }

    /*=============================通用 begin=========================================*/

    /**
     * 删除缓存
     *
     * @param key 键
     * @return long
     */
    @Override
    public long del(String key) throws IOException {
        long result = 0;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedisCommandsBytesExists(jedis, getBytesKey(key))) {
                result = jedisCommandsBytesDel(jedis, getBytesKey(key));
            }
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key 键
     * @return boolean
     */
    @Override
    public boolean exists(String key) throws IOException {
        boolean result;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            result = jedisCommandsBytesExists(jedis, getBytesKey(key));
        } finally {
            returnResource(jedis);
        }
        return result;

    }

    private boolean jedisCommandsBytesExists(Object jedisCommands, byte[] bytesKey) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.exists(bytesKey);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            return shardedJedis.exists(bytesKey);
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            jedisCluster.exists(bytesKey);
        }
        return false;
    }

    private byte[] jedisCommandsBytesGet(Object jedisCommands, byte[] bytesKey) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.get(bytesKey);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            return shardedJedis.get(bytesKey);
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            jedisCluster.get(bytesKey);
        }
        return null;
    }

    private String jedisCommandsBytesSet(Object jedisCommands, byte[] bytesKey, byte[] bytesValue) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.set(bytesKey, bytesValue);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            return shardedJedis.set(bytesKey, bytesValue);
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            jedisCluster.set(bytesKey, bytesValue);
        }
        return null;
    }

    private Set<byte[]> jedisCommandsBytesSmembers(Object jedisCommands, byte[] bytesKey) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.smembers(bytesKey);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            return shardedJedis.smembers(bytesKey);
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            jedisCluster.smembers(bytesKey);
        }
        return null;
    }

    @SuppressWarnings("DuplicatedCode")
    private long jedisCommandsBytesSadd(Object jedisCommands, byte[] bytesKey, byte[][] bytesValue) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.sadd(bytesKey, bytesValue);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            return shardedJedis.sadd(bytesKey, bytesValue);
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            return jedisCluster.sadd(bytesKey, bytesValue);
        }
        return 0;
    }

    private long jedisCommandsBytesDel(Object jedisCommands, byte[] bytesKey) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.del(bytesKey);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            return shardedJedis.del(bytesKey);
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            return jedisCluster.del(bytesKey);
        }
        return 0;
    }

    @SuppressWarnings("DuplicatedCode")
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

    @Override
    public void expire(String key, long milliseconds) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (milliseconds <= 0) {
                return;
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                jedis.pexpire(getBytesKey(key), milliseconds);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                shardedJedis.pexpire(getBytesKey(key), milliseconds);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                jedisCluster.pexpire(getBytesKey(key), milliseconds);
            }
        } finally {
            returnResource(jc);
        }
    }
    /*=============================通用 end==========================================*/

    /*========================================set begin========================================================*/

    /**
     * 设置Set，值为任意对象类型
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return long
     */
    @Override
    public long sadd(String key, Set value, int cacheSeconds) throws IOException {
        long result;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            byte[][] bytes = new byte[value.size()][];
            int i = 0;
            for (Object obj : value) {
                bytes[i++] = toBytes(obj);
            }
            result = jedisCommandsBytesSadd(jedis, getBytesKey(key), bytes);
            if (cacheSeconds != 0) {
                expire(key, cacheSeconds * 1000L);
            }
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set中追加值，类型为对象
     *
     * @param key   键
     * @param value 值
     * @return long
     */
    @Override
    public long sadd(String key, Object... value) throws IOException {
        long result;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            byte[][] bytes = new byte[value.length][];
            int i = 0;
            for (Object obj : value) {
                bytes[i++] = toBytes(obj);
            }
            result = jedisCommandsBytesSadd(jedis, getBytesKey(key), bytes);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    @Override
    public long scard(String key) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.scard(getBytesKey(key));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.scard(getBytesKey(key));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.scard(getBytesKey(key));
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public <T> Set<T> sdiff(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            //将key转为byte，与存入时对应
            byte[][] byteKeys = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                byteKeys[i] = getBytesKey(keys[i]);
            }
            Set<byte[]> sdiffBytes = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                sdiffBytes = jedis.sdiff(byteKeys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sdiff");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                sdiffBytes = jedisCluster.sdiff(byteKeys);
            }
            //转化结果
            Set<T> result = new HashSet<>();
            if (sdiffBytes != null) {
                for (byte[] sdiffByte : sdiffBytes) {
                    result.add((T) toObject(sdiffByte));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long sdiffStore(String newkey, String... keys) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            //将key转为byte，与存入时对应
            byte[][] byteKeys = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                byteKeys[i] = getBytesKey(keys[i]);
            }
            byte[] bytesNewKey = getBytesKey(newkey);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sdiffstore(bytesNewKey, byteKeys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sdiff");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sdiffstore(bytesNewKey, byteKeys);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public <T> Set<T> sinter(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            //将key转为byte，与存入时对应
            byte[][] byteKeys = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                byteKeys[i] = getBytesKey(keys[i]);
            }
            Set<byte[]> sinnerBytes = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                sinnerBytes = jedis.sinter(byteKeys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sinter");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                sinnerBytes = jedisCluster.sinter(byteKeys);
            }
            //转化结果
            Set<T> result = new HashSet<>();
            if (sinnerBytes != null) {
                for (byte[] sdiffByte : sinnerBytes) {
                    result.add((T) toObject(sdiffByte));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long sinterStore(String newKey, String... keys) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            //将key转为byte，与存入时对应
            byte[][] byteKeys = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                byteKeys[i] = getBytesKey(keys[i]);
            }
            byte[] bytesNewKey = getBytesKey(newKey);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sinterstore(bytesNewKey, byteKeys);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sinter");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sinterstore(bytesNewKey, byteKeys);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean sismember(String key, Object member) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] bytes = toBytes(member);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.sismember(bytesKey, bytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.sismember(bytesKey, bytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.sismember(bytesKey, bytes);
            }
            throw new RuntimeException("不支持的JedisCommands类型:" + jc);
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> smembers(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Set<T> value = null;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedisCommandsBytesExists(jedis, getBytesKey(key))) {
                value = new HashSet<>();
                Set<byte[]> set = jedisCommandsBytesSmembers(jedis, getBytesKey(key));
                assert set != null;
                for (byte[] bs : set) {
                    value.add((T) toObject(bs));
                }
            }
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long smove(String srckey, String dstkey, Object member) throws IOException {
        Object jc = null;
        try {
            byte[] srcKeyBytes = getBytesKey(srckey);
            byte[] dstKeyBytes = getBytesKey(dstkey);
            byte[] memberBytes = toBytes(member);
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.smove(srcKeyBytes, dstKeyBytes, memberBytes);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持smove");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.smove(srcKeyBytes, dstKeyBytes, memberBytes);
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> T spop(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byte[] result = jedis.spop(bytesKey);
                return result == null ? null : (T) toObject(result);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byte[] result = shardedJedis.spop(bytesKey);
                return result == null ? null : (T) toObject(result);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byte[] result = jedisCluster.spop(bytesKey);
                return result == null ? null : (T) toObject(result);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> spop(Class<T> tClass, String key, long count) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            Set<byte[]> bytesResult = null;
            Set<T> result = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytesResult = jedis.spop(bytesKey, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytesResult = shardedJedis.spop(bytesKey, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytesResult = jedisCluster.spop(bytesKey, count);
            }
            if (bytesResult != null) {
                result = new HashSet<>();
                for (byte[] bytes : bytesResult) {
                    result.add((T) toObject(bytes));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long srem(String key, Object... member) throws IOException {
        Object jc = null;
        try {
            byte[] keyBytes = getBytesKey(key);
            byte[][] memberBytes = new byte[member.length][];
            for (int i = 0; i < member.length; i++) {
                byte[] oBytes = toBytes(member[i]);
                memberBytes[i] = oBytes;
            }

            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.srem(keyBytes, memberBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.srem(keyBytes, memberBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.srem(keyBytes, memberBytes);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> sunion(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[][] bytesKey = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                bytesKey[i] = getBytesKey(keys[i]);
            }
            Set<T> set = new HashSet<>();
            Set<byte[]> resultBytes = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                resultBytes = jedis.sunion(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持sunion");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                resultBytes = jedisCluster.sunion(bytesKey);
            }
            if (resultBytes != null) {
                for (byte[] resultByte : resultBytes) {
                    set.add((T) toObject(resultByte));
                }
            }
            return set;
        } finally {
            returnResource(jc);
        }

    }

    /*========================================set end  ========================================================*/

    /*===========================sort set begin========================================*/

    @Override
    public long zadd(String key, double score, Object member) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zadd(getBytesKey(key), score, toBytes(member));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zadd(getBytesKey(key), score, toBytes(member));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zadd(getBytesKey(key), score, toBytes(member));
            }
            return 0L;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zadd(String key, Map<?, Double> valueScoreMap, int cacheSeconds) throws IOException {
        long result = zadd(key, valueScoreMap);
        if (result > 0) {
            expire(key, cacheSeconds * 1000L);
        }
        return result;
    }

    @Override
    public long zadd(String key, Map<?, Double> valueScoreMap) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Map<byte[], Double> members = new HashMap<>(16);
            for (Map.Entry<?, Double> entry : valueScoreMap.entrySet()) {
                members.put(toBytes(entry.getKey()), entry.getValue());
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zadd(getBytesKey(key), members);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zadd(getBytesKey(key), members);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zadd(getBytesKey(key), members);
            }
            return 0L;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zcard(String key) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zcard(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zcard(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zcard(bytesKey);
            }
            return 0L;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zcount(String key, double min, double max) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zcount(bytesKey, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zcount(bytesKey, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zcount(bytesKey, min, max);
            }
            return 0L;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public double zincrby(String key, double score, Object member) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zincrby(bytesKey, score, toBytes(member));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zincrby(bytesKey, score, toBytes(member));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zincrby(bytesKey, score, toBytes(member));
            }
            return 0L;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> zrange(Class<T> tClass, String key, long start, long end) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Set<byte[]> bytesResult = null;
            Set<T> result = new HashSet<>();
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytesResult = jedis.zrange(bytesKey, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytesResult = shardedJedis.zrange(bytesKey, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytesResult = jedisCluster.zrange(bytesKey, start, end);
            }
            if (bytesResult != null) {
                for (byte[] bytes : bytesResult) {
                    result.add((T) toObject(bytes));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Map<T, Double> zrangeWithScoresToMap(Class<T> tClass, String key, long start, long end) throws IOException, ClassNotFoundException {
        Set<Tuple> tuples = zrangeWithScores(key, start, end);
        Map<T, Double> result = new HashMap<>(8);
        if (tuples != null) {
            for (Tuple tuple : tuples) {
                Object o = toObject(tuple.getBinaryElement());
                result.put((T) o, tuple.getScore());
            }
        }
        return result;
    }

    @Override
    public Set<Tuple> zrangeWithScores(String key, long start, long end) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Set<Tuple> result = null;
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                result = jedis.zrangeWithScores(bytesKey, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                result = shardedJedis.zrangeWithScores(bytesKey, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                result = jedisCluster.zrangeWithScores(bytesKey, start, end);
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> zrangeByScore(Class<T> tClass, String key, double min, double max) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Set<T> result = new HashSet<>();
            Set<byte[]> bytes = null;
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytes = jedis.zrangeByScore(bytesKey, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytes = shardedJedis.zrangeByScore(bytesKey, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytes = jedisCluster.zrangeByScore(bytesKey, min, max);
            }
            if (bytes != null) {
                for (byte[] aByte : bytes) {
                    result.add((T) toObject(aByte));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Set<T> zrangeByScore(Class<T> tClass, String key, double min, double max, int offset, int count) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Set<T> result = new HashSet<>();
            Set<byte[]> bytes = null;
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytes = jedis.zrangeByScore(bytesKey, min, max, offset, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytes = shardedJedis.zrangeByScore(bytesKey, min, max, offset, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytes = jedisCluster.zrangeByScore(bytesKey, min, max, offset, count);
            }
            if (bytes != null) {
                for (byte[] aByte : bytes) {
                    result.add((T) toObject(aByte));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Set<Tuple> tuples = null;
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                tuples = jedis.zrangeByScoreWithScores(bytesKey, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                tuples = shardedJedis.zrangeByScoreWithScores(bytesKey, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                tuples = jedisCluster.zrangeByScoreWithScores(bytesKey, min, max);
            }
            return tuples;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Map<T, Double> zrangeByScoreWithScoresToMap(Class<T> tClass, String key, double min, double max) throws IOException, ClassNotFoundException {
        Map<T, Double> result = new HashMap<>();
        Set<Tuple> tuples = zrangeByScoreWithScores(key, min, max);
        if (tuples != null) {
            for (Tuple tuple : tuples) {
                result.put((T) toObject(tuple.getBinaryElement()), tuple.getScore());
            }
        }
        return result;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            Set<Tuple> tuples = null;
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                tuples = jedis.zrangeByScoreWithScores(bytesKey, min, max, offset, count);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                tuples = shardedJedis.zrangeByScoreWithScores(bytesKey, min, max, offset, count);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                tuples = jedisCluster.zrangeByScoreWithScores(bytesKey, min, max, offset, count);
            }
            return tuples;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> Map<T, Double> zrangeByScoreWithScoresToMap(Class<T> tClass, String key, double min, double max, int offset, int count) throws IOException, ClassNotFoundException {
        Map<T, Double> result = new HashMap<>();
        Set<Tuple> tuples = zrangeByScoreWithScores(key, min, max, offset, count);
        if (tuples != null) {
            for (Tuple tuple : tuples) {
                result.put((T) toObject(tuple.getBinaryElement()), tuple.getScore());
            }
        }
        return result;
    }

    @Override
    public long zrank(String key, Object member) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrank(bytesKey, toBytes(member));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zrank(bytesKey, toBytes(member));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrank(bytesKey, toBytes(member));
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zrevrank(String key, Object member) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrevrank(bytesKey, toBytes(member));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zrevrank(bytesKey, toBytes(member));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrevrank(bytesKey, toBytes(member));
            }
            return -1;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zrem(String key, Object... members) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[][] memberBytes = new byte[members.length][];
            for (int i = 0; i < members.length; i++) {
                memberBytes[i] = toBytes(members[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zrem(bytesKey, memberBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zrem(bytesKey, memberBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zrem(bytesKey, memberBytes);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zremrangeByRank(String key, int start, int end) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zremrangeByRank(bytesKey, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zremrangeByRank(bytesKey, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zremrangeByRank(bytesKey, start, end);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long zremrangeByScore(String key, double min, double max) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zremrangeByScore(bytesKey, min, max);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zremrangeByScore(bytesKey, min, max);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zremrangeByScore(bytesKey, min, max);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public Double zscore(String key, Object memeber) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zscore(bytesKey, toBytes(memeber));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.zscore(bytesKey, toBytes(memeber));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zscore(bytesKey, toBytes(memeber));
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long zinterstore(String dstKey, String... keys) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] dstBytesKey = getBytesKey(dstKey);
            byte[][] bytesKey = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                bytesKey[i] = getBytesKey(keys[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zinterstore(dstBytesKey, bytesKey);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持zinterstore");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zinterstore(dstBytesKey, bytesKey);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long zunionstore(String dstKey, String... keys) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] dstBytesKey = getBytesKey(dstKey);
            byte[][] bytesKey = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                bytesKey[i] = getBytesKey(keys[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.zunionstore(dstBytesKey, bytesKey);
            } else if (jc instanceof ShardedJedis) {
                throw new RuntimeException("ShardedJedis 暂不支持zinterstore");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.zunionstore(dstBytesKey, bytesKey);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    /*===========================sort set end==========================================*/


    /*===========================hash begin==========================================*/

    @SuppressWarnings("DuplicatedCode")
    @Override
    public boolean hmset(String key, Map map, int cacheSenconds) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            Map<byte[], byte[]> bytesMap = new HashMap<>();
            for (Object o : map.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                bytesMap.put(toBytes(entry.getKey()), toBytes(entry.getValue()));
            }
            String result = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                result = jedis.hmset(bytesKey, bytesMap);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                result = shardedJedis.hmset(bytesKey, bytesMap);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                result = jedisCluster.hmset(bytesKey, bytesMap);
            }
            if (RESULT_OK.equalsIgnoreCase(result)) {
                if (!Objects.equals(0, cacheSenconds)) {
                    expire(key, cacheSenconds * 1000L);
                }
                return true;
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <K, V> Map<K, V> hgetAll(Class<K> keyClass, Class<V> valClass, String key) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            Map<byte[], byte[]> result = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                result = jedis.hgetAll(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                result = shardedJedis.hgetAll(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                result = jedisCluster.hgetAll(bytesKey);
            }
            Map<K, V> mapRes = new HashMap<>();
            if (result != null) {
                for (Map.Entry<byte[], byte[]> entry : result.entrySet()) {
                    mapRes.put((K) toObject(entry.getKey()), (V) toObject(entry.getValue()));
                }
            }
            return mapRes;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hdel(String key, Object... fields) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[][] fieldsBytes = new byte[fields.length][];
            for (int i = 0; i < fields.length; i++) {
                fieldsBytes[i] = toBytes(fields[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.hdel(bytesKey, fieldsBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.hdel(bytesKey, fieldsBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.hdel(bytesKey, fieldsBytes);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public boolean hexists(String key, Object field) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.hexists(bytesKey, toBytes(field));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.hexists(bytesKey, toBytes(field));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.hexists(bytesKey, toBytes(field));
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> T hget(Class<T> tClass, String key, String field) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] fieldBytes = toBytes(field);
            byte[] byteResult = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteResult = jedis.hget(bytesKey, fieldBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byteResult = shardedJedis.hget(bytesKey, fieldBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteResult = jedisCluster.hget(bytesKey, fieldBytes);
            }
            if (byteResult != null) {
                return (T) toObject(byteResult);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hset(String key, Object field, Object value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] fieldBytes = toBytes(field);
            byte[] valueBytes = toBytes(value);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.hset(bytesKey, fieldBytes, valueBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.hset(bytesKey, fieldBytes, valueBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.hset(bytesKey, fieldBytes, valueBytes);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hsetnx(String key, Object field, Object value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] fieldBytes = toBytes(field);
            byte[] valueBytes = toBytes(value);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.hsetnx(bytesKey, fieldBytes, valueBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.hsetnx(bytesKey, fieldBytes, valueBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.hsetnx(bytesKey, fieldBytes, valueBytes);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> List<T> hvals(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            Collection<byte[]> byteResult = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteResult = jedis.hvals(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byteResult = shardedJedis.hvals(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteResult = jedisCluster.hvals(bytesKey);
            }
            List<T> result = new ArrayList<>();
            if (byteResult != null) {
                for (byte[] bytes : byteResult) {
                    result.add((T) toObject(bytes));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long hincrby(String key, String field, long value) {
        throw new UnsupportedOperationException("redis暂不支持此操作,请使用IJedisStrClient中对应的函数");
    }

    @Override
    public Double hincrby(String key, String field, double value) {
        throw new UnsupportedOperationException("redis暂不支持此操作,请使用IJedisStrClient中对应的函数");
    }

    @Override
    public <T> Set<T> hkeys(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            Set<byte[]> byteResult = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteResult = jedis.hkeys(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byteResult = shardedJedis.hkeys(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteResult = jedisCluster.hkeys(bytesKey);
            }
            Set<T> result = new HashSet<>();
            if (byteResult != null) {
                for (byte[] bytes : byteResult) {
                    result.add((T) toObject(bytes));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long hlen(String key) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.hlen(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.hlen(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.hlen(bytesKey);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> List<T> hmget(Class<T> tClass, String key, Object... fields) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            List<byte[]> byteResult = null;
            byte[][] fieldBytes = new byte[fields.length][];
            for (int i = 0; i < fields.length; i++) {
                fieldBytes[i] = toBytes(fields[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteResult = jedis.hmget(bytesKey, fieldBytes);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byteResult = shardedJedis.hmget(bytesKey, fieldBytes);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteResult = jedisCluster.hmget(bytesKey, fieldBytes);
            }
            List<T> result = new ArrayList<>();
            if (byteResult != null) {
                for (byte[] bytes : byteResult) {
                    result.add((T) toObject(bytes));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    /*===========================hash end==========================================*/

    /*===========================string begin==========================================*/

    /**
     * 设置数据，对象数据，序列化后存入redis
     *
     * @param key          键
     * @param value        值
     * @param cacheSeconds 超时时间，0为不超时
     * @return boolean
     */
    @Override
    public boolean set(String key, Object value, int cacheSeconds) throws IOException {
        String result;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            result = jedisCommandsBytesSet(jedis, getBytesKey(key), toBytes(value));
            if (cacheSeconds != 0) {
                expire(key, cacheSeconds * 1000L);
            }
            return "OK".equals(result);
        } finally {
            returnResource(jedis);
        }
    }

    @Override
    public <T> T get(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Object value = null;
        Object jedis = null;
        try {
            jedis = getResource(Object.class);
            if (jedisCommandsBytesExists(jedis, getBytesKey(key))) {
                value = toObject(jedisCommandsBytesGet(jedis, getBytesKey(key)));
            }
        } finally {
            returnResource(jedis);
        }
        return (T) value;
    }

    @Override
    public long setnx(String key, Object value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.setnx(bytesKey, toBytes(value));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.setnx(bytesKey, toBytes(value));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.setnx(bytesKey, toBytes(value));
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long setrange(String key, long offset, Object value) {
        throw new UnsupportedOperationException("redis暂不支持此setrange操作,请使用IJedisStrClient中对应的函数");
    }

    @Override
    public long append(String key, String value) {
        throw new UnsupportedOperationException("redis暂不支持此append操作,请使用IJedisStrClient中对应的函数");

    }

    @Override
    public long decrBy(String key, long number) {
        throw new UnsupportedOperationException("redis暂不支持此decrBy操作,请使用IJedisStrClient中对应的函数");
    }

    @Override
    public long incrBy(String key, long number) {
        throw new UnsupportedOperationException("redis暂不支持此incrBy操作,请使用IJedisStrClient中对应的函数");
    }

    @Override
    public <T> T getrange(Class<T> tClass, String key, long startOffset, long endOffset) {
        throw new UnsupportedOperationException("redis暂不支持此getrange操作,请使用IJedisStrClient中对应的函数");
    }

    @Override
    public <T> T getSet(Class<T> tClass, String key, T value) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] byteRes = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteRes = jedis.getSet(bytesKey, toBytes(value));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byteRes = shardedJedis.getSet(bytesKey, toBytes(value));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteRes = jedisCluster.getSet(bytesKey, toBytes(value));
            }
            if (byteRes != null) {
                return (T) toObject(byteRes);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> List<T> mget(Class<T> tClass, String... keys) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[][] bytesKeys = new byte[keys.length][];
            for (int i = 0; i < keys.length; i++) {
                bytesKeys[i] = getBytesKey(keys[i]);
            }
            List<byte[]> byteRes = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteRes = jedis.mget(bytesKeys);
            } else if (jc instanceof ShardedJedis) {
                throw new UnsupportedOperationException("sharedJedis不支持mget操作");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteRes = jedisCluster.mget(bytesKeys);
            }
            List<T> result = new ArrayList<>();
            if (byteRes != null) {
                for (byte[] byteRe : byteRes) {
                    result.add((T) toObject(byteRe));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public boolean mset(Object... keysvalues) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[][] bytesKeys = new byte[keysvalues.length][];
            for (int i = 0; i < keysvalues.length; i++) {
                if (i % 2 == 0) {
                    bytesKeys[i] = getBytesKey(keysvalues[i]);
                } else {
                    bytesKeys[i] = toBytes(keysvalues[i]);
                }
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return RESULT_OK.equalsIgnoreCase(jedis.mset(bytesKeys));
            } else if (jc instanceof ShardedJedis) {
                throw new UnsupportedOperationException("sharedJedis不支持mset操作");
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return RESULT_OK.equalsIgnoreCase(jedisCluster.mset(bytesKeys));
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long strlen(String key) throws IOException {
        throw new UnsupportedOperationException("redis暂不支持此strlen操作,请使用IJedisStrClient中对应的函数");
    }

    /*===========================string end==========================================*/

    /*===========================list begin==========================================*/

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long rpush(String key, Object... value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[][] bytesValue = new byte[value.length][];
            for (int i = 0; i < value.length; i++) {
                bytesValue[i] = toBytes(value[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.rpush(bytesKey, bytesValue);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.rpush(bytesKey, bytesValue);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.rpush(bytesKey, bytesValue);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long lpush(String key, Object... value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[][] bytesValue = new byte[value.length][];
            for (int i = 0; i < value.length; i++) {
                bytesValue[i] = toBytes(value[i]);
            }
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.lpush(bytesKey, bytesValue);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.lpush(bytesKey, bytesValue);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.lpush(bytesKey, bytesValue);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public long llen(String key) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.llen(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.llen(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.llen(bytesKey);
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public boolean lset(String key, int index, Object value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return "OK".equalsIgnoreCase(jedis.lset(bytesKey, index, toBytes(value)));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return "OK".equalsIgnoreCase(shardedJedis.lset(bytesKey, index, toBytes(value)));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return "OK".equalsIgnoreCase(jedisCluster.lset(bytesKey, index, toBytes(value)));
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long linsert(String key, ListPosition where, Object pivot, Object value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.linsert(bytesKey, where, toBytes(pivot), toBytes(value));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.linsert(bytesKey, where, toBytes(pivot), toBytes(value));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.linsert(bytesKey, where, toBytes(pivot), toBytes(value));
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> T lindex(Class<T> tClass, String key, long index) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] bytesResult = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytesResult = jedis.lindex(bytesKey, index);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytesResult = shardedJedis.lindex(bytesKey, index);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytesResult = jedisCluster.lindex(bytesKey, index);
            }
            if (bytesResult != null) {
                return (T) toObject(bytesResult);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> T lpop(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] bytesResult = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytesResult = jedis.lpop(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytesResult = shardedJedis.lpop(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytesResult = jedisCluster.lpop(bytesKey);
            }
            if (bytesResult != null) {
                return (T) toObject(bytesResult);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> T rpop(Class<T> tClass, String key) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            byte[] bytesResult = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                bytesResult = jedis.rpop(bytesKey);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                bytesResult = shardedJedis.rpop(bytesKey);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                bytesResult = jedisCluster.rpop(bytesKey);
            }
            if (bytesResult != null) {
                return (T) toObject(bytesResult);
            }
            return null;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public <T> List<T> lrange(Class<T> tClass, String key, long start, long end) throws IOException, ClassNotFoundException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            List<byte[]> byteRes = null;
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                byteRes = jedis.lrange(bytesKey, start, end);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                byteRes = shardedJedis.lrange(bytesKey, start, end);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                byteRes = jedisCluster.lrange(bytesKey, start, end);
            }
            List<T> result = new ArrayList<>();
            if (byteRes != null) {
                for (byte[] byteRe : byteRes) {
                    result.add((T) toObject(byteRe));
                }
            }
            return result;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public long lrem(String key, int count, Object value) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return jedis.lrem(bytesKey, count, toBytes(value));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return shardedJedis.lrem(bytesKey, count, toBytes(value));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return jedisCluster.lrem(bytesKey, count, toBytes(value));
            }
            return 0;
        } finally {
            returnResource(jc);
        }
    }

    @Override
    public boolean ltrim(String key, long start, long end) throws IOException {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            byte[] bytesKey = getBytesKey(key);
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                return RESULT_OK.equalsIgnoreCase(jedis.ltrim(bytesKey, start, end));
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jc;
                return RESULT_OK.equalsIgnoreCase(shardedJedis.ltrim(bytesKey, start, end));
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jc;
                return RESULT_OK.equalsIgnoreCase(jedisCluster.ltrim(bytesKey, start, end));
            }
            return false;
        } finally {
            returnResource(jc);
        }
    }

    /*===========================list end============================================*/

}
