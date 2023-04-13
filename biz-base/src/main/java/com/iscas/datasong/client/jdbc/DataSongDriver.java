package com.iscas.datasong.client.jdbc;

import com.iscas.templet.exception.Exceptions;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

@SuppressWarnings("ALL")
public class DataSongDriver implements Driver {
    public final static String DEFAULT_PREFIX = "jdbc:datasong:";
    public final static String DRIVER_PREFIX = "driver=";
    public final static String PASSWORD_CALLBACK_PREFIX = "passwordCallback=";
    public final static String NAME_PREFIX = "name=";
    public final static String FILTERS_PREFIX = "filters=";

    private static DataSongDriver registeredDriver;

    private String acceptPrefix = DEFAULT_PREFIX;
    private int majorVersion = 4;
    private int minorVersion = 0;

    static {
        try {
            register();
        } catch (SQLException e) {
            throw Exceptions.exceptionInInitializerError(e);
        }
    }

    public static boolean isRegistered() {
        return registeredDriver != null;
    }

    public static void register() throws SQLException {
        if (isRegistered()) {
            throw Exceptions.illegalStateException(
                    "Driver is already registered. It can only be registered once.");
        }
        DataSongDriver registeredDriver = new DataSongDriver();
        DriverManager.registerDriver(registeredDriver);
        DataSongDriver.registeredDriver = registeredDriver;
    }

    public static void deregister() throws SQLException {
        if (!isRegistered()) {
            throw Exceptions.illegalStateException("Driver is not registered (or it has not been registered using Driver.register() method)");
        }
        DriverManager.deregisterDriver(registeredDriver);
        registeredDriver = null;
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        if (!url.startsWith("jdbc:datasong://")) {
            return null;
        }
        DataSongDataSource dataSongDataSource = new DataSongDataSource(url);
        return dataSongDataSource.getConnection();
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        if (null == url || url.isEmpty()) {
            return false;
        }

        if (url.startsWith(acceptPrefix)) {
            return true;
        }

        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        if (!acceptsURL(url)) {
            return new DriverPropertyInfo[0];
        }

        String[] choices = new String[]{
                "true", "false"
        };
        DriverPropertyInfo[] pinfo = new DriverPropertyInfo[6];
        DriverPropertyInfo p;

        if (info == null) {
            info = new Properties();
        }
        p = new DriverPropertyInfo("user", null);
        p.value = info.getProperty("user");
        p.required = true;
        pinfo[0] = p;
        p = new DriverPropertyInfo("password", null);
        p.value = info.getProperty("password");
        p.required = true;
        pinfo[1] = p;
        p = new DriverPropertyInfo("get_column_name", null);
        p.value = info.getProperty("get_column_name", "true");
        p.required = false;
        p.choices = choices;
        pinfo[2] = p;
        p = new DriverPropertyInfo("ifexists", null);
        p.value = info.getProperty("ifexists", "false");
        p.required = false;
        p.choices = choices;
        pinfo[3] = p;
        p = new DriverPropertyInfo("default_schema", null);
        p.value = info.getProperty("default_schema", "false");
        p.required = false;
        p.choices = choices;
        pinfo[4] = p;
        p = new DriverPropertyInfo("shutdown", null);
        p.value = info.getProperty("shutdown", "false");
        p.required = false;
        p.choices = choices;
        pinfo[5] = p;

        return pinfo;
    }

    @Override
    public int getMajorVersion() {
        return this.majorVersion;
    }

    @Override
    public int getMinorVersion() {
        return this.minorVersion;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw Exceptions.sqlFeatureNotSupportedException();
    }
}
