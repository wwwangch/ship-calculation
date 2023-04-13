package com.iscas.common.redis.tools.impl;

import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.helper.MyObjectHelper;
import com.iscas.common.redis.tools.helper.MyStringHelper;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.Pool;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Jedis通用客户端接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/19 12:53
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "deprecation", "unchecked"})
public class JedisCommonClient  {
    protected Object jedisPool;
    protected JedisConnection jedisConnection;
    protected static String RESULT_OK = "OK";
    protected static int RESULT_1 = 1;
    protected static String DELAY_QUEUE_DEFUALT_KEY = "delay_queue_default_key_20190806";
    protected static Map<String, Consumer> MAP_DELAY = new ConcurrentHashMap<>();
    protected static Map<String, Boolean> MAP_DELAY_EXECUTE = new ConcurrentHashMap<>();
    /**
     * 获取资源
     * @param tClass class
     * @return T
     */
    public <T> T getResource(Class<T> tClass) throws JedisException {
        Object jedis = null;
        //noinspection CaughtExceptionImmediatelyRethrown
        try {
            if (jedisPool instanceof Pool) {
                jedis = ((Pool)jedisPool).getResource();
            } else if (jedisPool instanceof JedisCluster){
                jedis = jedisPool;
            } else //noinspection ConstantConditions
                if (jedisPool instanceof ShardedJedisPool) {
                jedis = ((ShardedJedisPool) jedisPool).getResource();
            }
        } catch (JedisException e) {
//            returnBrokenResource(null);
            throw e;
        }
        return (T) jedis;
    }

    /**
     * 归还资源
     * @param jedis jedis
     */
    @SuppressWarnings("DuplicatedCode")
    public void returnBrokenResource(Object jedis) {
        if (jedis != null) {
            if (jedis instanceof Jedis) {
                Jedis jedis1 = (Jedis) jedis;
                if (jedis1.isConnected()) {
                    jedis1.close();
                }
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                shardedJedis.close();
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
            }

        }
    }

    /**
     * 释放资源
     * @param jedis jedis
     */
    @SuppressWarnings("DuplicatedCode")
    public void returnResource(Object jedis) {
        if (jedis != null ) {
            if (jedis instanceof  Jedis) {
                Jedis jedis1 = (Jedis) jedis;
                if (jedis1.isConnected()) {
                    jedis1.close();
                }
            } else if (jedis instanceof ShardedJedis) {
                ShardedJedis shardedJedis = (ShardedJedis) jedis;
                shardedJedis.close();
            } else if (jedis instanceof JedisCluster) {
                JedisCluster jedisCluster = (JedisCluster) jedis;
                //集群不需要手动释放连接
            }
        }
    }

    /**
     * 获取byte[]类型Key
     * @param object object
     * @return byte[]
     */
    public static byte[] getBytesKey(Object object) throws IOException {
        if(object instanceof String){
            return MyStringHelper.getBytes((String)object);
        }else{
            return MyObjectHelper.serialize(object);
        }
    }

    /**
     * Object转换byte[]类型
     * @param object object
     * @return byte[]
     */
    public static byte[] toBytes(Object object) throws IOException {
        return MyObjectHelper.serialize(object);
    }

