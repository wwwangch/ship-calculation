package com.iscas.biz.mapper.common;

import com.iscas.biz.domain.common.Opration;
import com.iscas.biz.domain.common.Role;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhuquanwen
 */
@SuppressWarnings({"rawtypes", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"})
@Repository
public interface RoleMapper extends DynamicMapper<Role> {

    @Select("SELECT DISTINCT\n" +
            "\tt1.role_id,\n" +
            "\tt7.resource_id\n" +
            "FROM\n" +
            "\trole t1,\n" +
            "\trole_menu t2,\n" +
            "\tmenu t3,\n" +
            "\tmenu_opration t4,\n" +
            "\topration t5,\n" +
            "\topration_resource t6,\n" +
            "\tresources t7\n" +
            "WHERE\n" +
            "\tt1.role_id = t2.role_id\n" +
            "AND t2.menu_id = t3.menu_id\n" +
            "AND t3.menu_id = t4.menu_id\n" +
            "AND t4.op_id = t5.op_id\n" +
            "AND t5.op_id = t6.op_id\n" +
            "AND t6.resource_id = t7.resource_id\n" +
            "\n" +
            "union\n" +
            "\n" +
            "SELECT DISTINCT\n" +
            "\tt8.role_id,\n" +
            "\tt12.resource_id\n" +
            "FROM\n" +
            "\trole t8,\n" +
            "\trole_opration t9,\n" +
            "\topration t10,\n" +
            "\topration_resource t11,\n" +
            "\tresources t12\n" +
            "WHERE\n" +
            "\tt8.role_id = t9.role_id\n" +
            "AND t9.op_id = t10.op_id\n" +
            "AND t10.op_id = t11.op_id\n" +
            "AND t11.resource_id = t12.resource_id\n" +
            "\n" +
            "order by role_id")
    List<Map> selectRoleResource();


    @Select("select t2.* from role_opration t1, opration t2 where t1.op_id = t2.op_id and t1.role_id = #{roleId}")
    List<Opration> selectOprationByRoleId(@Param("roleId") Integer roleId);
}