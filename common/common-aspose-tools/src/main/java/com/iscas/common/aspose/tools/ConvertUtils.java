package com.iscas.common.aspose.tools;

import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveFormat;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 转换工具类，word转PDF等
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/9 11:30
 * @since jdk11
 */
@SuppressWarnings("unused")
public class ConvertUtils {

    static {
        LicenseUtils.initLicense();
    }

    private ConvertUtils() {
    }

    /**
     * excel转为pdf
     *
     * @param is 原始文件输入流
     * @param os 输出流 转换后的pdf
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static void excel2pdf(InputStream is, OutputStream os) throws Exception {
        long old = System.currentTimeMillis();
        Workbook workbook = new Workbook(is);
        // 设置pdf保存的格式以及强制所有列都在同一页
        PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
        pdfSaveOptions.setOnePagePerSheet(true);
        int sheetCount = workbook.getWorksheets().getCount();
        int[] showSheets = new int[sheetCount];
        for (int i = 0; i < sheetCount; i++) {
            showSheets[i] = i;
        }
        //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
        autoDraw(workbook, showSheets);
        //隐藏workbook中不需要的sheet页。
        printSheetPage(workbook, showSheets);
        workbook.save(os, pdfSaveOptions);
        long now = System.currentTimeMillis();
        System.out.println("Excel转换成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * doc转为docx
     *
     * @param is 原始文件输入流doc
     * @param os 输出流 转换后的docx
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static void doc2docx(InputStream is, OutputStream os) throws Exception {
        convert(is, os, SaveFormat.DOCX);
    }

    /**
     * docx转为doc
     *
     * @param is 原始文件输入流docx
     * @param os 输出流 转换后的doc
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static void docx2doc(InputStream is, OutputStream os) throws Exception {
        convert(is, os, SaveFormat.DOC);
    }

    /**
     * HTML转为WORD,docx格式
     *
     * @param htmlText html文本
     * @param os       输出流 转换后的word，docx格式
     * @throws Exception 异常
     * @date 2022/7/9
     * @since jdk11
     */
    public static void html2docx(String htmlText, OutputStream os) throws Exception {
        html2Word(htmlText, os, SaveFormat.DOCX);
    }

    /**
     * HTML转为WORD,doc格式
     *
     * @param htmlText html文本
     * @param os       输出流 转换后的word,doc格式
     * @throws Exception 异常
     * @date 2022/7/9
     * @since jdk11
     */
    public static void html2doc(String htmlText, OutputStream os) throws Exception {
        html2Word(htmlText, os, SaveFormat.DOC);
    }

    /**
     * HTML转为WORD
     *
     * @param htmlText   html文本
     * @param os         输出流 转换后的word
     * @param saveFormat 输出的格式。{@link SaveFormat#DOC} 或 {@link SaveFormat#DOCX}
     * @throws Exception 异常
     * @date 2022/7/9
     * @since jdk11
     */
    public static void html2Word(String htmlText, OutputStream os, int saveFormat) throws Exception {
        long old = System.currentTimeMillis();
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.insertHtml(htmlText);
        doc.save(os, saveFormat);
        long now = System.currentTimeMillis();
        System.out.println("转换成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * word转为HTML
     *
     * @param is 输入流 待转换的word
     * @param os 输出流 转换后的html
     * @throws Exception 异常
     * @date 2022/7/9
     * @since jdk11
     */
    public static void word2html(InputStream is, OutputStream os) throws Exception {
        long old = System.currentTimeMillis();
        Document doc = new Document(is);
        HtmlSaveOptions opts = new HtmlSaveOptions(SaveFormat.HTML);
        opts.setExportXhtmlTransitional(true);
        opts.setExportImagesAsBase64(true);
        opts.setExportPageSetup(true);
        doc.save(os, opts);
        long now = System.currentTimeMillis();
        System.out.println("转换成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * word转为PDF
     *
     * @param is 输入流
     * @param os 输出流
     * @throws Exception 异常
     * @date 2022/7/9
     * @since jdk11
     */
    public static void word2pdf(InputStream is, OutputStream os) throws Exception {
        convert(is, os, SaveFormat.PDF);
    }

    /**
     * pdf转为word docx
     *
     * @param is 输入流
     * @param os 输出流
     * @throws Exception 异常
     * @date 2022/11/29
     * @since jdk11
     */
    public static void pdf2docx(InputStream is, OutputStream os) throws Exception {
        long old = System.currentTimeMillis();
        com.aspose.pdf.Document doc = new com.aspose.pdf.Document(is);
        doc.save(os, com.aspose.pdf.SaveFormat.DocX);
        long now = System.currentTimeMillis();
        System.out.println("转换成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * 格式转换
     *
     * @param is         输入流
     * @param os         输出流
     * @param saveFormat 文件格式,参见{@link SaveFormat}
     * @throws Exception 异常
     * @date 2022/7/9
     * @since jdk11
     */
    public static void convert(InputStream is, OutputStream os, int saveFormat) throws Exception {
        long old = System.currentTimeMillis();
        Document doc = new Document(is);
        // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
        doc.save(os, saveFormat);
        long now = System.currentTimeMillis();
        System.out.println("转换成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * 隐藏workbook中不需要的sheet页。
     *
     * @param wb   excel workbook
     * @param page 显示页的sheet数组
     */
    private static void printSheetPage(Workbook wb, int[] page) {
        for (int i = 1; i < wb.getWorksheets().getCount(); i++) {
            wb.getWorksheets().get(i).setVisible(false);
        }
        if (null == page || page.length == 0) {
            wb.getWorksheets().get(0).setVisible(true);
        } else {
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).setVisible(true);
            }
        }
    }

    /**
     * 设置打印的sheet 自动拉伸比例
     *
     * @param wb   workbook
     * @param page 自动拉伸的页的sheet数组
     */
    private static void autoDraw(Workbook wb, int[] page) {
        if (null != page && page.length > 0) {
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
            }
        }
    }

}
