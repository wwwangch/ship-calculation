package com.iscas.biz.controller.common;

import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.mp.table.service.TableDefinitionService;
import com.iscas.biz.service.common.WsService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.ValidDataException;
import com.iscas.templet.view.table.TableSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.regexp.RE;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * websocket消息控制器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@Tag(name = "消息管理-WsDataController")
@RestController
@RequestMapping("/wsData")
@ConditionalOnMybatis
public class WsDataController extends BaseController {
    private final String tableIdentity = "ws_data";
    private final TableDefinitionService tableDefinitionService;
    private final WsService wsService;

    public WsDataController(TableDefinitionService tableDefinitionService, WsService wsService) {
        this.tableDefinitionService = tableDefinitionService;
        this.wsService = wsService;
    }

    @Operation(summary="获取表头", description="不带数据，带下拉列表")
    @GetMapping(value = "/header", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getTableHeaderWithOption() throws BaseException {
        return tableDefinitionService.getHeaderWithOption(tableIdentity);
    }

    @Operation(summary="查询数据", description="查询角色数据，提交TableSearchRequest")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "查询条件",
            content = @Content(schema = @Schema(implementation = TableSearchRequest.class)))
    @PostMapping
    public ResponseEntity getData(@RequestBody TableSearchRequest request)
            throws ValidDataException {
        return tableDefinitionService.getData(tableIdentity, request, null);
    }

    @Operation(summary="重新发送", description="重新发送")
    @Parameters(
            {
                    @Parameter(name = "id", description= "id", required = true)
            }
    )
    @PutMapping("/send/{id}")
    public ResponseEntity getData(@PathVariable Integer id) {
        wsService.retry(id);
        return getResponse();
    }

    /**
     * 接收消息回执
     * */
    @Operation(summary="消息回执-websocket消息")
    @Parameters(
            {
                    @Parameter(name = "msgId", description = "消息ID", required = true)
            }
    )
    @MessageMapping(value = "/ack/{msgId}")
    public void pathTest(Principal principal, @DestinationVariable String msgId) {
        wsService.ack(msgId);
    }
}
