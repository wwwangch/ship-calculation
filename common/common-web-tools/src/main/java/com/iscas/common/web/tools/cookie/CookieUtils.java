package com.iscas.common.web.tools.cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * cookie操作工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/13
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "unchecked"})
public class CookieUtils {
    private CookieUtils() { }
    /**
     * 根据名字获取cookie
     *
     * @param request 请求
     * @param name cookie名字
     * @return Cookie
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        return cookieMap.getOrDefault(name, null);
    }
    /**
     * 将cookie封装到Map里面
     *
     * @param request 请求
     * @return Map
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .map(cookies -> Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, cookie -> cookie)))
                .orElse(Collections.EMPTY_MAP);
    }
    /**
     * 保存Cookies
     *
     * @param response
     *            servlet请求
     * @param value
     *            保存值
     *
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time, String path) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(("/" + path).replaceAll("/+", "/"));
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        URLEncoder.encode(value, StandardCharsets.UTF_8);
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        response.addCookie(cookie);
        return response;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {
        return setCookie(response, name, value, time, "/");
    }
}
