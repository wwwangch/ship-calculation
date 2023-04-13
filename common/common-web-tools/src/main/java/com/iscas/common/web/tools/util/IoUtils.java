package com.iscas.common.web.tools.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 为了适配JDK8添加的方法，当前已弃用，未来会删除
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 11:06
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Deprecated
public class IoUtils {
    private IoUtils() {}

    public static void transferTo(InputStream is, OutputStream os) throws IOException {
        byte[] buff = new byte[1024];
        int len;
        while ((len = is.read(buff)) > 0) {
            os.write(buff, 0, len);
        }
    }
}
