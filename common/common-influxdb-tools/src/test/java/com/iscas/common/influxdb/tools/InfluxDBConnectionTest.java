package com.iscas.common.influxdb.tools;

import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/25 18:55
 * @since jdk11
 */
class InfluxDBConnectionTest {
    private InfluxDBConnection influxDBConnection;

    @BeforeEach
    public void before() {
        influxDBConnection = new InfluxDBConnection("111", "111",
                "http://192.168.100.22:8086", "test3", "hour");
        influxDBConnection.createRetentionPolicy("test3", "hour", 10, 1, true);
    }

    /**
     * 测试连接
     */
    @Test
    public void testConnection() {
    }

    /**
     * 测试创建数据库
     */
    @Test
    public void testCreateDb() {
        influxDBConnection.createDB("test3");
    }

    /**
     * 测试删除数据库
     */
    @Test
    public void testDeleteDb() {
        influxDBConnection.deleteDB("test3");
    }

    /**
     * ping
     */
    @Test
    public void testPing() {
        Assertions.assertTrue(influxDBConnection.ping());
    }

    /**
     * 测试创建保留策略
     */
    @Test
    public void testPolicy() {
        influxDBConnection.createRetentionPolicy("test3", "testPolicy3", 10, 10, true);
    }

    /**
     * 测试插入
     */
    @Test
    public void testInsert() {
        influxDBConnection.insert("test3", Map.of("a", "9"), Map.of("A", "一", "B", "二"),
                new Date().getTime());
    }

    /**
     * 测试批量插入
     */
    @Test
    public void testBatchInsert() {
        BatchPoints.Builder batchPointsBuilder = BatchPoints.database("test3");
        Point.Builder builder = Point.measurement("test3");
        builder.tag(Map.of("a", "6"));
        builder.fields(Map.of("A", "A1"));
        builder.time(new Date().getTime(), TimeUnit.MILLISECONDS);
        batchPointsBuilder.point(builder.build());
        influxDBConnection.batchInsert(batchPointsBuilder.build());
    }

    /**
     * 测试删除
     */
    @Test
    public void testDel() {
        QueryResult queryResult = influxDBConnection.executeCommand("DELETE FROM test3 WHERE a = '1'");
        System.out.println(queryResult);
    }

    /**
     * 测试查询
     */
    @Test
    public void testSelect() {
        QueryResult queryResult = influxDBConnection.executeCommand("SELECT * FROM test3 WHERE a = '2'");
        System.out.println(queryResult);
    }

}