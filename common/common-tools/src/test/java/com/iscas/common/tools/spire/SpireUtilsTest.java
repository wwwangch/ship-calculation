package com.iscas.common.tools.spire;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/30 15:19
 * @since jdk1.8
 */
class SpireUtilsTest {
    /**
     * 测试复制行
     * */
    @Test
    public void copyRow() {
        SpireUtils.copyRow(0, 0, "C:\\Users\\Administrator\\Desktop\\三亚测试\\动态表单.docx",
                "C:\\Users\\Administrator\\Desktop\\三亚测试\\动态表单扩容.docx", 8, 3);
    }

    @Test
    public void testAppend() {
        Document doc1 = new Document("D:/文档资料/安全认证.docx");
        Document doc2 = new Document("D:\\文档资料\\中宣部\\应用软件与系统集成-合稿V2.docx");
        doc1 = SpireUtils.appendWord(doc1, doc2);
        doc1.saveToFile("d:/tmp/ceshi2.docx", FileFormat.Docx_2013);
    }

}