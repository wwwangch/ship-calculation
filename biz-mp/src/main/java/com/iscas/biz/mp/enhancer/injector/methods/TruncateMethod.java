package com.iscas.biz.mp.enhancer.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.iscas.biz.mp.enhancer.enums.CustomSqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * truncate表
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/23 13:57
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class TruncateMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomSqlMethod sqlMethod = CustomSqlMethod.TRUNCATE;
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName());
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addDeleteMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource);
    }
}
