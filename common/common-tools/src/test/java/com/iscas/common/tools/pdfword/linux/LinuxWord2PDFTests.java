package com.iscas.common.tools.pdfword.linux;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * linux下word转PDF测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/22 18:07
 * @since jdk1.8
 */
public class LinuxWord2PDFTests {

    @Test
    @Disabled
    public void test() throws IOException, InterruptedException {
        LinuxWord2Pdf.wordToPdf("D:\\文档资料\\三部软件研发-java.docx", "D:a.pdf");
    }
}
