package com.iscas.base.biz.util;

import com.iscas.base.biz.autoconfigure.cors.CorsProps;
import com.iscas.common.tools.constant.HeaderKey;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/17 15:16
 * @since jdk1.8
 */
public class CorsUtils {
    private CorsUtils() {}

    private static final Pattern ORIGIN_ERROR_PATTERN = Pattern.compile("[&<>^$!]");

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String checkOrigin(HttpServletRequest request, HttpServletResponse response, CorsProps corsProps) throws IOException {
        String origin = request.getHeader(HeaderKey.ORIGIN);
        if (origin == null || "null".equals(origin)) {
            origin = corsProps.getOriginPattern();
        }
        //为了不报安全漏洞，检测一下origin
        Matcher matcher = ORIGIN_ERROR_PATTERN.matcher(origin);
        if (matcher.find()) {
            rejectRequest(response);
            return null;
        }
        return origin;
    }

    private static void rejectRequest(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getOutputStream().println("Invalid CORS request");
        response.getOutputStream().flush();
    }

}
