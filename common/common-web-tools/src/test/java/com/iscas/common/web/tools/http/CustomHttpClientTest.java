package com.iscas.common.web.tools.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/29 21:41
 * @since jdk1.8
 */
public class CustomHttpClientTest {
    private CustomHttpClient httpClient;

    @BeforeEach
    public void before() {
        httpClient = new CustomHttpClient(new CustomHttpClient.HttpClientProps());
    }

    @Test
    public void testGet1() throws IOException, InterruptedException {
        String res = httpClient.doGet("https://www.baidu.com");
        System.out.println(res);
    }

    @Test
    public void testGet2() throws IOException, InterruptedException {
        String res = httpClient.doGet("http://www.baidu.com", Map.of("Content-Type","text/html"));
        System.out.println(res);
    }

    @Test
    public void testGet3() throws IOException, InterruptedException {
        String res = httpClient.doGet("http://www.baidu.com", Map.of("Content-Type","text/html"), 10000);
        System.out.println(res);
    }

    @Test
    public void testGet4() throws IOException, InterruptedException {
        String res1 = httpClient.doGet("http://www.baidu.com", Map.of("Content-Type","text/html"), 10000, String.class);
        System.out.println(res1);

        byte[] bytes = httpClient.doGet("http://www.baidu.com", Map.of("Content-Type","text/html"), 10000, byte[].class);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        InputStream is = httpClient.doGet("http://localhost:7901/demo/111", Map.of("Content-Type","application/json"), 10000, InputStream.class);
        System.out.println(new String(is.readAllBytes(), StandardCharsets.UTF_8));
    }

    @Test
    public void testGet5() throws IOException, InterruptedException {
        HttpResponse<String> res1 = httpClient.doGetResponse("http://www.baidu.com", Map.of("Content-Type", "text/html"), 10000, String.class);
        System.out.println(res1.statusCode() + "::" + res1.body());

        HttpResponse<byte[]> res2 = httpClient.doGetResponse("http://www.baidu.com", Map.of("Content-Type","text/html"), 10000, byte[].class);
        System.out.println(res2.statusCode() + "::" + new String(res2.body(), StandardCharsets.UTF_8));

        HttpResponse<InputStream> res3 = httpClient.doGetResponse("http://localhost:7901/demo/111", Map.of("Content-Type","application/json"), 10000, InputStream.class);
        System.out.println(res3.statusCode() + "::" + new String(res3.body().readAllBytes(), StandardCharsets.UTF_8));
    }