    /**
     * byte[]型转换Object
     * @param bytes bytes
     * @return Object
     */
    public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
        return MyObjectHelper.unserialize(bytes);
    }

    /**
     * 获取分布式锁 返回Null表示获取锁失败
     * @since jdk1.8
     * @date 2018/11/6
     * @param lockName 锁名称
     * @param lockTimeoutInMS 锁超时时间
     * @return java.lang.String 锁标识
     */
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public String acquireLock(String lockName, long lockTimeoutInMS) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            String identifier = UUID.randomUUID().toString();
            String lockKey = "lock:" + lockName;
            String retIdentifier = null;
            String result = null;
            SetParams setParams = new SetParams();
            setParams.px(lockTimeoutInMS);
            setParams.nx();
            if (jc instanceof Jedis) {
                Jedis jedis = (Jedis) jc;
                result = jedis.set(lockKey, identifier, setParams);
            } else if (jc instanceof ShardedJedis) {
                ShardedJedis jedis = (ShardedJedis) jc;
                result = jedis.set(lockKey, identifier, setParams);
            } else if (jc instanceof JedisCluster) {
                JedisCluster jedis = (JedisCluster) jc;
                result = jedis.set(lockKey, identifier, setParams);
            }
            if (RESULT_OK.equals(result)) {
                retIdentifier = identifier;
            }
            return retIdentifier;
        } finally {
            returnResource(jc);
        }
    }

    /**
     * 释放锁
     * @since jdk1.8
     * @date 2018/11/6
     * @param lockName 锁key
     * @param identifier 锁标识
     * @return boolean
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean releaseLock(String lockName, String identifier) {
        Object conn = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            if (identifier == null) {
                return true;
            }
            conn = getResource(Object.class);
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedisCommandsBytesLuaEvalSha(conn, script, Collections.singletonList(lockKey), Collections.singletonList(identifier));
            if (Objects.equals(1, result)) {
                retFlag = true;
            }

        }  finally {
            returnResource(conn);
        }
        return retFlag;
    }

    /**
     *  IP 限流
     * @since jdk1.8
     * @date 2018/11/6
     * @param ip ip
     * @param timeout 规定时间 （秒）
     * @param limit 限制次数
     * @return 是否可以访问
     */
    public boolean accessLimit(String ip, int timeout, int limit) {
        Object conn = null;
        try {
            conn = getResource(Object.class);
            String lua = "local num = redis.call('incr', KEYS[1])\n" +
                    "if tonumber(num) == 1 then\n" +
                    "\tredis.call('expire', KEYS[1], ARGV[1])\n" +
                    "\treturn 1\n" +
                    "elseif tonumber(num) > tonumber(ARGV[2]) then\n" +
                    "\treturn 0\n" +
                    "else \n" +
                    "\treturn 1\n" +
                    "end\n";
            Object result = jedisCommandsBytesLuaEvalSha(conn, lua, Collections.singletonList(ip), Arrays.asList(String.valueOf(timeout),
                    String.valueOf(limit)));
            return "1".equals(result == null ? null : result.toString());
        }  finally {
            returnResource(conn);
        }

    }

    public Object jedisCommandsBytesLuaEvalSha(Object jedisCommands, String lua, List key, List val) {
        if (jedisCommands instanceof Jedis) {
            Jedis jedis = (Jedis) jedisCommands;
            return jedis.evalsha(jedis.scriptLoad(lua), key, val);
        } else if (jedisCommands instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisCommands;
            throw new RuntimeException("ShardedJedis 暂不支持执行Lua脚本");
        } else if (jedisCommands instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisCommands;
            return jedisCluster.evalsha(jedisCluster.scriptLoad(lua, lua), key, val);
        }
        return 0;
    }

    public PipelineBase getPipeline(Object jedisResource) {
        if (jedisResource instanceof Jedis) {
            Jedis jedis = (Jedis) jedisResource;
            return jedis.pipelined();
        } else if (jedisResource instanceof ShardedJedis) {
            ShardedJedis shardedJedis = (ShardedJedis) jedisResource;
            return shardedJedis.pipelined();
        } else if (jedisResource instanceof JedisCluster) {
            JedisCluster jedisCluster = (JedisCluster) jedisResource;
            throw new RuntimeException("JedisCluster 暂不支持pipeline");
        }
        throw new RuntimeException("jedisResource:" + jedisResource.getClass().getSimpleName() + " 为不支持的类型");
    }

    public void pipelineBatch(Consumer<PipelineBase> consumer) {
        Object jc = null;
        try {
            jc = getResource(Object.class);
            PipelineBase pipeline = getPipeline(jc);
            consumer.accept(pipeline);
            if (pipeline instanceof Pipeline) {
                ((Pipeline) pipeline).sync();
            } else if (pipeline instanceof ShardedJedisPipeline) {
                ((ShardedJedisPipeline) pipeline).sync();
            }
        } finally {
            returnResource(jc);
        }
    }


}
