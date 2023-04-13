package com.iscas.common.tools.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 与{@link TestUserDTO}映射的一个VO
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 16:30
 * @since jdk1.8
 */
@Accessors(chain = true)
@Data
public class TestUserVO2 {
    private Integer id;
    private String username;
    private String realName;
    private String email;
    private String phone;
    private Date createTime;
    private String createTimeStr;
    private String createTimeExpression;
    private List<TestRoleDTO> roleList;

}
