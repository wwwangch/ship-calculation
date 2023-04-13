package com.iscas.base.biz.filter;

import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.view.table.HttpMethod;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/7 13:47
 * @since jdk1.8
 */
@Slf4j
public class RefererFilter extends OncePerRequestFilter{
    private final String[] allowDomains;

    public RefererFilter(String[] allowDomains) {
        this.allowDomains = allowDomains;
    }
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        if(logger.isDebugEnabled()){
            log.debug("进入 RefererFilter 过滤器");
        }
        String referer = request.getHeader("referer");
        String host = request.getServerName();
        // 验证非get请求
        if (!HttpMethod.GET.toString().equals(request.getMethod())) {
            if (referer == null) {
                throw Exceptions.authorizationRuntimeException("referer校验失败，不允许的请求", "请求头未携带referer");
            }
            java.net.URL url;
            try {
                url = new java.net.URL(referer);
            } catch (MalformedURLException e) {
                // URL解析异常
                throw Exceptions.authorizationRuntimeException("referer校验失败，不允许的请求", "请求头中referer无法解析");
            }
            // 首先判断请求域名和referer域名是否相同,如果相同不用作判断了
            if (!host.equals(url.getHost())) {
                // 如果不等，判断是否在白名单中
                boolean flag = false;
                if (allowDomains != null) {
                    flag = Arrays.stream(allowDomains).anyMatch(domain -> Objects.equals(domain, url.getHost()));
                }
                if (!flag) {
                    throw Exceptions.authorizationRuntimeException("referer校验失败，不允许跨站请求", "referer不在白名单内");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}