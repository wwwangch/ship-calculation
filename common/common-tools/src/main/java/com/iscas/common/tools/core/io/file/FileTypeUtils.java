package com.iscas.common.tools.core.io.file;

import org.apache.commons.lang3.ArrayUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 判断一个文件类型的工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/8/6 8:48
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class FileTypeUtils {
    public static int FILE_PREFIX_LENGTH = 15;

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 判断一个文件的类型
     *
     * @param is 输入流
     * @return com.iscas.common.tools.core.io.file.FileTypeEnum
     * @throws IOException io异常
     * @date 2021/1/6
     * @since jdk1.8
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static FileTypeEnum getFileType(InputStream is) throws IOException {

        //读取FILE_PREFIX_LENGTH个字节，少于FILE_PREFIX_LENGTH，后面都是0
        int length = FILE_PREFIX_LENGTH;
        byte[] b = new byte[length];
        is.read(b);
        return getFileType(b);
    }

    /**
     * 判断一个文件的类型
     *
     * @param b FILE_PREFIX_LENGTH个字符串
     * @return com.iscas.common.tools.core.io.file.FileTypeEnum
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static FileTypeEnum getFileType(byte[] b) {

        //读取FILE_PREFIX_LENGTH个字节，少于FILE_PREFIX_LENGTH，后面都是0
        String prefix = bytesToHexString(b);
        FileTypeEnum[] values = FileTypeEnum.values();
        if (ArrayUtils.isNotEmpty(values)) {
            for (FileTypeEnum fileTypeEnum : values) {
                String enumPrefix = fileTypeEnum.getPrefix();
                assert prefix != null;
                if (prefix.toLowerCase().startsWith(enumPrefix.toLowerCase())) {
                    return fileTypeEnum;
                }
            }
        }
        return FileTypeEnum.UNKOWN;
    }

    /**
     * 判断一个文件的类型
     *
     * @param path 文件路径
     * @return com.iscas.common.tools.core.io.file.FileTypeEnum
     * @throws IOException IO异常
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static FileTypeEnum getFileType(String path) throws IOException {
        try (InputStream is = new FileInputStream(path)) {
            return getFileType(is);
        }
    }


}
