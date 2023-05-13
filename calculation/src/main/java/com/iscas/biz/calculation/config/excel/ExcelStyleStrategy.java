package com.iscas.biz.calculation.config.excel;


import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

/**
 * @author ch w
 * @version 1.0
 * @since 2022/8/29 17:12
 */
public class ExcelStyleStrategy extends AbstractCellStyleStrategy {

    private CellStyle cellStyle;

    private CellStyle headStyle;

    @Override
    protected void initCellStyle(Workbook workbook) {
        cellStyle = createContentStyle(workbook);
        headStyle = createHeadStyle(workbook);
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        cell.setCellStyle(headStyle);
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        cell.setCellStyle(cellStyle);
    }

    private CellStyle createHeadStyle(Workbook workbook) {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        headWriteFont.setFontHeightInPoints((short) 20);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        return StyleUtil.buildHeadCellStyle(workbook, headWriteCellStyle);
    }

    private CellStyle createContentStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
/*        // 下边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        // 左边框
        cellStyle.setBorderLeft(BorderStyle.THIN);
        // 上边框
        cellStyle.setBorderTop(BorderStyle.THIN);
        // 右边框
        cellStyle.setBorderRight(BorderStyle.THIN);*/
        // 水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 15);
        cellStyle.setFont(font);

        return cellStyle;
    }

}
