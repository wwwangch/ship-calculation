package com.iscas.common.tools.mapstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 17:13
 * @since jdk1.8
 */

public class MapStructTests {
    private TestUserDTO testUser = null;
    private TestRoleDTO testRole = null;

    @BeforeEach
    public void before() {
        testUser = new TestUserDTO()
                .setId(1)
                .setPassword("123456")
                .setUsername("zhangsan")
                .setEmail("123@456.com")
                .setPhone("18598745145")
                .setCreateTime(new Date())
                .setRealName("张三")
                .setRoles(Arrays.asList(new TestRoleDTO().setId(1).setName("管理员"),
                        new TestRoleDTO().setId(2).setName("普通用户")));

        testRole = new TestRoleDTO().setId(3).setName("超级管理员");

    }

    @Test
    public void test() {
        TestUserVO1 testUserVO1Assert = new TestUserVO1().setId(1).setRealName("张三");
        TestUserVO1 testUserVO1 = TestUserConverter.INSTANCE.toTestUserVO1(testUser);
        System.out.println(testUserVO1);
        Assertions.assertEquals(testUserVO1, testUserVO1Assert);

        TestUserVO2 testUserVO2 = TestUserConverter.INSTANCE.toTestUserVO2(testUser);
        System.out.println(testUserVO2);

        TestUserVO3 testUserVO3 = TestUserConverter.INSTANCE.toTestUserVO3(testUser, testRole);
        System.out.println(testUserVO3);

        TestUserVO3 vo3 = new TestUserVO3().setRealName("李四");
        TestUserConverter.INSTANCE.updateTestUserVO3(testUser, testRole, vo3);
        System.out.println(vo3);

    }
}
