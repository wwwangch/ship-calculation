package com.iscas.biz.mp.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * mybatis关闭自动提交的工具类
 * 可以自定义sessionfactory,
 * 为了连接非配置文件内的数据库，
 * 适用于数据归并或抽取时
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/10 0010 下午 15:48
 * @since jdk11
 */
@SuppressWarnings("unused")
public class MybatisUnCommitUtils {

    private static Map<String, String> createSqlMap(String sql) {
        Map<String, String> sqlMap = new HashMap<>(1);
        sqlMap.put("sql", sql);
        return sqlMap;
    }

    /**
     * 获取SqlSession
     * */
    public static SqlSession getSqlSession(SqlSessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }


    @SuppressWarnings("rawtypes")
    public static List<Map> executeSearch(SqlSession session, String sql) {
        Map<String, String> sqlMap = createSqlMap(sql);
        List<Map> result;
        String method = "com.iscas.biz.mp.enhancer.mapper.DynamicMapper.dynamicSelect";
        result = session.selectList(method, sqlMap);
        return result;

    }

    public static void executeInsert(SqlSession session, String sql) {
        Map<String, String> sqlMap = createSqlMap(sql);
        String method = "com.iscas.biz.mp.enhancer.mapper.DynamicMapper.dynamicInsert";
        session.insert(method, sqlMap);
    }


    public static void executeUpdate(SqlSession session, String sql) {
        Map<String, String> sqlMap = createSqlMap(sql);
        String method = "com.iscas.biz.mp.enhancer.mapper.DynamicMapper.dynamicUpdate";
        session.update(method, sqlMap);
    }


    public static void executeDelete(SqlSession session, String sql) {
        Map<String, String> sqlMap = createSqlMap(sql);
        String method = "com.iscas.biz.mp.enhancer.mapper.DynamicMapper.dynamicDelete";
        session.update(method, sqlMap);
    }

    /**
     * 批量提交
     * forceExecute 表示是否强制提交，sqls的size小于batchSize
     * */
    public static void executeBatch(SqlSession sqlSession, List<String> sqls, int batchSize, boolean forceExecute) throws SQLException {

        if (sqls == null) {
            return;
        }
        if (sqls.size() < batchSize && !forceExecute) {
            return;
        }

        Connection conn = sqlSession.getConnection();
        conn.setAutoCommit(false);
        try {
            Statement statement = conn.createStatement();
            for (String sql : sqls) {
                statement.addBatch(sql);
            }
            statement.executeBatch();
            conn.commit();
            sqls.clear();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw e;
        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
