package com.iscas.biz.mp.config.db;

import com.iscas.templet.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * atomikos在springboot3.x以后不可用了，所以废弃多数据源事务切换
 * <p>多数据源事务处理<p/>
 * <p>参考：<a href="https://blog.csdn.net/gaoshili001/article/details/79378902">https://blog.csdn.net/gaoshili001/article/details/79378902</a><p/>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/20 15:40
 * @since jdk1.8
 */
@SuppressWarnings("FieldMayBeFinal")
@Slf4j
public class MultiDataSourceTransaction implements Transaction {

    private final DataSource dataSource;

    private ConcurrentMap<String, Connection> connectionMap;

    public MultiDataSourceTransaction(DataSource dataSource) {
        if (dataSource == null) {
            throw Exceptions.runtimeException("No DataSource specified");
        }
        this.dataSource = dataSource;
        connectionMap = new ConcurrentHashMap<>();
    }


    @Override
    public Connection getConnection() throws SQLException {
        String databaseIdentification = DbContextHolder.getDbType();
        // 如果databaseIdentification为空，使用默认数据源（第一个数据源）
        if (Objects.isNull(databaseIdentification) && dataSource instanceof DynamicDataSource dynamicDataSource) {
            Map<Object, DataSource> resolvedDataSources = dynamicDataSource.getResolvedDataSources();
            databaseIdentification = (String) resolvedDataSources.entrySet().iterator().next().getKey();
        }

        if (!connectionMap.containsKey(databaseIdentification)) {
            Connection conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            connectionMap.put(databaseIdentification, conn);
        }
        return connectionMap.get(databaseIdentification);
    }

    @Override
    public void commit() throws SQLException {
        if (log.isDebugEnabled()) {
            log.debug("Committing JDBC Connection [" + connectionMap + "]");
        }
        for (Connection connection : connectionMap.values()) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (log.isDebugEnabled()) {
            log.debug("Rolling back JDBC Connection [" + connectionMap + "]");
        }
        for (Connection connection : connectionMap.values()) {
            if (!connection.getAutoCommit()) {
                connection.rollback();
            }
        }
    }

    @Override
    public void close() throws SQLException {
        for (Connection connection : connectionMap.values()) {
            DataSourceUtils.releaseConnection(connection, this.dataSource);
        }
    }

    @Override
    public Integer getTimeout() {
        return null;
    }

}
