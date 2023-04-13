package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.util.TableInfoUtils;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.view.table.TableHeaderResponse;
import com.iscas.templet.view.table.TableHeaderResponseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/3 9:49
 * @since jdk1.8
 */
@RestController
public class TestHeaderController extends BaseController {
    @GetMapping("/testHeader")
    public TableHeaderResponse getHeader(){
        TableHeaderResponse tableHeaderResponse = new TableHeaderResponse();
//        tableHeaderResponse.setStatus(200);
        try {
            TableHeaderResponseData tableHeaderResponseData = TableInfoUtils.getTableHeader("User");
            tableHeaderResponse.setValue(tableHeaderResponseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tableHeaderResponse;
    }
}
