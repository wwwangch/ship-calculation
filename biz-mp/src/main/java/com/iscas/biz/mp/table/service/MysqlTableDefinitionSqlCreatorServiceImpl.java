package com.iscas.biz.mp.table.service;


import com.iscas.biz.mp.table.service.interfaces.ITableDefinitionSqlCreatorService;

/**
 * mysql xxtable的sql拼接实现
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/2 13:53
 * @since jdk1.8
 */
public class MysqlTableDefinitionSqlCreatorServiceImpl implements ITableDefinitionSqlCreatorService {
    @Override
    public String getTableByIdentifySql() {
        return "SELECT * FROM ${tableDefinitionTableName} WHERE `tableIdentity` = #{tableIdentity} LIMIT 0,1 ";
    }

    @Override
    public String getHeaderByIdentifySql() {
        return "SELECT * FROM ${columnDefinitionTableName} WHERE `tableIdentity` = #{tableIdentity} ORDER BY sequence";
    }

    @Override
    public String getRefTableSql() {
        return "SELECT ${id} as id,${value} as value FROM ${tableName} ORDER BY id";
    }

    @Override
    public String getTableColumnsSql() {
        return "SELECT COLUMN_NAME from INFORMATION_SCHEMA.columns where TABLE_NAME = #{tableName} AND TABLE_SCHEMA = (select database())";
    }

    @Override
    public String sql() {
        return "${sql}";
    }

    @Override
    public String saveDataSql() {
        return sql() + ";SELECT @@Identity";
    }

    @Override
    public String deleteDataSql() {
        return "delete from ${tableName} where  ${primaryKey} = #{value}";
    }

    @Override
    public String batchDeleteDataSql() {
        return "<script>delete from ${tableName} where  ${primaryKey} in <foreach collection=\"ids\" index=\"index\" item=\"item\" open=\"(\" close=\")\" separator=\",\">  " +
                "     #{item}       " +
                "        </foreach></script>  ";
    }

    @Override
    public String getCountByFieldSql() {
        return "SELECT COUNT(${field}) AS COUNT from (${selectSql}) t where t.${field} = #{fieldValue}";
    }

}
