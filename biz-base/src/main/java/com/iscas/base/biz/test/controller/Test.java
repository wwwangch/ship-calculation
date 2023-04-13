package com.iscas.base.biz.test.controller;


import com.iscas.common.web.tools.file.FileDownloadUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/21 10:04
 * @since jdk1.8
 */
@RestController
public class Test {
    @PostMapping("/a/{name:.+}")
    public ResponseEntity<InputStreamResource> testa(@PathVariable String name){
        String filePath = "E:/aaa.xlsx";
//        File file = new File(filePath);
        FileSystemResource file = new FileSystemResource(filePath);
        try (InputStream is = new FileInputStream(filePath)) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"","aaa.xlsx" ));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new InputStreamResource(is));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/b/{name:.+}")
    public void testb(HttpServletRequest request, HttpServletResponse response, @PathVariable String name) throws Exception {
        String filePath = "E:/aaa.xlsx";
        FileDownloadUtils.downByStream(request, response,new FileInputStream(filePath), "a.xlsx");
    }

}
