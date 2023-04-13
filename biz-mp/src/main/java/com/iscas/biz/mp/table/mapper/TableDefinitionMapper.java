package com.iscas.biz.mp.table.mapper;


import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.TableDefinitionSqlContext;
import com.iscas.biz.mp.table.model.ColumnDefinition;
import com.iscas.biz.mp.table.model.TableDefinition;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author ZQM
 * @date 2016/5/27
 *
 *  * <p>为了适配多种数据库的方言，重新实现SQL,使用SelectProvider动态适配第一个数据源的数据库类型，</p>
 *  * <p>生成对应的SQL语句(xxtable在第一个数据源内) update by zqw 2021-12-02</p>
 */
@SuppressWarnings({"AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "unused"})
@Mapper
@Repository
@ConditionalOnMybatis
public interface TableDefinitionMapper {
	@SelectProvider(method = "getTableByIdentify", type = TableDefinitionSqlContext.class)
	TableDefinition getTableByIdentify(@Param("tableDefinitionTableName") String tableDefinitionTableName, @Param("tableIdentity") String tableIdentity);

	@SelectProvider(method = "getHeaderByIdentify", type = TableDefinitionSqlContext.class)
	List<ColumnDefinition> getHeaderByIdentify(@Param("columnDefinitionTableName") String columnDefinitionTableName, @Param("tableIdentity") String tableIdentity);

	@SelectProvider(method = "getRefTable", type = TableDefinitionSqlContext.class)
	List<Map<Object,Object>> getRefTable(@Param("tableName") String tableName, @Param("id") String id, @Param("value") String value);

	@SelectProvider(method = "getDataBySql", type = TableDefinitionSqlContext.class)
	List<Map<String,Object>> getDataBySql(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@SelectProvider(method = "getCountBySql", type = TableDefinitionSqlContext.class)
	int getCountBySql(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@SelectProvider(method = "getTableColumns", type = TableDefinitionSqlContext.class)
	List<String> getTableColumns(@Param("tableName") String tableName);

	@InsertProvider(method = "saveData", type = TableDefinitionSqlContext.class)
	@Options(useGeneratedKeys=true, keyProperty= "param.id")
	int saveData(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@UpdateProvider(method = "updateData", type = TableDefinitionSqlContext.class)
	int updateData(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@DeleteProvider(method = "deleteData", type = TableDefinitionSqlContext.class)
	int deleteData(@Param("tableName") String tableName, @Param("primaryKey") String primaryKey, @Param("value") Object value);

	@DeleteProvider(method = "batchDeleteData", type = TableDefinitionSqlContext.class)
	int batchDeleteData(@Param("tableName") String tableName, @Param("primaryKey") String primaryKey, @Param("ids") List<Object> ids);

	@SelectProvider(method = "getCountByField", type = TableDefinitionSqlContext.class)
	int getCountByField(@Param("selectSql") String selectSql, @Param("field") String field, @Param("fieldValue") Object fieldValue);
}
