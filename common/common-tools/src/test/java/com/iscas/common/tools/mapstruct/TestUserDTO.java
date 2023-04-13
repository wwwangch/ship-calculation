package com.iscas.common.tools.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 测试用户属性
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 16:28
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class TestUserDTO {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String email;
    private String phone;
    private Date createTime;
    private List<TestRoleDTO> roles;
}
