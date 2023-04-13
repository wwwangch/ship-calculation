package com.iscas.biz.controller.common.auth;

import com.iscas.base.biz.aop.auth.SkipAuthentication;
import com.iscas.biz.domain.common.Opration;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.biz.service.common.RoleService;
import com.iscas.biz.validator.anno.RoleConstraint;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableSearchRequest;
import com.iscas.templet.view.tree.TreeResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/21 16:37
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Tag(name = "角色管理-RoleController")
@RestController
@RequestMapping("/role")
@Validated
@ConditionalOnMybatis
public class RoleController extends BaseController {
    private final String tableIdentity = "role";
    private final TableDefinitionService tableDefinitionService;
    private final RoleService roleService;

    public RoleController(TableDefinitionService tableDefinitionService, RoleService roleService) {
        this.tableDefinitionService = tableDefinitionService;
        this.roleService = roleService;
    }

    @SkipAuthentication
    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/header", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(tableIdentity);
    }

    @Operation(summary = "查询角色数据", description = "查询角色数据，提交TableSearchRequest")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping
    public ResponseEntity getData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(tableIdentity, request, null);
    }

    @Operation(summary = "获取角色的菜单树", description = "获取角色的菜单树")
    @Parameters(
            {
                    @Parameter(name = "roleId", description = "角色id", required = true)
            }
    )
    @GetMapping("/menu/tree")
    public ResponseEntity getMenuTree(@NotNull(message = "roleId不能为空") Integer roleId) {
        return getResponse().setValue(roleService.getMenuTree(roleId));
    }


    @Operation(summary = "修改角色的菜单树", description = "修改角色的菜单树")
    @Parameters(
            {
                    @Parameter(name = "roleId", description = "角色id", required = true)
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的树结构值",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TreeResponseData.class)))
    @PutMapping("/menu/tree")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public ResponseEntity updateMenuTree(@RequestBody TreeResponseData treeResponseData, @RequestParam @NotNull(message = "{role.id.null.constraint.message}") Integer roleId) {
        roleService.updateMenuTree(treeResponseData, roleId);
        return getResponse();
    }

    @Operation(summary = "获取角色对应的操作权限", description = "获取角色对应的操作权限")
    @Parameters(
            {
                    @Parameter(name = "roleId", description = "角色id", required = true)
            }
    )
    @GetMapping("/opration")
    public ResponseEntity getOpration(@NotNull(message = "{role.id.null.constraint.message}") Integer roleId) {
        return getResponse().setValue(roleService.getOprations(roleId));
    }

    @Operation(summary = "修改角色对应的操作权限", description = "修改角色对应的操作权限")
    @Parameters(
            {
                    @Parameter(name = "roleId", description = "角色id", required = true)
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "操作权限", content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Opration.class))))
    @PutMapping("/opration")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public ResponseEntity editOpration(@RequestBody List<Opration> oprations, @RequestParam @NotNull(message = "{role.id.null.constraint.message}") Integer roleId) {
        roleService.editOpration(oprations, roleId);
        return getResponse();
    }

    @Operation(summary = "删除角色数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "id的集合", content = @Content(mediaType = "application/json", examples = {
            @ExampleObject(value = "[123, 124]")
    }))
    @PostMapping("/del")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public ResponseEntity deleteData(@RequestBody List<Object> ids)
            throws ValidDataException {
        return tableDefinitionService.batchDeleteData(tableIdentity, ids);
    }

    @Operation(summary = "新增角色数据", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增的数据", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = "{\"key1\":\"val1\", \"key2\":\"val2\"}")))
    @PostMapping("/data")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public ResponseEntity saveData(@RequestBody @RoleConstraint Map<String, Object> data)
            throws ValidDataException {
        return tableDefinitionService.saveData(tableIdentity, data, false);
    }

    @Operation(summary = "修改角色数据", description = "更新")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = "{\"key1\":\"val1\", \"key2\":\"val2\"}")))
    @PutMapping("/data")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public ResponseEntity editData(@RequestBody @RoleConstraint Map<String, Object> data)
            throws ValidDataException {
        return tableDefinitionService.saveData(tableIdentity, data, false, null, null);
    }
}
