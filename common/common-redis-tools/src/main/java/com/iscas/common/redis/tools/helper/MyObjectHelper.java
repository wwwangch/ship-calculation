package com.iscas.common.redis.tools.helper;

import java.io.*;

/**
 * 对象序列化类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/5 14:59
 * @since jdk1.8
 */
public class MyObjectHelper {
    /**
     * 序列化对象
     * @param object object
     * @return byte[] 字节数组
     */
    public static byte[] serialize(Object object) throws IOException {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        if (object != null) {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        }
        return null;
    }

    /**
     * 反序列化对象
     * @param bytes 字节数组
     * @return Object
     */
    public static Object unserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais;
        if (bytes != null && bytes.length > 0) {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        return null;
    }
}
