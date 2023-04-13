package com.iscas.common.tools.office.excel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Excel操作工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/13 19:30
 * @since jdk1.8
 */
public class ExcelUtilsTests {
    private static ExcelUtils.ExcelResult<Model> excelResult = new ExcelUtils.ExcelResult<>();
    private static LinkedHashMap<String, String> header = new LinkedHashMap<>();

    static {
        header.put("a", "列1");
        header.put("b", "列2");
        header.put("c", "列3");
        excelResult.setHeader(header);
        excelResult.setSheetName("sheet1");
        Model model1 = new Model("AW", "WE", "为我国");
        Model model2 = new Model("AWweg", "WsdE", "为g我国");
        Model model3 = new Model("AWsds", "sdWE", "为我weg国");
        List<Model> models = Arrays.asList(model1, model2, model3);
        excelResult.setContent(models);
    }

    /**
     * 创建一个测试Bean
     */
    static class Model {
        private String a;
        private String b;
        private String c;

        public Model(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Model() {
        }

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

    /**
     * 测试将数据写入xls格式Excel文件
     */
    @Test
    public void test1() throws Exception {
        File file = File.createTempFile("test", ".xls");
        file.deleteOnExit();
        String path = file.getAbsolutePath();
        ExcelUtils.exportXLSExcel(Arrays.asList(excelResult), 69, path);
        file.delete();
    }

    /**
     * 测试将数据写入xls格式Excel输出流
     */
    @Test
    public void test3() throws Exception {
        File file = File.createTempFile("test", ".xls");
        file.deleteOnExit();
        OutputStream os = new FileOutputStream(file);
        ExcelUtils.exportXLSExcel(Arrays.asList(excelResult), 69, os);
        os.close();
        file.delete();
    }

    /**
     * 测试将数据写入xlsx格式Excel文件
     */
    @Test
    public void test2() throws Exception {
        File file = File.createTempFile("test", ".xlsx");
        file.deleteOnExit();
        String path = file.getAbsolutePath();
        ExcelUtils.exportXLSXExcel(Arrays.asList(excelResult), 79, path);
        file.delete();
    }

    /**
     * 测试将数据写入xlsx格式Excel输出流
     */
    @Test
    public void test4() throws Exception {
        File file = File.createTempFile("test", ".xlsx");
        file.deleteOnExit();
        OutputStream os = new FileOutputStream(file);
        ExcelUtils.exportXLSXExcel(Arrays.asList(excelResult), 79, os);
        os.close();
        file.delete();
    }

    private File createExcel() throws Exception {
        File file = File.createTempFile("test", ".xls");
        file.deleteOnExit();
        String path = file.getAbsolutePath();
        ExcelUtils.exportXLSExcel(Arrays.asList(excelResult), 69, path);
        file.delete();
        return file;
    }

    private File createXlsxExcel() throws IOException, ExcelUtils.ExcelHandlerException {
        File file = File.createTempFile("test", ".xlsx");
        file.deleteOnExit();
        OutputStream os = new FileOutputStream(file);
        ExcelUtils.exportXLSXExcel(Arrays.asList(excelResult), 79, os);
        os.close();
        return file;
    }

    /**
     * 读取excel文件的输入流到一个Map中，key为sheet的名字
     */
    @Test
    public void test5() throws Exception {
        Map<String, List> result = new HashMap<>();
        File file = createExcel();
        String path = file.getAbsolutePath();
        ExcelUtils.readExcelToListMap(path, result);
        System.out.println(result);
        Assertions.assertNotNull(result);
        file.delete();
    }

    @Test
    public void test6() throws Exception {
        File file = createXlsxExcel();
        LinkedHashMap<String, List<String>> result = ExcelUtils.readExcelHeader(file);
        System.out.println(result);
        file.delete();
    }

    @Test
    public void test7() throws Exception {
        List<Model> models = new ArrayList<>();
        for (int i = 0; i < 70000; i++) {
            Model model = new Model("AWsds", "sdWE", "为我weg国");
            models.add(model);
        }
        excelResult.setContent(models);
        File file = File.createTempFile("test", ".xlsx");
        file.deleteOnExit();
        ExcelUtils.exportXLSXExcel(Arrays.asList(excelResult), 79, file.getAbsolutePath());
        file.delete();
    }

    @Test
    public void testFlowExportXLSXExcel() throws Throwable {
        File file = File.createTempFile("testExportExcel", ".xlsx");
        OutputStream os = new FileOutputStream(file);
        file.deleteOnExit();
        ExcelUtils.flowExportXLSXExcel(Arrays.asList("sheet页1", "sheet页2"), os, (times, sheetName) -> {
            if (times > 10) {
                return null;
            }
            if (Objects.equals("sheet页1", sheetName)) {
                List<Model> models = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {
                    Model model = new Model("页1-a" + i , "b" + i, "2020-11-12");
                    models.add(model);
                }
                excelResult.setContent(models);
            } else if (Objects.equals("sheet页2", sheetName)) {
                List<Model> models = new ArrayList<>();
                for (int i = 0; i < 10000; i++) {
                    Model model = new Model("页2-a" + i , "b" + i, "2020-11-12");
                    models.add(model);
                }
                excelResult.setContent(models);
            }
            return excelResult;

        });
        file.delete();
    }

}
