package com.iscas.common.tools.office.excel;

import cn.hutool.core.io.IoUtil;
import com.iscas.common.tools.core.io.file.FileUtils;
import com.iscas.common.tools.core.reflect.ReflectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * <p>Excel操作工具类<p/>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/13 18:16
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaCommentsMustBeJavadocFormat", "AlibabaLowerCamelCaseVariableNaming", "rawtypes", "unchecked"})
public class ExcelUtils {
    private static final String GT = ">";
    private static final String LT = "<";

    private ExcelUtils() {
    }

    @SuppressWarnings("unused")
    public static class ExcelHandlerException extends Exception {
        public ExcelHandlerException() {
            super();
        }

        public ExcelHandlerException(String message) {
            super(message);
        }

        public ExcelHandlerException(String message, Throwable cause) {
            super(message, cause);
        }

        public ExcelHandlerException(Throwable cause) {
            super(cause);
        }

        protected ExcelHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    /**
     * Excel结果类bean
     */
    public static class ExcelResult<T> {
        /**
         * sheet名称
         */
        private String sheetName;
        /**
         * 表头键值对 key : en ; value :ch
         */
        private LinkedHashMap<String, String> header;
        /**
         * Excel数据
         */
        private List<T> content;
        /**
         * 列的样式
         */
        private LinkedHashMap<String, Object> cellStyle;
        /**
         * 表头的样式
         */
        private Object headerStyle;

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(String sheetName) {
            this.sheetName = sheetName;
        }

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

        public LinkedHashMap<String, Object> getCellStyle() {
            return cellStyle;
        }

        public void setCellStyle(LinkedHashMap<String, Object> cellStyle) {
            this.cellStyle = cellStyle;
        }

        public Object getHeaderStyle() {
            return headerStyle;
        }

        public void setHeaderStyle(Object headerStyle) {
            this.headerStyle = headerStyle;
        }
    }

    /**
     * 未定义的字段
     */
    public static String NO_DEFINE = "no_define";
    public static int DEFAULT_COLOUMN_WIDTH = 17;


    /**
     * Excel写入文件,支持xls
     *
     * @param excelResults excel数据
     * @param path         输出路径
     * @throws Exception E
     * @date 2018/7/14
     * @since jdk1.8
     */
    public static <T> void exportXLSExcel(List<ExcelResult<T>> excelResults, String path) throws Exception {
        File file = new File(path);
        try (
                OutputStream out = new FileOutputStream(file)
        ) {
            exportXLSExcel(excelResults, DEFAULT_COLOUMN_WIDTH, out);
        }
    }


    /**
     * Excel写入文件,支持xls
     *
     * @param excelResults excel数据
     * @param colWidth     列宽
     * @param path         输出路径
     * @throws Exception E
     * @date 2018/7/14
     * @since jdk1.8
     */
    public static <T> void exportXLSExcel(List<ExcelResult<T>> excelResults, int colWidth,
                                          String path) throws Exception {
        File file = new File(path);
        try (
                OutputStream out = new FileOutputStream(file)
        ) {
            exportXLSExcel(excelResults, colWidth, out);
        }
    }

    /**
     * Excel写入文件，支持xlsx
     *
     * @param excelResults excel数据
     * @param path         输出路径
     * @throws Exception E
     * @date 2018/7/13
     * @since jdk1.8
     */
    public static <T> void exportXLSXExcel(List<ExcelResult<T>> excelResults,
                                           String path) throws Exception {
        File file = new File(path);
        try (
                OutputStream out = new FileOutputStream(file)
        ) {
            exportXLSXExcel(excelResults, DEFAULT_COLOUMN_WIDTH, out);
        }
    }

    /**
     * Excel写入文件，支持xlsx
     *
     * @param excelResults excel数据
     * @param colWidth     列宽
     * @param path         输出路径
     * @throws Exception E
     * @date 2018/7/13
     * @since jdk1.8
     */
    public static <T> void exportXLSXExcel(List<ExcelResult<T>> excelResults, int colWidth,
                                           String path) throws Exception {
        File file = new File(path);
        try (
                OutputStream out = new FileOutputStream(file)
        ) {
            exportXLSXExcel(excelResults, colWidth, out);
        }
    }

