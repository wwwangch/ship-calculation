package com.iscas.biz.mp.util;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * jdbc操作工具类
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 9:29
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class JdbcUtils {

    private JdbcUtils() {}

    public static Connection getConnection(String driverClassName, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        return getConnection(driverClassName, url, properties);
    }

    public static Connection getConnection(String driverClassName, String url, Properties properties) throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        return DriverManager.getConnection(url, properties);
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
}
