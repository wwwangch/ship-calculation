package com.iscas.common.redis.tools;

import com.iscas.common.redis.tools.helper.MyObjectHelper;
import com.iscas.common.redis.tools.impl.JdkNoneRedisClient;
import com.iscas.common.redis.tools.impl.jdk.JdkNoneRedisConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
public class JdkNoneRedisClientTests {
    private static IJedisClient jedisClient;
    private int goodsCount = 50;
    @BeforeAll
    public static void before() {
        JdkNoneRedisConnection jdkNoneRedisConnection = new JdkNoneRedisConnection();
        jedisClient = new JdkNoneRedisClient(jdkNoneRedisConnection);

    }


    /*======================================通用 begin==============================================================*/
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
     */
    @Test
    @Disabled
    public void test33() {
        for (int i = 0; i < 150; i++) {
            boolean flag = jedisClient.accessLimit("localhost", 10, 100);
            System.out.println(flag);
        }

    }

    /**
     * 测试模糊匹配删除
     * */
    @Test
    public void testDeleteByPattern() throws UnsupportedEncodingException {
        jedisClient.deleteByPattern("*");
    }

//
//
//    /**
//     * 测试设置存储对象的key的过期时间
//     */
//    @Test
//    public void test54() throws IOException, ClassNotFoundException {
//        try {
//            jedisClient.del("testKey");
//            jedisClient.set("testKey", "11111", 0);
//            jedisClient.expire("testKey", 5000);
//            Assert.assertEquals("11111", jedisClient.get(Object.class, "testKey"));
//            TimeUnit.SECONDS.sleep(6);
//            Assert.assertNull(jedisClient.get(Object.class, "testKey"));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            jedisClient.del("testKey");
//        }
//    }
//
    /**
     * 测试pipeline
     * */
    @Test
    @Disabled
    public void testPipelineBatch() throws IOException, ClassNotFoundException {
//        try {
//            jedisClient.del("testKey");
//            jedisClient.pipelineBatch(pipelineBase -> {
//                pipelineBase.set("testKey", "1111");
//                pipelineBase.append("testKey", "2222");
//            });
//            String value = jedisClient.get(String.class, "testKey");
//        } finally {
//            jedisClient.del("testKey");
//        }
    }

    /*======================================通用 end==============================================================*/

    /*=====================================测试SET BEGIN=================================================*/