    /**
     * Excel写入输出流，支持xlsx
     *
     * @param excelResults excel数据
     * @param colWidth     列宽
     * @param out          {@link OutputStream} 输出流
     * @throws ExcelHandlerException E
     * @date 2018/7/13
     * @since jdk1.8
     */
    @SuppressWarnings({"resource", "deprecation", "rawtypes", "MismatchedReadAndWriteOfArray"})
    public static <T> void exportXLSXExcel(List<ExcelResult<T>> excelResults, int colWidth, OutputStream out) throws ExcelHandlerException {
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Map<ClassField, MethodHandle> cacheMethod = new HashMap<>(16);
        try {
            for (ExcelResult<T> excelResult : excelResults) {
                List<T> list = excelResult.getContent();
                LinkedHashMap<String, String> headerMap = excelResult.getHeader();
                LinkedHashMap<String, ?> styleMap = excelResult.getCellStyle();
                CellStyle headerStyle = (CellStyle) excelResult.getHeaderStyle();
                Sheet sheet = workbook.createSheet(excelResult.getSheetName());
                //至少字节数
                int minBytes = Math.max(colWidth, DEFAULT_COLOUMN_WIDTH);
                int[] arrColWidth = new int[headerMap.size()];
                // 产生表格标题行,以及设置列宽
                String[] properties = new String[headerMap.size()];
                String[] headers = new String[headerMap.size()];
                int ii = 0;
                for (String fieldName : headerMap.keySet()) {
                    properties[ii] = fieldName;
                    headers[ii] = fieldName;
                    int bytes = fieldName.getBytes().length;
                    arrColWidth[ii] = Math.max(bytes, minBytes);
                    sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
                    ii++;
                }
                Row headerRow = sheet.createRow(0); //列头 rowIndex =1
                for (int j = 0; j < headers.length; j++) {
                    headerRow.createCell(j).setCellValue(headerMap.get(headers[j]));
                    if (headerStyle != null) {
                        headerRow.getCell(j).setCellStyle(headerStyle);
                    } else {
                        CellStyle cellStyle = workbook.createCellStyle();
                        DataFormat format = workbook.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat("@"));
                        headerRow.getCell(j).setCellStyle(cellStyle);
                        headerRow.getCell(j).setCellType(CellType.STRING);
                    }
                }
                if (list != null && list.size() > 0) {
                    for (int m = 0; m < list.size(); m++) {
                        Row dataRow = sheet.createRow(m + 1);
                        T t = list.get(m);
                        for (int j = 0; j < headers.length; j++) {
                            Cell newCell = dataRow.createCell(j);
                            Object cellValue;
                            if (t instanceof Map tMap) {
                                cellValue = tMap.get(headers[j]);
                            } else {
                                //如果是Java对象，利用反射
                                MethodHandle getterMethodHandle = getMehodHandle(t.getClass(), headers[j], cacheMethod);
                                cellValue = getterMethodHandle.invoke(t);
                            }
                            cellValue = handleCellValue(cellValue);
                            newCell.setCellValue(cellValue == null ? "" : String.valueOf(cellValue));
                            if (styleMap != null && styleMap.get(headers[j]) != null) {
                                newCell.setCellStyle((CellStyle) styleMap.get(headers[j]));
                            } else {
                                StylesTable stylesTable = new StylesTable();
                                CellStyle cellStyle = new XSSFCellStyle(stylesTable);

                                DataFormat format = workbook.createDataFormat();
                                cellStyle.setDataFormat(format.getFormat("@"));
                                newCell.setCellStyle(cellStyle);
                                newCell.setCellType(CellType.STRING);
                            }
                        }
                    }
                }
            }
            workbook.write(out);
            out.flush();
        } catch (Throwable e) {
            throw new ExcelHandlerException(e);
        }
    }

