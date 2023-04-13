//package com.iscas.biz.mp.config.db;
//
//import cn.hutool.core.io.FileUtil;
//import com.alibaba.druid.support.http.StatViewServlet;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.io.ClassPathResource;
//
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * @author lirenshen
// * @version 1.0
// * @date 2021/2/22 18:04
// * @since jdk1.8
// */
//public class CustomStatViewServlet extends StatViewServlet {
//    private static final String COMMON_JS = "common.js";
//    private static final String STATIC_JS_COMMON_JS = "static/js/common.js";
//
//    @Override
//    protected void returnResourceFile(String fileName, String uri, HttpServletResponse response) throws ServletException, IOException {
//
//        //********定制一些信息*********
//        String content;
//        if (fileName.endsWith(COMMON_JS)) {
//            try {
//                ClassPathResource classPathResource = new ClassPathResource(STATIC_JS_COMMON_JS);
//                File file = classPathResource.getFile();
//                content = FileUtil.readUtf8String(file);
//                response.getWriter().write(content);
//                return;
//            } catch (Exception ignored) {
//
//            }
//        }
//        super.returnResourceFile(fileName, uri, response);
//    }
//}
