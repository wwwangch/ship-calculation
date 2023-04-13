package com.iscas.biz.mp.test.mapper;


import com.iscas.biz.mp.test.model.Parent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@SuppressWarnings({"unused", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "UnusedReturnValue"})
@Repository
@Mapper
public interface ParentMapper {
    @Delete({
        "delete from parent",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into parent (id, name)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(Parent record);

    @Select({
        "select",
        "id, name",
        "from parent",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Parent selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, name",
        "from parent"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Parent> selectAll();

    @Update({
        "update parent",
        "set name = #{name,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Parent record);

    @Select({
            "select",
            "id, name",
            "from parent"
    })
    @Results(id="response", value={
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column = "id", property = "childs"
                    ,many = @Many(select = "com.iscas.base.mp.test.mapper.ChildMapper.selectByPid"))

    })
    List<Parent> selectCascadeAll();

    @Select("<script>"
            + "SELECT id,name "
            + "FROM parent "
            + "<where> 1=1 "
            + "<if test='parent.id != null' > "
            +  " and id = #{parent.id}"
            + "</if>"
            + "</where>"
            + "</script>")
    List<Parent> selectCascadeAllByCondition(@Param("parent") Parent parent);

}