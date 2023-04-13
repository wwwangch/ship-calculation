package com.iscas.biz.mp.table.service;


import com.iscas.biz.mp.table.service.interfaces.ITableDefinitionSqlCreatorService;
import com.iscas.templet.exception.Exceptions;

/**
 * oracle xxtable的sql拼接实现
 *
 * 注意，神州通用数据库需要将xxtable 和xxcolumn中配置的对应数据库的表名和列明字段为大写
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/2 13:53
 * @since jdk1.8
 */
public class OracleTableDefinitionSqlCreatorServiceImpl implements ITableDefinitionSqlCreatorService {

    @Override
    public String getTableByIdentifySql() {
        return "SELECT * FROM ${tableDefinitionTableName} WHERE TABLEIDENTITY = #{tableIdentity} AND ROWNUM < 2 ";
    }

    @Override
    public String getHeaderByIdentifySql() {
        return "SELECT * FROM ${columnDefinitionTableName} WHERE TABLEIDENTITY = #{tableIdentity} ORDER BY (SEQUENCE+'0')";
    }

    @Override
    public String getRefTableSql() {
        return "SELECT ${id} as \"id\",${value} as \"value\" FROM ${tableName} ORDER BY id";
    }

    @Override
    public String getTableColumnsSql() {
        throw Exceptions.unsupportedOperationException("oracle数据库暂不支持getTableColumns操作");
    }

    @Override
    public String sql() {
        return "${sql}";
    }

    @Override
    public String saveDataSql() {
        return sql();
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
