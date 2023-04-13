package com.iscas.base.biz.util;

import cn.hutool.core.io.IoUtil;
import com.iscas.common.tools.core.io.file.FileTypeEnum;
import com.iscas.common.tools.core.io.file.FileTypeUtils;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import org.springframework.http.MediaType;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/4/10 16:20
 * @since jdk1.8
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ResponseUtils {

    /**
     * 按流输出，设置content-type
     */
    public static void returnStream(InputStream is, HttpServletResponse response, String contentType) throws IOException {
        response.setContentType(contentType);
        IoUtil.copy(is, response.getOutputStream());
    }

    /**
     * 按流输出，按照流开头标识的文件格式自动设置content-type
     */
    public static void returnStream(InputStream is, HttpServletResponse response) throws IOException, BaseException {
        byte[] buf = new byte[FileTypeUtils.FILE_PREFIX_LENGTH];
        is.read(buf);

        FileTypeEnum fileType = FileTypeUtils.getFileType(buf);
        String contentType = switch (fileType) {
            case JPEG ->  MediaType.IMAGE_JPEG_VALUE;
            case PNG -> MediaType.IMAGE_PNG_VALUE;
            case GIF -> MediaType.IMAGE_GIF_VALUE;
            case XML -> MediaType.APPLICATION_XML_VALUE;
            case HTML -> MediaType.TEXT_HTML_VALUE;
            case ADOBE_ACROBAT ->  MediaType.APPLICATION_PDF_VALUE;
            default -> throw Exceptions.formatBaseException("不支持的文件类型:{}", fileType);
        };
        //现将读到的前几个字符传出去
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(buf);
        outputStream.flush();
        returnStream(is, response, contentType);
    }
}