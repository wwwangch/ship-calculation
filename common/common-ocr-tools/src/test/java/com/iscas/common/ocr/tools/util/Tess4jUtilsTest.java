package com.iscas.common.ocr.tools.util;

import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/22 14:43
 * @since jdk11
 */
public class Tess4jUtilsTest {

    @Test
    public void getText() throws IOException, TesseractException {
        String text = Tess4jUtils.getText("D:\\ocr\\Tess4J\\tessdata", "chi_sim", new FileInputStream("D:\\应用技术架构\\21.jpg"));
        System.out.println(text);
        Assertions.assertNotNull(text);
    }

    @Test
    public void getText2() throws IOException, TesseractException {
        String text = Tess4jUtils.getText("D:\\ocr\\Tess4J\\tessdata", new FileInputStream("D:\\应用技术架构\\1.jpg"));
        System.out.println(text);
        Assertions.assertNotNull(text);
    }

    @Test
    public void getMultiText() throws IOException, TesseractException {
        Map<String, String> res = Tess4jUtils.getMultiText("D:\\ocr\\Tess4J\\tessdata", "chi_sim", new File("D:\\应用技术架构"));
        System.out.println(res);
        Assertions.assertNotNull(res);
    }

}