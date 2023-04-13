package com.iscas.common.tools.core.io.serial;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/29 8:19
 * @since jdk1.8
 */
public class JdkSerializableUtilsTests {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        System.out.println("--------JDK序列化测试-内存中 begin---------");
        long begin = System.currentTimeMillis();
        byte[] bytes = null;
        for (int i = 0; i < 10000; i++) {

            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            Simple simple = new Simple("zhang" + i, (i + 1), map);
            bytes = JdkSerializableUtils.serialize(simple);
        }
        System.out.printf("序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        Simple simple = JdkSerializableUtils.deserialize(bytes);
        System.out.printf("反序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        System.out.println("--------JDK序列化测试-内存中 end---------");
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        System.out.println("--------JDK序列化测试-文件中 begin---------");
        long begin = System.currentTimeMillis();
        File file = File.createTempFile("jdk_data", ".bin");
        file.deleteOnExit();
        @Cleanup OutputStream os = new FileOutputStream(file);
        @Cleanup ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);
        for (int i = 0; i < 10000; i++) {

            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            Simple simple = new Simple("zhang" + i, (i + 1), map);
            JdkSerializableUtils.serialize(simple, objectOutputStream);
        }
        System.out.printf("序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        @Cleanup InputStream in = new FileInputStream(file);
        @Cleanup ObjectInputStream objectInputStream = new ObjectInputStream(in);
        Simple simple = JdkSerializableUtils.deserialize(objectInputStream);
        System.out.printf("反序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        file.delete();
    }
}
