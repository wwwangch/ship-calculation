package com.iscas.common.tools.hutool.core;

import cn.hutool.core.util.ArrayUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/22 15:42
 * @since jdk1.8
 */
public class ArrayUtilTests {

    /**
     * 测试构建数组
     * */
    @Test
    public void test1() {
        String[] result = ArrayUtil.append(null, "zhangsan", "lisi");
        System.out.println(Arrays.toString(result));
    }

    /**
     * 测试像集合一样追加数组
     * */
    @Test
    public void test2() {
        String[] result = ArrayUtil.append(null, "zhangsan", "lisi");

        result = ArrayUtil.append(result, "wangwu");
        System.out.println(Arrays.toString(result));
    }
}
