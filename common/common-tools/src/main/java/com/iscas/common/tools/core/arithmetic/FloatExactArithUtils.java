package com.iscas.common.tools.core.arithmetic;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 浮点数精确运算工具类
 * <p>
 * 使用BigDecimal代替直接浮点运算，在大量数据计算时效率低，谨慎使用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/11 21:00
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class FloatExactArithUtils {
    private FloatExactArithUtils() {
    }

    /**
     * 精确的浮点加法运算
     */
    public static double add(double data1, double data2) {
        BigDecimal b1 = BigDecimal.valueOf(data1);
        BigDecimal b2 = BigDecimal.valueOf(data2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 精确的浮点减法运算
     */
    public static double subtract(double data1, double data2) {
        BigDecimal b1 = BigDecimal.valueOf(data1);
        BigDecimal b2 = BigDecimal.valueOf(data2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 精确的浮点乘法运算
     */
    public static double multiply(double data1, double data2) {
        BigDecimal b1 = BigDecimal.valueOf(data1);
        BigDecimal b2 = BigDecimal.valueOf(data2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 相对精确的除法运算，指定精度后的数字四舍五入
     */
    public static double divide(double data1, double data2, int scale) {
        if (scale < 0) {
            throw new RuntimeException("精度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(data1);
        BigDecimal b2 = BigDecimal.valueOf(data2);
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 相对精确的除法运算，指定精度后的数字四舍五入，使用默认精度10
     */
    public static double divide(double data1, double data2) {
        return divide(data1, data2, 10);
    }

}
