package com.iscas.biz.controller.common.auth;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.OprationService;
import com.iscas.biz.service.common.OrgService;
import com.iscas.biz.service.common.RoleService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下拉列表
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/21 19:48
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestController
@RequestMapping("/combobox")
@Tag(name = "下拉列表-ComboboxController")
@ConditionalOnMybatis
public class ComboboxController extends BaseController {
    private final RoleService roleService;
    private final OrgService orgService;
    private final OprationService oprationService;

    public ComboboxController(RoleService roleService, OrgService orgService, OprationService oprationService) {
        this.roleService = roleService;
        this.orgService = orgService;
        this.oprationService = oprationService;
    }

    @Operation(summary="获取角色下拉列表", description="获取角色下拉列表")
    @GetMapping("/role")
    public ResponseEntity roleCombobox() {
        return getResponse().setValue(roleService.combobox());
    }

    @Operation(summary="获取组织机构树-2021-02-22 create by 朱全文")
    @GetMapping("/org/tree")
    public ResponseEntity orgTree() {
        return getResponse().setValue(orgService.getTree());
    }

    @Operation(summary="获取操作下拉列表-2021-02-22 create by 朱全文")
    @GetMapping("/opration")
    public ResponseEntity opration() {
        return getResponse().setValue(oprationService.combobox());
    }

}
