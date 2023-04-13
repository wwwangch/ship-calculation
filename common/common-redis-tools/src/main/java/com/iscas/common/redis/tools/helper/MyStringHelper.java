package com.iscas.common.redis.tools.helper;

import java.nio.charset.StandardCharsets;

/**
 * 字符串序列化类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/5 14:52
 * @since jdk1.8
 */
public class MyStringHelper {
    public static boolean isNotBlank(final CharSequence cs) {
        return !(isBlank(cs));
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转换为字节数组
     * @param bytes 字节数组
     * @return String
     */
    public static String toString(byte[] bytes){
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * 转换为字节数组
     * @param str 字符串
     * @return byte[] 字节数组
     */
    public static byte[] getBytes(String str){
        if (str != null){
            return str.getBytes(StandardCharsets.UTF_8);
        }else{
            return null;
        }
    }
}
