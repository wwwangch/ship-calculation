package com.iscas.biz.controller.common.auth;

import com.iscas.base.biz.aop.auth.SkipAuthentication;
import com.iscas.biz.domain.common.User;
import com.iscas.biz.mapper.common.UserMapper;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.biz.service.common.UserService;
import com.iscas.biz.validator.anno.UserConstraint;
import com.iscas.biz.validator.anno.UserPwdConstraint;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/22 11:15
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@Tag(name = "用户管理-MyUserController")
@RestController
@RequestMapping("/user")
@Validated
@ConditionalOnMybatis
public class MyUserController extends BaseController {

    private final String tableIdentity = "user";
    private final TableDefinitionService tableDefinitionService;
    private final UserService userService;
    private final UserMapper userMapper;

    public MyUserController(TableDefinitionService tableDefinitionService, UserService userService, UserMapper userMapper) {
        this.tableDefinitionService = tableDefinitionService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @SkipAuthentication
    @Operation(summary = "获取表头", description = "不带数据，带下拉列表")
    @GetMapping(value = "/header", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(tableIdentity);
    }

    @Operation(summary = "查询用户数据", description = "查询用户数据，提交TableSearchRequest")
    @Parameters(
            {
                    @Parameter(name = "orgId", description = "组织机构ID, 带组织机构查询的时候传入")
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "表格查询条件", content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping
    public ResponseEntity getData(@RequestBody TableSearchRequest request, @RequestParam(required = false) Integer orgId)
            throws ValidDataException {
        return userService.search(request, orgId);
    }

    @Operation(summary = "删除用户数据", description = "根据主键删除数据")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "删除用户的ID集合", content = @Content(mediaType = "application/json", array = @ArraySchema, examples = {
            @ExampleObject(value = "[123, 124]")
    }))
    @PostMapping("/del")
    public ResponseEntity deleteData(@RequestBody List<Object> ids) throws ValidDataException {
        userService.deleteCache(ids);
        return tableDefinitionService.batchDeleteData(tableIdentity, ids);
    }

    @Operation(summary = "新增用户数据", description = "插入")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "新增用户的数据，结构为一个对象或map",
            content = @Content(mediaType = "application/json", examples = {
                    @ExampleObject(value = "{\"prop1\": \"val1\", \"prop2\": \"val2\"}")
            }))
    @PostMapping("/data")
    public ResponseEntity saveData(@RequestBody @UserConstraint Map<String, Object> data)
            throws ValidDataException, NoSuchAlgorithmException {
        userService.deleteOneUserCache((String) data.get("user_name"));
        return userService.add(data);
    }

    @Operation(summary = "修改用户数据-2021-02-22", description = "更新-create by 朱全文")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据(未变动的数据也传)",
            content = @Content(schema = @Schema(example = "{\"key1\":\"value1\", \"key2\":\"value2\"}")))
    @PutMapping("/data")
    public ResponseEntity editData(@RequestBody @UserConstraint Map<String, Object> data) throws ValidDataException {
        //先删除缓存
        Integer userId = (Integer) data.get("user_id");
        if (userId != null) {
            User user = userMapper.selectById(userId);
            if (user != null) {
                userService.deleteOneUserCache(user.getUserName());
            }
        }
        return userService.edit(data);
    }

    @Operation(summary = "修改密码", description = "更新-create by 朱全文")
    @Parameters(
            {
                    @Parameter(name = "userId", description = "用户ID", required = true)
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "修改的数据",
            content = @Content(schema = @Schema(example = "{\"oldPwd\":\"xxx\", \"newPwd\":\"yyy\"}")))
    @PutMapping("/pwd/{userId:[0-9]+}")
    public ResponseEntity changePwd(@PathVariable Integer userId, @RequestBody @UserPwdConstraint Map<String, Object> data)
            throws BaseException, NoSuchAlgorithmException {
        userService.changePwd(userId, data);
        return getResponse();
    }
}
