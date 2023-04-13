package com.iscas.common.redis.tools;

import com.iscas.common.redis.tools.impl.JedisStrClient;
import com.iscas.common.redis.tools.impl.standalone.JedisStandAloneConnection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 单机redis测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/5 15:36
 * @since jdk1.8
 */
public class JedisClientStrTests {
    private IJedisStrClient jedisClient;
    private int goodsCount = 50;
    @Before
    public void before() {
        JedisConnection jedisConnection = new JedisStandAloneConnection();
        ConfigInfo configInfo = new ConfigInfo();
        configInfo.setMaxIdle(10);
        configInfo.setMaxTotal(50);
        configInfo.setMaxWait(20000);
        RedisInfo redisInfo = new RedisInfo();
        redisInfo.setHost("localhost");
        redisInfo.setPort(6379);
//        redisInfo.setPwd("iscas");
        redisInfo.setTimeout(10000);
        configInfo.setRedisInfos(Arrays.asList(redisInfo));
        jedisClient = new JedisStrClient(jedisConnection, configInfo);

    }

    //集群
//    @Before
//    @Ignore
//    public void before() {
//        JedisClusterConnection jedisConnection = new JedisClusterConnection();
//        ConfigInfo configInfo = new ConfigInfo();
//
//        configInfo.setClusterMaxAttempts(10);
//        configInfo.setClusterPassword("iscas");
//        configInfo.setClusterTimeout(2000);
//        configInfo.setClusterSoTimeout(2000);
//        RedisInfo redisInfo = new RedisInfo("localhost", 6379, 10000, "iscas");
//        configInfo.setRedisInfos(Arrays.asList(redisInfo));
//        jedisClient = new JedisStrClient(jedisConnection, configInfo);
//    }

    //shared
//    @Before
//    @Ignore
//    public void before() {
//        JedisConnection jedisConnection = new JedisShardConnection();
//        ConfigInfo configInfo = new ConfigInfo();
//        configInfo.setMaxIdle(10);
//        configInfo.setMaxTotal(50);
//        configInfo.setMaxWait(20000);
//        RedisInfo redisInfo = new RedisInfo("localhost", 6379, 10000, "iscas");
//        configInfo.setRedisInfos(Arrays.asList(redisInfo));
//        jedisClient = new JedisStrClient(jedisConnection, configInfo);
//    }

