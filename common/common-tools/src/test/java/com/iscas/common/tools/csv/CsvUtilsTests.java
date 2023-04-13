package com.iscas.common.tools.csv;

import cn.hutool.core.map.MapUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/24 11:01
 * @since jdk1.8
 */
public class CsvUtilsTests {

    @Test
    public void testWrite() throws IOException {
        File file = writeCsv();
        file.delete();
    }

    @Test
    public void testRead1() throws IOException {
        File file = writeCsv();
        @Cleanup InputStream is =  new FileInputStream(file);
        @Cleanup InputStreamReader fr = new InputStreamReader(is, "utf-8");
        List<Map<String, Object>> maps = CsvUtils.readCsvWithHeader(fr, ' ');
        maps.stream().forEach(System.out::println);
        file.delete();
    }

    @Test
    public void testRead2() throws IOException {

        File file = writeCsv();
        @Cleanup InputStream is =  new FileInputStream(file);
        @Cleanup InputStreamReader fr = new InputStreamReader(is, "utf-8");
        List<List<String>> lists = CsvUtils.readCsv(fr, ' ');
        lists.stream().forEach(System.out::println);
        file.delete();
    }

    private File writeCsv() throws IOException {
        CsvUtils.CsvResult<Map<Object, Object>> csvResult = new CsvUtils.CsvResult<>();
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("a", "欸");
        header.put("b", "必");
        header.put("c", "西");

        List<Map<Object, Object>> maps = Arrays.asList(MapUtil.builder().put("a", "hahaha")
                        .put("b", "lalala")
                        .put("c", "哈哈哈").build(),
                MapUtil.builder().put("a", "一年又一年")
                        .put("b", "啦啦啦啦")
                        .put("c", "测试测试").build());
        csvResult.setHeader(header);
        csvResult.setContent(maps);
        File file = File.createTempFile("test", ".csv");
        file.deleteOnExit();
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(file);
        @Cleanup OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
        CsvUtils.writeCsv(osw, ' ', csvResult, true);
        return file;
    }

    @Test
    public void testWriteUseEntity() throws IOException {
        File file = writeCsvUseEntity();
        file.delete();
    }

    private File writeCsvUseEntity() throws IOException {
        CsvUtils.CsvResult<TestModel> csvResult = new CsvUtils.CsvResult<>();
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("a", "欸");
        header.put("b", "必");
        header.put("c", "西");

        TestModel testModel1 = new TestModel();
        testModel1.setA("hahaha");
        testModel1.setB("lalala");
        testModel1.setC("哈哈哈");

        TestModel testModel2 = new TestModel();
        testModel2.setA("一年又一年");
        testModel2.setB("啦啦啦啦");
        testModel2.setC("测试测试");

        csvResult.setHeader(header);
        csvResult.setContent(List.of(testModel1, testModel2));
        File file = File.createTempFile("test", ".csv");
        file.deleteOnExit();
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(file);
        @Cleanup OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream);
        CsvUtils.writeCsv(osw, ' ', csvResult, true);
        return file;
    }

    private static class TestModel {
        private String a;
        private String b;
        private String c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }
}
