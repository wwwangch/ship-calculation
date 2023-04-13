package com.iscas.common.redis.tools.impl.cluster;

import com.iscas.common.redis.tools.ConfigInfo;
import com.iscas.common.redis.tools.JedisConnection;
import com.iscas.common.redis.tools.RedisInfo;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * jedis-cluster 连接
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/7 8:37
 * @since jdk1.8
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class JedisClusterConnection implements JedisConnection {
    private ConfigInfo configInfo;
    private volatile JedisCluster jedisCluster = null;
    private volatile RedisClusterClient lettuceClusterClient = null;

    @Override
    public Object getPool() {
        if(jedisCluster == null){
            synchronized (JedisClusterConnection.class){
                if(jedisCluster == null){
                    List<RedisInfo> redisInfos = configInfo.getRedisInfos();
                    if (redisInfos == null || redisInfos.size() == 0) {
                        throw new RuntimeException("redisInfos不能为空");
                    }
                    Set<HostAndPort> hostAndPortSet = redisInfos.stream()
                            .map(redisInfo -> new HostAndPort(redisInfo.getHost(), redisInfo.getPort())).collect(Collectors.toSet());
                    jedisCluster = new JedisCluster(hostAndPortSet, configInfo.getClusterTimeout(), configInfo.getClusterSoTimeout(),
                            configInfo.getClusterMaxAttempts(), configInfo.getClusterPassword(), new GenericObjectPoolConfig());
                }
            }
        }
        return jedisCluster;
    }

    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    public RedisClusterClient getLettuceClusterClient() {
        if(lettuceClusterClient == null){
            synchronized (JedisClusterConnection.class){
                if(lettuceClusterClient == null){
                    List<RedisInfo> redisInfos = configInfo.getRedisInfos();
                    List<RedisURI> redisURIS = new ArrayList<>();
                    for (RedisInfo redisInfo : redisInfos) {
//                        RedisURI.builder().withHost("redis://" + redisInfo.getHost())
                        RedisURI.Builder builder = RedisURI.builder().withHost(redisInfo.getHost())
                                .withPort(redisInfo.getPort())
                                .withTimeout(Duration.ofMillis(redisInfo.getTimeout()));
                        if (redisInfo.getPwd() != null) {
                            builder.withPassword(redisInfo.getPwd().toCharArray());
                        }
                        RedisURI redisURI = builder.build();
                        redisURIS.add(redisURI);
                    }
                    lettuceClusterClient = RedisClusterClient.create(redisURIS);
                }
            }
        }
        return lettuceClusterClient;
    }

    @Override
    public void initConfig(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    @Override
    public void close() {

    }
}
