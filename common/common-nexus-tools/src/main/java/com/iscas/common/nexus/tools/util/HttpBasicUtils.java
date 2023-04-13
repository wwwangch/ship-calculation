package com.iscas.common.nexus.tools.util;


import java.util.Base64;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/20 8:42
 */
public class HttpBasicUtils {
    private HttpBasicUtils() {}

    public static String createAuthorizationVal(String username, String pwd) {
        String authStr = username + ":" + pwd;
        byte[] authEncBytes = Base64.getEncoder().encode(authStr.getBytes());
        return "Basic " + new String(authEncBytes);
    }

}
