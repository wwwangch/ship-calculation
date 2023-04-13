package com.iscas.common.tools.pdfword;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给PDF添加水印
 *
 * @author solexit06
 */
@SuppressWarnings("unused")
public class WaterPdf {
    /**
     * outFile 输出文件地址+全名
     * inputhFile   要加水印的原文件
     * imagePath   水印图片的地址+全名
     */

    public static void setWatermark(String outFile, String inputFile, String imagePath) throws DocumentException, IOException {
        //获取需要加水印的输入文件
        PdfReader reader = new PdfReader(inputFile);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));
        PdfStamper stamper = new PdfStamper(reader, bos);
        //获取输入文件页数
        int total = reader.getNumberOfPages();
        for (int i = 1; i <= total; i++)
        //noinspection AlibabaRemoveCommentedCode
        {
            //在内容上方加水印
            PdfContentByte contentOver = stamper.getOverContent(i);
            //在内容下方加水印
            //PdfContentByte contentOver  = stamper.getUnderContent(i);
            //获取要作为水印的图片
            Image img = Image.getInstance(imagePath);
            //图片距离文档的左下边距
            img.setAbsolutePosition(10, 10);
            //图片水印的大小
            img.scaleToFit(575, 802);
            //旋转
            img.setRotationDegrees(0);
            //添加图片水印
            contentOver.addImage(img);
            contentOver.addImage(img);

            /*添加文字水印*/
            contentOver.beginText();
            //水印颜色
            contentOver.setColorFill(Color.RED);
            contentOver.endText();
        }
        stamper.close();
        System.out.println("添加水印成功");
    }
}
