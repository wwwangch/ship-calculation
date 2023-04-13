package com.iscas.common.tools.core.io.file;

import cn.hutool.core.io.IoUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 8:37
 * @since jdk1.8
 */
public class LargeFileTests {

    /**
     * 原始的IO流方式
     * */
    @Test
    @Disabled
    public void copy1() throws FileNotFoundException, IOException {
        long start = System.currentTimeMillis();
        File file = new File("d:/testLarge.rar");
        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup OutputStream os = new FileOutputStream("d:/aaa.tar");
        IoUtil.copy(is, os);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }

    /**
     * FileChannel.transferTo的方式
     * */
    @Test
    @Disabled
    public void copy2() throws FileNotFoundException, IOException {
        long start = System.currentTimeMillis();
        File file = new File("d:/testLarge.rar");
        @Cleanup FileInputStream is = new FileInputStream(file);
        @Cleanup FileOutputStream os = new FileOutputStream("d:/bbb.tar");
        IoUtil.copy(is, os);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }

    /**
     * 拷贝流，使用NIO
     * */
    @Test
    @Disabled
    public void copy3() throws FileNotFoundException, IOException {
        long start = System.currentTimeMillis();
        File file = new File("d:/testLarge.rar");
        @Cleanup FileInputStream is = new FileInputStream(file);
        @Cleanup FileOutputStream os = new FileOutputStream("d:/ccc.tar");
        IoUtil.copyByNIO(is, os, 1024, null);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) + "ms");
    }
}
