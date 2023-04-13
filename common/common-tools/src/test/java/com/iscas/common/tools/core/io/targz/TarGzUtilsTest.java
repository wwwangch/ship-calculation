package com.iscas.common.tools.core.io.targz;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tarGz 解压缩
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/27 13:32
 * @since jdk1.8
 */
class TarGzUtilsTest {

    @Test
    public void uncompress() throws IOException {
        TarGzUtils.uncompress(new File("C:\\Users\\Administrator\\Desktop\\数据中台后台\\test\\apache-tomcat-8.5.84.tar.gz"),
                new File("C:\\Users\\Administrator\\Desktop\\数据中台后台\\test\\test"));
    }

    @Test
    public void compress() throws IOException {
        TarGzUtils.compress(new File("C:\\Users\\Administrator\\Desktop\\数据中台后台\\test\\apache-tomcat-8.5.84"),
                new File("C:\\Users\\Administrator\\Desktop\\数据中台后台\\test\\apache-tomcat-8.5.84.tar.gz"));
    }
}