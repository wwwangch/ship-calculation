package com.iscas.base.biz.test.datasongplus.controller;

import com.iscas.base.biz.config.datasongplus.ConditionalDatasongPlus;
import com.iscas.base.biz.test.datasongplus.domain.Achievements;
import com.iscas.base.biz.test.datasongplus.service.AchievementsService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.table.TableResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/2 10:57
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/achievenments")
@ConditionalDatasongPlus
//@Api(tags = "测试使用datasong-client-plus")
public class AchievementsController extends BaseController {

    @Autowired
    private AchievementsService achievementsService;


    @Operation(summary="[测试/XXX] 测试datasong-client-plus的查询，带分页排序", description="create by:朱全文 2020-03-02")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "map", value = "上传到数据", required = true, dataType = "TableSearchRequest")
//            }
//    )
    @GetMapping()
    public ResponseEntity findByPage() {
        List<Achievements> all = achievementsService.findByPage();
        ResponseEntity response = getResponse();
        response.setValue(all);
        return response;
    }

    @Operation(summary="[测试/XXX] 测试datasong-client-plus的查询，直接返回表", description="create by:朱全文 2020-03-02")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "map", value = "上传到数据", required = true, dataType = "TableSearchRequest")
//            }
//    )
    @GetMapping("/totable")
    public TableResponse findToTable() {
        TableResponseData<List> tableResponseData = achievementsService.findToTable();
        TableResponse response = new TableResponse();
        response.setValue(tableResponseData);
        return response;
    }

    @Operation(summary="[测试/XXX] 测试新增", description="create by:朱全文 2020-03-02")
    @Parameters(
            {
                    @Parameter(name = "achievements", description = "新增的数据", required = true)
            }
    )
    @PostMapping()
    public ResponseEntity save(@RequestBody Achievements achievements) {
        ResponseEntity response = getResponse();
        String id = achievementsService.save(achievements);
        response.setValue(id);
        return response;
    }

    @Operation(summary="[测试/XXX] 测试查询", description="create by:朱全文 2020-03-02")
    @Parameters(
            {
                    @Parameter(name = "String", description = "查询的数据", required = true)
            }
    )
    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String name) {
        ResponseEntity response = getResponse();
        List<Achievements> achievements = achievementsService.findByAuthorCNLike(name);
        response.setValue(achievements);
        return response;
    }

    @Operation(summary="[测试/XXX] 测试删除", description="create by:朱全文 2020-03-02")
    @Parameters(
            {
                    @Parameter(name = "String", description= "待删除的ID", required = true)
            }
    )
    @DeleteMapping()
    public ResponseEntity delete(@RequestParam String id) {
        ResponseEntity response = getResponse();
        boolean flag = achievementsService.delete(id);
        response.setValue(flag);
        return response;
    }
}
