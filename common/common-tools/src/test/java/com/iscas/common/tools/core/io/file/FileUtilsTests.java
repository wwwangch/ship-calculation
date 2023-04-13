package com.iscas.common.tools.core.io.file;


import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * <b>添加了一部分单元测试 <b/>
 * @author zhuquanwen
 * @date: 2018/7/16
 **/
public class FileUtilsTests {


    @Test
    public void test() throws IOException {
        System.out.println("--------FileUtils按行读取所有begin---------");
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        @Cleanup PrintWriter pw = new PrintWriter(file);
        pw.println("1111");
        pw.println("222");
        pw.close();
        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup InputStreamReader isr = new InputStreamReader(is);
        List<String> res = FileUtils.readLines(isr);
        res.forEach(System.out::println);
        file.delete();
        System.out.println("--------FileUtils按行读取所有end---------");

    }

    @Test
    public void test2() throws IOException {
        System.out.println("--------FileUtils按行读取begin---------");
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        @Cleanup PrintWriter pw = new PrintWriter(file);
        pw.println("1111");
        pw.println("222");
        pw.println("222");
        pw.println("222");
        pw.close();
        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup InputStreamReader isr = new InputStreamReader(is);
        List<String> res = FileUtils.readLines(isr, 2);
        res.forEach(System.out::println);
        file.delete();
        System.out.println("--------FileUtils按行读取end---------");
    }

    @Test
    public void test3() throws IOException {
        System.out.println("--------FileUtils反向按行读取begin---------");
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        @Cleanup PrintWriter pw = new PrintWriter(file);
        pw.println("1111");
        pw.println("222");
        pw.println("222");
        pw.println("222");
        pw.close();
        List<String> res = FileUtils.reverseReadLines(file, "utf-8", 2);
        res.forEach(System.out::println);
        file.delete();
        System.out.println("--------FileUtils反向按行读取end---------");

    }

    @Test
    public void test4() throws IOException {
        File[] files = FileUtils.listAllFiles(new File("."));
        Arrays.stream(files).forEach(System.out::println);
    }


}
