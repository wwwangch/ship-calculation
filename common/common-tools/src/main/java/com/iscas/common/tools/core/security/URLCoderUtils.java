package com.iscas.common.tools.core.security;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * URL编码工具类
 *
 * @author zhuquanwen
 * @date 2018/7/13 16:29
 **/
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming", "AlibabaClassNamingShouldBeCamel"})
public final class URLCoderUtils {
    private URLCoderUtils() {
    }

    /**
     * 转换编码 ISO-8859-1到GB2312
     *
     * @param text text
     * @return String
     */
    public static String iso2Gb(String text) throws UnsupportedEncodingException {
        assert StringUtils.isNotBlank(text);
        return new String(text.getBytes(StandardCharsets.ISO_8859_1), "GB2312");
    }

    /**
     * 转换编码 GB2312到ISO-8859-1
     *
     * @param text text
     * @return String
     */
    public static String gb2Iso(String text) throws UnsupportedEncodingException {
        assert StringUtils.isNotBlank(text);
        return new String(text.getBytes("GB2312"), StandardCharsets.ISO_8859_1);
    }

    /**
     * Utf8URL编码
     *
     * @param text text
     * @return String
     */
    public static String utf8URLencode(String text) {
        assert StringUtils.isNotBlank(text);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c <= 255) {
                result.append(c);
            } else {
                byte[] b;
                b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                for (int value : b) {
                    int k = value;
                    if (k < 0) {
                        k += 256;
                    }
                    result.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return result.toString();
    }

    /**
     * Utf8URL解码
     *
     * @param text text
     * @return String
     */
    public static String utf8URLdecode(String text) {
        assert StringUtils.isNotBlank(text);
        StringBuilder result = new StringBuilder();
        int p;
        if (text.length() > 0) {
            text = text.toLowerCase();
            p = text.indexOf("%e");
            if (p == -1) {
                return text;
            }
            while (p != -1) {
                result.append(text, 0, p);
                text = text.substring(p);
                if (text.length() < 9) {
                    return result.toString();
                }
                result.append(codeToWord(text.substring(0, 9)));
                text = text.substring(9);
                p = text.indexOf("%e");
            }

        }

        return result + text;
    }

    /**
     * utf8URL编码转字符
     *
     * @param text text
     * @return String
     */
    private static String codeToWord(String text) {
        String result;

        if (utf8CodeCheck(text)) {
            byte[] code = new byte[3];
            code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
            code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
            code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
            result = new String(code, StandardCharsets.UTF_8);
        } else {
            result = text;
        }

        return result;
    }

    /**
     * 编码是否有效
     *
     * @param text text
     * @return boolean
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private static boolean utf8CodeCheck(String text) {
        StringBuilder sign = new StringBuilder();
        if (text.startsWith("%e")) {
            for (int i = 0, p = 0; p != -1; i++) {
                p = text.indexOf("%", p);
                if (p != -1) {
                    p++;
                }
                sign.append(p);
            }
        }
        return "147-1".equals(sign.toString());
    }

    /**
     * 判断是否Utf8Url编码
     *
     * @param text text
     * @return boolean
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static boolean isUtf8Url(String text) {
        assert StringUtils.isNotBlank(text);
        text = text.toLowerCase();
        int p = text.indexOf("%");
        if (p != -1 && text.length() - p > 9) {
            text = text.substring(p, p + 9);
        }
        return utf8CodeCheck(text);
    }


}
