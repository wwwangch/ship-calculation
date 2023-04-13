package com.iscas.common.tools.office.excel.easyexcel.write;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 * 参考：https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/write/WriteTest.java
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 12:08
 * @since jdk1.8
 */
public class EasyExcelWriteTests {

    /**
     * 最简单的写
     */
    @Test
    public void simpleWrite() throws IOException {
        // 写法1
        File file = File.createTempFile("demo", ".xlsx");
        String fileName = file.getAbsolutePath();
        file.deleteOnExit();
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("toIgnore");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, EasyExcelDemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板").doWrite(data());

        // 写法2
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            Set<String> includeColumnFiledNames = new HashSet<String>();
            includeColumnFiledNames.add("d");
            includeColumnFiledNames.add("date");
            includeColumnFiledNames.add("str");
            excelWriter = EasyExcel.write(fileName, EasyExcelDemoData.class).includeColumnFiledNames(includeColumnFiledNames).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        file.delete();
    }

    /**
     * 复杂表头写入
     * */
    @Test
    public void complexWrite() throws IOException {
        // 写法1
        File file = File.createTempFile("demo", ".xlsx");
        String fileName = file.getAbsolutePath();
        file.deleteOnExit();
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, EasyExcelComplexHeadData.class).sheet("模板").doWrite(data2());
        file.delete();
    }

    //更多高级使用参考：https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/write/WriteTest.java

    public List<EasyExcelDemoData> data() {
        List<EasyExcelDemoData> data = new ArrayList<>();
        data.add(new EasyExcelDemoData("111", new Date(), 1.2, "xxxweg"));
        return data;
    }

    public List<EasyExcelComplexHeadData> data2() {
        List<EasyExcelComplexHeadData> data = new ArrayList<>();
        data.add(new EasyExcelComplexHeadData("111", new Date(), 1.2));
        return data;
    }

    /**
     * 追加
     */
    @Test
    public void appendWrite() throws IOException {
//        // 写法1
        File file = new File("d:/test.xlsx");
        String fileName = file.getAbsolutePath();

        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("d");
        includeColumnFiledNames.add("date");
        includeColumnFiledNames.add("str");
        ExcelWriter excelWriter = EasyExcel.write(fileName, EasyExcelDemoData.class).includeColumnFiledNames(includeColumnFiledNames).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data2(), writeSheet);
        excelWriter.finish();

        ExcelWriter writer = EasyExcelFactory.write().withTemplate(file)
                .excelType(ExcelTypeEnum.XLSX)
                .file(new FileOutputStream("d:/test2.xlsx")).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        writer.write(data3(), writeSheet);
        writer.finish();

    }

    public List<EasyExcelComplexHeadData> data3() {
        List<EasyExcelComplexHeadData> data = new ArrayList<>();
        data.add(new EasyExcelComplexHeadData("111", new Date(), 1.2));
        return data;
    }

}
