package com.iscas.common.access.tools.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/8/2 14:17
 * @since jdk11
 */
class AccessJdbcUtilsTest {
    private Connection conn;

    @BeforeEach
    void getConnection() throws SQLException, ClassNotFoundException {
        String path = "D:\\文档资料\\数据中台\\测试数据\\Flights\\flights.mdb";
        conn = AccessJdbcUtils.getConnection(path, "", "");
    }

    @Test
    public void testReadDomestic() throws SQLException, IOException {
        String sql = "SELECT * FROM Domestic";
        Statement statement = AccessJdbcUtils.createStatement(conn);
        ResultSet rs = statement.executeQuery(sql);
        try (PrintWriter pw = new PrintWriter("d:/tmp/Domesic.csv", Charset.forName("gbk"))) {
            String str;
            while (rs.next()) {
                str = rs.getString("Address_en") + "," + rs.getString("Address_cn") +
                        "," + rs.getString("Abbreviation");
                pw.println(str);
            }
        }
    }

    @Test
    public void testReadAirline() throws SQLException, IOException {
        String sql = "SELECT * FROM AirlineDate2";
        Statement statement = AccessJdbcUtils.createStatement(conn);
        ResultSet rs = statement.executeQuery(sql);
        try (PrintWriter pw = new PrintWriter("d:/tmp/Airline.csv", Charset.forName("gbk"))) {
            String str;
            while (rs.next()) {
                str = rs.getString("startCity") + "," + rs.getString("lastCity") +
                        "," + rs.getString("Company") + "," + rs.getString("AirlineCode") +
                        "," + rs.getString("StartDrome") + "," + rs.getString("ArriveDrome") +
                        "," + rs.getString("StartTime") + "," + rs.getString("ArriveTime") +
                        "," + rs.getString("Mode") + "," + rs.getString("AirlineStop") +
                        "," + rs.getString("Week");
                pw.println(str);
            }
        }
    }
}