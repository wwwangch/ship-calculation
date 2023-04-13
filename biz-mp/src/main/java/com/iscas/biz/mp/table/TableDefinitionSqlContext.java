package com.iscas.biz.mp.table;

import com.iscas.biz.mp.table.service.interfaces.ITableDefinitionSqlCreatorService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * 生成TableDefinition需要的SQL
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/2 13:27
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Component
public class TableDefinitionSqlContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        TableDefinitionSqlContext.applicationContext = applicationContext;
    }

    public TableDefinitionSqlContext() {}

    public String getTableByIdentify() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .getTableByIdentifySql();
    }

    public String getHeaderByIdentify() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .getHeaderByIdentifySql();
    }

    public String getRefTable() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .getRefTableSql();
    }

    public String getDataBySql() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .sql();
    }

    public String getCountBySql() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .sql();
    }

    public String getTableColumns() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .getTableColumnsSql();
    }

    public String saveData() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .saveDataSql();
    }

    public String updateData() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .sql();
    }

    public String deleteData() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .deleteDataSql();
    }

    public String batchDeleteData() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .batchDeleteDataSql();
    }

    public String getCountByField() {
        return applicationContext.getBean(ITableDefinitionSqlCreatorService.class)
                .getCountByFieldSql();
    }

}
