package com.iscas.common.tools.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 测试角色
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 16:52
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class TestRoleDTO {
    private Integer id;
    private String name;

}
