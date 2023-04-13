package com.iscas.common.nexus.tools.util;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/19 9:52
 */
public class StringUtils {
    private StringUtils() {
    }

    public static boolean isBlank(final String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }
}
