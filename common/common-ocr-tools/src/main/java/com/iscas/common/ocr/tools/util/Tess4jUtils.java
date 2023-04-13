package com.iscas.common.ocr.tools.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ITesseract线程不安全，不要在多线程中使用共享的ITesseract
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/11/18 19:57
 * @since jdk11
 */
public class Tess4jUtils {
    private Tess4jUtils() {
    }

    /**
     * 识别一个
     *
     * @param dataPath 训练库路径
     * @param language 语言
     * @param is       图片输入流
     * @return java.lang.String
     * @date 2022/11/23
     * @since jdk11
     */
    public static String getText(String dataPath, String language, InputStream is) throws IOException, TesseractException {
        ITesseract instance = getTesseract(dataPath, language);
        return getText(instance, is);
    }

    /**
     * 识别一个，默认使用中文
     *
     * @param dataPath 训练库路径
     * @param is       图片输入流
     * @return java.lang.String
     * @date 2022/11/23
     * @since jdk11
     */
    public static String getText(String dataPath, InputStream is) throws IOException, TesseractException {
        return getText(dataPath, "chi_sim", is);
    }

    /**
     * 识别一个
     *
     * @param instance ITesseract
     * @param is       图片输入流
     * @return java.lang.String
     * @date 2022/11/23
     * @since jdk11
     */
    public static String getText(ITesseract instance, InputStream is) throws IOException, TesseractException {
        BufferedImage bufferedImage = ImageIO.read(is);
        return instance.doOCR(bufferedImage);
    }

    /**
     * 识别一个路径，返回key为文件名，value为识别结果
     *
     * @param dataPath 训练库的路径
     * @param language 语言
     * @param file     文件
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @date 2022/11/23
     * @since jdk11
     */
    public static Map<String, String> getMultiText(String dataPath, String language, File file) throws IOException, TesseractException {
        Map<String, String> result = new HashMap<>(16);
        if (file.isDirectory()) {
            ITesseract instance = getTesseract(dataPath, language);
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                if (subFile.isFile()) {
                    result.put(subFile.getName(), getText(instance, new FileInputStream(subFile)));
                }
            }
        }
        return result;
    }

    private static ITesseract getTesseract(String dataPath, String language) {
        ITesseract instance = new Tesseract();
        instance.setDatapath(dataPath);
        instance.setLanguage(language);
        return instance;
    }


}
