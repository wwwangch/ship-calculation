package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhuquanwen
 */
@TableName("user_role")
@Data
public class UserRoleKey {
    private Integer userId;

    private Integer roleId;
}