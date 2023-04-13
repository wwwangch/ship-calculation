package com.iscas.common.tools.office.excel.easyexcel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 10:30
 * @since jdk1.8
 */
@Slf4j
public class EasyExcelCovertDataListener extends AnalysisEventListener<EasyExcelConverterData> {
    private List<EasyExcelConverterData> list;

    public EasyExcelCovertDataListener(List<EasyExcelConverterData> list) {
        this.list = list;
    }

    @Override
    public void invoke(EasyExcelConverterData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("=====解析完成======");
    }
}
