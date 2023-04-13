package com.iscas.common.aspose.tools;

import com.aspose.words.Bookmark;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/11 14:52
 * @since jdk11
 */
class WordOperateUtilsTest {

    @Test
    public void testAppendAuto() throws Exception {
        LicenseUtils.initLicense();
        Document doc1 = new Document("D:/文档资料/安全认证.docx");
        Document doc2 = new Document("D:/文档资料/平台技术调研.docx");
        Document doc3 = new Document("D:\\文档资料\\中宣部\\应用软件与系统集成-合稿V2.docx");
        doc1 = WordOperateUtils.appendWordAuto(doc1, doc2, doc3);
        doc1.save(Files.newOutputStream(Paths.get("d:/tmp/ceshi2.docx")), SaveFormat.DOCX);
    }



    /**
     * 测试拼接一个word文档
     */
    @Test
    public void testAppendDoc() throws Exception {
        LicenseUtils.initLicense();
        Document doc1 = new Document("D:/文档资料/安全认证.docx");
        Document doc2 = new Document("D:\\文档资料\\中宣部\\应用软件与系统集成-合稿V2.docx");
        doc1 = WordOperateUtils.appendWord(doc1, doc2, true, null);
        doc1.save(Files.newOutputStream(Paths.get("d:/tmp/ceshi.docx")), SaveFormat.DOCX);
    }

    /**
     * 测试拼接一个word文档
     */
    @Test
    public void testAppendDoc2() throws Exception {
        try (
                InputStream mainIs = Files.newInputStream(Paths.get("D:/文档资料/安全认证.docx"));
                InputStream addIs = Files.newInputStream(Paths.get("D:/文档资料/智能门户.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/文档资料/智能menhu.docx"));
        ) {
            WordOperateUtils.appendWord(mainIs, addIs, os, SaveFormat.DOCX, true, null);
        }
    }

    /**
     * 测试拼接一个word文档
     */
    @Test
    public void testAppendDoc3() throws Exception {
        try (
                InputStream mainIs = Files.newInputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\_指南附件1.技术改进类（方式一）项目申报书_5表单(1).doc"));
                InputStream addIs = Files.newInputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\智能门户.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\合并结果.docx"));
        ) {
            WordOperateUtils.appendWord(mainIs, addIs, os, SaveFormat.DOCX, true, "test1");
        }
    }

    /**
     * 测试拼接一个pdf文档
     */
    @Test
    public void testAppendDoc4() throws Exception {
        try (
                InputStream mainIs = Files.newInputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\_指南附件1.技术改进类（方式一）项目申报书_5表单(1).doc"));
                InputStream addIs = Files.newInputStream(Paths.get("D:\\朱全文报销\\舟山汉庭酒店发票.pdf"));
                OutputStream os = Files.newOutputStream(Paths.get("C:\\Users\\Administrator\\Desktop\\合并结果.docx"));
        ) {
            WordOperateUtils.appendPdf(mainIs, addIs, os, SaveFormat.DOCX, true, "test1");
        }
    }

    /**
     * 测试清洁文档，将批注都删除，接受修订
     */
    @Test
    public void cleanWord() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:/tmp/test.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/clean.docx"));
        ) {
            WordOperateUtils.cleanWord(is, os, SaveFormat.DOCX);
        }
    }

    /**
     * 测试获取所有书签
     */
    @Test
    public void testGetBookmarks() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:/tmp/test.docx"));
        ) {
            List<Bookmark> bookMarks = WordOperateUtils.getBookMarks(is);
            bookMarks.forEach(System.out::println);
        }
    }

    /**
     * 设置书签值
     */
    @Test
    public void testSetBookmarkVal() throws Exception {
        try (
                InputStream is = Files.newInputStream(Paths.get("D:/tmp/test.docx"));
                OutputStream os = Files.newOutputStream(Paths.get("D:/tmp/testxx.docx"))
        ) {
            WordOperateUtils.setBookmarkVal(is, os, SaveFormat.DOCX, "你好你好", "哇啦啦啦");
        }
        try (
                InputStream is2 = Files.newInputStream(Paths.get("D:/tmp/testxx.docx"))
        ) {
            WordOperateUtils.getBookMarks(is2).forEach(bookmark -> {
                try {
                    System.out.println("bookmark:" + bookmark.getName() + "," + bookmark.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * 测试复制列
     * */
    @Test
    public void copyCell3() throws Exception {
        WordOperateUtils.copyCell(0, 0, "C:\\Users\\Administrator\\Desktop\\三亚测试\\xxx.docx",
                "C:\\Users\\Administrator\\Desktop\\三亚测试\\yyy.docx", 21, 4 , 3, 5, 4);
    }

    /**
     * 生成目录
     * */
    @Test
    public void insertCatalogue() throws Exception {
        LicenseUtils.initWordsLicense();
        Document doc = new Document("C:\\Users\\Administrator\\Documents\\WeChat Files\\zhuquanwen\\FileStorage\\File\\2023-02\\文件合并测试申报书.docx");
        WordOperateUtils.insertCatalogue(doc);
        doc.save("d:/test.docx", SaveFormat.DOCX);
    }
}