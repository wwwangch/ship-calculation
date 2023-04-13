package com.iscas.biz.mapper.common;

import com.iscas.biz.domain.common.RoleOprationKey;
import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhuquanwen
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
@Repository
public interface RoleOprationMapper extends DynamicMapper<RoleOprationKey> {
    @Delete("delete from role_opration where role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") Integer roleId);

    int insertBatch(@Param("roleOprationKeys") List<RoleOprationKey> roleOprationKeys);
}