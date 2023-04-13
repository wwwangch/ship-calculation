package com.iscas.common.tools.core.io.file;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * jar包路径测试类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/13
 * @since jdk1.8
 */
public class JarPathUtilsTests {
    /**
     * 获得class文件存放的位置
     * */
    @Test
    public void test() throws UnsupportedEncodingException {
        System.out.println("--------JarPathUtils.getJarPath(Class clazz) begin---------");
        String basePath = JarPathUtils.getJarPath(JarPathUtils.class);
        System.out.println(basePath);
        Assertions.assertNotNull(basePath);
        System.out.println("--------JarPathUtils.getJarPath(Class clazz) begin---------");
    }
}
