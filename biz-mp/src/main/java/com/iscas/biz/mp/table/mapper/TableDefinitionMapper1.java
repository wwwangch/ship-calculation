package com.iscas.biz.mp.table.mapper;
import com.iscas.biz.mp.table.model.ColumnDefinition;
import com.iscas.biz.mp.table.model.TableDefinition;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ZQM
 * @date 2016/5/27
 */
@SuppressWarnings({"AlibabaLowerCamelCaseVariableNaming", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "unused"})
@Mapper
@Repository
public interface TableDefinitionMapper1 {
	@Select("SELECT * FROM ${tableDefinitionTableName} WHERE TABLEIDENTITY = #{TABLEIDENTITY} LIMIT 0,1 ")
	TableDefinition getTableByIdentify(@Param("tableDefinitionTableName") String tableDefinitionTableName, @Param("TABLEIDENTITY") String TABLEIDENTITY);

	@Select("SELECT * FROM ${columnDefinitionTableName} WHERE TABLEIDENTITY = #{TABLEIDENTITY} ORDER BY (SEQUENCE+'0')")
	List<ColumnDefinition> getHeaderByIdentify(@Param("columnDefinitionTableName") String columnDefinitionTableName, @Param("TABLEIDENTITY") String TABLEIDENTITY);

	@Select("SELECT ${id} as id,${value} as value FROM ${TABLENAME} ORDER BY ID")
	List<Map<Object,Object>> getRefTable(@Param("TABLENAME") String TABLENAME, @Param("id") String id, @Param("value") String value);

	@Select("${sql}")
	List<Map<String,Object>> getDataBySql(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@Select("${sql}")
	int getCountBySql(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@Select("SELECT COLUMN_NAME from INFORMATION_SCHEMA.columns where TABLE_NAME = #{TABLENAME} AND TABLE_SCHEMA = (select database())")
	List<String> getTableColumns(@Param("TABLENAME") String TABLENAME);


	@Insert("${sql}")//需要用replace
	@Options(useGeneratedKeys=true, keyProperty= "param.id")
	int saveData(@Param("sql") String sql, @Param("param") Map<String, Object> param);


	@Update("${sql}")
	int updateData(@Param("sql") String sql, @Param("param") Map<String, Object> param);

	@Delete("delete from ${TABLENAME} where  ${primaryKey} = #{value}")
	int deleteData(@Param("TABLENAME") String TABLENAME, @Param("primaryKey") String primaryKey, @Param("value") Object value);


	@Delete("<script>delete from ${TABLENAME} where  ${primaryKey} in <foreach collection=\"ids\" index=\"index\" item=\"item\" open=\"(\" close=\")\" separator=\",\">  " +
			"     #{item}       " +
			"        </foreach></script>  ")
	int batchDeleteData(@Param("TABLENAME") String TABLENAME, @Param("primaryKey") String primaryKey, @Param("ids") List<Object> ids);


	@Select("SELECT COUNT(${field}) AS COUNT from (${selectSql}) t where t.${field} = #{fieldValue}")
	int getCountByField(@Param("selectSql") String selectSql, @Param("field") String field, @Param("fieldValue") Object fieldValue);
}
