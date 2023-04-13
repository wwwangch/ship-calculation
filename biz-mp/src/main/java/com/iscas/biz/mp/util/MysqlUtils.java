package com.iscas.biz.mp.util;

import cn.hutool.core.collection.CollectionUtil;
import com.iscas.biz.mp.model.mysql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/30 9:48
 * @since jdk1.8
 */
public class MysqlUtils {
    private static final String PRIMARY_KEY = "PRIMARY KEY";
    private static final String UNIQUE = "UNIQUE";
    private static final String FOREIGN_KEY = "FOREIGN KEY";

    private MysqlUtils() {}

    public static List<MysqlColumn> getColumn(Connection conn, String dbName, String tableName) throws SQLException {
        try {
            List<MysqlColumn> cols = new ArrayList<>();
            String sql = "select * from information_schema.COLUMNS where TABLE_SCHEMA = ? AND TABLE_NAME = ?";
            ResultSet rs = JdbcUtils.query(conn, sql, Arrays.asList(dbName, tableName));
            while (rs.next()) {
                cols.add(convertOneTableCols(rs));
            }
            return cols;
        } finally {
            try {
                JdbcUtils.closeConn(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private static MysqlColumn convertOneTableCols(ResultSet rs) throws SQLException {
        MysqlColumn column = new MysqlColumn();
        column.setTableName(rs.getString("TABLE_NAME"));
        column.setColName(rs.getString("COLUMN_NAME"));
        column.setDefaultVal(rs.getObject("COLUMN_DEFAULT"));
        column.setNullable(Objects.equals("YES", rs.getString("IS_NULLABLE")));
        column.setDataType(rs.getString("DATA_TYPE"));
        column.setOrdinalPosition(rs.getInt("ORDINAL_POSITION"));
        column.setComment(rs.getString("COLUMN_COMMENT"));
        column.setTableSchema(rs.getString("TABLE_SCHEMA"));
        column.setCharMaxLen(rs.getObject("CHARACTER_MAXIMUM_LENGTH") == null ? null :
                rs.getLong("CHARACTER_MAXIMUM_LENGTH"));
        column.setCharOctetLen(rs.getObject("CHARACTER_OCTET_LENGTH") == null ? null :
                rs.getLong("CHARACTER_OCTET_LENGTH"));
        column.setCharset(rs.getString("CHARACTER_SET_NAME"));
        column.setCollation(rs.getString("COLLATION_NAME"));
        column.setColumnType(rs.getString("COLUMN_TYPE"));
        column.setNumPrecision(rs.getObject("NUMERIC_PRECISION") == null ? null :
                rs.getLong("NUMERIC_PRECISION"));
        column.setNumScale(rs.getObject("NUMERIC_SCALE") == null ? null :
                rs.getLong("NUMERIC_SCALE"));
        return column;
    }

    public static MysqlTable getTable(Connection conn, String dbName, String tableName) throws SQLException {
        try {
            String sql = "select * from information_schema.TABLES where TABLE_SCHEMA = ? AND TABLE_NAME = ?";
            ResultSet rs = JdbcUtils.query(conn, sql, Arrays.asList(dbName, tableName));
            if (rs.next()) {
                return convertOneTable(rs);
            }
            return null;
        } finally {
            try {
                JdbcUtils.closeConn(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static List<MysqlTable> getTables(Connection conn, String dbName) throws SQLException {
        try {
            List<MysqlTable> tables = new ArrayList<>();
            String sql = "select * from information_schema.TABLES where TABLE_SCHEMA = ?";
            ResultSet rs = JdbcUtils.query(conn, sql, List.of(dbName));
            while (rs.next()) {
                tables.add(convertOneTable(rs));
            }
            return tables;
        } finally {
            try {
                JdbcUtils.closeConn(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static MysqlTable convertOneTable(ResultSet rs) throws SQLException {
        MysqlTable table = new MysqlTable();
        table.setName(rs.getString("TABLE_NAME"));
        table.setComment(rs.getString("TABLE_COMMENT"));
        table.setSchema(rs.getString("TABLE_SCHEMA"));
        table.setType(rs.getString("TABLE_TYPE"));
        table.setEngine(rs.getString("ENGINE"));
        table.setVersion(rs.getObject("VERSION") == null ? null  : rs.getObject("VERSION").toString());
        table.setRowFormat(rs.getObject("ROW_FORMAT") == null ? null  : rs.getObject("ROW_FORMAT").toString());
        table.setRows(rs.getObject("TABLE_ROWS") == null ? 0L  : rs.getLong("TABLE_ROWS"));
        table.setCreateTime(rs.getObject("CREATE_TIME") == null ? null : rs.getDate("CREATE_TIME"));
        table.setUpdateTime(rs.getObject("UPDATE_TIME") == null ? null : rs.getDate("UPDATE_TIME"));
        table.setCollation(rs.getString("TABLE_COLLATION"));
        return table;
    }

    public static List<MysqlConstraint> getConstraint(Connection conn, String dbName, String tableName) throws SQLException {
        try {
            List<MysqlStatistic> statistics = getStatistic(conn, dbName, tableName);
            List<MysqlRelation> relations = doGetRelationByChild(conn, dbName, tableName);
            List<MysqlConstraint> constraints = new ArrayList<>();
            String sql = "select * from information_schema.TABLE_CONSTRAINTS where TABLE_SCHEMA = ? and TABLE_NAME = ?";
            ResultSet rs = JdbcUtils.query(conn, sql, Arrays.asList(dbName, tableName));
            while (rs.next()) {
                constraints.add(convertOneConstraint(rs, statistics, relations));
            }
            return constraints;
        } finally {
            try {
                JdbcUtils.closeConn(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public static List<MysqlRelation> getRelationByChild(Connection conn, String dbName, String tableName) throws SQLException {
        try (conn) {
            return doGetRelationByChild(conn, dbName, tableName);
        }
    }


    private static List<MysqlRelation> doGetRelationByChild(Connection conn, String dbName, String tableName) throws SQLException {
        List<MysqlRelation> relations = new ArrayList<>();
        String sql = "select * from information_schema.KEY_COLUMN_USAGE where TABLE_SCHEMA =? and TABLE_NAME = ?" +
                " and REFERENCED_TABLE_NAME is not null order by CONSTRAINT_NAME, ORDINAL_POSITION";
        ResultSet rs = JdbcUtils.query(conn, sql, Arrays.asList(dbName, tableName));
        Set<String> consSet = new HashSet<>();
        MysqlRelation relation = null;
        while (rs.next()) {
            String consName = rs.getString("CONSTRAINT_NAME");
            String colName = rs.getString("COLUMN_NAME");
            String reTableName = rs.getString("REFERENCED_TABLE_NAME");
            String reColName = rs.getString("REFERENCED_COLUMN_NAME");
            if (!consSet.contains(consName)) {
                if (relation != null) {
                    relations.add(relation);
                }
                relation = new MysqlRelation();
            }
            relation.setConstrainName(consName);
            relation.setChildTableName(tableName);
            relation.setMainTableName(reTableName);
            relation.getChildCols().add(colName);
            relation.getMainCols().add(reColName);
            consSet.add(consName);
        }
        if (relation != null) {
            relations.add(relation);
        }
        return relations;
    }

    public static List<MysqlRelation> getRelationByMain(Connection conn, String dbName, String tableName) throws SQLException {
        try {
            List<MysqlRelation> relations = new ArrayList<>();
            String sql = "select * from information_schema.KEY_COLUMN_USAGE where TABLE_SCHEMA =? and REFERENCED_TABLE_NAME = ?" +
                    " and TABLE_NAME is not null order by CONSTRAINT_NAME, ORDINAL_POSITION";
            ResultSet rs = JdbcUtils.query(conn, sql, Arrays.asList(dbName, tableName));
            Set<String> consSet = new HashSet<>();
            MysqlRelation relation = null;
            while (rs.next()) {
                String consName = rs.getString("CONSTRAINT_NAME");
                String colName = rs.getString("COLUMN_NAME");
                String childTableName = rs.getString("TABLE_NAME");
                String reColName = rs.getString("REFERENCED_COLUMN_NAME");
                if (!consSet.contains(consName)) {
                    if (relation != null) {
                        relations.add(relation);
                    }
                    relation = new MysqlRelation();
                }
                relation.setConstrainName(consName);
                relation.setChildTableName(childTableName);
                relation.setMainTableName(tableName);
                relation.getChildCols().add(colName);
                relation.getMainCols().add(reColName);
                consSet.add(consName);
            }
            if (relation != null) {
                relations.add(relation);
            }
            return relations;
        } finally {
            JdbcUtils.closeConn(conn);
        }
    }


    private static List<MysqlStatistic> getStatistic(Connection conn, String dbName, String tableName) throws SQLException {
        List<MysqlStatistic> statistics = new ArrayList<>();
        String sql = "select * from information_schema.STATISTICS where TABLE_SCHEMA = ? and TABLE_NAME = ?" +
                " order by SEQ_IN_INDEX";
        ResultSet rs = JdbcUtils.query(conn, sql, Arrays.asList(dbName, tableName));
        while (rs.next()) {
            statistics.add(convertOneStatistic(rs));
        }
        return statistics;
    }

    private static MysqlStatistic convertOneStatistic(ResultSet rs) throws SQLException {
        MysqlStatistic statistic = new MysqlStatistic();
        statistic.setSchema(rs.getString("TABLE_SCHEMA"));
        statistic.setTableName(rs.getString("TABLE_NAME"));
        statistic.setIndexName(rs.getString("INDEX_NAME"));
        statistic.setColumnName(rs.getString("COLUMN_NAME"));
        statistic.setSeq(rs.getInt("SEQ_IN_INDEX"));
        return statistic;
    }

    private static MysqlConstraint convertOneConstraint(ResultSet rs, List<MysqlStatistic> statistics,
                                                        List<MysqlRelation> relations) throws SQLException {
        MysqlConstraint constraint = new MysqlConstraint();
        constraint.setTableName(rs.getString("TABLE_NAME"));
        constraint.setConstraint(rs.getString("CONSTRAINT_NAME"));
        String constraintType = rs.getString("CONSTRAINT_TYPE");
        if (Objects.equals(PRIMARY_KEY, constraintType)) {
            constraint.setConstraintType(MysqlConstraint.ConstraintEnum.P);
        } else if (Objects.equals(UNIQUE, constraintType)) {
            constraint.setConstraintType(MysqlConstraint.ConstraintEnum.U);
        } else if (Objects.equals(FOREIGN_KEY, constraintType)) {
            constraint.setConstraintType(MysqlConstraint.ConstraintEnum.R);
        }
        if (CollectionUtil.isNotEmpty(statistics)) {
            for (MysqlStatistic statistic : statistics) {
                if (Objects.equals(statistic.getIndexName(), constraint.getConstraint())) {
                    constraint.getColNames().add(statistic.getColumnName());
                }
            }
        }
        if (CollectionUtil.isNotEmpty(relations)) {
            for (MysqlRelation relation : relations) {
                if (Objects.equals(relation.getConstrainName(), constraint.getConstraint())) {
                    constraint.getColNames().addAll(relation.getChildCols());
                }
            }
        }
        return constraint;
    }


}
