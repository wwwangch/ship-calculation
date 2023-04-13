package com.iscas.base.biz.filter;

import com.iscas.base.biz.aop.auth.SkipAuthentication;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.config.auth.SkipAuthProps;
import com.iscas.base.biz.model.auth.AuthContext;
import com.iscas.base.biz.model.auth.Role;
import com.iscas.base.biz.model.auth.Url;
import com.iscas.base.biz.service.AbstractAuthService;
import com.iscas.base.biz.service.IAuthCacheService;
import com.iscas.base.biz.util.AuthContextHolder;
import com.iscas.base.biz.util.AuthUtils;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 继承{@link OncePerRequestFilter} 是为了保证每个请求只执行一次过滤，例如转发不再次执行。
 *
 * @author zhuquanwen
 * @date 2018/3/21 16:05
 **/
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Slf4j
public class LoginFilter extends OncePerRequestFilter implements Constants {

    private static volatile Map<RequestMappingInfo, HandlerMethod> requestInfoMethodMap;

    private final AbstractAuthService authService;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * URL是否跳过权限认证的本地缓存，为了提升匹配效率
     */
    private final Map<String, Boolean> skipAuthenticationMap = new ConcurrentHashMap<>();

    public LoginFilter(AbstractAuthService authService) {
        this.authService = authService;
    }

    @SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaMethodTooLong"})
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (log.isTraceEnabled()) {
            log.trace("进入 LoginFilter 过滤器");
        }
        String contextPath = request.getContextPath();
        AuthContext authContext = new AuthContext();
        try {
            Map<String, Url> urlMap;
            try {
                urlMap = authService.getUrls();
            } catch (Exception e) {
                throw Exceptions.authenticationRuntimeException("获取角色信息失败", e);
            }

            Collection<Url> urls = urlMap.values();
            //判断请求的URL是否在配置了权限的URL中
            boolean needFlag = urls.stream().anyMatch(url -> pathMatcher.match(contextPath + url.getName(), request.getRequestURI()));
            if (needFlag) {
                needFlag = SpringUtils.getBean(SkipAuthProps.class).getUrls().stream().noneMatch(url -> pathMatcher.match((contextPath + url).replaceAll("/+", "/"), request.getRequestURI()));
            }

            //如果找到匹配的urlx
            if (needFlag) {

                //如果跳过了认证，就不做下面的处理了
                if (checkSkipAuthentication(request)) {
                    filterChain.doFilter(request, response);
                    return;
                }

                String token = AuthUtils.getToken(request);
                if (token == null) {
                    throw Exceptions.authenticationRuntimeException("未携带身份认证信息", "header中未携带 Authorization 或未携带cookie或cookie中无Authorization");
                }
                authContext.setToken(token);
                IAuthCacheService authCacheService = SpringUtils.getApplicationContext().getBean(IAuthCacheService.class);
//                if (authCacheService.get(token, Constants.AUTH_CACHE) == null) {
                if (CacheUtils.getCache(Constants.AUTH_CACHE, token, String.class) == null) {
                    throw Exceptions.authenticationRuntimeException("身份认证信息有误", "token有误或已被注销");
                }
                //如果token不为null,校验token
                String username;
                int userId;
                try {
                    String userIdAndName = authService.verifyToken(token);
                    String[] strs = userIdAndName.split(";");
                    userId = Integer.parseInt(strs[0]);
                    username = strs[1];
                } catch (ValidTokenException e) {
                    throw Exceptions.authenticationRuntimeException("校验身份信息出错", "校验token出错");
                }
                if (!authCacheService.listContains("user-token:" + username, token)) {
                    throw Exceptions.authenticationRuntimeException("身份认证信息有误", "token已失效");
                }

                authContext.setUsername(username);
                authContext.setUserId(userId);

                List<Role> roles = authService.getRoles(username);
                authContext.setRoles(roles);

                //如果是超级管理员角色super,直接跳过认证，认为他具有所有权限
                if (roles != null) {
                    if (roles.stream().anyMatch(role1 -> Objects.equals(role1.getName(), Constants.SUPER_ROLE_KEY))) {
                        authContext.setSuper(true);
                        AuthContextHolder.setContext(authContext);
                        filterChain.doFilter(request, response);
                        return;
                    }
                }
                if (roles == null) {
                    throw Exceptions.authenticationRuntimeException("用户或其角色信息不存在", "token中携带的用户或其角色信息不存在");
                }
                for (Role role : roles) {
                    List<Url> urlsx = role.getUrls();
                    if (!CollectionUtils.isEmpty(urlsx)) {
                        if (urlsx.stream().anyMatch(url -> pathMatcher.match(contextPath + url.getName(), request.getRequestURI()))) {
                            AuthContextHolder.setContext(authContext);
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                }

                //失败了返回信息
                throw Exceptions.authorizationRuntimeException("鉴权失败");
            }
            //上面没有报错，或者没有执行doFilter，说明此请求不需要权限
            authContext.setNeedPermission(false);
            AuthContextHolder.setContext(authContext);
            filterChain.doFilter(request, response);
        } finally {
            AuthContextHolder.setContext(authContext);
        }
    }

    private boolean checkSkipAuthentication(HttpServletRequest request) {
        return skipAuthenticationMap.compute(request.getRequestURI(), (uri, flag) -> {
            if (flag != null) {
                return flag;
            }
            Map<RequestMappingInfo, HandlerMethod> requestInfoMap = getRequestInfoMap();
            if (MapUtils.isNotEmpty(requestInfoMap)) {
                String requestURI = getRequestURI(request);
                for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestInfoMap.entrySet()) {
                    RequestMappingInfo requestMappingInfo = entry.getKey();
                    PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
                    assert patternsCondition != null;
                    List<String> matchingPatterns = patternsCondition.getMatchingPatterns(requestURI);
                    if (CollectionUtils.isNotEmpty(matchingPatterns)) {
                        //匹配到了这个URL后，检测method或其类上是否有@SkipAuthentication注解，如果有直接返回true
                        Method method = entry.getValue().getMethod();
                        SkipAuthentication skipAuthentication = Optional.ofNullable(AnnotationUtils.findAnnotation(method, SkipAuthentication.class))
                                .orElseGet(() -> AnnotationUtils.findAnnotation(method.getDeclaringClass(), SkipAuthentication.class));
                        if (skipAuthentication != null) {
                            return true;
                        }
                    }
                }
            }
            return false;
        });

    }

    private String getRequestURI(HttpServletRequest request) {
        //去除context-path
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (contextPath != null) {
            requestURI = "/" + StringUtils.substringAfter(requestURI, contextPath);
        }
        requestURI = requestURI.replaceAll("//+", "/");
        return requestURI;
    }

    private Map<RequestMappingInfo, HandlerMethod> getRequestInfoMap() {
        if (requestInfoMethodMap == null) {
            synchronized (LoginFilter.class) {
                if (requestInfoMethodMap == null) {
                    requestInfoMethodMap = SpringUtils.getMvcUriMethods();
                }
            }
        }
        return requestInfoMethodMap;
    }

}
