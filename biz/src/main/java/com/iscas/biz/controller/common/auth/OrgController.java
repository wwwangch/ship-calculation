package com.iscas.biz.controller.common.auth;

import com.iscas.biz.domain.common.Org;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.service.common.OrgService;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构管理
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/20 18:07
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@Tag(name = "组织机构管理-OrgController")
@RestController
@RequestMapping("/org")
@Validated
@ConditionalOnMybatis
public class OrgController extends BaseController {
    private final OrgService orgService;

    public OrgController(OrgService orgService) {
        this.orgService = orgService;
    }

    @Operation(summary="[组织机构]获取组织机构树", description="create by:朱全文 2021-02-20")
    @GetMapping
    public TreeResponse get() throws BaseException {
        return getTreeResponse().setValue(orgService.getTree());
    }

    @Operation(summary="[组织机构]新增组织机构节点", description="create by:朱全文 2021-02-20")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "组织机构数据",
            content = @Content(schema = @Schema(implementation = Org.class)))
    @PostMapping("/node")
    public ResponseEntity addNode(@Valid @RequestBody Org org) throws BaseException {
        return getResponse().setValue(orgService.addOrg(org));
    }

    @Operation(summary="[组织机构]修改组织机构节点", description="create by:朱全文 2021-02-20")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "组织机构数据",
            content = @Content(schema = @Schema(implementation = Org.class)))
    @PutMapping("/node")
    public ResponseEntity editNode(@Valid @RequestBody Org org) {
        return getResponse().setValue(orgService.editOrg(org));
    }

    @Operation(summary="[组织机构]删除组织机构节点", description="create by:朱全文 2021-02-20")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "组织机构ID集合", content = @Content(examples = @ExampleObject(value = "[123, 124]")))
    @PostMapping("/node/del")
    @Caching(evict = {
            @CacheEvict(value = "permission", key = "'url_map'"),
            @CacheEvict(value = "permission", key = "'menus'"),
            @CacheEvict(value = "permission", key = "'role_map'"),
            @CacheEvict(value = "permission", key = "'username:*'")
    })
    public ResponseEntity deleteNode(@RequestBody List<Integer> orgIds) {
        AssertCollectionUtils.assertCollectionNotEmpty(orgIds, "orgIds不能未空");
        orgService.deleteNode(orgIds);
        return getResponse();
    }

}
