package com.iscas.base.biz.filter;


import com.iscas.base.biz.autoconfigure.cors.CorsProps;
import com.iscas.base.biz.config.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义跨域过滤器，可以通过springboot auto config 配置
 *
 * @author zhuquanwen
 * @date 2018/3/20 15:12
 **/
@SuppressWarnings("FieldMayBeFinal")
@Slf4j
public class CustomCorsFilter extends OncePerRequestFilter {
    private CorsProps corsProps;
    private Set<String> ignoreUrlAllMatchSet = new HashSet<>();
    Set<String> ignoreUrlPrefixSet = new HashSet<>();


    public CustomCorsFilter(CorsProps corsProps) {
        if (log.isDebugEnabled()) {
            log.debug("进入 CustomCrosFilter 过滤器");
        }
        this.corsProps = corsProps;
        if (corsProps.getIgnoreUrls() != null) {
            for (String urlStr : corsProps.getIgnoreUrls()) {
                if (urlStr.endsWith("/*")) {
                    ignoreUrlPrefixSet.add(urlStr.substring(0, urlStr.lastIndexOf("/*")));
                } else if (urlStr.endsWith("/**")) {
                    ignoreUrlPrefixSet.add(urlStr.substring(0, urlStr.lastIndexOf("/**")));
                } else {
                    ignoreUrlAllMatchSet.add(urlStr);
                }
            }
        }

    }


    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (!ignoreMath(request) && CorsUtils.isCorsRequest(request)) {
            String origin = com.iscas.base.biz.util.CorsUtils.checkOrigin(request, response, corsProps);
            if (origin == null) {
                return;
            }
            response.setHeader(Constants.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
            //服务器同意客户端发送cookies
            response.setHeader(Constants.ACCESS_CONTROL_ALLOW_CREDENTIALS, corsProps.getCredentials());

            response.setHeader(Constants.ACCESS_CONTROL_ALLOW_METHODS, corsProps.getMethods());
            response.setHeader(Constants.ACCESS_CONTROL_ALLOW_HEADERS, corsProps.getHeaders());
            if (CorsUtils.isPreFlightRequest(request)) {
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean ignoreMath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        String uri1 = uri.substring(uri.indexOf(contextPath) + contextPath.length());
        if (ignoreUrlAllMatchSet.contains(uri1)) {
            return true;
        }
        return ignoreUrlPrefixSet.stream().anyMatch(uri1::startsWith);
    }

}