    /**
     * 测试设置集合，并测试获取集合的值
     */
    @Test
    public void testSadd() throws IOException, ClassNotFoundException {
        try {
            Set<Object> set = new HashSet<>();
            set.add("x");
            set.add("y");
            set.add("z");
            set.add("m");
            long result = jedisClient.sadd("testKey", set, 0);
            Set resultSet = jedisClient.smembers(Object.class, "testKey");
            Assertions.assertEquals(4, result);
            resultSet.forEach(System.out::println);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试集合追加值，数据为对象
     */
    @Test
    public void testSdd2() throws IOException, ClassNotFoundException {
        try {
            long result = jedisClient.sadd("testKey", 1, 2, 4, "wegw");
            Set resultSet = jedisClient.smembers(Object.class, "testKey");
            Assertions.assertEquals(4, result);
            resultSet.forEach(System.out::println);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试交集
     */
    @Test
    public void testSinter() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "22222", "33333", 45);
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey2", "11111", "22222", "44444", 45);
            Set<Object> result = jedisClient.sinter(Object.class, "testKey", "testKey2");
            result.stream().forEach(System.out::println);
            Assertions.assertEquals(3, result.size());
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试交集,存入新集合
     */
    @Test
    public void testSinterStore() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333", 67);
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey2", "11111", "44444", 67);
            long result = jedisClient.sinterStore("newKey", "testKey", "testKey2");
            Set<Object> newSet = jedisClient.smembers(Object.class, "newKey");
            newSet.stream().forEach(System.out::println);
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("newKey");
        }
    }

    /**
     * 测试集合中某个元素是否存在
     */
    @Test
    public void testSismember() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333", 1111);
            boolean exists = jedisClient.sismember("testKey", 1111);
            Assertions.assertEquals(true, exists);
        } finally {
            jedisClient.del("testKey");
        }
    }


    /**
     * 测试获取集合中所有的元素,集合中存储为对象
     */
    @Test
    public void testSmembers() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333", 23455);
            Set<Object> members = jedisClient.smembers(Object.class, "testKey");
            members.stream().forEach(System.out::println);
            Assertions.assertEquals(3, members.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从集合中移除对象成员,放入目标集合
     */
    @Test
    public void testSmove() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("srcKey");
            jedisClient.del("dstKey");
            jedisClient.sadd("srcKey", "11111", "33333");
            jedisClient.sadd("dstKey", "2222", "5555");
            jedisClient.smove("srcKey", "dstKey", "11111");
            Set<Object> srcSet = jedisClient.smembers(Object.class, "srcKey");
            Set<Object> dstSet = jedisClient.smembers(Object.class, "dstKey");
            srcSet.forEach(System.out::println);
            dstSet.forEach(System.out::println);
            Assertions.assertEquals(1, srcSet.size());
            Assertions.assertEquals(3, dstSet.size());
        } finally {
            jedisClient.del("srcKey");
            jedisClient.del("dstKey");
        }
    }


    /**
     * 测试从集合中随机移除对象成员并返回
     */
    @Test
    public void testSpop() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333");
            Object data = jedisClient.spop(Object.class, "testKey");
            System.out.println(data);
            Assertions.assertEquals(1, jedisClient.scard("testKey"));
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取集合中对象元素的个数
     */
    @Test
    public void testScard() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", 2, 3, 4);
            long sum = jedisClient.scard("testKey");
            Assertions.assertEquals(3, sum);
        } finally {
            jedisClient.del("testKey");
        }
    }


    /**
     * 测试从集合中随机移除对象成员并返回
     */
    @Test
    public void testSpopCount() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", "33333", 3);
            Set<Object> datas = jedisClient.spop(Object.class, "testKey", 3);
            datas.forEach(System.out::println);
            Assertions.assertEquals(3, datas.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从集合中删除成员
     */
    @Test
    public void testSrem() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.sadd("testKey", "11111", 33333);
            long result = jedisClient.srem("testKey", "11111", "44444");
            Set<Object> set = jedisClient.smembers(Object.class, "testKey");
            set.forEach(System.out::println);
            Assertions.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试去并集
     */
    @Test
    public void testSunion() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.sadd("testKey", "1", "2", "3", 2222);
            jedisClient.sadd("testKey2", "1", "2", "3", "7", 345);
            Set sunionSet = jedisClient.sunion(Object.class, "testKey", "testKey2");
            sunionSet.forEach(System.out::println);
            Assertions.assertEquals(6, sunionSet.size());
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试sdiff 差集
     */
    @Test
    public void testSdiff() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            long result = jedisClient.sadd("testKey", 1, 2, 4, "wegw");
            long result2 = jedisClient.sadd("testKey2", 1, 2, 6, 7);
            Set resultSet = jedisClient.sdiff(Object.class, "testKey", "testKey2");
            Assertions.assertEquals(2, resultSet.size());
            resultSet.forEach(System.out::println);
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试sdiff 差集
     */
    @Test
    public void testSdiffStore() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
            long result = jedisClient.sadd("testKey", 1, 2, 4, "wegw");
            long result2 = jedisClient.sadd("testKey2", 1, 2, 6, 7);
            jedisClient.sdiffStore("testKey3", "testKey", "testKey2");
            Set<Object> key3Result = jedisClient.smembers(Object.class, "testKey3");
            Assertions.assertEquals(2, key3Result.size());
            key3Result.forEach(System.out::println);
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
        }
    }

    /*=====================================测试SET END===================================================*/


    /*=============================sort set begin======================================*/
    /**
     * 测试将一个元素插入zset
     * */
    @Test
    public void testZadd() throws IOException {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.zadd("testKey", 1, 123);
            Assertions.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试将多个元素插入zset
     * */
    @Test
    public void testZadd2() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<Integer, Double> map = new HashMap<>();
            map.put(1, 6.7);
            map.put(2, 12.9);
            long result = jedisClient.zadd("testKey", map);
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试将多个元素插入zset, 带超时时间
     * */
    @Test
    public void testZadd3() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<Integer, Double> map = new HashMap<>();
            map.put(1, 6.7);
            map.put(2, 12.9);
            long result = jedisClient.zadd("testKey", map,  2);
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试zset中元素的个数
     * */
    @Test
    public void testZcard() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<Integer, Double> map = new HashMap<>();
            map.put(1, 6.7);
            map.put(2, 12.9);
            jedisClient.zadd("testKey", map,  20);
            long result = jedisClient.zcard("testKey");
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }
    /**
     * 测试获取zset中制定权重区间内的元素的个数
     * */
    @Test
    public void testZcount() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            long result = jedisClient.zcount("testKey", 1.0, 1.6);
            Assertions.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试为zset中某个元素增加权重
     * */
    @Test
    public void testZincrby() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            double result = jedisClient.zincrby("testKey", 3, "2");
            Assertions.assertEquals(5.0, result, 1);
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
    public void testZrange() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> set1 = jedisClient.zrange(String.class, "testKey", 0, -1);
            set1.forEach(System.out::println);
            //如果元素不存在，增加这个元素
            Set<String> set2 = jedisClient.zrange(String.class, "testKey", 0, 0);
            Assertions.assertEquals("1", set2.iterator().next());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset制定范围的元素,并附带权重
     * */
    @Test
    @Disabled
    public void testZrangeWithScores() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Set<Tuple> tuples = jedisClient.zrangeWithScores("testKey", 0, -1);
            tuples.forEach(tuple -> {
                try {
                    System.out.println(MyObjectHelper.unserialize(tuple.getBinaryElement()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(tuple.getScore());
            });
            Assertions.assertEquals(2, tuples.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取zset制定范围的元素,并附带权重,返回Map
     * */
    @Test
    public void testZrangeWithScoresToMap() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Map<String, Double> map = jedisClient.zrangeWithScoresToMap(String.class, "testKey", 0, -1);
            map.entrySet().forEach(entry -> {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            });
            Assertions.assertEquals(2, map.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素
     * */
    @Test
    public void testZrangeByScore() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result = jedisClient.zrangeByScore(String.class, "testKey", 1.0, 1.7);
            result.forEach(System.out::println);
            Assertions.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素, 带偏移量
     * */
    @Test
    public void testZrangeByScore2() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Set<String> result = jedisClient.zrangeByScore(String.class, "testKey", 1.0, 4, 1, 2);
            result.forEach(System.out::println);
            Assertions.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重
     * */
    @Test
    @Disabled
    public void testZrangeByScoreWithScores() throws IOException, ClassNotFoundException {
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
                try {
                    System.out.println(MyObjectHelper.unserialize(tuple.getBinaryElement()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(tuple.getScore());
            });
            Assertions.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重,返回Map
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
            Map<String, Double> result = jedisClient.zrangeByScoreWithScoresToMap(String.class, "testKey", 1.0, 2.0);
            result.entrySet().forEach(System.out::println);
            Assertions.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重, 带偏移量
     * */
    @Test
    @Disabled
    public void testZrangeByScoreWithScores2() throws IOException, ClassNotFoundException {
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
                try {
                    System.out.println(MyObjectHelper.unserialize(tuple.getBinaryElement()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(tuple.getScore());
            });
            Assertions.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重,返回Map, 带偏移量
     * */
    @Test
    public void testZrangeByScoreWithScoresToMap2() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, Double> memebers = new HashMap<>();
            memebers.put("1", 1.0);
            memebers.put("2", 2.0);
            memebers.put("3", 3.0);
            memebers.put("4", 4.0);
            memebers.put("5", 5.0);
            jedisClient.zadd("testKey", memebers);
            Map<String, Double> result = jedisClient.zrangeByScoreWithScoresToMap(String.class, "testKey", 1.0, 2.0, 0, 1);
            result.entrySet().forEach(System.out::println);
            Assertions.assertEquals(1, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试按照权重查找zset中元素,返回值附带权重, 返回Map，带偏移量
     * */
    @Test
    public void testZrank() throws IOException {
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
            Assertions.assertEquals(1, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从高到低权重排列，某个元素的位置
     * */
    @Test
    public void testZrevrank() throws IOException {
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
            Assertions.assertEquals(3, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从zset中删除元素
     * */
    @Test
    public void testZrem() throws IOException {
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
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从zset中删除指定位置的元素
     * */
    @Test
    public void testZremrangeByRank() throws IOException {
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
            Assertions.assertEquals(3, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从zset中删除指定群众的元素
     * */
    @Test
    public void testZremrangeByScore() throws IOException {
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
            Assertions.assertEquals(3, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试指定元素在zset中的权重
     * */
    @Test
    public void testZscore() throws IOException {
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
            Assertions.assertEquals(1.0, result1, 1);
            Double result2 = jedisClient.zscore("testKey", "88");
            Assertions.assertNull(result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试zset取交集并存入新集合
     * */
    @Test
    public void testZinterstore() throws IOException, ClassNotFoundException {
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
            Set<String> set = jedisClient.zrange(String.class, "testKey3", 0, -1);
            set.forEach(System.out::println);
            Assertions.assertEquals(result, 4);

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
    public void testZunionstore() throws IOException, ClassNotFoundException {
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
            Set<String> set = jedisClient.zrange(String.class, "testKey3", 0, -1);
            set.forEach(System.out::println);
            Assertions.assertEquals(result, 6);

        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.del("testKey3");
        }
    }

    /*=============================sort set end========================================*/


    /*=============================hash start========================================*/
    /**
     * 测试存入hash
     * */
    @Test
    public void testHmset() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            boolean result = jedisClient.hmset("testKey", hash, 5);
            Assertions.assertEquals(true, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取hash中所有的元素
     * */
    @Test
    public void testHgetAll() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            Map<String, String> result = jedisClient.hgetAll(String.class, String.class, "testKey");
            result.entrySet().forEach(System.out::println);
            Assertions.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试从hash中删除元素
     * */
    @Test
    public void testHdel() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            long result = jedisClient.hdel("testKey", "a", "b");
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hash中是否存在某个key
     * */
    @Test
    public void testHexists() throws IOException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            boolean hexists = jedisClient.hexists("testKey", "a");
            Assertions.assertTrue(hexists);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hash中指定key的值
     * */
    @Test
    public void testHget() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            String result = jedisClient.hget(String.class, "testKey", "a");
            Assertions.assertEquals(result, "1");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试向hash中添加一个键值对
     * */
    @Test
    public void testHset() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            jedisClient.hset("testKey", "c", 3);
            Assertions.assertEquals(3, jedisClient.hgetAll(String.class, Object.class, "testKey").size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试向hash中添加一个键值对,当这个field不存在时才添加
     * */
    @Test
    public void testHsetnx() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            jedisClient.hsetnx("testKey", "c", "3");
            Assertions.assertEquals("3", jedisClient.hget(String.class, "testKey", "c"));

            jedisClient.hsetnx("testKey", "c", "311");
            Assertions.assertNotEquals("311", jedisClient.hget(String.class, "testKey", "c"));
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试获取hash的val
     * */
    @Test
    public void testHvals() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            List<String> result = jedisClient.hvals(String.class, "testKey");
            Assertions.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hkeys
     * */
    @Test
    public void testHkeys() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1.0");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            Set<String> set = jedisClient.hkeys(String.class, "testKey");
            Assertions.assertEquals(2, set.size());
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
            Assertions.assertEquals(2, result);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试hmget
     * */
    @Test
    public void testHmget() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            Map<String, String> hash = new HashMap<>();
            hash.put("a", "1.0");
            hash.put("b", "2");
            jedisClient.hmset("testKey", hash, 0);
            List<String> result = jedisClient.hmget(String.class, "testKey", "a", "b");
            Assertions.assertEquals(2, result.size());
        } finally {
            jedisClient.del("testKey");
        }
    }

    /*=============================hash end  ========================================*/

    /*=============================string begin========================================*/
    /**
     * 测试插入一个对象，有超时时间，并测试获取这个对象
     */
    @Test
    public void testSetAndGet() throws IOException, ClassNotFoundException {
        try {
            boolean result = jedisClient.set("testKey", "value", 20);
            String testValue = jedisClient.get(String.class, "testKey");
            Assertions.assertTrue(result);
            Assertions.assertEquals(testValue, "value");
            Map map = new HashMap();
            map.put("x", Arrays.asList(1,2,3));
            jedisClient.set("testKey", map, 20);
            Map testMap = jedisClient.get(Map.class, "testKey");
            System.out.println(testMap);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试setnx
     */
    @Test
    public void testSetnx() throws IOException, ClassNotFoundException {
        try {
            Map map = new HashMap();
            map.put("a", 123);
            jedisClient.del("testKey");
            long result1 = jedisClient.setnx("testKey", map);
            Assertions.assertEquals(result1, 1L);
            long result2 = jedisClient.setnx("testKey", 22);
            Assertions.assertEquals(result2, 0L);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试setrange
     * */
    @Test
    @Disabled
    public void testSetrange() throws IOException, ClassNotFoundException {

    }

    /**
     * 测试decrBy
     * */
    @Test
    @Disabled
    public void testDecrBy() throws IOException {

    }

    /**
     * 测试getSet
     * */
    @Test
    @Disabled
    public void testGetSet() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            String result1 = jedisClient.getSet(String.class, "testKey", "10000");
            Assertions.assertNull(result1);
            String result2 = jedisClient.getSet(String.class,"testKey", "123");
            Assertions.assertEquals(result2, "10000");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试mget
     * */
    @Test
    public void testMget() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
            jedisClient.set("testKey", "111", 0);
            jedisClient.set("testKey2", 222, 0);
            List<Object> result = jedisClient.mget(Object.class, "testKey", "testKey2");
            Assertions.assertEquals("111", result.get(0));
            Assertions.assertEquals(222, result.get(1));
        } finally {
            jedisClient.del("testKey");
            jedisClient.del("testKey2");
        }
    }

    /**
     * 测试mset
     * */
    @Test
    public void testMset() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("111");
            jedisClient.del("222");
            jedisClient.mset("111", "a", "222", "b");
            List<String> result = jedisClient.mget(String.class, "111", "222");
            Assertions.assertEquals("a", result.get(0));
            Assertions.assertEquals("b", result.get(1));
        } finally {
            jedisClient.del("111");
            jedisClient.del("222");
        }
    }

    /**
     * 测试strlen
     * */
    @Test
    @Disabled
    public void testStrlen() throws IOException {

    }

    /*=============================string end==========================================*/


    /*=============================list begin==========================================*/
    /**
     * 测试rpush
     * */
    @Test
    public void testRpush() throws IOException {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.rpush("testKey", "10000", "22222");
            long result2 = jedisClient.rpush("testKey", "aaa", "bbb");
            Assertions.assertEquals(4, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lpush
     * */
    @Test
    public void testLpush() throws IOException {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.lpush("testKey", "10000", "22222");
            long result2 = jedisClient.lpush("testKey", "aaa", "bbb");
            Assertions.assertEquals(4, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试llen
     * */
    @Test
    public void testLlen() throws IOException {
        try {
            jedisClient.del("testKey");
            long result = jedisClient.rpush("testKey", "10000", "22222");
            long result2 = jedisClient.llen("testKey");
            Assertions.assertEquals(2, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lset
     * */
    @Test
    public void testLset() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            boolean result1 = jedisClient.lset("testKey", 1, "dfg");
            Assertions.assertTrue(result1);
            boolean result2 = jedisClient.lset("testKey", 0, "zqw");
            Assertions.assertTrue(result2);

        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试linsert
     * */
    @Test
    public void testLinsert() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            long result = jedisClient.linsert("testKey", ListPosition.AFTER, "10000", "33333");
            Assertions.assertEquals(result, 3);
            long result2 = jedisClient.linsert("testKey", ListPosition.AFTER, "10000", 45678);
            Assertions.assertEquals(result2, 4);
        } finally {
            jedisClient.del("testKey");
        }
    }
    /**
     * 测试lindex
     * */
    @Test
    public void testLindex() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            String result = jedisClient.lindex(String.class, "testKey", 1);
            Assertions.assertEquals(result, "22222");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lpop
     * */
    @Test
    public void testLpop() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            String result = jedisClient.lpop(String.class, "testKey");
            Assertions.assertEquals(result, "10000");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lpop
     * */
    @Test
    public void testRpop() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222");
            String result = jedisClient.rpop(String.class, "testKey");
            Assertions.assertEquals(result, "22222");
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lrange
     * */
    @Test
    public void testLrange() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222", "444", "adsdb");
            List<String> result = jedisClient.lrange(String.class, "testKey", 0, 2);
            Assertions.assertEquals(result.size(), 3);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试lrem
     * */
    @Test
    public void testLrem() throws IOException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222", "10000", "22222", "10000");
            long result = jedisClient.lrem("testKey", 6, "10000");
            Assertions.assertEquals(3, result);
            long result2 = jedisClient.lrem("testKey", -1, "22222");
            Assertions.assertEquals(1, result2);
        } finally {
            jedisClient.del("testKey");
        }
    }

    /**
     * 测试ltrim
     * */
    @Test
    public void testLtrim() throws IOException, ClassNotFoundException {
        try {
            jedisClient.del("testKey");
            jedisClient.rpush("testKey", "10000", "22222", "10000", "22222", "10000");
            boolean result = jedisClient.ltrim("testKey", 2, 3);
            Assertions.assertTrue(result);
            List<String> result2 = jedisClient.lrange(String.class, "testKey", 0, -1);
            Assertions.assertEquals(2, result2.size());
        } finally {
            jedisClient.del("testKey");
        }
    }
//    /*=============================list end============================================*/


}
