package com.iscas.common.tools.core.string;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 正则表达式字符串操作类
 *
 * @author zhuquanwen
 * @date 2018/7/16
 **/
@SuppressWarnings({"unused", "rawtypes", "unchecked", "CaughtExceptionImmediatelyRethrown"})
public class StringRegExUtils {
    private StringRegExUtils() {
    }

    /**
     * 要求大小写都匹配正则表达式
     *
     * @param pattern 正则表达式模式
     * @param str     要匹配的字串
     * @return boolean值
     * @since jdk1.8
     */
    @SuppressWarnings("CaughtExceptionImmediatelyRethrown")
    public static boolean ereg(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.find();
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹配且替换字串
     *
     * @param pattern 正则表达式模式
     * @param newstr  要替换匹配到的新字串
     * @param str     原始字串
     * @return 匹配后的字符串
     * @since jdk1.8
     */

    @SuppressWarnings("CaughtExceptionImmediatelyRethrown")
    public static String eregReplace(String pattern, String newstr, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.replaceAll(newstr);
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 主要用于模板中模块标记分析函数 把查找到的元素加到vector中
     *
     * @param pattern 为正则表达式模式
     * @param str     原始字串
     * @return vector
     * @since jdk1.8
     */
    public static Vector splitTags2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(eregReplace("(\\[\\#)|(\\#\\])", "", m.group()));
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 模块标记分析函数
     * 功能主要是把查找到的元素加到vector中
     *
     * @param pattern 为正则表达式模式
     * @param str     原始字串
     * @since jdk1.8
     */
    public static String[] splitTags(String pattern, String str) {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = eregReplace("(\\[\\#)|(\\#\\])", "", m.group());
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }


    /**
     * 匹配所有符合模式要求的字串并加到矢量vector数组中
     *
     * @param pattern 为正则表达式模式
     * @param str     原始字串
     * @return vector
     * @since jdk1.8
     */
    public static Vector regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(m.group());
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹配所有符合模式要求的字串并加到字符串数组中
     *
     * @param pattern 为正则表达式模式
     * @param str     原始字串
     * @return array
     * @since jdk1.8
     */
    public static String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = m.group();
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    public static String escapeDollarBackslash(String original) {
        StringBuilder buffer = new StringBuilder(original.length());
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * 提取指定字串的函数
     * 功能主要是把查找到的元素
     *
     * @param pattern 为正则表达式模式
     * @param str     原始字串
     * @since jdk1.8
     */
    public static String fetchStr(String pattern, String str) {
        String returnValue = null;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                returnValue = m.group();
            }
            return returnValue;
        } catch (PatternSyntaxException e) {
            return returnValue;
        }
    }
}
