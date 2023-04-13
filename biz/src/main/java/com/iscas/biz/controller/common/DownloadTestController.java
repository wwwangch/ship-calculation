package com.iscas.biz.controller.common;

import com.iscas.base.biz.util.SpringFileDownloadUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/6 17:12
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/download")
public class DownloadTestController {
    @GetMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        return SpringFileDownloadUtils.download(fileName, "D:\\ideaProjects\\newframe\\biz\\src\\main\\resources\\static\\pk_1.tif");
    }

    @GetMapping("/test2")
    public void download() throws IOException {
        FileDownloadUtils.downByStream(SpringUtils.getRequest(), SpringUtils.getResponse(), new FileInputStream("D:\\ideaProjects\\newframe\\biz\\src\\main\\resources\\static\\pk_1.tif"),
                "PK_1.tif");
    }
}
