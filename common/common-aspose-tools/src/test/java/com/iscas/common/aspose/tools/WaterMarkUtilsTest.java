package com.iscas.common.aspose.tools;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/9 13:15
 * @since jdk11
 */
class WaterMarkUtilsTest {
    @Test
    public void test() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:\\文档资料\\_部署安装\\离线安装k8s\\1、安装k8s集群\\离线安装k8s集群.docx"));
                OutputStream os =Files.newOutputStream(Paths.get("D:\\test.docx"))
        ) {
            WaterMarkUtils.addDocxWatermarkText(is, "中国科学院软件研究所", os);
        }
    }

    @Test
    public void test2() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:\\文档资料\\_部署安装\\离线安装k8s\\1、安装k8s集群\\离线安装k8s集群.docx"));
                OutputStream os =Files.newOutputStream(Paths.get("D:\\test.docx"))
        ) {
            WaterMarkUtils.addDocxWaterMarkTextMore(is, "中国科学院软件研究所", os);
        }
    }
}