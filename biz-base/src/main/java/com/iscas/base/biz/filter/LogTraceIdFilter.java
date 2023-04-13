package com.iscas.base.biz.filter;

import com.iscas.base.biz.config.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/12 11:10
 */
@Slf4j
public class LogTraceIdFilter extends OncePerRequestFilter implements Constants {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String traceId = request.getHeader(TRACE_ID_HEADER);
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        MDC.put(LOG_TRACE_ID, traceId);
        log.debug("设置traceId：{}", traceId);
        chain.doFilter(request, response);
    }
}
