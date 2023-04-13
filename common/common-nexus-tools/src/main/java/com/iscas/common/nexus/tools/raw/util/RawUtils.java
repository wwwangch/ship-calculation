package com.iscas.common.nexus.tools.raw.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.iscas.common.nexus.tools.CustomHttpClient;
import com.iscas.common.nexus.tools.OkHttpCustomClient;
import com.iscas.common.nexus.tools.OkHttpProps;
import com.iscas.common.nexus.tools.exception.NexusException;
import com.iscas.common.nexus.tools.raw.model.FileInfo;
import com.iscas.common.nexus.tools.util.*;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * raw仓库操作工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/19 9:14
 */
public class RawUtils {


    private static class CustomHttpClientHolder {
        private static CustomHttpClient client = CustomHttpClient.newClient();
    }

    private static class OkHttpClientHolder {
        private static OkHttpCustomClient okHttpCustomClient = new OkHttpCustomClient(new OkHttpProps());
    }

    public static CustomHttpClient getHttpClient() {
        return CustomHttpClientHolder.client;
    }

    private static OkHttpCustomClient getOkHttpClient() {
        return OkHttpClientHolder.okHttpCustomClient;
    }

    private RawUtils() {
    }

    /**
     * 获取某个路径下的文件清单
     *
     * @param nexusAddress 服务地，例如：{@link <a href="http://172.16.10.190:8081">...</a>}
     * @param repoName     仓库名字
     * @param path         地址
     * @return java.util.List<com.iscas.common.nexus.tools.raw.model.FileInfo>
     * @date 2023/1/19
     */
    public static List<FileInfo> getFileInfo(String nexusAddress, String repoName, String path) throws IOException, InterruptedException, NexusException {
        String url = createUrl(nexusAddress, repoName, path);
        String html = getHttpClient().doGet(url);
        if (StringUtils.isBlank(html)) {
            throw new NexusException(String.format("未能从请求:%s获取返回结果", url));
        }
        List<FileInfo> fileInfos = parseHtml(html, path);

        // 获取ID
        String getUrl = nexusAddress + "/service/rest/v1/search?repository=%s";
        getUrl = String.format(getUrl, repoName);
        if (path != null) {
            getUrl = getUrl + "&group=" + path;
        }
        List<Map<String, Object>> infos = new ArrayList<>();
        getInfo(getUrl, null, infos);
        Map<String, String> nameIdMap = infos.stream().collect(Collectors.toMap(info ->
                        ("/" + info.get("name")).replaceAll("//+", "/"),
                info -> (String) info.get("id")));
        for (FileInfo fileInfo : fileInfos) {
            String key = fileInfo.getPath().replaceAll("//+", "/");
            fileInfo.setId(nameIdMap.get(key));
        }
        return fileInfos;
    }


    /**
     * 下载文件
     *
     * @param path    文件路径
     * @param timeout 超时时间
     * @param os      输出流
     * @date 2023/1/19
     */
    public static void downloadFile(String nexusAddress, String repoName, String path, long timeout, OutputStream os) throws IOException, InterruptedException {
        try (InputStream is = getHttpClient().doGet(createDownloadFileUrl(nexusAddress, repoName, path), new HashMap<>(0), timeout, InputStream.class)) {
            is.transferTo(os);
        }
    }

    /**
     * 下载一个文件夹到本地目录
     *
     * @param nexusAddress nexus地址
     * @param repoName     仓库名称
     * @param path         文件路径
     * @param timeout      超时时间
     * @param targetDir    目标目录
     * @date 2023/1/19
     */
    public static void doDownloadDirToLocal(String nexusAddress, String repoName, String path, long timeout, String targetDir) throws IOException, InterruptedException, NexusException {
        doDownloadDirToLocal(nexusAddress, repoName, null, path, timeout, targetDir);
    }

