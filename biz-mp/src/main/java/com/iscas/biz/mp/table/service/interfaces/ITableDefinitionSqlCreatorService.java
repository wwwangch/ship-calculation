package com.iscas.biz.mp.table.service.interfaces;

/**
 * TableDefinition的SQL拼接的接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/2 13:52
 * @since jdk1.8
 */
public interface ITableDefinitionSqlCreatorService {
    /**
     * 获取表信息
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String getTableByIdentifySql();

    /**
     * 获取表头
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String getHeaderByIdentifySql();

    /**
     * 获取关联表
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String getRefTableSql();

    /**
     * 获取列信息
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String getTableColumnsSql();

    /**
     * sql
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String sql();

    /**
     * 保存数据
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String saveDataSql();

    /**
     * 删除惧
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String deleteDataSql();

    /**
     * 批量删除
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String batchDeleteDataSql();

    /**
     * getCountByFieldSql
     * @since jdk11
     * @date 2022/4/17
     * @return java.lang.String
     */
    String getCountByFieldSql();

}
