package com.iscas.common.tools.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/11 19:26
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class TestUserVO3 {
    private String realName;
    private TestRoleDTO role;
}
