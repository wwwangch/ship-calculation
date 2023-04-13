package com.iscas.biz.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.filter.LoginFilter;
import com.iscas.base.biz.filter.started.AbstractStartedFilter;
import com.iscas.base.biz.filter.started.StartedFilterComponent;
import com.iscas.biz.domain.common.Role;
import com.iscas.biz.domain.common.User;
import com.iscas.biz.domain.common.UserRoleKey;
import com.iscas.biz.mapper.common.RoleMapper;
import com.iscas.biz.mapper.common.UserMapper;
import com.iscas.biz.mapper.common.UserRoleMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.common.tools.core.security.MD5Utils;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.BaseRuntimeException;
import com.iscas.templet.exception.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/4/21 13:50
 * @since jdk1.8
 */
@SuppressWarnings("unused")
//@StartedFilterComponent(order = 1)
@Slf4j
@ConditionalOnMybatis
@ConditionalOnBean(LoginFilter.class)
public class InitUserRoleFilter extends AbstractStartedFilter {

    @Override
    public void doFilterInternal(ApplicationContext applicationContext) {
        try {
            initUserRole(applicationContext);
        } catch (Exception e) {
            throw Exceptions.runtimeException(e);
        }
        super.doFilterInternal(applicationContext);
    }

    @Override
    public String getName() {
        return "初始化用户角色过滤器";
    }

    private void initUserRole(ApplicationContext applicationContext) throws BaseException, NoSuchAlgorithmException {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        RoleMapper roleMapper = applicationContext.getBean(RoleMapper.class);
        UserRoleMapper userRoleMapper = applicationContext.getBean(UserRoleMapper.class);
        Environment environment = applicationContext.getBean(Environment.class);
        String superUserDefaultPwd = environment.getProperty("super_user_default_pwd");
        if (StringUtils.isEmpty(superUserDefaultPwd)) {
            throw Exceptions.baseException("超级管理员密码未配置");
        }
        User user = userMapper.selectByUserName(Constants.SUPER_USER_KEY);
        if (user == null) {
            user = new User().setUserName(Constants.SUPER_USER_KEY)
                    .setUserPwd(MD5Utils.saltMD5(superUserDefaultPwd));
            userMapper.insertUser(user);
        }
        Role role = Optional.ofNullable(roleMapper.selectList(new QueryWrapper<Role>().lambda().eq(Role::getRoleName, Constants.SUPER_ROLE_KEY)))
                .map(superRoles -> superRoles.size() == 0 ? null : superRoles.get(0))
                .orElseGet(() -> {
                    Role rolex = new Role();
                    rolex.setRoleName(Constants.SUPER_ROLE_KEY);
                    roleMapper.insert(rolex);
                    return rolex;
                });
        userRoleMapper.delete(new QueryWrapper<UserRoleKey>().lambda().eq(UserRoleKey::getUserId, user.getUserId()));
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setRoleId(role.getRoleId());
        userRoleKey.setUserId(user.getUserId());
        userRoleMapper.insert(userRoleKey);
    }
}
