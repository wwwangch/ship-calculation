package com.iscas.base.biz.util;

import com.iscas.templet.exception.Exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/25 17:14
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class StringUtils {
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }
    public static String upperFist(String str){
        if(str == null){
            throw Exceptions.runtimeException("str can not be null");
        }
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
    public static String lowerFist(String str){

        if(str == null){
            throw Exceptions.runtimeException("str can not be null");
        }
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }


    public static String[][] split(String str, String[] splitStrs) {
        List<String[]> result = new ArrayList<>();
        split(str, splitStrs, result);
        return result.toArray(new String[result.size()][]);
    }

    private static void split(String str, String[] splitStrs, List<String[]> result) {
        int index = -1;
        String splitStr = null;
        for (String s : splitStrs) {
            int index1 = str.indexOf(s);
            if (index1 > 0) {
                if (index == -1 || index1 < index) {
                    index = index1;
                    splitStr = s;
                }
            }
        }
        if (index < 0) {
            result.add(new String[]{str});
        } else {
            String firstStr = org.apache.commons.lang3.StringUtils.substringBefore(str, splitStr);
            result.add(new String[]{firstStr, splitStr});
            String lastStr = org.apache.commons.lang3.StringUtils.substringAfter(str, splitStr);
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(lastStr)) {
                split(lastStr, splitStrs, result);
            }
        }
    }

}
