package com.iscas.biz.mp.config.db;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.MysqlTableDefinitionSqlCreatorServiceImpl;
import com.iscas.biz.mp.table.service.OracleTableDefinitionSqlCreatorServiceImpl;
import com.iscas.biz.mp.table.service.OscarTableDefinitionSqlCreatorServiceImpl;
import com.iscas.biz.mp.table.service.interfaces.ITableDefinitionSqlCreatorService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import java.text.MessageFormat;

/**
 * 配置TableDefinition
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/2 13:50
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@ConditionalOnMybatis
@AutoConfiguration
public class TableDefinitionSqlCreatorConfig implements EnvironmentAware {
    private Environment environment;

    public static String mode = "mysql";

    private static final String MYSQL = ".mysql.";
    private static final String OSCAR = ".oscar.";
    private static final String ORACLE = ".OracleDriver";

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ITableDefinitionSqlCreatorService tableDefinitionSqlCreatorService() {
        String datasourceNames = environment.getProperty("spring.datasource.names");
        //取第一个数据源
        assert datasourceNames != null;
        String db1Name = datasourceNames.split(",")[0];
        String driverClassNameKey = MessageFormat.format("spring.datasource.druid.{0}.driver-class-name", db1Name);
        String driverClassName = environment.getProperty(driverClassNameKey);

        //暂时按照driverClassName判断
        assert driverClassName != null;
        if (driverClassName.contains(MYSQL)) {
            mode = "mysql";
            return new MysqlTableDefinitionSqlCreatorServiceImpl();
        } else if (driverClassName.contains(OSCAR)) {
            mode = "oscar";
            return new OscarTableDefinitionSqlCreatorServiceImpl();
        } else if (driverClassName.contains(ORACLE)) {
            mode = "oracle";
            return new OracleTableDefinitionSqlCreatorServiceImpl();
        } else {
            //暂时默认都以为跟mysql语句一样吧
            return new MysqlTableDefinitionSqlCreatorServiceImpl();
        }
    }
}
