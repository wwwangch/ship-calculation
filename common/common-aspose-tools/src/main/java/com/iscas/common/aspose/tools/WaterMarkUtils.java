package com.iscas.common.aspose.tools;

import com.aspose.words.*;
import com.aspose.words.Shape;

import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 添加水印的方法
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/9 12:58
 * @since jdk11
 */
@SuppressWarnings("unused")
public class WaterMarkUtils {
    static {
        LicenseUtils.initLicense();
    }

    private WaterMarkUtils() {
    }

    /**
     * 为word添加水印,输出docx格式
     *
     * @param is   输入流参数，原始word的输入流
     * @param text 水印文字
     * @param os   输出流
     * @date 2022/7/11
     * @since jdk11
     */
    public static void addDocxWatermarkText(InputStream is, String text, OutputStream os) throws Exception {
        addWatermarkText(is, text, os, SaveFormat.DOCX);
    }

    /**
     * 为word添加水印,输出doc格式
     *
     * @param is   输入流参数，原始word的输入流
     * @param text 水印文字
     * @param os   输出流
     * @date 2022/7/11
     * @since jdk11
     */
    public static void addDocWatermarkText(InputStream is, String text, OutputStream os) throws Exception {
        addWatermarkText(is, text, os, SaveFormat.DOC);
    }

    /**
     * 添加水印
     *
     * @param is         输入流参数，原始文件的输入流
     * @param text       水印文字
     * @param os         输出流 输出文件的流
     * @param saveFormat 输出文档格式
     * @date 2022/7/11
     * @since jdk11
     */
    public static void addWatermarkText(InputStream is, String text, OutputStream os, int saveFormat) throws Exception {
        long old = System.currentTimeMillis();
        Document doc = new Document(is);
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
        //水印内容
        watermark.getTextPath().setText(text);
        //水印字体
        watermark.getTextPath().setFontFamily("宋体");
        //水印宽度
        watermark.setWidth(100);
        //水印高度
        watermark.setHeight(20);
        //旋转水印
        watermark.setRotation(-40);
        //水印颜色 浅灰色
        watermark.getFill().setColor(Color.lightGray);
        watermark.setStrokeColor(Color.lightGray);
        //设置相对水平位置
        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        //设置相对垂直位置
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        //设置包装类型
        watermark.setWrapType(WrapType.NONE);
        //设置垂直对齐
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置文本水平对齐方式
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);
        for (Section sect : doc.getSections()) {
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
        doc.save(os, saveFormat);
        long now = System.currentTimeMillis();
        System.out.println("添加水印成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * 插入多个水印,输出doc格式的
     *
     * @param is         输入流
     * @param wmText     水印内容
     * @param os         输出流
     * @throws Exception 异常
     */
    public static void addDocWaterMarkTextMore(InputStream is, String wmText, OutputStream os) throws Exception {
        addWaterMarkTextMore(is, wmText, os, SaveFormat.DOC);
    }

    /**
     * 插入多个水印,输出docx格式的
     *
     * @param is         输入流
     * @param wmText     水印内容
     * @param os         输出流
     * @throws Exception 异常
     */
    public static void addDocxWaterMarkTextMore(InputStream is, String wmText, OutputStream os) throws Exception {
        addWaterMarkTextMore(is, wmText, os, SaveFormat.DOCX);
    }

    /**
     * 插入多个水印
     *
     * @param is         输入流
     * @param wmText     水印内容
     * @param os         输出流
     * @param saveFormat 格式
     * @throws Exception 异常
     */
    public static void addWaterMarkTextMore(InputStream is, String wmText, OutputStream os, int saveFormat) throws Exception {
        long old = System.currentTimeMillis();
        Document doc = new Document(is);
        Paragraph watermarkPara = new Paragraph(doc);
        for (int j = 0; j < 500; j = j + 100) {
            for (int i = 0; i < 700; i = i + 85) {
                Shape waterShape = shapeMore(doc, wmText, j, i);
                watermarkPara.appendChild(waterShape);
            }
        }
        for (Section sect : doc.getSections()) {
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
        doc.save(os, saveFormat);
        long now = System.currentTimeMillis();
        System.out.println("添加水印成功，共耗时：" + (now - old) + "毫秒");
    }

    /**
     * 在页眉中插入水印
     *
     * @param watermarkPara 段落
     * @param sect          章节
     * @param headerType    类型
     * @throws Exception 异常
     */
    private static void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);
        if (header == null) {
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }
        header.appendChild(watermarkPara.deepClone(true));
    }

    /**
     * 设置水印属性
     *
     * @param doc    文档
     * @param wmText 水印内容
     * @param left   靠左的位置
     * @param top    靠上的位置
     * @return Shape
     * @throws Exception 异常
     */
    public static Shape shapeMore(Document doc, String wmText, double left, double top) throws Exception {
        Shape waterShape = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
        //水印内容
        waterShape.getTextPath().setText(wmText);
        //水印字体
        waterShape.getTextPath().setFontFamily("宋体");
        //水印宽度
        waterShape.setWidth(40);
        //水印高度
        waterShape.setHeight(13);
        //旋转水印
        waterShape.setRotation(-40);
        //水印颜色
        waterShape.setStrokeColor(new Color(210, 210, 210));
        //将水印放置在页面中心
        waterShape.setLeft(left);
        waterShape.setTop(top);
        //设置包装类型
        waterShape.setWrapType(WrapType.NONE);
        return waterShape;
    }

}
