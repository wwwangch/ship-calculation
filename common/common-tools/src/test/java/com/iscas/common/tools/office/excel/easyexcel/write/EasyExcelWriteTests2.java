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
 * 参考：https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/write/WriteTest.java
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 12:08
 * @since jdk1.8
 */
public class EasyExcelWriteTests2 {



    /**
     * 一次写入
     * */
    @Test
    public void test1() throws IOException {
        File file = new File("d:/test_1.xlsx");
        String fileName = file.getAbsolutePath();
        ExcelWriter excelWriter = null;

        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("d");
        includeColumnFiledNames.add("date");
        includeColumnFiledNames.add("str");
        excelWriter = EasyExcel.write(fileName, EasyExcelDemoData.class).includeColumnFiledNames(includeColumnFiledNames).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(100000), writeSheet);
        excelWriter.finish();

    }


    /**
     * 追加
     */
    @Test
    public void test2() throws IOException {
        File file = new File("d:/test0.xlsx");
        String fileName = file.getAbsolutePath();
        ExcelWriter excelWriter = null;
        Set<String> includeColumnFiledNames = new HashSet<String>();
        includeColumnFiledNames.add("d");
        includeColumnFiledNames.add("date");
        includeColumnFiledNames.add("str");
        excelWriter = EasyExcel.write(fileName, EasyExcelDemoData.class).includeColumnFiledNames(includeColumnFiledNames).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(10000), writeSheet);
        excelWriter.finish();
        for (int i = 0; i < 9; i++) {
            ExcelWriter writer = EasyExcelFactory.write().withTemplate("d:/test" + i + ".xlsx")
                    .excelType(ExcelTypeEnum.XLSX)
                    .file(new FileOutputStream("d:/test" + (i + 1) + ".xlsx")).build();
            writer.write(data(10000), writeSheet);
            writer.finish();

        }


    }



    public List<EasyExcelDemoData> data(int count) {
        List<EasyExcelDemoData> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            data.add(new EasyExcelDemoData("111", new Date(), 1.2, "xxxweg"));

        }
        return data;
    }



}
