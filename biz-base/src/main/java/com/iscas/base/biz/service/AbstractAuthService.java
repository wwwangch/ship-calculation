package com.iscas.base.biz.service;

import com.auth0.jwt.interfaces.Claim;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.autoconfigure.auth.TokenProps;
import com.iscas.base.biz.model.auth.Menu;
import com.iscas.base.biz.model.auth.Role;
import com.iscas.base.biz.model.auth.Url;
import com.iscas.base.biz.util.*;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.LoginException;
import com.iscas.templet.exception.ValidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 用户权限认证service
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 18:58
 * @since jdk1.8
 */
@SuppressWarnings({"AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "rawtypes"})
public abstract class AbstractAuthService implements Constants {
    public abstract Map<String, Role> getAuth() ;
    public abstract Map<String, Url> getUrls() ;
    public abstract List<Role> getRoles(String username) ;
    public abstract List<Menu> getMenus();

    public abstract void loginHandler(HttpServletResponse response, Map<String,String> user,
                                      ResponseEntity responseEntity, int expire, int cookieExpire) throws LoginException;

    public void invalidToken(HttpServletRequest request) {
        String token = AuthUtils.getToken(request);
//        SpringUtils.getBean(IAuthCacheService.class).remove(token, Constants.AUTH_CACHE);
       CacheUtils.evictCache(Constants.AUTH_CACHE, token);
        request.getSession().invalidate();
    }

    public String verifyToken(String token) throws ValidTokenException {
        try {
            Map<String, Claim> clainMap = JWTUtils.verifyToken(token, SpringUtils.getBean(TokenProps.class).getCreatorMode());
            String username = clainMap.get("username").asString();
            Integer userId = clainMap.get("userId").asInt();
            if (username == null) {
                throw Exceptions.validTokenException("token 校验失败");
            }
            return userId + ";" + username;
        } catch (ValidTokenException e) {
            throw Exceptions.validTokenException(e.getMessage(), e.getMsgDetail());
        } catch (Exception e) {
            throw Exceptions.validTokenException("token 校验失败", e.getMessage(), e);
        }
    }
}
