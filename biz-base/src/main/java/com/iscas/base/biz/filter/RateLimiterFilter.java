package com.iscas.base.biz.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.iscas.base.biz.config.ratelimiter.RateLimiterProps;
import com.iscas.templet.exception.Exceptions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RateLimiter 应用总体限流过滤器
 *
 * @author zhuquanwen
 **/
@SuppressWarnings("UnstableApiUsage")
public class RateLimiterFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RateLimiterProps rateLimiterProps;
    private final RateLimiter rateLimiter;
    private final List<String> staticUrls;

    public RateLimiterFilter(RateLimiterProps rateLimiterProps) {
        this.rateLimiterProps = rateLimiterProps;
        this.staticUrls = rateLimiterProps.getStaticUrl();
        rateLimiter = RateLimiter.create(rateLimiterProps.getPermitsPerSecond());
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (log.isTraceEnabled()) {
            log.trace("进入 RateLimiterFilter 过滤器");
        }

        String contextPath = request.getContextPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        if (!CollectionUtils.isEmpty(staticUrls) && staticUrls.stream().anyMatch(url -> antPathMatcher.match(contextPath + url, request.getRequestURI()))) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!rateLimiter.tryAcquire(rateLimiterProps.getMaxWait().toMillis(), TimeUnit.MILLISECONDS)) {
            //获取令牌失败，并且超过超时时间
            log.warn(request.getRemoteAddr() + "访问" + request.getRequestURI() + "获取令牌失败");
            throw Exceptions.requestTimeoutRuntimeException("服务器繁忙,请求超时");
        }
        filterChain.doFilter(request, response);
    }
}
