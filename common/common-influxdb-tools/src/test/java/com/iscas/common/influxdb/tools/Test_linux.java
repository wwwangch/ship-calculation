package com.iscas.common.influxdb.tools;

import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Test_linux {
    private InfluxDBConnection influxDBConnection;

    @BeforeEach
    public void before() {
        influxDBConnection = new InfluxDBConnection("111", "111",
                "http://172.16.10.192:8086", "test3", "hour");
        influxDBConnection.createRetentionPolicy("test3", "hour", 10, 1, true);
    }

    @Test
    public void testBatchInsert() {
        influxDBConnection.createDB("test3");
        long start = System.currentTimeMillis();
        long l = System.currentTimeMillis();
        long count = 0;
        for (int i = 0; i < 10000; i++) {
            BatchPoints.Builder batchPointsBuilder = BatchPoints.database("test3");
            for (int j = 0; j < 5000; j++) {
                Point.Builder builder = Point.measurement("m1");
                builder.tag(Map.of("a" + i, "test" + l));
                builder.fields(Map.of("A", "A" + l));
                l += 500L;
                builder.time(l, TimeUnit.MILLISECONDS);
                batchPointsBuilder.point(builder.build());

                Point.Builder builder2 = Point.measurement("m1");
                builder2.tag(Map.of("a" + i, "test" + l));
                builder2.fields(Map.of("A", "A" + l));
                l += 500L;
                builder2.time(l, TimeUnit.MILLISECONDS);
                batchPointsBuilder.point(builder2.build());
            }
            BatchPoints batchPoints = batchPointsBuilder.build();
            try {
                influxDBConnection.batchInsert(batchPoints);
            } catch (Exception e) {
                e.printStackTrace();
                //重试
                for (int j = 0; j < 5; j++) {
                    try {
                        influxDBConnection.batchInsert(batchPoints);
                        break;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            count = count + 10000L;
            System.out.println("==============已经插入" + count + "条");
        }
        long end = System.currentTimeMillis();
        System.out.println(">>>>>>>>>>>>>>>>>插入总计耗时:" + (end - start) + "毫秒");

    }


    /**
     * 测试删除
     */
    @Test
    public void testDel() {
        QueryResult queryResult = influxDBConnection.executeCommand("DELETE FROM test3");
        System.out.println(queryResult);
    }

    /**
     * 测试删除
     */
    @Test
    public void testSelect() {
        QueryResult queryResult = influxDBConnection.executeCommand("SELECT * FROM test3");
        System.out.println(queryResult);
    }

}
