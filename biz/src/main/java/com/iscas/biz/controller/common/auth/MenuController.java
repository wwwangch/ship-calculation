package com.iscas.biz.controller.common.auth;

import com.iscas.biz.domain.common.Menu;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.MenuService;
import com.iscas.common.tools.assertion.AssertCollectionUtils;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.view.tree.TreeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/22 8:22
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Tag(name = "菜单管理-MenuController")
@RestController
@RequestMapping("/menu")
@ConditionalOnMybatis
public class MenuController extends BaseController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Operation(summary = "[菜单管理]获取菜单树-2021-02-22", description = "create by:朱全文")
    @GetMapping
    public TreeResponse get() throws BaseException {
        return getTreeResponse().setValue(menuService.getTree());
    }

    @Operation(summary = "[菜单管理]新增组织机构节点-2021-02-22", description = "create by:朱全文")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "菜单数据", content = @Content(schema = @Schema(implementation = Menu.class)))
    @PostMapping("/node")
    public ResponseEntity addNode(@Valid @RequestBody Menu menu) throws BaseException {
        return getResponse().setValue(menuService.addMenu(menu));
    }

    @Operation(summary = "[菜单管理]修改菜单节点-2021-02-22", description = "create by:朱全文")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "菜单数据", content = @Content(schema = @Schema(implementation = Menu.class)))
    @PutMapping("/node")
    public ResponseEntity editNode(@Valid @RequestBody Menu menu) {
        return getResponse().setValue(menuService.editMenu(menu));
    }

    @Operation(summary = "[菜单管理]删除菜单节点-2021-02-22", description = "create by:朱全文")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "菜单数据", content = @Content(examples = @ExampleObject(value = "[123, 124]")))
    @PostMapping("/node/del")
    public ResponseEntity deleteNode(@RequestBody List<Integer> menuIds) {
        AssertCollectionUtils.assertCollectionNotEmpty(menuIds, "menuIds不能未空");
        menuService.deleteNode(menuIds);
        return getResponse();
    }
}