    /**
     * Excel写入输出流，支持xls
     *
     * @param results  excel数据
     * @param colWidth 列宽
     * @param out      {@link OutputStream} 输出流
     * @throws ExcelHandlerException e
     * @date 2018/7/13
     * @since jdk1.8
     */
    @SuppressWarnings({"resource", "rawtypes"})
    public static <T> void exportXLSExcel(List<ExcelResult<T>> results, int colWidth,
                                          OutputStream out) throws ExcelHandlerException {
        try {
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            Map<ClassField, MethodHandle> cacheMethod = new HashMap<>(16);
            for (ExcelResult<T> excelResult : results) {
                List<T> list = excelResult.getContent();
                if (list != null && list.size() > 0 && list.size() > 65534) {
                    throw new ExcelHandlerException("Excel超过了允许的大小");
                }
                LinkedHashMap<String, String> headerMap = excelResult.getHeader();
                LinkedHashMap<String, ?> styleMap = excelResult.getCellStyle();
                HSSFCellStyle headerStyle = (HSSFCellStyle) excelResult.getHeaderStyle();
                HSSFSheet sheet = workbook.createSheet(excelResult.getSheetName());
                int minBytes = Math.max(colWidth, DEFAULT_COLOUMN_WIDTH);//至少字节数
                int[] arrColWidth = new int[headerMap.size()];
                // 产生表格标题行,以及设置列宽
                @SuppressWarnings("MismatchedReadAndWriteOfArray") String[] properties = new String[headerMap.size()];
                String[] headers = new String[headerMap.size()];
                int ii = 0;
                for (String fieldName : headerMap.keySet()) {
                    properties[ii] = fieldName;
                    headers[ii] = fieldName;
                    int bytes = fieldName.getBytes().length;
                    arrColWidth[ii] = Math.max(bytes, minBytes);
                    sheet.setColumnWidth(ii, arrColWidth[ii] * 256);
                    ii++;
                }
                HSSFRow headerRow = sheet.createRow(0); //列头 rowIndex =1
                for (int j = 0; j < headers.length; j++) {
                    headerRow.createCell(j).setCellValue(headerMap.get(headers[j]));
                    if (headerStyle != null) {
                        headerRow.getCell(j).setCellStyle(headerStyle);
                    } else {
                        HSSFCellStyle cellStyle = workbook.createCellStyle();
                        DataFormat format = workbook.createDataFormat();
                        cellStyle.setDataFormat(format.getFormat("@"));
                        headerRow.getCell(j).setCellStyle(cellStyle);
                        headerRow.getCell(j).setCellType(CellType.STRING);
                    }
                }
                if (list != null && list.size() > 0) {
                    for (int m = 0; m < list.size(); m++) {
                        HSSFRow dataRow = sheet.createRow(m + 1);
                        T t = list.get(m);
                        for (int j = 0; j < headers.length; j++) {
                            HSSFCell newCell = dataRow.createCell(j);
                            Object cellValue;
                            if (t instanceof Map tMap) {
                                cellValue = tMap.get(headers[j]);
                            } else {
                                MethodHandle getterMethodHandle = getMehodHandle(t.getClass(), headers[j], cacheMethod);
                                cellValue = getterMethodHandle.invoke(t);
                            }
                            cellValue = handleCellValue(cellValue);
                            newCell.setCellValue(cellValue == null ? "" : String.valueOf(cellValue));
                            if (styleMap != null && styleMap.get(headers[j]) != null) {
                                newCell.setCellStyle((HSSFCellStyle) styleMap.get(headers[j]));
                            } else {
                                HSSFCellStyle cellStyle = workbook.createCellStyle();
                                HSSFDataFormat format = workbook.createDataFormat();
                                cellStyle.setDataFormat(format.getFormat("@"));
                                newCell.setCellStyle(cellStyle);
                                newCell.setCellType(CellType.STRING);
                            }
                        }
                    }
                }
            }

            workbook.write(out);
            out.flush();
        } catch (ExcelHandlerException e) {
            throw e;
        } catch (Throwable e) {
            throw new ExcelHandlerException(e);
        }
    }


    @SuppressWarnings("rawtypes")
    public static Map<String, Object> readExcelWithHeader(InputStream inputStream) throws ExcelHandlerException {
        Map<String, Object> result = new HashMap<>(2 << 4);
        // IO流读取文件
        try {
            Workbook wb = WorkbookFactory.create(inputStream);
            if (wb instanceof XSSFWorkbook xssfWb) {
                LinkedHashMap<String, List<String>> headerMap = readXLSXHeader(xssfWb);
                LinkedHashMap<String, List<LinkedHashMap>> data = readXLSXData(xssfWb);
                result.put("header", headerMap);
                result.put("data", data);
            } else if (wb instanceof HSSFWorkbook hssfWb) {
                LinkedHashMap<String, List<String>> headerMap = readXLSHeader(hssfWb);
                LinkedHashMap<String, List<LinkedHashMap>> data = readXLSData(hssfWb);
                result.put("header", headerMap);
                result.put("data", data);
            }
        } catch (Exception e) {
            throw new ExcelHandlerException(e);
        }

        return result;
    }


