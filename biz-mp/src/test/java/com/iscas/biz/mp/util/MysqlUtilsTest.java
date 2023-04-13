package com.iscas.biz.mp.util;

import com.iscas.biz.mp.model.mysql.MysqlColumn;
import com.iscas.biz.mp.model.mysql.MysqlConstraint;
import com.iscas.biz.mp.model.mysql.MysqlRelation;
import com.iscas.biz.mp.model.mysql.MysqlTable;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * MysqlUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>5月 30, 2021</pre>
 */
@RunWith(JUnit4.class)
public class MysqlUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getTable(Connection conn, String dbName, String tableName)
     */
    @Test
    public void testGetTable() throws Exception {
        Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/quick-frame-samples?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true", "root", "root");

        MysqlTable mysqlTable = MysqlUtils.getTable(conn, "quick-frame-samples", "datatest");
        System.out.println(mysqlTable);
    }

    /**
     * Method: getTables(Connection conn, String dbName)
     */
    @Test
    public void testGetTables() throws Exception {
        Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/quick-frame-samples?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true", "root", "root");

        List<MysqlTable> tables = MysqlUtils.getTables(conn, "quick-frame-samples");
        tables.forEach(System.out::println);
    }

    /**
     * Method: getTables(Connection conn, String dbName)
     */
    @Test
    public void testGetColumns() throws Exception {
        Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/quick-frame-samples?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true", "root", "root");

        List<MysqlColumn> users = MysqlUtils.getColumn(conn, "quick-frame-samples", "user");
        users.forEach(System.out::println);
    }

    @Test
    public void testGetConstraint() throws SQLException, ClassNotFoundException {
        Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/quick-frame-samples?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true", "root", "root");

        List<MysqlConstraint> users = MysqlUtils.getConstraint(conn, "test", "test2");
        users.forEach(System.out::println);
    }

    @Test
    public void testGetRelationByChild() throws SQLException, ClassNotFoundException {
        Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/quick-frame-samples?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true", "root", "root");

        List<MysqlRelation> relations = MysqlUtils.getRelationByChild(conn, "test", "test2");
        relations.forEach(System.out::println);
    }

    @Test
    public void testGetRelationByMain() throws SQLException, ClassNotFoundException {
        Connection conn = JdbcUtils.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/quick-frame-samples?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&allowMultiQueries=true", "root", "root");

        List<MysqlRelation> relations = MysqlUtils.getRelationByMain(conn, "test", "test1");
        relations.forEach(System.out::println);
    }


} 
