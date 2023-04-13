package com.iscas.common.access.tools.util;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/2 14:12
 * @since jdk11
 */
@SuppressWarnings("unused")
public class AccessJdbcUtils {
    private static final String DRIVER_CLASS_NAME = "net.ucanaccess.jdbc.UcanaccessDriver";
    private AccessJdbcUtils() {}

    public static Connection getConnection(String path, String username, String password) throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        return getConnection(path, properties);
    }

    public static Connection getConnection(String path, Properties properties) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS_NAME);
        return DriverManager.getConnection(getUrl(path), properties);
    }

    public static Statement createStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }

    public static PreparedStatement createPrepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    public static ResultSet query(Connection conn, String sql) throws SQLException {
        Statement statement = createStatement(conn);
        return statement.executeQuery(sql);
    }

    public static ResultSet query(Connection conn, String sql, List<Object> params) throws SQLException {
        PreparedStatement statement = createPrepareStatement(conn, sql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
        }
        return statement.executeQuery();
    }

    public static void closeConn(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void closeRs(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public static void closeRsAndStatement(ResultSet rs) throws SQLException {
        if (rs != null) {
            Statement statement = rs.getStatement();
            rs.close();
            statement.close();
        }
    }

    private static String getUrl(String path) {
        return "jdbc:ucanaccess://" + path;
    }
}
