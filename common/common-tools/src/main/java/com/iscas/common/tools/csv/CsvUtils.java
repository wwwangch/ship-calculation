package com.iscas.common.tools.csv;

import cn.hutool.core.text.csv.*;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Csv读写工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/24 10:04
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
public class CsvUtils {
    private CsvUtils() {
    }

    /**
     * Csv结果类bean
     */
    public static class CsvResult<T> {
        /**
         * 表头键值对 key : en ; value :ch
         */
        private LinkedHashMap<String, String> header;
        /**
         * Excel数据
         */
        private List<T> content;

        public LinkedHashMap<String, String> getHeader() {
            return header;
        }

        public void setHeader(LinkedHashMap<String, String> header) {
            this.header = header;
        }

        public List<T> getContent() {
            return content;
        }

        public void setContent(List<T> content) {
            this.content = content;
        }
    }


    /**
     * 写入csv
     *
     * @param filePath       文件路径
     * @param fieldSeparator 列分隔符
     * @param csvResult      待写入的数据
     * @param withHeader     是否写入表头(第一行)
     * @param charset        编码格式
     * @throws IOException IO异常
     * @date 2021/3/24
     * @since jdk1.8
     */
    public static void writeCsv(String filePath, char fieldSeparator, CsvResult csvResult, boolean withHeader, String charset) throws IOException {
        File file = new File(filePath);
        writeCsv(file, fieldSeparator, csvResult, withHeader, charset);
    }


    /**
     * 写入csv
     *
     * @param file           文件
     * @param fieldSeparator 列分隔符
     * @param csvResult      待写入的数据
     * @param withHeader     是否写入表头(第一行)
     * @param charset        编码格式
     * @throws IOException io异常
     * @date 2021/3/24
     * @since jdk1.8
     */
    public static void writeCsv(File file, char fieldSeparator, CsvResult csvResult, boolean withHeader, String charset) throws IOException {
        @Cleanup OutputStream os = new FileOutputStream(file);
        @Cleanup OutputStreamWriter osw = new OutputStreamWriter(os, charset);
        writeCsv(osw, fieldSeparator, csvResult, withHeader);
    }

    /**
     * 写入csv
     *
     * @param writer         writer对象
     * @param fieldSeparator 列分隔符
     * @param csvResult      待写入的数据
     * @param withHeader     是否写入表头(第一行)
     * @date 2021/3/24
     * @since jdk1.8
     */
    @SuppressWarnings("unchecked")
    public static void writeCsv(Writer writer, char fieldSeparator, CsvResult csvResult, boolean withHeader) {
        CsvWriteConfig csvWriteConfig = new CsvWriteConfig();
        csvWriteConfig.setFieldSeparator(fieldSeparator);
        @Cleanup CsvWriter csvWriter = new CsvWriter(writer, csvWriteConfig);
        LinkedHashMap<String, String> header = csvResult.getHeader();
        if (withHeader) {
            String[] headerLine = header.values().toArray(String[]::new);
            csvWriter.write(headerLine);
        }
        List content = csvResult.getContent();
        Map<ClassField, MethodHandle> methodHandleMap = new HashMap<>(16);

        String[][] contentArray = (String[][]) content.stream().map(t ->
                header.keySet().stream()
                        .map(s -> {
                            Object o;
                            if (t instanceof Map tMap) {
                                o = tMap.get(s);
                            } else {
                                try {
                                    MethodHandle methodHandle = methodHandleMap.compute(new ClassField(t.getClass(), s), (k, v) -> {
                                        try {
                                            return v == null ?
                                                    ReflectUtils.getGetterMethodHandle(t.getClass(), s) : v;
                                        } catch (Throwable e) {
                                            throw new RuntimeException("获取java对象数据的值出错", e);
                                        }
                                    });
                                    o = methodHandle.invoke(t);
                                } catch (Throwable e) {
                                    throw new RuntimeException("获取java对象数据的值出错", e);
                                }
                            }
                            return o == null ? "" : o.toString();
                        }).toArray(String[]::new)
        ).toArray(String[][]::new);
        csvWriter.write(contentArray);
    }

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class ClassField {
        private Class tClass;
        private String field;
    }

    /**
     * 读取CSV文件
     *
     * @param reader         reader
     * @param fieldSeparator 分隔符
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @date 2021/3/24
     * @since jdk1.8
     */
    public static List<Map<String, Object>> readCsvWithHeader(Reader reader, char fieldSeparator) {
        CsvReadConfig csvReadConfig = new CsvReadConfig();
        csvReadConfig.setFieldSeparator(fieldSeparator);
        CsvReader csvReader = new CsvReader(csvReadConfig);
        CsvData csvData = csvReader.read(reader);
        List<String> header = csvData.getRow(0);
        Optional<List<Map<String, Object>>> optionalMaps = Optional.of(csvData.getRows())
                .map(row -> row.stream()
                        .skip(1)
                        .map(csvRow -> {
                            Map<String, Object> dataMap = new HashMap<>(16);
                            int[] i = new int[1];
                            header.forEach(h -> dataMap.put(h, csvRow.get(i[0]++)));
                            return dataMap;
                        }).toList()
                );
        return optionalMaps.orElse(null);
    }

    /**
     * 读取CSV文件，无表头
     *
     * @param reader         reader
     * @param fieldSeparator 分隔符
     * @return java.util.List<java.util.List < java.lang.String>>
     * @date 2021/3/24
     * @since jdk1.8
     */
    public static List<List<String>> readCsv(Reader reader, char fieldSeparator) {
        CsvReadConfig csvReadConfig = new CsvReadConfig();
        csvReadConfig.setFieldSeparator(fieldSeparator);
        CsvReader csvReader = new CsvReader(csvReadConfig);
        CsvData csvData = csvReader.read(reader);
        Optional<List<List<String>>> optional = Optional.of(csvData.getRows())
                .map(vd -> vd.stream()
                        .map(CsvRow::getRawList)
                        .toList()
                );
        return optional.orElse(null);
    }

    /**
     * 读取CSV文件，无表头
     *
     * @param file           文件
     * @param fieldSeparator 分隔符
     * @param charset        编码格式
     * @return java.util.List<java.util.List < java.lang.String>>
     * @throws IOException io异常
     * @date 2021/3/24
     * @since jdk1.8
     */
    public static List<List<String>> readCsv(File file, char fieldSeparator, String charset) throws IOException {
        @Cleanup InputStream is = new FileInputStream(file);
        @Cleanup InputStreamReader isr = new InputStreamReader(is, charset);
        return readCsv(isr, fieldSeparator);
    }

    /**
     * 读取CSV文件，无表头
     *
     * @param path           文件路径
     * @param fieldSeparator 分隔符
     * @param charset        编码格式
     * @return java.util.List<java.util.List < java.lang.String>>
     * @throws IOException IO异常
     * @date 2021/3/24
     * @since jdk1.8
     */
    public static List<List<String>> readCsv(String path, char fieldSeparator, String charset) throws IOException {
        File file = new File(path);
        return readCsv(file, fieldSeparator, charset);
    }

}