    /**
     * 下载并发压缩一个文件夹
     *
     * @param nexusAddress nexus地址
     * @param repoName     仓库名称
     * @param path         文件路径
     * @param timeout      超时时间
     * @param os           输出流
     * @date 2023/1/19
     */
    public static void downloadDir(String nexusAddress, String repoName, String path, long timeout, OutputStream os) throws IOException, InterruptedException, NexusException {
        ZipOutputStream zos;
        if (os instanceof ZipOutputStream) {
            zos = (ZipOutputStream) os;
        } else {
            zos = new ZipOutputStream(os);
        }
        try {
            compress(nexusAddress, repoName, null, false, path, timeout, zos);
        } finally {
            try {
                zos.flush();
                zos.closeEntry();
                zos.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * 下载并发压缩多个文件或目录
     *
     * @param nexusAddress nexus地址
     * @param repoName     仓库名称
     * @param fileInfos    文件目录信息的集合
     * @param timeout      超时时间
     * @param os           输出流
     * @date 2023/1/19
     */
    public static void downloadMulti(String nexusAddress, String repoName, List<FileInfo> fileInfos, long timeout, OutputStream os) throws IOException, InterruptedException, NexusException {
        ZipOutputStream zos;
        if (os instanceof ZipOutputStream) {
            zos = (ZipOutputStream) os;
        } else {
            zos = new ZipOutputStream(os);
        }
        try {
            for (FileInfo fileInfo : fileInfos) {
                String type = fileInfo.getType();
                String path = fileInfo.getPath();
                if (Objects.equals("file", type)) {
                    compress(nexusAddress, repoName, null, true, path, timeout, zos);
                } else if (Objects.equals("dir", type)) {
                    compress(nexusAddress, repoName, null, false, path, timeout, zos);
                }
            }

        } finally {
            try {
                zos.flush();
                zos.closeEntry();
                zos.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * 上传文件
     *
     * @param nexusAddress 地址
     * @param repoName     库名
     * @param username     用户名
     * @param pwd          密码
     * @param path         文件路径
     * @param is           输入流
     * @param fileName     文件名
     * @date 2023/1/19
     */
    public static void uploadFile(String nexusAddress, String repoName, String username, String pwd, String path, InputStream is, String fileName) throws IOException, NexusException {
        String uploadUrl = nexusAddress + "/service/rest/v1/components?repository=" + repoName;
        OkHttpCustomClient.UploadInfo<InputStream> uploadInfo = new OkHttpCustomClient.UploadInfo<>();
        uploadInfo.setData(is);
        uploadInfo.setFileName(fileName);
        uploadInfo.setFormKey("raw.asset1");
        try (Response response = getOkHttpClient().doUploadWithRes(uploadUrl, createHttpBasicHeader(username, pwd),
                List.of(uploadInfo), new HashMap<>() {{
                    put("raw.asset1.filename", fileName);
                    put("raw.directory", path);
                }})) {
            int code = response.code();
            if (code < 200 || code >= 300) {
                throw new NexusException(response.body() == null ? "null" : response.body().string());
            }
        }
    }

    /**
     * 上传文件
     *
     * @param nexusAddress 地址
     * @param username     用户名
     * @param pwd          密码
     * @param fileId       文件ID
     * @param timeout      超时时间
     * @date 2023/1/19
     */
    public static void deleteFile(String nexusAddress, String username, String pwd, String fileId, long timeout) throws IOException, InterruptedException, NexusException {
        String url = nexusAddress + "/service/rest/v1/components/" + fileId;
        String authorizationVal = HttpBasicUtils.createAuthorizationVal(username, pwd);
        HttpResponse<String> response = getHttpClient().doDeleteResponse(url, new HashMap<>() {{
            put("Authorization", authorizationVal);
        }}, timeout, String.class);
        int code = response.statusCode();
        if (code < 200 || code >= 300) {
            throw new NexusException(response.body());
        }
    }


//    /**
//     * 上传文件
//     *
//     * @param path     文件路径
//     * @param timeout  超时时间
//     * @param is       输入流
//     * @param fileName 文件名
//     * @date 2023/1/19
//     */
//    public static void uploadFile2(String nexusAddress, String repoName, String username, String pwd, String path, long timeout, InputStream is, String fileName) throws IOException, InterruptedException, NexusException {
//        String uploadUrl = nexusAddress + "/service/rest/v1/components?repository=" + repoName;
//        Map<String, Object> formData = new HashMap<>();
//        formData.put("raw.directory", path);
//        CustomHttpClient.FileInfo<InputStream> fileInfo = new CustomHttpClient.FileInfo<>();
//        fileInfo.setData(is);
//        fileInfo.setFileName(fileName);
//        formData.put("raw.asset1", fileInfo);
//        formData.put("raw.asset1.filename", fileName);
//        HttpResponse<String> response = getHttpClient().doUploadResponse(uploadUrl, createHttpBasicHeader(username, pwd), formData, timeout, String.class);
//        int code = response.statusCode();
//        if (code < 200 || code >= 300) {
//            throw new NexusException(response.body());
//        }
//
//    }


    private static Map<String, String> createHttpBasicHeader(String username, String pwd) {
        String authorizationVal = HttpBasicUtils.createAuthorizationVal(username, pwd);
        return new HashMap<>() {{
            put("Authorization", authorizationVal);
        }};
    }

    /**
     * 使用Jsoup解析返回的HTML
     */
    private static List<FileInfo> parseHtml(String html, String path) throws NexusException {
        // 使用jsoup解析
        List<FileInfo> fileInfos = new ArrayList<>();
        Document document = Jsoup.parse(html);
        //noinspection ConstantValue
        if (document == null) {
            throw new NexusException("解析html出错");
        }
        Elements tables = document.getElementsByTag("table");
        if (CollectionUtils.isEmpty(tables)) {
            throw new NexusException("解析html时未获取到table标签");
        }
        Element table = tables.get(0);
        Elements trs = table.getElementsByTag("tr");
        for (int i = 1; i < trs.size(); i++) {
            Element tr = trs.get(i);
            Elements tds = tr.getElementsByTag("td");
            // 如果第一行是parentDirectory，跳过
            if (!isParentDirectoryTd(tds)) {
                FileInfo fileInfo = new FileInfo();
                // 文件名
                Element nameElement = tds.get(0);
                String fileName = nameElement.text();
                fileInfo.setName(fileName);
                Elements aElements = nameElement.getElementsByTag("a");
                if (CollectionUtils.isNotEmpty(aElements)) {
                    String href = aElements.get(0).attr("href");
                    //noinspection ConstantValue
                    if (Objects.nonNull(href) && (href.startsWith("http:") || href.startsWith("https:"))) {
                        // 文件类型
                        fileInfo.setType("file");

                        // 文件最后修改时间
                        // todo 日期修改时间 格式可能会出现  “星期五 一月 20 13:12:20 CST 2023”
//                        getTextAndSet(tds.get(1), s -> fileInfo.setLastModified(LocalDateTimeUtils.dateToLocalDateTime(new Date(s))));
                        // 文件大小
                        getTextAndSet(tds.get(2), s -> fileInfo.setSize(Long.parseLong(s)));
                    } else {
                        // 文件类型
                        fileInfo.setType("dir");
                    }
                    // 文件路径
                    fileInfo.setPath(path + "/" + fileName);
                    // 描述
                    getTextAndSet(tds.get(3), s -> fileInfo.setSize(Long.parseLong(s)));
                    fileInfos.add(fileInfo);
                }
            }
        }
        return fileInfos;
    }

    private static void getTextAndSet(Element element, Consumer<String> consumer) {
        if (Objects.nonNull(element)) {
            String text = element.text();
            if (StringUtils.isNotBlank(text)) {
                consumer.accept(text);
            }
        }
    }

    private static boolean isParentDirectoryTd(Elements tds) {
        if (CollectionUtils.isNotEmpty(tds)) {
            Element td = tds.get(0);
            Elements as = td.getElementsByTag("a");
            if (CollectionUtils.isNotEmpty(as) && as.get(0).hasAttr("href")) {
                String href = as.get(0).attr("href");
                return StringUtils.isNotBlank(href) && href.startsWith("../");
            }
        }
        return false;
    }

    private static String createUrl(String nexusAddress, String repoName, String path) {
        return nexusAddress + ("/service/rest/repository/browse/" + repoName + path).replaceAll("//+", "/");
    }

    private static String createDownloadFileUrl(String nexusAddress, String repoName, String path) {
        return nexusAddress + ("/repository/" + repoName + path).replaceAll("//+", "/");
    }

    private static void doDownloadDirToLocal(String nexusAddress, String repoName, File file, String path, long timeout, String targetDir) throws IOException, InterruptedException, NexusException {
        if (Objects.isNull(file)) {
            file = new File(targetDir);
            if (!file.exists()) {
                //noinspection ResultOfMethodCallIgnored
                file.mkdirs();
            }
        }
        List<FileInfo> fileInfos = getFileInfo(nexusAddress, repoName, path);
        if (CollectionUtils.isNotEmpty(fileInfos)) {
            for (FileInfo fileInfo : fileInfos) {
                String type = fileInfo.getType();
                String subPath = fileInfo.getPath();
                String name = fileInfo.getName();
                File subFile = new File(file, name);
                if (Objects.equals("file", type)) {
                    // 下载文件
                    if (!subFile.exists()) {
                        File parentFile = subFile.getParentFile();
                        if (!parentFile.exists()) {
                            //noinspection ResultOfMethodCallIgnored
                            parentFile.mkdirs();
                        }
                    }
                    try (OutputStream os = new FileOutputStream(subFile)) {
                        downloadFile(nexusAddress, repoName, subPath, timeout, os);
                    }
                } else if (Objects.equals("dir", type)) {
                    // 创建文件夹
                    if (!subFile.exists()) {
                        //noinspection ResultOfMethodCallIgnored
                        subFile.mkdirs();
                    }
                    // 递归下载下一层的文件或文件夹
                    doDownloadDirToLocal(nexusAddress, repoName, subFile, subPath, timeout, targetDir);
                }
            }
        }
    }

    private static void compress(String nexusAddress, String repoName, String dir, boolean isFile, String path, long timeout, ZipOutputStream zos) throws IOException, InterruptedException, NexusException {
        if (Objects.isNull(dir) && !Objects.equals("/", path)) {
            dir = Objects.requireNonNull(path).substring(path.lastIndexOf("/") + 1);
            if (isFile) {
                zos.putNextEntry(new ZipEntry(dir));
                // 下载并写入文件
                downloadFile(nexusAddress, repoName, path, timeout, zos);
            } else {
                // 生成一个文件夹
                zos.putNextEntry(new ZipEntry(dir + "/"));
            }
        }
        if (!isFile) {
            List<FileInfo> fileInfos = getFileInfo(nexusAddress, repoName, path);
            if (CollectionUtils.isNotEmpty(fileInfos)) {
                for (FileInfo fileInfo : fileInfos) {
                    String type = fileInfo.getType();
                    String subPath = fileInfo.getPath();
                    String name = fileInfo.getName();
                    String entryName = Objects.isNull(dir) ? name : dir + "/" + name;
                    if (Objects.equals("file", type)) {
                        zos.putNextEntry(new ZipEntry(entryName));
                        // 下载并写入文件
                        downloadFile(nexusAddress, repoName, subPath, timeout, zos);
                    } else if (Objects.equals("dir", type)) {
                        // 写入文件夹
                        zos.putNextEntry(new ZipEntry(entryName + "/"));
                        // 递归下载下一层的文件或文件夹
                        compress(nexusAddress, repoName, entryName, false, subPath, timeout, zos);
                    }
                }
            }
        }
    }

    private static void getInfo(String getUrl, String continuationToken, List<Map<String, Object>> infos) throws IOException, InterruptedException {
        if (StringUtils.isNotBlank(continuationToken)) {
            getUrl += "&continuationToken=" + continuationToken;
        }
        String s = getHttpClient().doGet(getUrl);
        Map<String, Object> map = JsonUtils.fromJson(s, new TypeReference<Map<String, Object>>() {
        });
        if (map != null) {
            @SuppressWarnings("unchecked") List<Map<String, Object>> items = (List<Map<String, Object>>) map.get("items");
            if (CollectionUtils.isNotEmpty(items)) {
                infos.addAll(items);
            }
            continuationToken = (String) map.get("continuationToken");
            if (StringUtils.isNotBlank(continuationToken)) {
                getInfo(getUrl, continuationToken, infos);
            }
        }
    }


}
