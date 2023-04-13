package com.iscas.biz.mp.test.mapper;


import com.iscas.biz.mp.test.model.Child;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@SuppressWarnings({"unused", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"})
@Mapper
@Repository
public interface ChildMapper {
    @Delete({
        "delete from child",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into child (id, a, pid)",
        "values (#{id,jdbcType=INTEGER}, #{a,jdbcType=VARCHAR}, #{pid,jdbcType=INTEGER})"
    })
    int insert(Child record);

    @Select({
        "select",
        "id, a, pid",
        "from child",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
        @Result(column = "a", property = "a", jdbcType = JdbcType.VARCHAR),
        @Result(column = "pid", property = "pid", jdbcType = JdbcType.INTEGER)
    })
    Child selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, a, pid",
        "from child"
    })
    @Results({
        @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
        @Result(column = "a", property = "a", jdbcType = JdbcType.VARCHAR),
        @Result(column = "pid", property = "pid", jdbcType = JdbcType.INTEGER)
    })
    List<Child> selectAll();

    @Update({
        "update child",
        "set a = #{a,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Child record);

    @Select({
            "select",
            "id, a, pid",
            "from child",
            "where pid = #{pid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "a", property = "a", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pid", property = "pid", jdbcType = JdbcType.INTEGER)
    })
    Child selectByPid(Integer pid);
}