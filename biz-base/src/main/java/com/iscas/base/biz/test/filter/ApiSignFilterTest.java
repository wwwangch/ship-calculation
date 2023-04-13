package com.iscas.base.biz.test.filter;

import com.iscas.base.biz.test.controller.ApiSignControllerTest;
import com.iscas.base.biz.util.ApiSignUtils;
import com.iscas.templet.exception.Exceptions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/2/21 15:52
 * @since jdk1.8
 */
@Component
@ConditionalOnBean(ApiSignControllerTest.class)
public class ApiSignFilterTest extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = StringUtils.substringAfter(requestURI, request.getContextPath());
        if (StringUtils.equalsAny(url, "/api/sign/t1", "/api/sign/t2")) {
            if (!ApiSignUtils.checkSignature(request, ApiSignUtils.DEFAULT_SECRET_KEY)) {
                throw Exceptions.baseRuntimeException("验证接口签名失败");
            }
        }
        chain.doFilter(request, response);
    }
}
