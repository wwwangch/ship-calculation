package com.iscas.common.tools.core.array;

import java.util.Arrays;

/**
 * 数组增强操作
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/18 15:50
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class ArrayRaiseUtils {
    private ArrayRaiseUtils() {
    }

    /**
     * 判断一个数组中是否含有某个元素，使用equals方法判断
     *
     * @since jdk1.8
     * @date 2018/7/18
     * @param objects 数组
     * @param obj 判断元素
     * @return boolean
     */
    public static boolean contains(Object[] objects, Object obj) {
        if (objects == null) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        for (Object objx: objects) {
            if (objx.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 合并两个二维数组
     *
     * @since jdk1.8
     * @date 2018/7/18
     * @param array1 数组1
     * @param array2 数组2
     * @return Object[][] 合并后结果
     */
    public static Object[][] merge2Array(Object[][] array1, Object[][] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        array1 = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, array1, length1, length2);
        return array1;
    }
}
