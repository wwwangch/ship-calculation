package com.iscas.common.k8s.tools.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/23 17:30
 * @since jdk1.8
 */
public class MathUtils {

    public static double scale(double data, int scale) {
        BigDecimal bg = new BigDecimal(data);
        return bg.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
}
