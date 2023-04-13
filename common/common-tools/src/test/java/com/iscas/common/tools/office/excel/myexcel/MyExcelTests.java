//package com.iscas.common.tools.office.excel.myexcel;
//
//import com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder;
//import com.github.liaochong.myexcel.utils.FileExportUtil;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/11/24 18:02
// * @since jdk1.8
// */
//public class MyExcelTests {
//
//    @Test
//    void customWidthBuild() throws Exception {
//        try (DefaultStreamExcelBuilder<Map> excelBuilder = DefaultStreamExcelBuilder.of(Map.class)
//                .fixedTitles()
//                .titles(List.of("a", "b", "c", "d"))
//                .widths(15, 20, 25, 30)
//                .start()) {
//            data(excelBuilder, 10000000);
//            Workbook workbook = excelBuilder.build();
//            FileExportUtil.export(workbook, new File("d:/tempx/custom_width_build.xlsx"));
//        }
//    }
//
//    private void data(DefaultStreamExcelBuilder<Map> excelBuilder, int size) {
//
//        List<CompletableFuture> futures = new LinkedList<>();
////        List<String> ss = new ArrayList<>();
////        ss.add("1");
////        ss.add("2");
//        Random r = new Random();
//        for (int i = 0; i < size; i++) {
//            int index = i;
//            CompletableFuture future = CompletableFuture.runAsync(() -> {
//                Map map = new HashMap() {{
//                    put("a", index);
//                    put("b", 2);
//                    put("c", 3);
//                    put("d", r.nextInt());
//                }};
//                excelBuilder.append(map);
//            });
//            futures.add(future);
//        }
//        futures.forEach(CompletableFuture::join);
//    }
//
//}
