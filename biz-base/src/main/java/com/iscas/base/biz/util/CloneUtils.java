package com.iscas.base.biz.util;

import lombok.Cleanup;

import java.io.*;

/**
 * 参考：{@link cn.hutool.core.util.ObjectUtil#cloneByStream(Object)} -update by zqw 20210422
 *
 * @author lirenshen
 * @version 1.0
 * @date 2021/1/21 17:02
 * @since jdk1.8
 */
@SuppressWarnings("unchecked")
public class CloneUtils {

    public static <T extends Serializable> T clone(T source) {
        T clone = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            @Cleanup ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(source);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            @Cleanup ObjectInputStream ois = new ObjectInputStream(bais);
            clone = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clone;
    }
}
