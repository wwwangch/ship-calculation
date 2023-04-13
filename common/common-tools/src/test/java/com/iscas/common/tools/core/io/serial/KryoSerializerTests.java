package com.iscas.common.tools.core.io.serial;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/28 21:33
 * @since jdk1.8
 */
public class KryoSerializerTests {
    @Test
    public void test() throws IOException {
        System.out.println("--------Kryo序列化测试-内存中 begin---------");
        long begin = System.currentTimeMillis();
        byte[] bytes = null;
        for (int i = 0; i < 10000; i++) {
            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            Simple simple = new Simple("zhang" + i, (i + 1), map);
            bytes = KryoSerializerUtils.serialize(simple);
        }
        System.out.printf("序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        Simple simple = KryoSerializerUtils.deserialize(bytes, Simple.class);
        System.out.printf("反序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        System.out.println("--------Kryo序列化测试-内存中 end---------");
    }

    @Test
    public void test2() throws IOException {
        System.out.println("--------Kryo序列化测试-文件中 begin---------");
        File file = File.createTempFile("kryo_data", ".bin");
        file.deleteOnExit();
        long begin = System.currentTimeMillis();
        @Cleanup OutputStream os = new FileOutputStream(file);
        @Cleanup Output output = new Output(os);
        for (int i = 0; i < 10000; i++) {
            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            Simple simple = new Simple("zhang" + i, (i + 1), map);
            KryoSerializerUtils.serialize(simple, output);
            Simple newmsg = null;
        }
        System.out.printf("序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        begin = System.currentTimeMillis();
        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup Input input = new Input(is);
        Simple simple = KryoSerializerUtils.deserialize(input, Simple.class);
        System.out.printf("反序列化总计耗时:[%d]\n", (System.currentTimeMillis() - begin));
        file.delete();
        System.out.println("--------Kryo序列化测试-文件中 end---------");
    }


}
