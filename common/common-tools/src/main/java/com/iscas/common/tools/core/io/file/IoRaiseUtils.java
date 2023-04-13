package com.iscas.common.tools.core.io.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 一些IO增强操作
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/4 19:45
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class IoRaiseUtils {
    private IoRaiseUtils() {}

    public static void closeAnyway(InputStream is) {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeAnyway(OutputStream os) {
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void close(InputStream is) throws IOException {
        is.close();
    }

    public static void close(OutputStream os) throws IOException {
       os.close();
    }

}
