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
public class EasyExcelNameDemoListener extends AnalysisEventListener<EasyExcelNameDemoVO> {
    private List<EasyExcelNameDemoVO> list;

    public EasyExcelNameDemoListener(List<EasyExcelNameDemoVO> list) {
        this.list = list;
    }

    @Override
    public void invoke(EasyExcelNameDemoVO data, AnalysisContext context) {
        System.out.println(Thread.currentThread().getName());
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("=====解析完成======");
    }
}
