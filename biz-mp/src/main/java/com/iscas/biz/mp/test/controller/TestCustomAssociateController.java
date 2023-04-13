package com.iscas.biz.mp.test.controller;

import com.iscas.biz.mp.aop.associate.*;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.service.common.TableAssoService;
import com.iscas.biz.mp.service.common.TableService;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/4 11:29
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "deprecation", "rawtypes"})
@RestController
@RequestMapping("/asso")
@ConditionalOnMybatis
public class TestCustomAssociateController {
    @Autowired
    private TableAssoService tableAssoService;
    @Autowired
    private TableService tableService;
    @PostMapping("/test1")
    @CustomAssociates(
            associateTables = {
                    @AssociateTable(name="parent"),
                    @AssociateTable(name="child"),
                    @AssociateTable(name="adic")
            },
            associates = {
                    @CustomAssociate(table1 = "parent", table1Col = "id", table2 = "child", table2Col = "pid", associateType = CustomAssociateType.LEFT),
                    @CustomAssociate(table1 = "adic", table1Col = "a", table2 = "child", table2Col = "a", associateType = CustomAssociateType.RIGHT)
            }
    )
    public ResponseEntity test1(@RequestBody TableSearchRequest tableSearchRequest){

        return tableAssoService.getTableReponse(tableSearchRequest, null);
    }

    @PostMapping("/test2")
    @CustomAssociates(
            associateTables = {
                    @AssociateTable(name="user"),
                    @AssociateTable(name="unit"),
                    @AssociateTable(name="usbkey"),
                    @AssociateTable(name="contact"),
                    @AssociateTable(name="dict_user_status")
            },
            associates = {
                    @CustomAssociate(table1="user", table2 = "unit", table1Col = "unitId", table2Col = "id",associateType = CustomAssociateType.LEFT),
                    @CustomAssociate(table1="user", table2 = "usbkey", table1Col = "usbkey", table2Col = "content",associateType = CustomAssociateType.LEFT),
                    @CustomAssociate(table1="user", table2 = "contact", table1Col = "contactId", table2Col = "id",associateType = CustomAssociateType.LEFT),
                    @CustomAssociate(table1="user", table2 = "dict_user_status", table1Col = "status", table2Col = "id",associateType = CustomAssociateType.LEFT),
            },
            results = {
                    @CustomResult(table="user", column = "id"),
                    @CustomResult(table="user", column = "role"),
                    @CustomResult(table="user", column = "username"),
                    @CustomResult(table="unit", column = "name", alias = "unitName"),
                    @CustomResult(table="contact", column = "name", alias = "contactName"),
                    @CustomResult(table="contact", column = "identityCard"),
                    @CustomResult(table="contact", column = "phone"),
                    @CustomResult(table="contact", column = "telephone"),
                    @CustomResult(table="usbkey", column = "code"),
                    @CustomResult(table="usbkey", column = "content"),
                    @CustomResult(table="user", column = "status"),
                    @CustomResult(table="dict_user_status", column = "value"),
            }
    )
    public TableResponse test2(@RequestBody TableSearchRequest request){
        return tableAssoService.getTableReponse(request, "user.role='apply'");
    }


    @SuppressWarnings("unchecked")
    @PostMapping("/test3")
    public TableResponse test3(@RequestBody TableSearchRequest request){
        return tableService.dynamicResponse(request, "user",null);
    }
}
