package com.iscas.common.tools.office.excel.easyexcel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/24 10:30
 * @since jdk1.8
 */
@Slf4j
public class EasyExcelDemoListener extends AnalysisEventListener<EasyExcelDemoVO> {
    private List<EasyExcelDemoVO> list;

    public EasyExcelDemoListener(List<EasyExcelDemoVO> list) {
        this.list = list;
    }

    @Override
    public void invoke(EasyExcelDemoVO data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("=====解析完成======");
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", headMap.toString());
    }
}
