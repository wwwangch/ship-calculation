package com.iscas.common.aspose.tools;

import com.aspose.pdf.Document;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/29 15:17
 * @since jdk1.8
 */
public class PdfOperateUtilsTest {

    @Test
    public void test() throws FileNotFoundException {
        PdfOperateUtils.merge(new Document("C:\\Users\\Administrator\\Desktop\\三亚测试\\一.pdf"),
                new Document("C:\\Users\\Administrator\\Desktop\\三亚测试\\二.pdf"), new FileOutputStream("C:\\Users\\Administrator\\Desktop\\三亚测试\\合并结果.pdf"));
    }
}