    /*==============================通用begin======================================*/
    /**
     * 测试分布式锁
     * */
    @Test
    public void test32() throws InterruptedException {
        //JVM 锁
        final Object lock = new Object();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        Runnable runnable = () -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                //不使用任何锁
//                goodsCount--;

                //使用JVM锁
//                synchronized (lock) {
//                    goodsCount--;
//                }

                //使用redis作分布式锁
                String lockFlag = null;
                while (true) {
                    lockFlag = jedisClient.acquireLock("goodscount", 100000);
                    if (lockFlag != null) {
                        goodsCount--;
                        break;
                    }
                }
                jedisClient.releaseLock("goodscount", lockFlag);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10 ; i++) {
            executorService.submit(runnable);
            countDownLatch.countDown();
        }
//        // 假设一个足够长的时间去验证最后的goodCount
        for (int i = 0; i < 10 ; i++) {
            System.out.println(goodsCount);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    /**
     * 测试分布式限流
     * */
    @Test
    public void test33() {
        for (int i = 0; i < 150 ; i++) {
            boolean flag = jedisClient.accessLimit("localhost", 10, 100);
            System.out.println(flag);
        }

    }

    /**
     * 测试延时队列
     * */
    @Test
    public void test34() {
        jedisClient.putDelayQueue("this is test", 5, TimeUnit.SECONDS, (task)-> {
            System.out.println(task);
        });
    }

    /**
     * 测试设置key的过期时间
     * */
    @Test
    public void test36() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "11111", 0);
            jedisClient.expire("testKey", 5000);
            Assert.assertEquals("11111", jedisClient.get("testKey"));
            TimeUnit.SECONDS.sleep(6);
            Assert.assertNull(jedisClient.get("testKey"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试pipeline
     * */
    @Test
    public void testPipelineBatch() {
        try {
            jedisClient.del("testKey");
            jedisClient.pipelineBatch(pipelineBase -> {
                pipelineBase.set("testKey", "1111");
                pipelineBase.append("testKey", "2222");
            });
            String value = jedisClient.get("testKey");
            Assert.assertEquals("11112222", value);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试pipeline,必须在Cluster模式下才能用
     * */
    @Test
    @Ignore
    public void testPipelineClusterBatch() {
        try {
            jedisClient.del("testKey");
            jedisClient.pipelineClusterBatch(pipeline -> {
                pipeline.set("testKey", "1111");
                pipeline.append("testKey", "2222");
            });
            String value = jedisClient.get("testKey");
            Assert.assertEquals("11112222", value);
        } finally {
            jedisClient.del("testKey");
        }
    }
    /*==============================通用end========================================*/


    /*=====================================测试SET BEGIN===================================================*/
    /**
     * 测试设置集合，数据为字符串
     * */
    @Test
    public void testAdd() throws IOException {
        try {
            jedisClient.del("set1");
            Set<String> set = new HashSet<>();
            set.add("x");
            set.add("y");
            set.add("z");
            set.add("m");
            long result = jedisClient.sadd("set1", set.toArray(new String[0]));
            System.out.println(result);
        } finally {
            jedisClient.del("set1");
        }
    }

    /**
     * 测试集合追加值，数据为字符串
     * */
    @Test
    public void testAdd2()  {
        try {
            jedisClient.del("set1");
            long result = jedisClient.sadd("set1", "x", "p", "q");
            System.out.println(result);
        } finally {
            jedisClient.del("set1");
        }
    }

    /**
     * 测试差集
     * */
    @Test
    public void testSdiff() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "22222", "33333");
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey2", "11111", "22222", "44444");
            Set<String> diffSet = jedisClient.sdiff("testKey", "testKey2");
            diffSet.forEach(System.out::println);
            Assert.assertEquals(1, diffSet.size());
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试差集，存储到新集合中
     * */
    @Test
    public void testSdiffStore() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "22222", "33333");
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey2", "11111", "22222", "44444");
            long result = jedisClient.sdiffStore("newKey", "testKey", "testKey2");
            System.out.println(result);
            Set<String> newSet = jedisClient.smembers("newKey");
            newSet.stream().forEach(System.out::println);
            Assert.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("newKey");
        }
    }

