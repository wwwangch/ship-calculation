package com.iscas.biz.mp.enhancer.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.iscas.biz.mp.enhancer.enums.CustomSqlMethod;
import org.apache.ibatis.mapping.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/23 16:22
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class FetchByStreamMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomSqlMethod sqlMethod = CustomSqlMethod.FETCH_BY_STREAM;
        String sql = String.format(sqlMethod.getSql(), sqlSelectColumns(tableInfo, true),
                tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo), sqlComment());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        String statementName = mapperClass.getName() + DOT + sqlMethod.getMethod();
        if (configuration.hasStatement(statementName, false)) {
            logger.warn(LEFT_SQ_BRACKET + statementName + "] Has been loaded by XML or SqlProvider or Mybatis's Annotation, so ignoring this injection for [" + getClass() + RIGHT_SQ_BRACKET);
            return null;
        }
        return builderAssistant.addMappedStatement(sqlMethod.getMethod(), sqlSource, StatementType.PREPARED, SqlCommandType.SELECT,
                1000, null, null, null, null, modelClass,
                ResultSetType.FORWARD_ONLY, true, true, false, null, null, null,
                configuration.getDatabaseId(), languageDriver, null);
    }
}
