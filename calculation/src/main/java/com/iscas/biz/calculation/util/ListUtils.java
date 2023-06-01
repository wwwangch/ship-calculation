package com.iscas.biz.calculation.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yichuan@iscas.ac.cn
 * @Date 2023/6/1 15:10
 */
public class ListUtils {
    public static List<Double> convertStrToDoubleList(String str) {
        List<Double> result = new ArrayList<>();
        if (str != null && !str.trim().isEmpty()) {
            String[] strArr = str.trim().split(",");
            for (String arr : strArr) {
                try {
                    Double parseDouble = Double.parseDouble(arr.trim());
                    result.add(parseDouble);
                } catch (NumberFormatException e) {
                    // 如果出现非法的 Double 类型，则忽略该值
                }
            }
        }
        return result;
    }
}
