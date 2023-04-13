package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhuquanwen
 */
@TableName("role_opration")
@Data
public class RoleOprationKey {
    private Integer roleId;

    private Integer opId;

    @SuppressWarnings("unused")
    public RoleOprationKey() {}

    public RoleOprationKey(Integer roleId, Integer opId) {
        this.roleId = roleId;
        this.opId = opId;
    }

}
