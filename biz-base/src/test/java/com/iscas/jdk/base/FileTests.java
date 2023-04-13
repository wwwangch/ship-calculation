package com.iscas.jdk.base;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 文件一些操作测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/10/26 14:24
 * @since jdk1.8
 */
public class FileTests {
    @Test
    @Ignore
    public void test() {
        try {
            File tmpFile = File.createTempFile("tmp", ".txt", new File("c:"));
            System.out.println(tmpFile.getAbsolutePath());
            Thread.sleep(5000);
            tmpFile.deleteOnExit();

            File file = new File("c:/test.txt");
            if (!file.exists()) file.createNewFile();
            Thread.sleep(5000);
            file.deleteOnExit();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Ignore
    @Test
    public void test2() {
        File file = new File("e:/test.docx");
        long  t1 = System.currentTimeMillis();
        file.setLastModified(t1);
        long t2 = file.lastModified();
        Assert.assertEquals(t1, t2);
    }

    @Test
    public void test3() {
        File[] files = File.listRoots();
        Arrays.stream(files).forEach(System.out::println);
    }
}
