package com.iscas.base.biz.service;

import com.iscas.base.biz.model.auth.User;
import com.iscas.templet.common.ResponseEntity;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 用户认证、权限认证service接口
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "rawtypes"})
public interface IUserService {
    User findByUsername(String username);
    void loginHandler(HttpServletResponse response, HttpSession session,
                      User user, ResponseEntity responseEntity,
                      int tokenExpire, int tokenCookieExpire) throws Exception;
}
