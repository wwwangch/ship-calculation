package com.iscas.common.aspose.tools;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/9 12:39
 * @since jdk11
 */
class ConvertUtilsTest {

    /**
     * 测试word转为PDF
     */
    @Test
    public void testWord2Pdf() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:\\文档资料\\_部署安装\\离线安装k8s\\1、安装k8s集群\\离线安装k8s集群.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/test.pdf"))
        ) {
            ConvertUtils.word2pdf(is, os);
        }
    }

    /**
     * 测试word转为HTML
     */
    @Test
    public void testWord2Html() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:\\文档资料\\_部署安装\\离线安装k8s\\1、安装k8s集群\\离线安装k8s集群.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/test.html"))
        ) {
            ConvertUtils.word2html(is, os);
        }
    }

    /**
     * 测试HTML转为word
     */
    @Test
    public void testHtml2Docx() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:/tmp/test.html"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/test.docx"))
        ) {
            ConvertUtils.html2docx(new String(is.readAllBytes(), StandardCharsets.UTF_8), os);
        }
    }

    /**
     * 测试docx转为doc
     */
    @Test
    public void testDocx2Doc() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:/tmp/test.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/test.doc"))
        ) {
            ConvertUtils.docx2doc(is, os);
        }
    }

    /**
     * 测试doc转为docx
     */
    @Test
    public void testDoc2Docx() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:/tmp/test.doc"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/testdocx.docx"))
        ) {
            ConvertUtils.docx2doc(is, os);
        }
    }

    /**
     * 测试EXCEL转为pdf
     */
    @Test
    public void testExcel2Pdf() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:\\文档资料\\数据中台\\数据中台2022-05-31功能修改.xlsx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/test210.pdf"))
        ) {
            ConvertUtils.excel2pdf(is, os);
        }
    }

    /**
     * 测试pdf转为word
     */
    @Test
    public void testPdf2docx() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:\\朱全文报销\\舟山汉庭酒店发票.pdf"));
                OutputStream os = Files.newOutputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\test.docx"))
        ) {
            ConvertUtils.pdf2docx(is, os);
        }
    }

}