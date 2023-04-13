package com.iscas.common.tools.office.iceblue;

import com.spire.doc.Document;
import org.junit.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 9:51
 * @since jdk1.8
 */
public class CompareWordTest {
    @Test
    public void compare() {
        //创建Document实例
        Document doc1 = new Document();
        //加载第一个Word示例文档
        doc1.loadFromFile("d:/wordcompare/平台技术调研.docx");
        //创建Document实例
        Document doc2 = new Document();
        //加载第二个Word示例文档
        doc2.loadFromFile("d:/wordcompare/平台技术调研1.docx");
        //比较两个示例文档的内容差异
        doc1.compare(doc2, "e-iceblue");
        //保存结果文档
        doc1.saveToFile("d:/wordcompare/平台技术调研-result.docx");
    }

}
