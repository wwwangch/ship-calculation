package com.iscas.common.influxdb.tools;

import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Test27E_linux {
    private InfluxDBConnection influxDBConnection;

    @BeforeEach
    public void before() {
        influxDBConnection = new InfluxDBConnection("111", "111",
                "http://172.16.10.192:8086", "test27e", "hour");
        influxDBConnection.createRetentionPolicy("test27e", "hour", 10, 1, true);
    }

    @Test
    public void testBatchInsert() {
        influxDBConnection.createDB("test27e");
        long start = System.currentTimeMillis();
        long l = System.currentTimeMillis();
        long count = 0;
        for (int i = 0; i < 2700; i++) {
            BatchPoints.Builder batchPointsBuilder = BatchPoints.database("test27e");
            for (int j = 0; j < 50000; j++) {
                Point.Builder builder = Point.measurement("m1");
                builder.tag(Map.of("tagkey", "testTag" + i));
                builder.fields(Map.of("A", "A" + l));
                l += 500L;
                builder.time(l, TimeUnit.MILLISECONDS);
                batchPointsBuilder.point(builder.build());

                Point.Builder builder2 = Point.measurement("m1");
                builder2.tag(Map.of("tagkey", "testTag" + i));
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
            count = count + 100000L;
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
        QueryResult queryResult = influxDBConnection.executeCommand("DELETE FROM m1");
        System.out.println(queryResult);
    }

    /**
     * 测试获取总数
     */
    @Test
    public void testCount() {
        QueryResult queryResult = influxDBConnection.executeCommand("select count(*) FROM m1");
        System.out.println(queryResult);
    }

    /**
     * 测试查询
     */
    @Test
    public void testSelect() {
        long s = System.currentTimeMillis();
        QueryResult queryResult = influxDBConnection.executeCommand("SELECT * FROM m1 where time >= '2022-12-28T00:00:00Z' and time <= '2022-12-29T00:00:00Z' limit 10000");
        System.out.println("耗时：" + (System.currentTimeMillis() - s) + "毫秒");
        System.out.println(queryResult.getResults().get(0).getSeries().get(0).getValues().size());
    }

}
