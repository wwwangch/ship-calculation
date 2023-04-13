package com.iscas.common.influxdb.tools;

import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.*;
import org.influxdb.dto.Point.Builder;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * InfluxDB数据库连接操作类
 *
 * @author zhuquanwen
 */
@SuppressWarnings("JavadocDeclaration")
public class InfluxDBConnection {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接地址
     */
    private String openurl;

    /**
     * 数据库名称
     */
    private String dbName;


    /**
     * 保留策略
     */
    private String retentionPolicy;

    private InfluxDB influxDB;

    public InfluxDBConnection(String username, String password, String url, String dbName, String retentionPolicy) {
        this.username = username;
        this.password = password;
        this.openurl = url;
        this.dbName = dbName;
        this.retentionPolicy = retentionPolicy == null || retentionPolicy.equals("") ? "autogen" : retentionPolicy;
        initInfluxDb();
    }

    /**
     * 创建数据库
     *
     * @param dbName 数据库名称
     * @date 2022/8/26
     * @since jdk11
     */
    public void createDB(String dbName) {
        influxDB.query(new Query("CREATE DATABASE " + dbName, dbName));
    }

    /**
     * 删除数据库
     *
     * @param dbName 数据库名称
     * @date 2022/8/26
     * @since jdk11
     */
    public void deleteDB(String dbName) {
        influxDB.query(new Query("DROP DATABASE " + dbName, dbName));
    }

    /**
     * 删除数据库连接是否正常
     *
     * @date 2022/8/26
     * @since jdk11
     */
    public boolean ping() {
        boolean isConnected = false;
        Pong pong;
        try {
            pong = influxDB.ping();
            if (pong != null) {
                isConnected = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return isConnected;
    }

    public InfluxDB initInfluxDb() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(openurl, username, password, new OkHttpClient.Builder().readTimeout(Duration.ofMinutes(2)));
        }
        try {
            // if (!influxDB.databaseExists(database)) {
            // influxDB.createDatabase(database);
            // }
        } catch (Exception e) {
            // 该数据库可能设置动态代理，不支持创建数据库
            // e.printStackTrace();
        } finally {
            influxDB.setRetentionPolicy(retentionPolicy);
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        influxDB.enableBatch(20, 200, TimeUnit.MILLISECONDS);
        return influxDB;
    }

    /**
     * 创建自定义保留策略
     *
     * @param dataBaseName 数据库名
     * @param policyName   策略名
     * @param days         保存天数
     * @param replication  保存副本数量
     * @param isDefault    是否设为默认保留策略
     */
    public void createRetentionPolicy(String dataBaseName, String policyName, int days, int replication,
                                      Boolean isDefault) {
        String sql = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %sd REPLICATION %s ", policyName,
                dataBaseName, days, replication);
        if (isDefault) {
            sql = sql + " DEFAULT";
        }
        query(sql);
    }

    /**
     * 查询
     *
     * @param command 查询语句
     * @return
     */
    public QueryResult query(String command) {
        return influxDB.query(new Query(command, dbName));
    }

    /**
     * 插入
     *
     * @param measurement 表
     * @param tags        标签
     * @param fields      字段
     * @param time        时间(毫秒数)
     */
    public void insert(String measurement, Map<String, String> tags, Map<String, Object> fields, long time/*, TimeUnit timeUnit*/) {
        Builder builder = Point.measurement(measurement);
        builder.tag(tags);
        builder.fields(fields);
        if (0 != time) {
            builder.time(time, TimeUnit.MILLISECONDS);
        }
        influxDB.write(dbName, retentionPolicy, builder.build());
        influxDB.flush();
    }

    /**
     * 批量写入测点
     *
     * @param batchPoints 批量测点
     */
    public void batchInsert(BatchPoints batchPoints/*, TimeUnit timeUnit*/) {
        influxDB.write(batchPoints);
        // influxDB.enableGzip();
        // influxDB.enableBatch(2000,100,timeUnit);
        // influxDB.disableGzip();
        // influxDB.disableBatch();
    }

//    /**
//     * 批量写入数据
//     *
//     * @param database        数据库
//     * @param retentionPolicy 保存策略
//     * @param consistency     一致性
//     * @param records         要保存的数据（调用BatchPoints.lineProtocol()可得到一条record）
//     */
//    public void batchInsert(final String database, final String retentionPolicy, final ConsistencyLevel consistency,
//                            TimeUnit timeUnit, final List<String> records) {
////        influxDB.write(database, retentionPolicy, consistency, timeUnit, records);
//    }

    /**
     * 删除
     *
     * @param command 删除语句
     * @return 返回信息
     */
    public QueryResult executeCommand(String command) {
        return influxDB.query(new Query(command, dbName));
    }

    /**
     * 关闭数据库
     */
    public void close() {
        influxDB.close();
    }

    /**
     * 构建Point
     *
     * @param measurement
     * @param time
     * @param fields
     * @return
     */
    public Point pointBuilder(String measurement, long time, TimeUnit timeUnit, Map<String, String> tags,
                              Map<String, Object> fields) {
        Point point = Point.measurement(measurement).time(time, timeUnit).tag(tags).fields(fields).build();
        return point;
    }

}
