package com.iscas.common.tools.jsscss;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import lombok.Cleanup;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * JS/CSS压缩相关工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/8 15:35
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class JsCssCompressUtils {
    private static final String TYPE_YUI = "yui";
    private static final String TYPE_PACK = "pack";

    private JsCssCompressUtils() {
    }

    /**
     * css压缩
     *
     * @param content 字节数组
     * @return byte[]
     * @throws IOException IO异常
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static byte[] csscompress(byte[] content) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        @Cleanup Reader in = new InputStreamReader(bais);
        CssCompressor csscompressor = new CssCompressor(in);
        @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
        @Cleanup Writer writer = new PrintWriter(os);
        csscompressor.compress(writer, -1);
        writer.flush();
        return os.toByteArray();
    }

    /**
     * CSS压缩
     *
     * @param content css
     * @return java.lang.String
     * @throws IOException IO异常
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static String csscompress(String content) throws IOException {
        @Cleanup Reader in = new InputStreamReader(IOUtils.toInputStream(content, "utf-8"));
        CssCompressor csscompressor = new CssCompressor(in);
        @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
        @Cleanup Writer writer = new PrintWriter(os);
        csscompressor.compress(writer, -1);
        writer.flush();
        return os.toString(StandardCharsets.UTF_8);
    }

    /**
     * js压缩
     *
     * @param content JS字节数组
     * @param type    压缩方式 yui 或pack
     * @return byte[]
     * @throws Exception 异常
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static byte[] jscompress(byte[] content, String type) throws Exception {

        if (content == null || content.length == 0) {
            return content;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(content);
        @Cleanup Reader in = new InputStreamReader(bais);
        JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
            @Override
            public void warning(String message, String sourceName,
                                int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("/n[WARNING] " + message);
                } else {
                    System.err.println("/n[WARNING] " + line + ':' + lineOffset + ':' + message);
                }
            }

            @Override
            public void error(String message, String sourceName,
                              int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("/n[ERROR] " + message);
                } else {
                    System.err.println("/n[ERROR] " + line + ':' + lineOffset + ':' + message);
                }
            }

            @Override
            public EvaluatorException runtimeError(String message, String sourceName,
                                                   int line, String lineSource, int lineOffset) {
                error(message, sourceName, line, lineSource, lineOffset);
                return new EvaluatorException(message);
            }
        });
        @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
        @Cleanup Writer writer = new PrintWriter(os);
        if (Objects.equals(TYPE_YUI, type)) {
            compressor.compress(writer, -1, true, false, false, false);
        } else if (Objects.equals(TYPE_PACK, type)) {
            //普通压缩
            compressor.compress(writer, 0, true, false, false, false);
        }
        writer.flush();
        return os.toByteArray();
    }

    /**
     * JS压缩
     *
     * @param content js
     * @param type    压缩方式yui 或pack
     * @return java.lang.String
     * @throws IOException IO异常
     * @date 2021/1/6
     * @since jdk1.8
     */
    public static String jscompress(String content, String type) throws IOException {
        @Cleanup Reader in = new InputStreamReader(IOUtils.toInputStream(content, "utf-8"));
        JavaScriptCompressor compressor = new JavaScriptCompressor(in, new ErrorReporter() {
            @Override
            public void warning(String message, String sourceName,
                                int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("/n[WARNING] " + message);
                } else {
                    System.err.println("/n[WARNING] " + line + ':' + lineOffset + ':' + message);
                }
            }

            @Override
            public void error(String message, String sourceName,
                              int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("/n[ERROR] " + message);
                } else {
                    System.err.println("/n[ERROR] " + line + ':' + lineOffset + ':' + message);
                }
            }

            @Override
            public EvaluatorException runtimeError(String message, String sourceName,
                                                   int line, String lineSource, int lineOffset) {
                error(message, sourceName, line, lineSource, lineOffset);
                return new EvaluatorException(message);
            }
        });
        @Cleanup ByteArrayOutputStream os = new ByteArrayOutputStream();
        @Cleanup Writer writer = new PrintWriter(os);
        if (Objects.equals(TYPE_YUI, type)) {
            compressor.compress(writer, -1, true, false, false, false);
        } else if (Objects.equals(TYPE_PACK, type)) {
            //普通压缩
            compressor.compress(writer, 0, true, false, false, false);
        }
        writer.flush();
        return os.toString(StandardCharsets.UTF_8);
    }
}
