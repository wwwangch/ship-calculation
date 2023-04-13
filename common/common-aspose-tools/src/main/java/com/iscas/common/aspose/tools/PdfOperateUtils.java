package com.iscas.common.aspose.tools;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

import java.io.OutputStream;

/**
 * PDF操作工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/29 15:13
 * @since jdk1.8
 */
public class PdfOperateUtils {
    static {
        LicenseUtils.initLicense();
    }

    private PdfOperateUtils() {}

    public static void merge(Document doc1, Document doc2, OutputStream os) {
        doc1.getPages().add(doc2.getPages());
        doc1.save(os, SaveFormat.Pdf);
    }
}
