package com.iscas.base.biz.util;

import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.model.access.AccessLog;
import com.iscas.base.biz.model.auth.AuthContext;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/2 9:52
 * @since jdk1.8
 */
public class AccessLogUtils {
    private static final Logger ACCESS_LOGGER = LoggerFactory.getLogger("accessLogger");

    private AccessLogUtils() {
    }

    /**
     * 记录访问日志
     *
     * @param request http request
     * @param status  http 状态码
     * @date 2023/2/21
     */
    public static void log(HttpServletRequest request, int status) {
        Long startTime = request == null ? null : (Long) request.getAttribute(Constants.KEY_REQUEST_START_TIME);

        Date createTime = new Date();
        AuthContext context = AuthContextHolder.getContext();
        assert request != null;
        AccessLog accessLog = new AccessLog()
                .setIp(SpringUtils.getRemoteAddr(request))
                .setMethod(request.getMethod())
                .setUri(request.getRequestURI())
                .setCreateTime(createTime)
                .setDuration(startTime != null ? createTime.getTime() - startTime : null)
                .setStatus(status)
                .setUsername(context == null ? null : context.getUsername());
        ACCESS_LOGGER.info(accessLog.toString());
    }
}
