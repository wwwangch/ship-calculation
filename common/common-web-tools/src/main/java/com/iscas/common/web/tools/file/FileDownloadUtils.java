package com.iscas.common.web.tools.file;

import com.iscas.common.web.tools.file.limiter.BandWidthLimiter;
import com.iscas.common.web.tools.file.limiter.LimiterOutputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * web-file 相关操作工具集
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/14 21:19
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class FileDownloadUtils {
    private static final String MSIE_6 = "MSIE 6.0";
    private static final String MSIE_7 = "MSIE 7.0";
    private static final String MSIE_8 = "MSIE 8.0";
    private static final String MSIE_9 = "MSIE 9.0";
    private static final String CHROME = "Chrome";
    private static final String SAFARI = "Safari";

    /**
     * 私有的构造方法
     */
    private FileDownloadUtils() {
    }

    /**
     * 当前正在下载文件的连接数，作文件下载限流使用
     */
    public static AtomicInteger onlineDownloadNumber = new AtomicInteger(0);

    public static String getContentDispositionVal(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        String val;
        String requestHeader = request.getHeader("User-Agent");
        if (requestHeader.contains(MSIE_6) || requestHeader.contains(MSIE_7)) {
            // IE6, IE7 浏览器
            val = "attachment;filename=" + new String(name.getBytes(), "ISO8859-1");
        } else if (requestHeader.contains(MSIE_8)) {
            // IE8
            val = "attachment;filename=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        } else if (requestHeader.contains(MSIE_9)) {
            // IE9
            val = "attachment;filename=" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        } else if (requestHeader.contains(CHROME)) {
            // 谷歌
            val = "attachment;filename*=UTF-8''" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        } else if (requestHeader.contains(SAFARI)) {
            // 苹果
            val = "attachment;filename=" + new String(name.getBytes(), "ISO8859-1");
        } else {
            // 火狐或者其他的浏览器
            val = "attachment;filename*=UTF-8''" + URLEncoder.encode(name, StandardCharsets.UTF_8);
        }
        return val;
    }

    public static void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String name) throws UnsupportedEncodingException {
        response.reset();
        // 改成文件下载
        response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("content-disposition", getContentDispositionVal(request, name));
        String origin = request.getHeader("Origin");
        if (origin == null || "".equals(origin) || "null".equals(origin)) {
            origin = "*";
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
    }


    /**
     * http下载文件
     *
     * @param request  {@link HttpServletRequest}请求
     * @param response {@link HttpServletResponse}响应
     * @param path     要下载的文件路径
     * @param name     文件名称
     * @throws IOException e
     * @date 2018/7/14
     * @since jdk1.8
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response, String path,
                                String name) throws IOException {
        File file = new File(path);
        downFile(request, response, file, name);
    }

    /**
     * http下载文件
     *
     * @param request  {@link HttpServletRequest}请求
     * @param response {@link HttpServletResponse}响应
     * @param file     要下载的文件
     * @param name     文件名称
     * @throws IOException e
     * @date 2018/7/14
     * @since jdk11
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response, File file,
                                String name) throws IOException {

        // 文件的长度
        Long fileLength = file.length();
        if (fileLength != 0) {
            response.reset();
            setResponseHeader(request, response, name);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            try (
                    FileInputStream fis = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    // 输出流
                    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())
            ) {
                bis.transferTo(bos);
            }
        }
    }

    /**
     * 限流下载文件，需要传入maxRate kb/s  最大下载速率
     *
     * @param request  {@link HttpServletRequest} 请求
     * @param response {@link HttpServletResponse} 响应
     * @param path     文件位置
     * @param name     文件名称
     * @param maxRate  最大下载速率 kb/s
     * @throws IOException e
     * @date 2018/7/16
     * @since jdk11
     */
    public static void downFileWithLimiter(HttpServletRequest request, HttpServletResponse response, String path,
                                           String name, int maxRate) throws IOException {
        // 文件的长度
        Long fileLength = new File(path).length();
        if (fileLength != 0) {
            response.reset();
            setResponseHeader(request, response, name);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            BandWidthLimiter bandwidthLimiter = new BandWidthLimiter(maxRate);
            try (
                    FileInputStream fis = new FileInputStream(path);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    // 输出流
                    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                    OutputStream os = new LimiterOutputStream(bos, bandwidthLimiter)
            ) {
                bis.transferTo(os);
            }
        }
    }


    /**
     * <p>设置最大的下载带宽</p><br/>
     * <p>最好不要频繁设置这个带宽，比如服务启动的时候设置一次<p/>
     *
     * @param maxRate 最大下载带宽 单位kb
     * @date 2018/7/14
     * @since jdk1.8
     */
    public static void setMaxRate(int maxRate) {
        BandWidthLimiter.maxBandWith = maxRate;
    }


    /**
     * <p>按照最大下载带宽自动平均分布下载速率<p/> <br/>
     * <p>设置一个服务最大下载带宽,按照当前正在下载的人的数量平均分配下载速率<p/>
     *
     * @param request  {@link HttpServletRequest} 请求
     * @param response {@link HttpServletResponse} 响应
     * @param path     文件位置
     * @param name     文件名称
     * @throws IOException e
     * @date 2018/7/13
     * @see #setMaxRate(int)
     * @since jdk11
     */
    public static void downFileWithLimiter(HttpServletRequest request, HttpServletResponse response, String path,
                                           String name) throws IOException {

        int onlineNumber = onlineDownloadNumber.incrementAndGet();
        int maxRate = BandWidthLimiter.maxBandWith / onlineNumber;
        // 文件的长度
        Long fileLength = new File(path).length();
        if (fileLength != 0) {
            response.reset();
            setResponseHeader(request, response, name);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            BandWidthLimiter bandwidthLimiter = new BandWidthLimiter(maxRate);
            try {
                try (
                        FileInputStream fis = new FileInputStream(path);
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        // 输出流
                        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                        OutputStream os = new LimiterOutputStream(bos, bandwidthLimiter)
                ) {
                    bis.transferTo(os);
                }
            } finally {
                onlineDownloadNumber.decrementAndGet();
            }
        }
    }

    /**
     * 通过输入流下载文件
     *
     * @param request     {@link HttpServletRequest} 请求
     * @param response    {@link HttpServletResponse} 响应
     * @param inputStream {@link InputStream} 输入流
     * @param name        文件名称
     * @throws IOException e
     * @date 2018/7/13
     * @since jdk11
     */
    public static void downByStream(HttpServletRequest request, HttpServletResponse response, InputStream inputStream,
                                    String name) throws IOException {
//        name = transFileName(name, request);
        setResponseHeader(request, response, name);
        ServletOutputStream outputStream = response.getOutputStream();
        try (
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                // 输出流
                BufferedOutputStream bos = new BufferedOutputStream(outputStream)
        ) {
            bis.transferTo(outputStream);
        }
    }
}