    /**
     * 测试交集
     * */
    @Test
    public void testSinter() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "22222", "33333");
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey2", "11111", "22222", "44444");
            Set<String> result = jedisClient.sinter("testKey", "testKey2");
            result.stream().forEach(System.out::println);
            Assert.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试交集,存入新集合
     * */
    @Test
    public void testSinterStore() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey2", "11111", "44444");
            long result = jedisClient.sinterStore("newKey","testKey", "testKey2");
            Set<String> newSet = jedisClient.smembers("newKey");
            newSet.stream().forEach(System.out::println);
            Assert.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("newKey");
        }
    }

    /**
     * 测试集合中某个元素是否存在
     * */
    @Test
    public void testSismember() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            boolean exists = jedisClient.sismember("testKey", "11111");
            Assert.assertEquals(true, exists);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取集合中所有的元素
     * */
    @Test
    public void testSmembers() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            Set<String> members = jedisClient.smembers("testKey");
            members.stream().forEach(System.out::println);
            Assert.assertEquals(2, members.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从集合中移除成员,放入目标集合
     * */
    @Test
    public void testSmove() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("srcKey");
            jedisClient.del("dstKey");
            jedisClient.sadd("srcKey", "11111", "33333");
            jedisClient.sadd("dstKey", "2222", "5555");
            jedisClient.smove("srcKey", "dstKey", "11111");
            Set<String> srcSet = jedisClient.smembers("srcKey");
            Set<String> dstSet = jedisClient.smembers("dstKey");
            srcSet.forEach(System.out::println);
            dstSet.forEach(System.out::println);
            Assert.assertEquals(1, srcSet.size());
            Assert.assertEquals(3, dstSet.size());
        } finally {
            jedisClient.del("srcKey");
            jedisClient.del("dstKey");
        }
    }

    /**
     * 测试从集合中随机移除成员并返回
     * */
    @Test
    public void testScard() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            String data = jedisClient.spop("testKey");
            System.out.println(data);
            Assert.assertEquals(1, jedisClient.scard("testKey"));
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从集合中随机移除成员多个并返回
     * */
    @Test
    public void testSpop() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            Set<String> datas = jedisClient.spop("testKey", 3);
            datas.forEach(System.out::println);
            Assert.assertEquals(2, datas.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从集合中删除成员
     * */
    @Test
    public void testSrem() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            long result = jedisClient.srem("testKey", "11111", "44444");
            Set<String> set = jedisClient.smembers("testKey");
            set.forEach(System.out::println);
            Assert.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试去并集
     * */
    @Test
    public void testSunion() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey", "1", "2", "3");
            jedisClient.sadd("testKey2", "1", "2", "3", "7");
            Set<String> sunionSet = jedisClient.sunion("testKey", "testKey2");
            sunionSet.forEach(System.out::println);
            Assert.assertEquals(4, sunionSet.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /*=====================================测试SET END  ===================================================*/

    /*=============================sort set begin======================================*/
    /**
     * 测试向zset添加一个元素
     * */
    @Test
    public void testZadd() {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.zadd("testKey", 1, "xxxxx");
            Assert.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试向zset添加元素，带超时时间
     * */
    @Test
    public void testZadd2() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            long result = jedisClient.zadd("testKey", memebers, 20);
            Assert.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试向zset添加元素，不带超时时间
     * */
    @Test
    public void testZadd3() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            long result = jedisClient.zadd("testKey", memebers);
            Assert.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset中元素的个数
     * */
    @Test
    public void testZcard() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zcard("testKey");
            Assert.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset中制定权重区间内的元素的个数
     * */
    @Test
    public void testZcount() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zcount("testKey", 1.0, 1.6);
            Assert.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试为zset中某个元素增加权重
     * */
    @Test
    public void testZincrby() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            double result = jedisClient.zincrby("testKey", 3, "2");
            Assert.assertEquals(5.0, result, 1);
            //如果元素不存在，增加这个元素
            double result2 = jedisClient.zincrby("testKey", 3, "4");
            System.out.println(result2);
            System.out.println(jedisClient.zcard("testKey"));
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset制定范围的元素
     * */
    @Test
    public void testZrange() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> set1 = jedisClient.zrange("testKey", 0, -1);
            set1.forEach(System.out::println);
            //如果元素不存在，增加这个元素
            Set<String> set2 = jedisClient.zrange("testKey", 0, 0);
            Assert.assertEquals("1", set2.iterator().next());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset制定范围的元素,并附带权重
     * */
    @Test
    public void testZrangeWithScores() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Set<Tuple> tuples = jedisClient.zrangeWithScores("testKey", 0, -1);
            tuples.forEach(tuple -> {
                System.out.println(tuple.getElement());
                System.out.println(tuple.getScore());
            });
            Assert.assertEquals(2, tuples.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset制定范围的元素,并附带权重,返回Map
     * */
    @Test
    public void testZrangeWithScoresToMap() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Map<String, Double> map = jedisClient.zrangeWithScoresToMap("testKey", 0, -1);
            map.entrySet().forEach(entry -> {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            });
            Assert.assertEquals(2, map.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素
     * */
    @Test
    public void testZrangeByScore() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result = jedisClient.zrangeByScore("testKey", 1.0, 1.7);
            result.forEach(System.out::println);
            Assert.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素, 带偏移量
     * */
    @Test
    public void testZrangeByScore2() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result = jedisClient.zrangeByScore("testKey", 1.0, 2, 1, 2);
            result.forEach(System.out::println);
            Assert.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重
     * */
    @Test
    public void testZrangeByScoreWithScores() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<Tuple> result = jedisClient.zrangeByScoreWithScores("testKey", 1.0, 2.0);
            result.forEach(tuple -> {
                System.out.println(tuple.getElement());
                System.out.println(tuple.getScore());
            });
            Assert.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重, 返回Map
     * */
    @Test
    public void testZrangeByScoreWithScoresToMap() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Map<String, Double> result = jedisClient.zrangeByScoreWithScoresToMap("testKey", 1.0, 2.0);
            System.out.println(result);
            Assert.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }


    /**
     * 测试按照权重查找zset中元素,返回值附带权重, 带偏移量
     * */
    @Test
    public void testZrangeByScoreWithScores2() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<Tuple> result = jedisClient.zrangeByScoreWithScores("testKey", 1.0, 2.0, 0, 1);
            result.forEach(tuple -> {
                System.out.println(tuple.getElement());
                System.out.println(tuple.getScore());
            });
            Assert.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重, 返回Map，带偏移量
     * */
    @Test
    public void testZrangeByScoreWithScoresToMap2() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Map<String, Double> result = jedisClient.zrangeByScoreWithScoresToMap("testKey", 1.0, 2.0, 0, 1);
            System.out.println(result);
            Assert.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从低到高权重排列，某个元素的位置
     * */
    @Test
    public void testZrank() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zrank("testKey", "2");
            Assert.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从高到低权重排列，某个元素的位置
     * */
    @Test
    public void testZrevrank() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zrevrank("testKey", "2");
            Assert.assertEquals(3, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从zset中删除元素
     * */
    @Test
    public void testZrem() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zrem("testKey", "2", "5");
            Assert.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从zset中删除指定位置的元素
     * */
    @Test
    public void testZremrangeByRank() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zremrangeByRank("testKey", 2, 4);
            Assert.assertEquals(3, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从zset中删除指定群众的元素
     * */
    @Test
    public void testZremrangeByScore() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zremrangeByScore("testKey", 1.0, 3.7);
            Assert.assertEquals(3, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试指定元素在zset中的权重
     * */
    @Test
    public void testZscore() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Double result1 = jedisClient.zscore("testKey", "1");
            Assert.assertEquals(1.0, result1, 1);
            Double result2 = jedisClient.zscore("testKey", "88");
            Assert.assertNull(result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试通过字典区间返回有序集合的成员
     * */
    @Test
    public void testZrangeByLex() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result1 = jedisClient.zrangeByLex("testKey", "-", "+");
            result1.forEach(System.out::println);
            Assert.assertEquals(5, result1.size());

            Set<String> result2 = jedisClient.zrangeByLex("testKey", "[2", "(4");
            result2.forEach(System.out::println);
            Assert.assertEquals(2, result2.size());

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试通过字典区间返回有序集合的成员
     * */
    @Test
    public void testZrangeByLex2() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result1 = jedisClient.zrangeByLex("testKey", "-", "+");
            result1.forEach(System.out::println);
            Assert.assertEquals(5, result1.size());

            Set<String> result2 = jedisClient.zrangeByLex("testKey", "[2", "(4", 0, 1);
            result2.forEach(System.out::println);
            Assert.assertEquals(1, result2.size());

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试通过字典区间删除有序集合的成员
     * */
    @Test
    public void testZremrangeByLex() {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result1 = jedisClient.zrangeByLex("testKey", "-", "+");
            result1.forEach(System.out::println);
            Assert.assertEquals(5, result1.size());

            long count = jedisClient.zremrangeByLex("testKey", "[2", "(4");
            Assert.assertEquals(2, count);

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试zset取交集并存入新集合
     * */
    @Test
    public void testZinterstore() {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);

            Map<String, Double> memebers2 = new HashMap<>();
            memebers2.put("1", 1.0);
            memebers2.put("2", 2.0);
            memebers2.put("3", 3.0);
            memebers2.put("4", 4.0);
            memebers2.put("6", 6.0);
            jedisClient.zadd("testKey2", memebers2);
            long result = jedisClient.zinterstore("testKey3", "testKey", "testKey2");
            Set<String> set = jedisClient.zrange("testKey3", 0, -1);
            set.forEach(System.out::println);
            Assert.assertEquals(result, 4);

        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
        }
    }

    /**
     * 测试zset取交集并存入新集合
     * */
    @Test
    public void testZunionstore() {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);

            Map<String, Double> memebers2 = new HashMap<>();
            memebers2.put("1", 1.0);
            memebers2.put("2", 2.0);
            memebers2.put("3", 3.0);
            memebers2.put("4", 4.0);
            memebers2.put("6", 6.0);
            jedisClient.zadd("testKey2", memebers2);
            long result = jedisClient.zunionstore("testKey3", "testKey", "testKey2");
            Set<String> set = jedisClient.zrange("testKey3", 0, -1);
            set.forEach(System.out::println);
            Assert.assertEquals(result, 6);

        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
        }
    }

    /*=============================sort set end========================================*/


    /*=============================hash begin  ========================================*/
    /**
     * 测试存入hash
     * */
    @Test
    public void testHmset() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            boolean result = jedisClient.hmset("testKey", hash, 5);
            Assert.assertEquals(true, result);

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取hash中所有的元素
     * */
    @Test
    public void testHgetAll() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            Map<String, String> result = jedisClient.hgetAll("testKey");
            result.entrySet().forEach(System.out::println);
            Assert.assertEquals(2, result.size());

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从hash中删除元素
     * */
    @Test
    public void testHdel() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            long result = jedisClient.hdel("testKey", "a", "b");
            Assert.assertEquals(2, result);

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hash中是否存在某个key
     * */
    @Test
    public void testHexists() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            boolean hexists = jedisClient.hexists("testKey", "a");
            Assert.assertTrue(hexists);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hash中指定key的值
     * */
    @Test
    public void testHget() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            String result = jedisClient.hget("testKey", "a");
            Assert.assertEquals(result, "1");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试向hash中添加一个键值对
     * */
    @Test
    public void testHset() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            jedisClient.hset("testKey", "c", "3");
            Assert.assertEquals(3, jedisClient.hgetAll("testKey").size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试向hash中添加一个键值对,当这个field不存在时才添加
     * */
    @Test
    public void testHsetnx() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            jedisClient.hsetnx("testKey", "c", "3");
            Assert.assertEquals("3", jedisClient.hget("testKey", "c"));

            jedisClient.hsetnx("testKey", "c", "311");
            Assert.assertNotEquals("311", jedisClient.hget("testKey", "c"));

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取hash的val
     * */
    @Test
    public void testHvals() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            List<String> result = jedisClient.hvals("testKey");
            Assert.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hincrby
     * */
    @Test
    public void testHincrby() {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            jedisClient.hincrby("testKey", "a", 2);
            Map<String, String> result = jedisClient.hgetAll("testKey");
            result.entrySet().forEach(System.out::println);
            Assert.assertEquals("3", jedisClient.hget("testKey", "a"));
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hincrby
     * */
    @Test
    public void testHincrby2() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1.0");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            jedisClient.hincrby("testKey", "a", 2.7);
            Map<String, String> result = jedisClient.hgetAll("testKey");
            result.entrySet().forEach(System.out::println);
            Assert.assertEquals("3.7", jedisClient.hget("testKey", "a"));
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hkeys
     * */
    @Test
    public void testHkeys() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1.0");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            Set<String> set = jedisClient.hkeys("testKey");
            Assert.assertEquals(2, set.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hlen
     * */
    @Test
    public void testHlen() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1.0");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            long result = jedisClient.hlen("testKey");
            Assert.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hmege
     * */
    @Test
    public void testHmget() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1.0");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            List<String> result = jedisClient.hmget("testKey", "a", "b");
            Assert.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }


    /*=============================hash end    ========================================*/


    /*=============================string begin========================================*/

    /**
     * 测试插入一个字符串，无超时时间
     */
    @Test
    public void testSet() {
        try {
            jedisClient.del("testKey");
            boolean result = jedisClient.set("testKey", "value", 0);
            Assert.assertTrue(result);
        } finally {
            jedisClient.del("testKey");
        }

    }

    /**
     * 测试插入一个字符串，有超时时间
     * */
    @Test
    public void testSet2() {
        try {
            jedisClient.del("testKey");
            boolean result = jedisClient.set("testKey", "value", 20);
            Assert.assertTrue(result);
        } finally {
            jedisClient.del("testKey");
        }
    }


    /**
     * 测试获取字符串
     * */
    @Test
    public void testGet() {
        try {
            jedisClient.del("testKey");
            boolean setResult = jedisClient.set("testKey", "value", 0);
            String result = jedisClient.get("testKey");
            Assert.assertEquals(result, "value");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试setnx
     * */
    @Test
    public void testSetnx() {
        try {
            jedisClient.del("testKey");
            long result1 = jedisClient.setnx("testKey", "value");
            Assert.assertEquals(result1, 1L);
            long result2 = jedisClient.setnx("testKey", "value2");
            Assert.assertEquals(result2, 0);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试setrange
     * */
    @Test
    public void testSetrange() {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "123456", 0);
            long result = jedisClient.setrange("testKey", 2, "99999999");
            Assert.assertEquals(result, 10);
            String strRes = jedisClient.get("testKey");
            Assert.assertEquals("1299999999", strRes);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试append
     * */
    @Test
    public void testAppend() {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "123456", 0);
            long len = jedisClient.append("testKey", "abcd");
            Assert.assertEquals(10, len);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试decrBy
     * */
    @Test
    public void testDecrBy() {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "10000", 0);
            long result = jedisClient.decrBy("testKey", 1000);
            Assert.assertEquals(9000, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试incrBy
     * */
    @Test
    public void testIncrBy() {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "10000", 0);
            long result = jedisClient.incrBy("testKey", 1000);
            Assert.assertEquals(11000, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试getrange
     * */
    @Test
    public void testGetRange() {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "10000", 0);
            String result = jedisClient.getrange("testKey", 0, 2);
            Assert.assertEquals("100", result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试getSet
     * */
    @Test
    public void testGetSet() {
        try {
            jedisClient.del("testKey");
            String result1 = jedisClient.getSet("testKey", "10000");
            Assert.assertNull(result1);
            String result2 = jedisClient.getSet("testKey", "123");
            Assert.assertEquals(result2, "10000");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试mget
     * */
    @Test
    public void testMget() {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.set("testKey", "111", 0);
            jedisClient.set("testKey2", "222", 0);
            List<String> result = jedisClient.mget("testKey", "testKey2");
            Assert.assertEquals("111", result.get(0));
            Assert.assertEquals("222", result.get(1));
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试mset
     * */
    @Test
    public void testMset() {
        try {
            jedisClient.del("111");
            jedisClient.del("222");
            jedisClient.mset("111", "a", "222", "b");
            List<String> result = jedisClient.mget("111", "222");
            Assert.assertEquals("a", result.get(0));
            Assert.assertEquals("b", result.get(1));
        } finally {
            jedisClient.del("111");
            jedisClient.del("222");
        }
    }

    /**
     * 测试strlen
     * */
    @Test
    public void testStrlen() {
        try {
            jedisClient.del("testKey");
            jedisClient.set("testKey", "10000", 0);
            long len = jedisClient.strlen("testKey");
            Assert.assertEquals(5, len);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /*=============================string end==========================================*/

    /*=============================list begin==========================================*/
    /**
     * 测试rpush
     * */
    @Test
    public void testRpush() {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.rpush("testKey", "10000", "22222");
            long result2 = jedisClient.rpush("testKey", "aaa", "bbb");
            Assert.assertEquals(4, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试rpush
     * */
    @Test
    public void testLpush() {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.lpush("testKey", "10000", "22222");
            long result2 = jedisClient.lpush("testKey", "aaa", "bbb");
            Assert.assertEquals(4, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试llen
     * */
    @Test
    public void testLlen() {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.rpush("testKey", "10000", "22222");
            long result2 = jedisClient.llen("testKey");
            Assert.assertEquals(2, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lset
     * */
    @Test
    public void testLset() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            boolean result1 = jedisClient.lset("testKey", 1, "dfg");
            Assert.assertTrue(result1);
            boolean result2 = jedisClient.lset("testKey", 0, "zqw");
            Assert.assertTrue(result2);

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试linsert
     * */
    @Test
    public void testLinsert() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            long result = jedisClient.linsert("testKey", ListPosition.AFTER, "10000", "33333");
            Assert.assertEquals(result, 3);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lindex
     * */
    @Test
    public void testLindex() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            String result = jedisClient.lindex("testKey", 1);
            Assert.assertEquals(result, "22222");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lpop
     * */
    @Test
    public void testLpop() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            String result = jedisClient.lpop("testKey");
            Assert.assertEquals(result, "10000");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试rpop
     * */
    @Test
    public void testRpop() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            String result = jedisClient.rpop("testKey");
            Assert.assertEquals(result, "22222");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lrange
     * */
    @Test
    public void testLrange() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222", "444", "adsdb");
            List<String> result = jedisClient.lrange("testKey", 0, 2);
            Assert.assertEquals(result.size(), 3);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lrem
     * */
    @Test
    public void testLrem() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222", "10000", "22222", "10000");
            long result = jedisClient.lrem("testKey", 6, "10000");
            Assert.assertEquals(3, result);
            long result2 = jedisClient.lrem("testKey", -1, "22222");
            Assert.assertEquals(1, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试ltrim
     * */
    @Test
    public void testLtrim() {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222", "10000", "22222", "10000");
            boolean result = jedisClient.ltrim("testKey", 2, 3);
            Assert.assertTrue(result);
            List<String> result2 = jedisClient.lrange("testKey", 0, -1);
            Assert.assertEquals(2, result2.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /*=============================list end============================================*/



}
