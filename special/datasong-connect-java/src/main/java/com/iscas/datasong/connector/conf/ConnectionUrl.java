package com.iscas.datasong.connector.conf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/29 16:22
 * @since jdk1.8
 */
public class ConnectionUrl {

    private static final String REGEX = "jdbc:datasong://.+:(\\d|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])/.+";
    private static final Pattern COMPILE = Pattern.compile(REGEX);

    public static boolean acceptsUrl(String connString) {
        //jdbc:datasong://192.168.100.88:3306/quick-frame-samples
        Matcher matcher = COMPILE.matcher(connString);
        return matcher.matches();
    }
}
