package com.iscas.biz.domain.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhuquanwen
 */
@TableName("role_menu")
@Data
public class RoleMenuKey {
    private Integer roleId;

    private Integer menuId;

    public RoleMenuKey() {}

    public RoleMenuKey(Integer roleId, Integer menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

}