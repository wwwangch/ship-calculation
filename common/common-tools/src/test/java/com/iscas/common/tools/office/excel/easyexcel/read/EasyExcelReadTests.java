package com.iscas.common.tools.office.excel.easyexcel.read;

import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 参考：https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 10:15
 * @since jdk1.8
 */
@Slf4j
public class EasyExcelReadTests {

    /**
     * 不创建对象的读
     */
    @Test
    public void noModelRead() {
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo.xlsx");
        List<Map> list = new ArrayList<>();
        EasyExcel.read(pathResource.getStream(), new EasyExcelNoneModelListener(list)).sheet(0).doRead();
        list.forEach(System.out::println);
    }

    /**
     * 读取excel 经过测试只能存入对象 方式1
     * 指定表头的行号
     * 顺带解析表头
     *
     * */
    @Test
    public void simpleRead1() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
//        String fileName = "D:\\tempx\\demo.xlsx";
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo.xlsx");
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<EasyExcelDemoVO> list = new ArrayList<>();
        EasyExcel.read(pathResource.getStream(), EasyExcelDemoVO.class, new EasyExcelDemoListener(list)).headRowNumber(1).sheet(0).doRead();
        list.forEach(System.out::println);
        log.info("========读取完成=============");
    }

    /**
     * 读取excel 经过测试只能存入对象 方式2
     * */
    @Test
    public void simpleRead2() {
//        String fileName = "D:\\tempx\\demo.xlsx";
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo.xlsx");
        ExcelReader excelReader = null;
        try {
            List<EasyExcelDemoVO> list = new ArrayList<>();
            excelReader = EasyExcel.read(pathResource.getStream(), EasyExcelDemoVO.class, new EasyExcelDemoListener(list)).headRowNumber(1).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            list.forEach(System.out::println);
            log.info("========读取完成=============");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    /**
     * 指定列的下标或者列名与实体的映射读取
     */
    @Test
    public void indexOrNameRead() {
//        String fileName = "D:\\tempx\\demo.xlsx";
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo.xlsx");
        ExcelReader excelReader = null;
        try {
            List<EasyExcelNameDemoVO> list = new ArrayList<>();
            excelReader = EasyExcel.read(pathResource.getStream(), EasyExcelNameDemoVO.class, new EasyExcelNameDemoListener(list)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            System.out.println(Thread.currentThread().getName());
            list.forEach(System.out::println);
            log.info("========读取完成=============");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    /**
     * 读取所有的sheet
     */
    @Test
    public void readAllSheet() {
//        String fileName = "D:\\tempx\\demo.xlsx";
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo.xlsx");
        ExcelReader excelReader = null;
        try {
            List<EasyExcelNameDemoVO> list = new ArrayList<>();
            excelReader = EasyExcel.read(pathResource.getStream(), EasyExcelNameDemoVO.class, new EasyExcelNameDemoListener(list)).build();
            excelReader.readAll();
            list.forEach(System.out::println);
            log.info("========读取完成=============");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    /**
     * 读取多个sheet
     */
    @Test
    public void readMoreSheet() {
//        String fileName = "D:\\tempx\\demo.xlsx";
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo.xlsx");
        ExcelReader excelReader = null;
        try {
            List<EasyExcelNameDemoVO> list2 = new ArrayList<>();
            List<EasyExcelDemoVO> list1 = new ArrayList<>();
            excelReader = EasyExcel.read(pathResource.getStream()).build();
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(EasyExcelDemoVO.class).registerReadListener(new EasyExcelDemoListener(list1)).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(EasyExcelNameDemoVO.class).registerReadListener(new EasyExcelNameDemoListener(list2)).build();
            // 这里注意 一定要把sheet1 sheet2 一起传进去，不然有个问题就是03版的excel 会读取多次，浪费性能
            excelReader.read(readSheet1, readSheet2);
            list1.forEach(System.out::println);
            list2.forEach(System.out::println);
            log.info("========读取完成=============");
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    /**
     * 测试自定义格式转换
     * */
    @Test
    public void converterRead() {
        ClassPathResource pathResource = new ClassPathResource("exceltest/easyexcel/demo2.xlsx");

        List<EasyExcelConverterData> list = new ArrayList<>();
        EasyExcel.read(pathResource.getStream(), EasyExcelConverterData.class, new EasyExcelCovertDataListener(list))
                // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
                // 如果就想单个字段使用请使用@ExcelProperty 指定converter
                // .registerConverter(new CustomStringStringConverter())
                // 读取sheet
                .sheet(0).doRead();
        list.forEach(System.out::println);
        log.info("=========读取完成============");
    }





    //其他高级应用参考https://github.com/alibaba/easyexcel/blob/master/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java


}
