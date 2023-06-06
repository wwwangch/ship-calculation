package com.iscas.biz.calculation.util;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 自动设置列宽
 *
 * @date 01/22/2021 14:53
 */
public class ExcelWidthStyleStrategy extends AbstractColumnWidthStyleStrategy {

    // 统计setColumnWidth被调用多少次
    private static int count = 0;
    
    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> cellDataList, Cell cell, Head head,
                                  Integer relativeRowIndex, Boolean isHead) {
        // 简单设置
        Sheet sheet = writeSheetHolder.getSheet();
        sheet.setColumnWidth(cell.getColumnIndex(), 5000);
        System.out.println(count++);
    }
}
