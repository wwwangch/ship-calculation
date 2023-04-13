package com.iscas.biz.mp.config.db;

import com.iscas.templet.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import org.apache.ibatis.transaction.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
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
@Deprecated
public class MultiDataSourceTransaction_backup implements Transaction {

    private final DataSource dataSource;

    private Connection mainConnection;

    private String mainDatabaseIdentification;

    private ConcurrentMap<String, Connection> otherConnectionMap;


    private boolean isConnectionTransactional;

    private boolean autoCommit;


    public MultiDataSourceTransaction_backup(DataSource dataSource) {
        if (dataSource == null) {
            throw Exceptions.runtimeException("No DataSource specified");
        }
        this.dataSource = dataSource;
        otherConnectionMap = new ConcurrentHashMap<>();
        mainDatabaseIdentification = DbContextHolder.getDbType();
    }



    @Override
    public Connection getConnection() throws SQLException {
        String databaseIdentification = DbContextHolder.getDbType();
        if (Objects.equals(databaseIdentification, mainDatabaseIdentification)) {
            if (mainConnection == null) {
                openMainConnection();
                mainDatabaseIdentification = databaseIdentification;
            }
            return mainConnection;
        } else {
            if (!otherConnectionMap.containsKey(databaseIdentification)) {
                try {
                    Connection conn = dataSource.getConnection();
                    otherConnectionMap.put(databaseIdentification, conn);
                } catch (SQLException ex) {
                    throw Exceptions.runtimeException("Could not get JDBC Connection", ex);
                }
            }
            return otherConnectionMap.get(databaseIdentification);
        }

    }


    private void openMainConnection() throws SQLException {
        this.mainConnection = DataSourceUtils.getConnection(this.dataSource);
        this.autoCommit = this.mainConnection.getAutoCommit();
        this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.mainConnection, this.dataSource);

        if (log.isDebugEnabled()) {
            log.debug(
                    "JDBC Connection ["
                            + this.mainConnection
                            + "] will"
                            + (this.isConnectionTransactional ? " " : " not ")
                            + "be managed by Spring");
        }
    }

    @Override
    public void commit() throws SQLException {
        if (this.mainConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            if (log.isDebugEnabled()) {
                log.debug("Committing JDBC Connection [" + this.mainConnection + "]");
            }
            this.mainConnection.commit();
            for (Connection connection : otherConnectionMap.values()) {
                connection.commit();
            }
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (this.mainConnection != null && this.isConnectionTransactional && !this.autoCommit) {
            if (log.isDebugEnabled()) {
                log.debug("Rolling back JDBC Connection [" + this.mainConnection + "]");
            }
            this.mainConnection.rollback();
            for (Connection connection : otherConnectionMap.values()) {
                connection.rollback();
            }
        }
    }

    @Override
    public void close() throws SQLException {
        DataSourceUtils.releaseConnection(this.mainConnection, this.dataSource);
        for (Connection connection : otherConnectionMap.values()) {
            DataSourceUtils.releaseConnection(connection, this.dataSource);
        }
    }

    @Override
    public Integer getTimeout() {
        return null;
    }

}
