package com.iscas.base.biz.aop.auth;


import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.config.StaticInfo;
import com.iscas.base.biz.model.auth.Role;
import com.iscas.base.biz.service.AbstractAuthService;
import com.iscas.base.biz.util.AuthUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.exception.Exceptions;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色aop
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 15:32
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Aspect
@Component
@Slf4j
@ConditionalOnBean(AbstractAuthService.class)
public class RequiredRoleAspect implements Constants {

    @Autowired
    private AbstractAuthService authService;


    @Around("@annotation(requiredRole)")
    public Object before(final ProceedingJoinPoint joinPoint, RequiredRole requiredRole) throws Throwable {
        //如果未开启限流注解，不进行方法限流逻辑判断
        if(!StaticInfo.ENABLE_AUTH){
            return joinPoint.proceed();
        }
        //获取request对象
        HttpServletRequest request = SpringUtils.getRequest();
        //首先判断Authorization 中有没有上传信息
        String token = AuthUtils.getToken();
        if (token == null) {
            log.error(request.getRemoteAddr() + "访问" + request.getRequestURI() +
                    " :header中未携带 Authorization 或未携带cookie或cookie中无Authorization");
            throw Exceptions.authorizationException("未携带身份认证信息", "header中未携带 Authorization 或未携带cookie或cookie中无Authorization");
        }
        //如果token不为null,校验token
        String username = authService.verifyToken(token).split(";")[1];
        List<Role> roles = authService.getRoles(username);
        if (CollectionUtils.isNotEmpty(roles)) {
            log.error(request.getRemoteAddr() + "访问" + request.getRequestURI() +
                    " :token中携带的用户或其角色信息不存在");
            throw Exceptions.authorizationException("获取角色信息出错", "token中携带的用户或其角色信息不存在");
        }
        return joinPoint.proceed();
    }
}
