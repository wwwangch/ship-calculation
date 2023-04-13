package com.iscas.common.nexus.tools.raw.util;

import com.iscas.common.nexus.tools.exception.NexusException;
import com.iscas.common.nexus.tools.raw.model.FileInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/19 14:32
 */
class RawUtilsTest {

    @Test
    void getFileInfo() throws IOException, InterruptedException, NexusException {
        List<FileInfo> fileInfos = RawUtils.getFileInfo("http://localhost:8081", "raw-public", "/");
        fileInfos.forEach(System.out::println);
    }

    @Test
    void downloadFile() throws IOException, InterruptedException {
        try (OutputStream os = Files.newOutputStream(Files.createTempFile("test", ".sql"))) {
//        try (OutputStream os = Files.newOutputStream(Path.of(URI.create("file:/d:/tmp/newframe.sql")))) {
            RawUtils.downloadFile("http://172.16.10.190:8081", "raw-public", "/root/newframe.sql", 2000, os);
        }
    }

    @Test
    void downloadDirToLocal() throws IOException, InterruptedException, NexusException {
        RawUtils.doDownloadDirToLocal("http://172.16.10.190:8081", "raw-public", "/root/logs", 2000, "d:/tmp/logs");
    }

    @Test
    void downloadDir() throws IOException, InterruptedException, NexusException {
        try (OutputStream os = Files.newOutputStream(Files.createTempFile("test", ".zip"))) {
            //        try (OutputStream os = Files.newOutputStream(Path.of(URI.create("file:/d:/tmp/test.zip")))) {
            RawUtils.downloadDir("http://172.16.10.190:8081", "raw-public", "/root", 2000, os);
        }
    }

    @Test
    void downloadMulti() throws IOException, InterruptedException, NexusException {
//        try (OutputStream os = Files.newOutputStream(Files.createTempFile("test", ".zip"))) {
        try (OutputStream os = Files.newOutputStream(Path.of(URI.create("file:/d:/tmp/test.zip")))) {
            FileInfo fileInfo1 = new FileInfo();
            fileInfo1.setType("file");
            fileInfo1.setPath("/root/newframe.sql");
            FileInfo fileInfo2 = new FileInfo();
            fileInfo2.setType("dir");
            fileInfo2.setPath("/root/logs");

            RawUtils.downloadMulti("http://172.16.10.190:8081", "raw-public", List.of(fileInfo1, fileInfo2), 2000, os);
        }
    }

    @Test
    void uploadFile() throws IOException, InterruptedException, NexusException {
        try (InputStream is = Files.newInputStream(Path.of(URI.create("file:/d:/测试目录.pdf")))) {
            RawUtils.uploadFile("http://localhost:8081", "raw-hosted", "admin", "admin123",
                    "/root", is, "test2.pdf");
        }
    }

    @Test
    void deleteFile() throws IOException, InterruptedException, NexusException {
        RawUtils.deleteFile("http://localhost:8081", "admin", "admin123", "cmF3LWhvc3RlZDoxYTY4NzRkMzFiYTg0NjgxMjRiOTA4NWQ1NmVhNmQzZA", 10000);
    }
}