package com.iscas.jdk.base;

import org.junit.Test;

/**
 * 测试Java label在多重循环中的使用
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/26 13:44
 * @since jdk1.8
 */
public class JavaLabelTests {
    @Test
    public void test() {
        out:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i == 5 && j ==5) break out;
                System.out.printf("i:%d,j:%d", i, j);
                System.out.println();
            }
        }

        out:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(j ==5) continue out;
                System.out.printf("i:%d,j:%d", i, j);
                System.out.println();
            }
        }
    }
}
