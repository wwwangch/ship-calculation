package com.iscas.biz.config.log;

import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.util.AccessLogUtils;
import com.iscas.common.tools.constant.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * 访问/审计日志
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/29 20:42
 * @since jdk1.8
 */
public class AccessLogInterceptor implements HandlerInterceptor, Constants {

    private final String responseHeaderServer;

    public AccessLogInterceptor(String responseHeaderServer) {
        this.responseHeaderServer = responseHeaderServer;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, @NotNull Object handler) {
        //将信息绑定在request中
        request.setAttribute(KEY_REQUEST_START_TIME, System.currentTimeMillis());
        response.addHeader("server", responseHeaderServer);
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, HttpServletResponse response, @NotNull Object handler,
                           @Nullable ModelAndView modelAndView) {
        boolean condition = Objects.equals(response.getStatus(), HttpStatus._200) ||
                Objects.equals(response.getStatus(), HttpStatus._404);
        if (condition) {
            AccessLogUtils.log(request, response.getStatus());
        }
    }
}