    /**
     * 将一个文件输入的的Excel表头读出来
     *
     * @param file 文件
     * @return java.util.List<java.lang.String>
     * @throws Exception e
     * @date 2018/9/18
     * @since jdk1.8
     */
    public static LinkedHashMap<String, List<String>> readExcelHeader(File file) throws Exception {
        try (
                InputStream is = new FileInputStream(file)
        ) {
            return readExcelHeader(is);
        }
    }

    /**
     * 将一个输入流输入的的Excel表头读出来
     *
     * @param is 输入流
     * @return java.util.List<java.lang.String>
     * @throws ExcelHandlerException e
     * @date 2018/9/18
     * @since jdk1.8
     */
    public static LinkedHashMap<String, List<String>> readExcelHeader(InputStream is) throws ExcelHandlerException {
        LinkedHashMap<String, List<String>> headerMap = new LinkedHashMap<>();
        try {
            Workbook wb = WorkbookFactory.create(is);
            if (wb instanceof XSSFWorkbook xssfWorkbook) {
                headerMap = readXLSXHeader(xssfWorkbook);
            } else if (wb instanceof HSSFWorkbook hssfWorkbook) {
                headerMap = readXLSHeader(hssfWorkbook);
            }
        } catch (Exception e) {
            throw new ExcelHandlerException(e);
        }
        return headerMap;
    }

    public static void readExcelToListMap(String path, Map<String, List> resultMap) throws ExcelHandlerException {

        try (
                InputStream is = new FileInputStream(path)
        ) {
            readExcelToListMap(is, resultMap);
        } catch (Exception e) {
            throw new ExcelHandlerException(e);
        }
    }


    /**
     * 将EXCEL读取到map中<br/>
     * map以sheet名称为key,以List<Map>为值
     *
     * @param inputStream 文件输入流
     * @param resultMap   结果
     * @throws ExcelHandlerException e
     * @date 2018/7/14
     * @since jdk1.8
     */
    public static void readExcelToListMap(InputStream inputStream, Map<String, List> resultMap) throws ExcelHandlerException {
        Workbook wb;
        try {
            wb = WorkbookFactory.create(inputStream);
            if (wb instanceof XSSFWorkbook xssfWorkbook) {
                readXLSXToListMap(xssfWorkbook, resultMap);
            } else if (wb instanceof HSSFWorkbook hssfWorkbook) {
                readXLSToListMap(hssfWorkbook, resultMap);
            } else {
                throw new ExcelHandlerException("excel文件格式错误，必须是xls或xlsx格式");
            }
        } catch (ExcelHandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new ExcelHandlerException(e);
        }

    }

    private static LinkedHashMap<String, List<String>> readXLSHeader(HSSFWorkbook hssfWb) {
        LinkedHashMap<String, List<String>> headerMap = new LinkedHashMap<>();
        for (int i = 0; i < hssfWb.getNumberOfSheets(); i++) {
            List<String> headers = new ArrayList<>();
            //读取sheet(页)
            HSSFSheet xssfSheet = hssfWb.getSheetAt(i);
            //读取第一行
            HSSFRow xssfRow = xssfSheet.getRow(0);
            if (xssfRow != null) {
                int totalCells = xssfRow.getLastCellNum();
                //读取列，从第一列开始
                for (int c = 0; c < totalCells; c++) {
                    HSSFCell cell = xssfRow.getCell(c);
                    if (cell == null) {
                        continue;
                    }

                    Object value = cell.getStringCellValue();
                    headers.add(String.valueOf(value));

                }
            }
            headerMap.put(xssfSheet.getSheetName(), headers);
        }
        return headerMap;
    }

