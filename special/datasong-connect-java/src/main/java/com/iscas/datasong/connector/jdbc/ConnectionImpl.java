package com.iscas.datasong.connector.jdbc;

import com.iscas.datasong.client.DataSongHttpClient;
import com.iscas.datasong.connector.Constants;
import com.iscas.datasong.connector.conf.HostInfo;
import com.iscas.datasong.lib.common.DataSongException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/29 18:32
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
public class  ConnectionImpl implements Connection, Constants {
    private final HostInfo origHostInfo;
    private String dsUrl;
    private String dbName;
    private DataSongHttpClient dsHttpClient;

    private boolean closed;

    public HostInfo getOrigHostInfo() {
        return origHostInfo;
    }

    public String getDsUrl() {
        return dsUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public DataSongHttpClient getDsHttpClient() {
        return dsHttpClient;
    }

    /**
     * 默认结果集类型
     * */
    private static final int DEFAULT_RESULT_SET_TYPE = ResultSet.TYPE_FORWARD_ONLY;

    private static final int DEFAULT_RESULT_SET_CONCURRENCY = ResultSet.CONCUR_READ_ONLY;

    public ConnectionImpl(HostInfo hostInfo) throws SQLException {
        this.origHostInfo = hostInfo;
        //生成datasong的URL
        createDsUrl(hostInfo);
        //生成datasong cleint
        try {
            createDsHttpClient(hostInfo);
        } catch (DataSongException e) {
            throw new SQLException("获取datasong连接失败", e);
        }
    }



    public static ConnectionImpl getInstance(HostInfo hostInfo) throws SQLException {
        return new ConnectionImpl(hostInfo);
    }

    @Override
    public Statement createStatement() {
        return createStatement(DEFAULT_RESULT_SET_TYPE, DEFAULT_RESULT_SET_CONCURRENCY);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) {
        StatementImpl statement = new StatementImpl(this);
        statement.setResultSetType(resultSetType);
        statement.setResultSetConcurrency(resultSetConcurrency);
        return statement;
    }


    @Override
    public PreparedStatement prepareStatement(String sql) {
        return prepareStatement(sql, DEFAULT_RESULT_SET_TYPE, DEFAULT_RESULT_SET_CONCURRENCY);
    }

    @Override
    public CallableStatement prepareCall(String sql) {
        throw new UnsupportedOperationException("暂不支持方法prepareCall");
    }

    @Override
    public String nativeSQL(String sql) {
        throw new UnsupportedOperationException("暂不支持方法nativeSQL");
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
    }

    @Override
    public boolean getAutoCommit() {
        return false;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() throws SQLException {
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public DatabaseMetaData getMetaData() {
        return null;
    }

    @Override
    public void setReadOnly(boolean readOnly) {

    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void setCatalog(String catalog) {

    }

    @Override
    public String getCatalog() {
        return null;
    }

    @Override
    public void setTransactionIsolation(int level) {

    }

    @Override
    public int getTransactionIsolation() {
        return Connection.TRANSACTION_NONE;
    }

    @Override
    public SQLWarning getWarnings() {
        return null;
    }

    @Override
    public void clearWarnings() {

    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) {
        PreparedStatementImpl statement = new PreparedStatementImpl(this, sql);
        statement.setResultSetType(resultSetType);
        statement.setResultSetConcurrency(resultSetConcurrency);
        return statement;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) {
        throw new UnsupportedOperationException("暂不支持方法prepareCall");
    }

    @Override
    public Map<String, Class<?>> getTypeMap() {
        return null;
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) {

    }

    @Override
    public void setHoldability(int holdability) {

    }

    @Override
    public int getHoldability() {
        return 0;
    }

    @Override
    public Savepoint setSavepoint() {
        return null;
    }

    @Override
    public Savepoint setSavepoint(String name) {
        return null;
    }

    @Override
    public void rollback(Savepoint savepoint) {

    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) {

    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        return createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        return prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        throw new UnsupportedOperationException("暂不支持方法prepareCall");
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) {
        return prepareStatement(sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) {
        return prepareStatement(sql);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) {
        return prepareStatement(sql);
    }

    @Override
    public Clob createClob() {
        return null;
    }

    @Override
    public Blob createBlob() {
        return null;
    }

    @Override
    public NClob createNClob() {
        return null;
    }

    @Override
    public SQLXML createSQLXML() {
        return null;
    }

    @Override
    public boolean isValid(int timeout) {
        return false;
    }

    @Override
    public void setClientInfo(String name, String value) {

    }

    @Override
    public void setClientInfo(Properties properties) {

    }

    @Override
    public String getClientInfo(String name) {
        return null;
    }

    @Override
    public Properties getClientInfo() {
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) {
        return null;
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) {
        return null;
    }

    @Override
    public void setSchema(String schema) {

    }

    @Override
    public String getSchema() {
        return null;
    }

    @Override
    public void abort(Executor executor) throws SQLException {

    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) {

    }

    @Override
    public int getNetworkTimeout() {
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }

    private void createDsUrl(HostInfo hostInfo) {
        dbName = hostInfo.getDatabase();
        dsUrl = String.format("%s%s%s%s/%s", DS_URL_PREFIX_HTTP, hostInfo.getHostPortPair(), DS_URL_DATASONG,
                DS_URL_DATASERVICE, dbName);
    }

    private void createDsHttpClient(HostInfo hostInfo) throws DataSongException {
        try {
            Constructor<DataSongHttpClient> constructor = DataSongHttpClient.class.getDeclaredConstructor(String.class, String.class);
            constructor.setAccessible(true);
            dsHttpClient = constructor.newInstance(String.format("http://%s:%s/datasong/", hostInfo.getHost(), hostInfo.getPort()), dbName);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
