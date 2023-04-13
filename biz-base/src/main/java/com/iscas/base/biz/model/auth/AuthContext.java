package com.iscas.base.biz.model.auth;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 身份认证、授权的上下文
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/21 10:57
 * @since jdk1.8
 */
@Data
public class AuthContext {
    /**用户ID*/
    protected Object userId;
    /**用户名*/
    protected String username;
    /**token*/
    protected String token;
    /**当前用户拥有的角色*/
    protected List<Role> roles = new ArrayList<>();
    /**是否为超级管理员*/
    protected boolean isSuper;
    /**此URL是否需要做权限认证，不做权限认证，用户名等信息都为空，不会去读取*/
    protected boolean isNeedPermission = true;
}
