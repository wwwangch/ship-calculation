package com.iscas.datasong.connector.jdbc;

import com.iscas.datasong.connector.Constants;
import com.iscas.datasong.connector.conf.ConnectionUrl;
import com.iscas.datasong.connector.conf.HostInfo;
import com.iscas.datasong.connector.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 驱动
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/29 16:10
 * @since jdk1.8
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class Driver implements java.sql.Driver {
    static {
        try {
            java.sql.DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException("Can't register driver!");
        }
    }

    public Driver() {
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (!ConnectionUrl.acceptsUrl(url)) {
            return null;
        }
        return ConnectionImpl.getInstance(HostInfo.getInstance(url, info));
    }

    @Override
    public boolean acceptsURL(String url) {
        //jdbc:datasong://192.168.100.88:3306/quick-frame-samples
        return ConnectionUrl.acceptsUrl(url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return StringUtils.safeIntParse(Constants.MAJOR_VERSION);
    }

    @Override
    public int getMinorVersion() {
        return StringUtils.safeIntParse(Constants.MINOR_VERSION);
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }
}
