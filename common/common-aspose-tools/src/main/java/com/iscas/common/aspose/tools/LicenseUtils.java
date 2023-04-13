package com.iscas.common.aspose.tools;

import com.aspose.words.License;

import java.io.InputStream;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/9 12:53
 * @since jdk11
 */
public class LicenseUtils {
    private LicenseUtils() {
    }

    private volatile static License wordLicense = null;

    private volatile static com.aspose.cells.License excelLicense = null;

    private volatile static com.aspose.pdf.License pdfLicense = null;

    //    static {
//        String license =
//                "<License>\n" +
//                        "  <Data>\n" +
//                        "    <Products>\n" +
//                        "      <Product>Aspose.Cells for Java</Product>\n" +
//                        "      <Product>Aspose.Words for Java</Product>\n" +
//                        "      <Product>Aspose.Slides for Java</Product>\n" +
//                        "    </Products>\n" +
//                        "    <EditionType>Enterprise</EditionType>\n" +
//                        "    <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
//                        "    <LicenseExpiry>20991231</LicenseExpiry>\n" +
//                        "    <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
//                        "  </Data>\n" +
//                        "  <Signature>datax</Signature>\n" +
//                        "</License>";
//        try {
//            new License().setLicense(new ByteArrayInputStream(license.getBytes("UTF-8")));
//        } catch (Exception e) {
//        }
//    }

    /**
     * 初始化license
     *
     * @date 2022/7/9
     * @since jdk11
     */
    public static void initLicense() {
        initWordsLicense();
        initCellsLicense();
        initPdfLicense();
    }

    public static void initWordsLicense() {
        if (wordLicense == null) {
            synchronized (LicenseUtils.class) {
                if (wordLicense == null) {
                    try (InputStream is = ConvertUtils.class.getClassLoader().getResourceAsStream("license.xml")) {
                        wordLicense = new License();
                        wordLicense.setLicense(is);
                    } catch (Exception e) {
                        throw new RuntimeException("获取license出错", e);
                    }
                }
            }
        }
    }

    public static void initCellsLicense() {
        if (excelLicense == null) {
            synchronized (LicenseUtils.class) {
                if (excelLicense == null) {
                    try (InputStream is = ConvertUtils.class.getClassLoader().getResourceAsStream("license.xml")) {
                        excelLicense = new com.aspose.cells.License();
                        excelLicense.setLicense(is);
                    } catch (Exception e) {
                        throw new RuntimeException("获取license出错", e);
                    }
                }
            }
        }
    }

    public static void initPdfLicense() {
        if (pdfLicense == null) {
            synchronized (LicenseUtils.class) {
                if (pdfLicense == null) {
                    try (InputStream is = ConvertUtils.class.getClassLoader().getResourceAsStream("license.xml")) {
                        pdfLicense = new com.aspose.pdf.License();
                        pdfLicense.setLicense(is);
                    } catch (Exception e) {
                        throw new RuntimeException("获取license出错", e);
                    }
                }
            }
        }
    }
}
