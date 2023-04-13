package com.iscas.common.tools.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * 用户实体转换接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 16:34
 * @since jdk1.8
 */
@Mapper
public interface TestUserConverter {
    TestUserConverter INSTANCE = Mappers.getMapper(TestUserConverter.class);

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "realName", target = "realName")
    /**最基本的映射*/
    TestUserVO1 toTestUserVO1(TestUserDTO testUser);

    /**复杂映射，带dateFormat,以及expression*/
    @Mapping(source = "roles", target = "roleList")
    @Mapping(source = "createTime", target = "createTimeStr", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "createTimeExpression", expression = "java(org.apache.commons.lang3.time.DateFormatUtils.format(testUser.getCreateTime(), \"yyyy-MM-dd\"))")
    TestUserVO2 toTestUserVO2(TestUserDTO testUser);


    /**
     * 多个实体合成一个实体
     * */
    @Mapping(source = "user.realName", target = "realName")
    @Mapping(source = "role", target = "role")
    TestUserVO3 toTestUserVO3(TestUserDTO user, TestRoleDTO role);

    /**更新属性*/
    void updateTestUserVO3(TestUserDTO user, TestRoleDTO role, @MappingTarget TestUserVO3 testUserVO3);

}
