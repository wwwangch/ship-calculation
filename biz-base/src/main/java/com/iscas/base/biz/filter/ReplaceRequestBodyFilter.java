package com.iscas.base.biz.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 *  替换请求体过滤器，使得requestbody可以被多次获取使用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/21 15:52
 * @since jdk1.8
 */
public class ReplaceRequestBodyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain chain) throws ServletException, IOException, ServletException {
        HttpServletRequest requestWrapper = new RequestWrapper(request);
        chain.doFilter(requestWrapper, response);
    }
}