    @Test
    public void testGetAsync1() throws IOException, InterruptedException {
        CompletableFuture<HttpResponse<byte[]>> future = httpClient.doGetByteResponseAsync("https://www.baidu.com", Map.of("Content-Type", "text/html"),
                10000);
        future.thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    System.out.println(new String(body, StandardCharsets.UTF_8));
                });
        Thread.currentThread().join(10000);
    }

    @Test
    public void testGetAsync2() throws IOException, InterruptedException {
        CompletableFuture<HttpResponse<String>> future = httpClient.doGetStringResponseAsync("https://www.baidu.com", Map.of("Content-Type", "text/html"),
                10000);
        future.thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        Thread.currentThread().join(10000);
    }

    @Test
    public void testGetAsync3() throws IOException, InterruptedException {
        CompletableFuture<HttpResponse<InputStream>> future = httpClient.doGetInputStreamResponseAsync("https://www.baidu.com", Map.of("Content-Type", "text/html"),
                10000);
        future.thenApply(HttpResponse::body)
                .thenAccept(is -> {
                    try {
                        System.out.println(new String(is.readAllBytes(), "utf-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        Thread.currentThread().join(10000);
    }

    @Test
    public void testPost1() throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.doPostResponse("http://localhost:7901/demo/tx2", Map.of(), "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}", 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testPost2() throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.doPostResponse("http://localhost:7901/demo/tx2", Map.of(), HttpRequest.BodyPublishers.ofString("{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}"), 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testPost3() throws IOException, InterruptedException {
        String res = httpClient.doPost("http://localhost:7901/demo/tx2", Map.of(), "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}", 10000, String.class);
        System.out.println(res);
    }

    @Test
    public void testPost4() throws IOException, InterruptedException {
        String res = httpClient.doPost("http://localhost:7901/demo/tx2", Map.of(), "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}", 10000, String.class);
        System.out.println(res);
    }

    @Test
    public void testPost5() throws IOException, InterruptedException {
        String res = httpClient.doPost("http://localhost:7901/demo/tx2", Map.of(), "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}", 10000);
        System.out.println(res);
    }

    @Test
    public void testPost6() throws IOException, InterruptedException {
        String res = httpClient.doPost("http://localhost:17902/demo/tx2", Map.of(), "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}");
        System.out.println(res);
    }

    @Test
    public void testPost7() throws IOException, InterruptedException {
        String res = httpClient.doPost("http://localhost:7901/demo/tx2", "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}");
        System.out.println(res);
    }

    @Test
    public void testPost8() throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.doPostResponse("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1),
                10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testPost9() throws IOException, InterruptedException {
        String result = httpClient.doPost("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1),
                10000, String.class);
        System.out.println(result);
    }

    @Test
    public void testPost10() throws IOException, InterruptedException {
        String result = httpClient.doPost("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1),
                10000);
        System.out.println(result);
    }

    @Test
    public void testPost11() throws IOException, InterruptedException {
        String result = httpClient.doPost("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1));
        System.out.println(result);
    }

    @Test
    public void testPost12() throws IOException, InterruptedException {
        String result = httpClient.doPost("http://localhost:17902/demo/tx9", Map.of("key", "中", "value", 1));
        System.out.println(result);
    }

    @Test
    public void testPost13() throws InterruptedException {
        CompletableFuture<HttpResponse<byte[]>> future = httpClient.doPostByteResponseAsync("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1), 10000);
        future.thenApply(HttpResponse::body)
                .thenAccept(bytes -> {
                    System.out.println(new String(bytes, StandardCharsets.UTF_8));
                });
        Thread.currentThread().join(10000);
    }

    @Test
    public void testPost14() throws InterruptedException {
        CompletableFuture<HttpResponse<InputStream>> future = httpClient.doPostInputStreamResponseAsync("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1), 10000);
        future.thenApply(HttpResponse::body)
                .thenAccept(is -> {
                    try {
                        System.out.println(new String(is.readAllBytes(), StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        Thread.currentThread().join(10000);
    }

    @Test
    public void testPost15() throws InterruptedException {
        CompletableFuture<HttpResponse<String>> future = httpClient.doPostStringResponseAsync("http://localhost:17902/demo/tx9", null, Map.of("key", "中", "value", 1), 10000);
        future.thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        Thread.currentThread().join(10000);
    }
    @Test
    public void testPost16() throws IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<String>> future = httpClient.doPostStringResponseAsync("http://localhost:17902/demo/tx2", Map.of(), "{\n" +
                "    \"pageNumber\":1,\n" +
                "    \"pageSize\":20\n" +
                "}", 10000);
        System.out.println(future.get().body());
    }


    @Test
    public void testUpload1() throws IOException, InterruptedException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo<File>(new File("d:/aaa.txt"), "aaa.txt"));
        HttpResponse<String> response = httpClient.doUploadResponse("http://localhost:17902/demo/tx7", null, formMap, 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testUpload2() throws IOException, InterruptedException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo<File>(new File("d:/aaa.doc"), "啦啦啦.doc"));
        formMap.put("key", "中国");
        HttpResponse<String> response = httpClient.doUploadResponse("http://localhost:17902/demo/tx10", null, formMap, 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testUpload3() throws IOException, InterruptedException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo[] {new CustomHttpClient.FileInfo<File>(new File("d:/aaa.txt"), "啦啦啦.txt"),
                new CustomHttpClient.FileInfo<File>(new File("d:/aaa.doc"), "aaa.doc")});
        formMap.put("key", "中国");
        HttpResponse<String> response = httpClient.doUploadResponse("http://localhost:17902/demo/tx11", null, formMap, 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testUpload4() throws IOException, InterruptedException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo[] {new CustomHttpClient.FileInfo<String>("d:/aaa.txt", "啦啦啦.txt"),
                new CustomHttpClient.FileInfo<File>(new File("d:/aaa.doc"), "aaa.doc")});
        formMap.put("key", "中国");
        HttpResponse<String> response = httpClient.doUploadResponse("http://localhost:17902/demo/tx11", null, formMap, 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testUpload5() throws IOException, InterruptedException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo[] {new CustomHttpClient.FileInfo<byte[]>(Files.readAllBytes(new File("d:/aaa.txt").toPath()), "啦啦啦.txt"),
                new CustomHttpClient.FileInfo<File>(new File("d:/aaa.doc"), "aaa.doc")});
        formMap.put("key", "中国");
        HttpResponse<String> response = httpClient.doUploadResponse("http://localhost:17902/demo/tx11", null, formMap, 10000, String.class);
        System.out.println(response.body());
    }

    @Test
    public void testUpload6() throws IOException, InterruptedException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo[] {new CustomHttpClient.FileInfo<InputStream>(new FileInputStream(new File("d:/aaa.txt")), "啦啦啦.txt"),
                new CustomHttpClient.FileInfo<File>(new File("d:/aaa.doc"), "aaa.doc")});
        formMap.put("key", "中国");
        HttpResponse<String> response = httpClient.doUploadResponse("http://localhost:17902/demo/tx11", null, formMap, 10000, String.class);
        System.out.println(response.body());
    }


    @Test
    public void testUpload7() throws IOException, InterruptedException, ExecutionException {
        HashMap<String, Object> formMap = new HashMap<>();
        formMap.put("file", new CustomHttpClient.FileInfo[] {new CustomHttpClient.FileInfo<InputStream>(new FileInputStream(new File("d:/aaa.txt")), "啦啦啦.txt"),
                new CustomHttpClient.FileInfo<File>(new File("d:/aaa.doc"), "aaa.doc")});
        formMap.put("key", "中国");
        CompletableFuture<HttpResponse<String>> future = httpClient.doUploadStringResponseAsync("http://localhost:17902/demo/tx11", null, formMap, 10000);
        System.out.println(future.get());
    }

    @Test
    public void testDownload1() throws IOException, InterruptedException {
        HttpRequest httpRequest = httpClient.buildGetRequest("http://localhost:17902/demo/tx12", null, 10000);
        Path path = httpClient.doDownload(httpRequest, "d:/bbb.txt");
        System.out.println(Files.readString(path));
    }

    @Test
    public void testDownload2() throws IOException, InterruptedException {
        HttpRequest httpRequest = httpClient.buildGetRequest("http://localhost:17902/demo/tx12", null, 10000);
        HttpResponse<Path> httpResponse = httpClient.doDownloadResponse(httpRequest, "d:/bbb.txt");
        System.out.println(httpResponse.statusCode() + "::::" + Files.readString(httpResponse.body()));
    }

    @Test
    public void testPut1() throws IOException, InterruptedException {
        String result = httpClient.doPut("http://localhost:17902/demo/tx13?key=中国", "d:/bbb.txt");
        System.out.println(result);
    }

    @Test
    public void testPut2() throws IOException, InterruptedException {
        String result = httpClient.doPut("http://localhost:17902/demo/tx14", Map.of("key", "微软", "value", 5));
        System.out.println(result);
    }

    @Test
    public void testPut3() throws IOException, InterruptedException {
        String result = httpClient.doPut("http://localhost:17902/demo/tx13?key=中国", null, "d:/bbb.txt");
        System.out.println(result);
    }

    @Test
    public void testPut4() throws IOException, InterruptedException {
        String result = httpClient.doPut("http://localhost:17902/demo/tx14", null, Map.of("key", "微软", "value", 5));
        System.out.println(result);
    }

    @Test
    public void testPut5() throws IOException, InterruptedException {
        String result = httpClient.doPut("http://localhost:17902/demo/tx13?key=中国", null, "d:/bbb.txt", 10000);
        System.out.println(result);
    }

    @Test
    public void testPut6() throws IOException, InterruptedException {
        String result = httpClient.doPut("http://localhost:17902/demo/tx14", null, Map.of("key", "微软", "value", 5), 1000);
        System.out.println(result);
    }

    @Test
    public void testPut7() throws IOException, InterruptedException {
        byte[] bytes = httpClient.doPut("http://localhost:17902/demo/tx13?key=中国", null, "d:/bbb.txt", 10000, byte[].class);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void testPut8() throws IOException, InterruptedException {
        InputStream inputStream = httpClient.doPut("http://localhost:17902/demo/tx14", null, Map.of("key", "微软", "value", 5), 1000, InputStream.class);
        System.out.println(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8));
    }

    @Test
    public void testPut9() throws IOException, InterruptedException {
        HttpResponse<byte[]> httpResponse = httpClient.doPutResponse("http://localhost:17902/demo/tx13?key=中国", null, "d:/bbb.txt", 10000, byte[].class);
        System.out.println(new String(httpResponse.body(), StandardCharsets.UTF_8));
    }

    @Test
    public void testPut10() throws IOException, InterruptedException {
        HttpResponse<InputStream> is = httpClient.doPutResponse("http://localhost:17902/demo/tx14", null, Map.of("key", "微软", "value", 5), 1000, InputStream.class);
        System.out.println(new String(is.body().readAllBytes(), StandardCharsets.UTF_8));
    }

    @Test
    public void testPut11() throws IOException, InterruptedException {
        HttpResponse<InputStream> is = httpClient.doPutResponse("http://localhost:17902/demo/tx13?key=11", null, HttpRequest.BodyPublishers.ofString("wegweg"), 1000, InputStream.class);
        System.out.println(new String(is.body().readAllBytes(), StandardCharsets.UTF_8));
    }

    @Test
    public void testPut12() throws IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<byte[]>> future = httpClient.doPutByteResponseAsync("http://localhost:17902/demo/tx14", null, Map.of("key", "微软", "value", 5), 1000);
        System.out.println(new String(future.get().body(), StandardCharsets.UTF_8));
    }

    @Test
    public void testPut13() throws IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<String>> future = httpClient.doPutStringResponseAsync("http://localhost:17902/demo/tx13?key=11", null, "12", 1000);
        System.out.println(future.get().body());
    }

    @Test
    public void testDelete1() throws IOException, InterruptedException {
        String res = httpClient.doDelete("http://localhost:17902/demo/tx15");
        System.out.println(res);
    }

    @Test
    public void testDelete2() throws IOException, InterruptedException {
        String res = httpClient.doDelete("http://localhost:17902/demo/tx15", Map.of("a", "b"));
        System.out.println(res);
    }

    @Test
    public void testDelete3() throws IOException, InterruptedException {
        String res = httpClient.doDelete("http://localhost:17902/demo/tx15", Map.of("a", "b"), 10000);
        System.out.println(res);
    }

    @Test
    public void testDelete4() throws IOException, InterruptedException {
        byte[] res = httpClient.doDelete("http://localhost:17902/demo/tx15", Map.of("a", "b"), 10000, byte[].class);
        System.out.println(res);
    }

    @Test
    public void testDelete5() throws IOException, InterruptedException {
        HttpResponse<byte[]> res = httpClient.doDeleteResponse("http://localhost:17902/demo/tx15", Map.of("a", "b"), 10000, byte[].class);
        System.out.println(res.body());
    }

    @Test
    public void testDelete6() throws IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<InputStream>> future = httpClient.doDeleteInputStreamResponseAsync("http://localhost:17902/demo/tx15", Map.of("a", "b"), 10000);
        InputStream is = future.get().body();
        System.out.println(Arrays.toString(is.readAllBytes()));
    }

    @Test
    public void testDelete7() throws IOException, InterruptedException, ExecutionException {
        CompletableFuture<HttpResponse<byte[]>> future = httpClient.doDeleteByteResponseAsync("http://localhost:17902/demo/tx15", Map.of("a", "b"), 10000);
        byte[] bytes = future.get().body();
        System.out.println(new String(bytes));
    }


}