    private static LinkedHashMap<String, List<String>> readXLSXHeader(XSSFWorkbook xssfWb) {
        LinkedHashMap<String, List<String>> headerMap = new LinkedHashMap<>();
        for (int i = 0; i < xssfWb.getNumberOfSheets(); i++) {
            List<String> headers = new ArrayList<>();
            //读取sheet(页)
            XSSFSheet xssfSheet = xssfWb.getSheetAt(0);
            //读取第一行
            XSSFRow xssfRow = xssfSheet.getRow(0);
            if (xssfRow != null) {
                int totalCells = xssfRow.getLastCellNum();
                //读取列，从第一列开始
                for (int c = 0; c < totalCells; c++) {
                    XSSFCell cell = xssfRow.getCell(c);
                    if (cell == null) {
                        continue;
                    }
                    Object value = cell.getStringCellValue();
                    headers.add(String.valueOf(value));
                }
            }
            headerMap.put(xssfSheet.getSheetName(), headers);
        }
        return headerMap;
    }

    private static LinkedHashMap<String, List<LinkedHashMap>> readXLSXData(XSSFWorkbook wb) {
        LinkedHashMap<String, List<LinkedHashMap>> result = new LinkedHashMap<>();
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            List<LinkedHashMap> list = new ArrayList<>();
            XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
            int totalRows = xssfSheet.getLastRowNum();
            //读取Row,从第二行开始
            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    LinkedHashMap map = new LinkedHashMap();
                    int totalCells = xssfRow.getLastCellNum();
                    //读取列，从第一列开始
                    for (int c = 0; c < totalCells; c++) {
                        XSSFCell cell = xssfRow.getCell(c);
                        if (cell == null) {
                            continue;
                        }
                        Object value = switch (cell.getCellType()) {
                            case NUMERIC -> cell.getNumericCellValue();
                            case STRING -> cell.getStringCellValue();
                            case BOOLEAN -> cell.getBooleanCellValue();
                            case FORMULA -> cell.getCellFormula() + "";
                            case BLANK -> "";
                            case ERROR -> "非法字符";
                            default -> null;
                        };
                        String key = xssfSheet.getRow(0).getCell(c).getStringCellValue();
                        map.put(key, value);
                    }
                    Set set = map.keySet();
                    boolean addFlag = false;
                    for (Object obj : set) {
                        if (map.get(obj) != null && !"".equals(map.get(obj))) {
                            addFlag = true;
                        }
                    }
                    if (addFlag) {
                        list.add(map);
                    }
                }
            }
            result.put(xssfSheet.getSheetName(), list);
        }
        return result;
    }

    private static LinkedHashMap<String, List<LinkedHashMap>> readXLSData(HSSFWorkbook wb) {
        LinkedHashMap<String, List<LinkedHashMap>> result = new LinkedHashMap<>();
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            List<LinkedHashMap> list = new ArrayList<>();
            HSSFSheet xssfSheet = wb.getSheetAt(numSheet);
            int totalRows = xssfSheet.getLastRowNum();
            //读取Row,从第二行开始
            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                HSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    LinkedHashMap map = new LinkedHashMap();
                    int totalCells = xssfRow.getLastCellNum();
                    //读取列，从第一列开始
                    for (int c = 0; c < totalCells; c++) {
                        HSSFCell cell = xssfRow.getCell(c);
                        if (cell == null) {
                            continue;
                        }
                        Object value = switch (cell.getCellType()) {
                            case NUMERIC -> cell.getNumericCellValue();
                            case STRING -> cell.getStringCellValue();
                            case BOOLEAN -> cell.getBooleanCellValue();
                            case FORMULA -> cell.getCellFormula() + "";
                            case BLANK -> "";
                            case ERROR -> "非法字符";
                            default -> null;
                        };
                        String key = xssfSheet.getRow(0).getCell(c).getStringCellValue();
                        map.put(key, value);
                    }
                    Set set = map.keySet();
                    boolean addFlag = false;
                    for (Object obj : set) {
                        if (map.get(obj) != null && !"".equals(map.get(obj))) {
                            addFlag = true;
                        }
                    }
                    if (addFlag) {
                        list.add(map);
                    }
                }
            }
            result.put(xssfSheet.getSheetName(), list);
        }
        return result;
    }

    private static void readXLSXToListMap(XSSFWorkbook wb, Map<String, List> resultMap) {
        //读取sheet(页)
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            List<Map> list = new ArrayList<>();
            XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            int totalRows = xssfSheet.getLastRowNum();
            //读取Row,从第二行开始
            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    Map map = new LinkedHashMap();
                    int totalCells = xssfRow.getLastCellNum();
                    //读取列，从第一列开始
                    for (int c = 0; c < totalCells; c++) {
                        XSSFCell cell = xssfRow.getCell(c);
                        if (cell == null) {
                            continue;
                        }
                        Object value = switch (cell.getCellType()) {
                            case NUMERIC -> cell.getNumericCellValue();
                            case STRING -> cell.getStringCellValue();
                            case BOOLEAN -> cell.getBooleanCellValue();
                            case FORMULA -> cell.getCellFormula() + "";
                            case BLANK -> "";
                            case ERROR -> "非法字符";
                            default -> null;
                        };
                        String key = xssfSheet.getRow(0).getCell(c).getStringCellValue();
                        map.put(key, value);
                    }
                    Set set = map.keySet();
                    boolean addFlag = false;
                    for (Object obj : set) {
                        if (map.get(obj) != null && !"".equals(map.get(obj))) {
                            addFlag = true;
                        }
                    }
                    if (addFlag) {
                        list.add(map);
                    }
                }
            }
            if (resultMap.get(xssfSheet.getSheetName()) != null) {
                List<Map> listx = resultMap.get(xssfSheet.getSheetName());
                listx.addAll(list);
            } else {
                resultMap.put(xssfSheet.getSheetName(), list);
            }
        }

    }

    private static void readXLSToListMap(HSSFWorkbook wb, Map<String, List> resultMap) {
        //读取sheet(页)
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            List<Map> list = new ArrayList<>();
            HSSFSheet xssfSheet = wb.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            int totalRows = xssfSheet.getLastRowNum();
            //读取Row,从第二行开始
            for (int rowNum = 1; rowNum <= totalRows; rowNum++) {
                HSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    Map map = new LinkedHashMap();
                    int totalCells = xssfRow.getLastCellNum();
                    //读取列，从第一列开始
                    for (int c = 0; c < totalCells; c++) {
                        HSSFCell cell = xssfRow.getCell(c);
                        if (cell == null) {
                            continue;
                        }
                        Object value = switch (cell.getCellType()) {
                            case NUMERIC -> cell.getNumericCellValue();
                            case STRING -> cell.getStringCellValue();
                            case BOOLEAN -> cell.getBooleanCellValue();
                            case FORMULA -> cell.getCellFormula() + "";
                            case BLANK -> "";
                            case ERROR -> "非法字符";
                            default -> null;
                        };
                        String key = xssfSheet.getRow(0).getCell(c).getStringCellValue();
                        map.put(key, value);
                    }
                    Set set = map.keySet();
                    boolean addFlag = false;
                    for (Object obj : set) {
                        if (map.get(obj) != null && !"".equals(map.get(obj))) {
                            addFlag = true;
                        }
                    }
                    if (addFlag) {
                        list.add(map);
                    }
                }
            }
            if (resultMap.get(xssfSheet.getSheetName()) != null) {
                List<Map> listx = resultMap.get(xssfSheet.getSheetName());
                listx.addAll(list);
            } else {
                resultMap.put(xssfSheet.getSheetName(), list);
            }
        }

    }

    public static HSSFCellStyle getHSSFCellStyle(HSSFWorkbook workbook) {
        // 单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont cellFont = workbook.createFont();
        cellStyle.setFont(cellFont);
        return cellStyle;
    }

    public static CellStyle getSXSSFCellStyle(SXSSFWorkbook workbook) {
        // 单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font cellFont = workbook.createFont();
//        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        return cellStyle;
    }

    /**
     * 样式
     */
    public static HSSFCellStyle getHSSFHeaderStyle(HSSFWorkbook workbook) {
        // 列头样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
//        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        return headerStyle;
    }

    //样式
    public static CellStyle getSXSSFHeaderStyle(SXSSFWorkbook workbook) {
        // 列头样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
//        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        return headerStyle;
    }

    /**
     * Excel流式写入，不须POI依赖，非空Excel模板暂未做处理，建议使用{@link #flowExportXLSXExcel(List, OutputStream, FlowExcelDataProducer)}
     *
     * @param sheetNames            多个sheet页的名字
     * @param os                    导出的流，可以为文件流或其他输出流
     * @param flowExcelDataProducer 生成数据的回调，会一直回调，直至返回为null
     * @param templateExcelFile     模板excel文件，这里必须为空Excel,否则会出问题，  todo 非空Excel模板暂未做处理
     * @throws Exception e
     * @date 2021/06/02
     * @since jdk1.8
     */
    @SuppressWarnings({"resource", "RegExpSimplifiable"})
    public static void flowExportXLSXExcel(List<String> sheetNames, OutputStream os,
                                           FlowExcelDataProducer flowExcelDataProducer, File templateExcelFile) throws Throwable {
        //判断sheet页文件的表达式
        String regex = "xl/worksheets/sheet[0-9]+\\.xml";
        //套一层压缩流写出
        try (ZipOutputStream zos = new ZipOutputStream(os)) {
            ZipFile tmpZipFile = new ZipFile(templateExcelFile);
            Enumeration<? extends ZipEntry> entries = tmpZipFile.entries();
            while (entries.hasMoreElements()) {
                //除了sheet页，其他都直接输出到压缩流内
                ZipEntry zipEntry = entries.nextElement();
                String zipEntryName = zipEntry.getName();
                zos.putNextEntry(new ZipEntry(zipEntryName));
                if (Pattern.matches(regex, zipEntryName)) {
                    //获取对应的sheet页名字
                    String no = StringUtils.substringBetween(zipEntryName, "xl/worksheets/sheet", ".xml");
                    String currentSheetName = sheetNames.get(Integer.parseInt(no) - 1);

                    //如果是sheet页数据，调用回调函数不断获取数据，
                    // 并输出至sheet页，直到获取到的数据为空
                    try (
                            InputStream is = tmpZipFile.getInputStream(zipEntry);
                            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8)
                    ) {
                        OutputStreamWriter osw = new OutputStreamWriter(zos, StandardCharsets.UTF_8);
                        List<String> lines = FileUtils.readLines(isr);
                        if (lines.size() != 2) {
                            throw new RuntimeException(String.format("sheet页：%s的格式出现错误", zipEntryName));
                        }
                        //第一行为xml的声明
                        osw.write(lines.get(0));
                        osw.write("\n");
                        //第二行为值以及描述，没换行，按应有的行分割
                        String dataStr = lines.get(1);
                        for (String elementStr : dataStr.replace(GT, GT + "\n").split("\n")) {
                            if (elementStr.trim().startsWith("<sheetData")) {
                                int colCount = 100;

                                osw.write("<sheetData>");

                                int[] rowIndex = new int[1];
                                int times = 0;
                                //获取数据，在sheetData下写入，直至拿不到数据。
                                boolean firstWrite = true;

                                while (true) {
                                    ExcelResult excelResult = flowExcelDataProducer.supply(++times, currentSheetName);
                                    if (excelResult == null) {
                                        break;
                                    }
                                    //写入数据
                                    writeData(osw, excelResult, firstWrite, rowIndex);
                                    firstWrite = false;
                                    osw.flush();
                                }
                                osw.write("</sheetData>\n");
                            } else {
                                osw.write(elementStr);
                            }
                        }
                        osw.flush();
                    }
                } else {
                    //如果是非sheet页数据，直接输出到压缩流内
                    try (InputStream is = tmpZipFile.getInputStream(zipEntry)) {
                        //输出
                        IoUtil.copy(is, zos);
                    }
                }
            }
        }
    }

    /**
     * Excel流式写入，使用POI自动生成Excel空模板，写入，需要POI的依赖
     *
     * @param sheetNames            多个sheet页的名字
     * @param os                    导出的流，可以为文件流或其他输出流
     * @param flowExcelDataProducer 生成数据的回调，会一直回调，直至返回为null
     * @throws Exception e
     * @date 2021/06/02
     * @since jdk1.8
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void flowExportXLSXExcel(List<String> sheetNames, OutputStream os,
                                           FlowExcelDataProducer flowExcelDataProducer) throws Throwable {
        //通过POI生成一个临时excel，这里可以不用POI，使用一个模板的Excel文件也可以
        File tmpFile = File.createTempFile("tmpExcel", ".xlsx");
        try {
            try (
                    XSSFWorkbook wb = new XSSFWorkbook();
                    OutputStream fos = new FileOutputStream(tmpFile)
            ) {
                //创建sheet页
                sheetNames.forEach(wb::createSheet);
                wb.write(fos);
            }
            flowExportXLSXExcel(sheetNames, os, flowExcelDataProducer, tmpFile);
        } finally {
            //删除临时文件
            tmpFile.delete();
        }
    }


    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class ClassField {
        private Class tClass;
        private String field;
    }

    private static void writeData(OutputStreamWriter osw, ExcelResult excelResult, boolean firstWrite, int[] rowIndex) throws Throwable {
        String cellStr = "<c r=\"@wordIndex@@index@\" s=\"1\" t=\"s\" ><v>@data@</v></c>";
        List content = excelResult.getContent();
        final LinkedHashMap<String, String> header = excelResult.getHeader();
        //如果是第一次写入，顺带写入表头
        if (firstWrite) {
            int index = 0;
            osw.write("<row r=\"" + 1 + "\">");
            for (Map.Entry<String, String> entry : header.entrySet()) {
                String headerCh = entry.getValue();
                osw.write(cellStr.replace("@wordIndex@", getColNoByIndex(index++))
                        .replace("@index@", "1").replace("@data@", headerCh == null ? "" : headerCh));
            }
            osw.write("</row>");
            rowIndex[0]++;
        }

        //缓存反射的method
        Map<ClassField, MethodHandle> cachedMethodMap = new HashMap<>(16);

        if (content != null) {
            for (int i = 0; i < content.size(); i++) {
                Object t = content.get(i);
                ++rowIndex[0];
                int index = 0;
                osw.write("<row r=\"" + rowIndex[0] + "\">");
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    Object cellValue;
                    if (t instanceof Map) {
                        cellValue = ((Map) t).get(entry.getKey());
                    } else {
                        MethodHandle getterMethodHandle = getMehodHandle(t.getClass(), entry.getKey(), cachedMethodMap);
                        cellValue = getterMethodHandle.invoke(t);
                        //                        cellValue = getReadMethod(cachedMethodMap, entry.getKey(), t.getClass()).invoke(t);
                    }
                    cellValue = handleCellValue(cellValue);
                    osw.write(cellStr.replace("@wordIndex@", getColNoByIndex(index++))
                            .replace("@index@", String.valueOf(rowIndex[0])).replace("@data@", cellValue == null ? "" : cellValue.toString()));
                }
                osw.write("</row>");
                if (i % 100 == 99) {
                    //100个一批flush
                    osw.flush();
                }
            }
        }
        osw.flush();
    }

    private static MethodHandle getMehodHandle(Class tClass, String field, Map<ClassField, MethodHandle> map) throws NoSuchFieldException, IllegalAccessException {
        ClassField classField = new ClassField(tClass, field);
        if (map.containsKey(classField)) {
            return map.get(classField);
        }
        MethodHandle methodHandle = ReflectUtils.getGetterMethodHandle(tClass, field);
        map.put(classField, methodHandle);
        return methodHandle;
    }

    private static Method getReadMethod(Map<String, Method> cachedMethodMap, String key, Class clazz) throws IntrospectionException {
        Method method;
        if (!cachedMethodMap.containsKey(key)) {
            PropertyDescriptor pd = new PropertyDescriptor(key, clazz);
            method = pd.getReadMethod();//获得get方法
            cachedMethodMap.put(key, method);
        } else {
            method = cachedMethodMap.get(key);
        }
        return method;
    }

    /**
     * 下标从0开始，获取对应的Excel列的标号，分别为A B C D ...... 26对应AA，27对应AB，依次类推
     */
    private static String getColNoByIndex(int index) {
        if (index < 26) {
            return String.valueOf((char) (index + (int) 'A'));
        } else {
            //最多支持26 * 26列，足够了，就这样吧。
            return getColNoByIndex((index - 26) / 26) + getColNoByIndex((index - 26) % 26);
        }
    }

    private static Object handleCellValue(Object cellValue) {
        if (cellValue instanceof LocalDateTime localDateTime) {
            // 将LocalDateTime类型格式化为字符串
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return localDateTime.format(formatter);
        } else if (cellValue instanceof LocalDate localDate) {
            // 将LocalDate类型格式化为字符串
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return localDate.format(formatter);
        }
        return cellValue;
    }

}
