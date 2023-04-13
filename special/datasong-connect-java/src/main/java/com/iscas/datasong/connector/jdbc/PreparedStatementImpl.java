package com.iscas.datasong.connector.jdbc;

import com.iscas.datasong.connector.util.DateSafeUtils;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/5 16:16
 * @since jdk11
 */
@SuppressWarnings("unused")
public class PreparedStatementImpl extends StatementImpl implements PreparedStatement {

    private String[] sqlSegment;

    private String[] sqlData;

    public PreparedStatementImpl(ConnectionImpl connection) {
        super(connection);
    }

    public PreparedStatementImpl(ConnectionImpl connection, String sql) {
        super(connection);
        sqlSegment = (sql + " ").split("\\?");
        sqlData = new String[sqlSegment.length - 1];
        Arrays.fill(sqlData, "?");
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return super.executeQuery(createExecuteSql());
    }

    @Override
    public int executeUpdate() throws SQLException {
        return super.executeUpdate(createExecuteSql());
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) {
        sqlData[parameterIndex - 1] = "null";
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setByte(int parameterIndex, byte x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setShort(int parameterIndex, short x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setInt(int parameterIndex, int x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setLong(int parameterIndex, long x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setFloat(int parameterIndex, float x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setDouble(int parameterIndex, double x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        sqlData[parameterIndex - 1] = String.valueOf(x);
    }


    @Override
    public void setString(int parameterIndex, String x) {
        if (x == null) {
            sqlData[parameterIndex - 1] = "null";
        } else {
            sqlData[parameterIndex - 1] = "'" + x + "'";
        }
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) {
        throw new UnsupportedOperationException("暂时不支持方法：setBytes");
    }

    private void setJavaUtilDate(int parameterIndex, java.util.Date x) {
        if (x == null) {
            sqlData[parameterIndex - 1] = "null";
        } else {
            String dateFormat = DateSafeUtils.format(x);
            sqlData[parameterIndex - 1] = "'" + dateFormat + "'";
        }
    }

    @Override
    public void setDate(int parameterIndex, Date x) {
        setJavaUtilDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) {
        setJavaUtilDate(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) {
        setJavaUtilDate(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        throw new UnsupportedOperationException("暂时不支持方法：setAsciiStream");
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) {
        throw new UnsupportedOperationException("暂时不支持方法：setUnicodeStream");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        throw new UnsupportedOperationException("暂时不支持方法：setBinaryStream");
    }

    @Override
    public void clearParameters() {
        Arrays.fill(sqlData, "?");
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) {
        if (x == null) {
            setNull(parameterIndex, 0);
        } else if (x instanceof Boolean) {
            setBoolean(parameterIndex, (Boolean) x);
        } else if (x instanceof Byte) {
            setByte(parameterIndex, (byte) x);
        } else if (x instanceof Short) {
            setShort(parameterIndex, (short) x);
        } else if (x instanceof Long) {
            setLong(parameterIndex, (long) x);
        } else if (x instanceof Float) {
            setFloat(parameterIndex, (float) x);
        } else if (x instanceof Double) {
            setDouble(parameterIndex, (double) x);
        } else if (x instanceof Integer) {
            setInt(parameterIndex, (int) x);
        } else if (x instanceof BigDecimal) {
            setBigDecimal(parameterIndex, (BigDecimal) x);
        } else if (x instanceof String) {
            setString(parameterIndex, (String) x);
        } else if (x instanceof byte[]) {
            setBytes(parameterIndex, (byte[]) x);
        } else if (x instanceof Date) {
            setDate(parameterIndex, (Date) x);
        } else if (x instanceof Time) {
            setTime(parameterIndex, (Time) x);
        } else if (x instanceof Timestamp) {
            setTimestamp(parameterIndex, (Timestamp) x);
        } else  {
            setString(parameterIndex, x.toString());
        }
    }

    @Override
    public void setObject(int parameterIndex, Object x) {
        setObject(parameterIndex, x, 0);
    }

    @Override
    public boolean execute() throws SQLException {
        return super.execute(createExecuteSql());
    }

    @Override
    public void addBatch() {
        super.addBatch(createExecuteSql());
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        throw new UnsupportedOperationException("暂时不支持方法：setCharacterStream");
    }

    @Override
    public void setRef(int parameterIndex, Ref x) {
        throw new UnsupportedOperationException("暂时不支持方法：setRef");
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) {
        throw new UnsupportedOperationException("暂时不支持方法：setBlob");
    }

    @Override
    public void setClob(int parameterIndex, Clob x) {
        throw new UnsupportedOperationException("暂时不支持方法：setClob");
    }

    @Override
    public void setArray(int parameterIndex, Array x) {
        throw new UnsupportedOperationException("暂时不支持方法：setArray");
    }

    @Override
    public ResultSetMetaData getMetaData() {
        throw new UnsupportedOperationException("暂时不支持方法：getMetaData");
    }

    private void setJavaUtilDate(int parameterIndex, java.util.Date x, Calendar cal) {
        TimeZone timeZone = cal.getTimeZone();
        String dateFormat = DateSafeUtils.format(x, timeZone);
        setString(parameterIndex, dateFormat);
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        setJavaUtilDate(parameterIndex, x, cal);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        setJavaUtilDate(parameterIndex, x, cal);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
        setJavaUtilDate(parameterIndex, x, cal);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) {
        setNull(parameterIndex, sqlType);
    }

    @Override
    public void setURL(int parameterIndex, URL x) {
        throw new UnsupportedOperationException("暂时不支持方法：setURL");
    }

    @Override
    public ParameterMetaData getParameterMetaData() {
        throw new UnsupportedOperationException("暂时不支持方法：getParameterMetaData");
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) {
        throw new UnsupportedOperationException("暂时不支持方法：setRowId");
    }

    @Override
    public void setNString(int parameterIndex, String value) {
        throw new UnsupportedOperationException("暂时不支持方法：setNString");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setNCharacterStream");
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) {
        throw new UnsupportedOperationException("暂时不支持方法：setNClob");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setClob");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setBlob");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setNClob");
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) {
        throw new UnsupportedOperationException("暂时不支持方法：setSQLXML");

    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) {
        setObject(parameterIndex, x, 0);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setAsciiStream");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setBinaryStream");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) {
        throw new UnsupportedOperationException("暂时不支持方法：setCharacterStream");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) {
        throw new UnsupportedOperationException("暂时不支持方法：setAsciiStream");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) {
        throw new UnsupportedOperationException("暂时不支持方法：setBinaryStream");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) {
        throw new UnsupportedOperationException("暂时不支持方法：setCharacterStream");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) {
        throw new UnsupportedOperationException("暂时不支持方法：setNCharacterStream");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) {
        throw new UnsupportedOperationException("暂时不支持方法：setClob");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) {
        throw new UnsupportedOperationException("暂时不支持方法：setBlob");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) {
        throw new UnsupportedOperationException("暂时不支持方法：setNClob");
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) {
        setObject(parameterIndex, x, 0);
    }

    @Override
    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) {
        setObject(parameterIndex, x, 0);
    }

    @Override
    public long executeLargeUpdate() throws SQLException {
        return executeUpdate(createExecuteSql());
    }

    private String createExecuteSql() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sqlSegment.length; i++) {
            sb.append(sqlSegment[i]);
            if (i < sqlData.length) {
                sb.append(sqlData[i]);
            }
        }
        return sb.toString();
    }
}
