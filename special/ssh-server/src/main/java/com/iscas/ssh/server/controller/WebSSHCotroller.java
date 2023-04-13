package com.iscas.ssh.server.controller;

import com.iscas.ssh.server.model.SftpFile;
import com.iscas.ssh.server.model.WebSSHData;
import com.iscas.ssh.server.service.SSHService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import com.jcraft.jsch.JSchException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * ssh连接控制器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/27 14:26
 * @since jdk1.8
 */
@RestController
@RequestMapping("/ssh/connect")
@Tag(name = "SSH连接控制器-WebSSHController")
public class WebSSHCotroller extends BaseController {
    @Autowired
    private SSHService sshService;

    /**
     * 开启一个新的会话连接窗口,sshData是连接信息
     */
    @MessageMapping("/connect")
    public ResponseEntity connect(Principal user, WebSSHData sshData) throws IOException, JSchException {
        sshService.initConnection(sshData.getConnectionId(), user);
        sshService.recvHandle(sshData);
        return getResponse();
    }

    /**
     * 开启一个新的会话连接窗口，WebSSHData是命令信息
     */
    @MessageMapping("/command")
    public ResponseEntity command(Principal user, WebSSHData sshData) throws IOException, JSchException {
        sshService.recvHandle(sshData);
        return getResponse();
    }

    /**
     * 心跳的响应，connectionId是连接的ID
     */
    @MessageMapping("/pong")
    public ResponseEntity command(Principal user, String connectionId) throws IOException, JSchException {
        sshService.pong(connectionId);
        return getResponse();
    }


    @Operation(summary = "获取远程服务器的文件列表", description = "create by zqw")
    @Parameters({
            @Parameter(name = "connectionId", description = "连接ID", required = true),
            @Parameter(name = "dir", description = "目录，如果不传，默认使用/", schema = @Schema(example = "/opt"))

    })
    @GetMapping("/list")
    public ResponseEntity getFileList(String connectionId, @RequestParam(required = false) String dir) throws BaseException {
        try {
            List<SftpFile> sftpFiles = sshService.listDir(connectionId, dir);
            return getResponse().setValue(sftpFiles);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw Exceptions.baseException("获取远程服务器的文件列表出错", e);
        }
    }

    @Operation(summary = "从远程服务器下载文件", description = "create by zqw")
    @Parameters({
            @Parameter(name = "connectionId", description = "连接ID", required = true),
            @Parameter(name = "path", description = "文件目录", required = true, schema = @Schema(example = "/opt"))

    })
    @GetMapping("/download")
    public void download(String connectionId, String path) throws BaseException {
        try {
            sshService.download(connectionId, path);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw Exceptions.baseException("文件下载出错", e);
        }
    }

    @Operation(summary = "向远程服务器传输数据", description = "create by zqw")
    @PostMapping("/upload")
    @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(type = "object"), schemaProperties = {
            @SchemaProperty(name = "files", schema = @Schema(type = "string", format = "binary")),
            @SchemaProperty(name = "connectionId", schema = @Schema(type = "string")),
            @SchemaProperty(name = "dest", schema = @Schema(type = "string", example = "/opt", defaultValue = "/opt"))
    }))
    public ResponseEntity upload(String connectionId,  MultipartFile[] files, String dest) throws BaseException {
        try {
            sshService.upload(connectionId, files, dest);
            return getResponse();
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw Exceptions.baseException("文件传输出错", e);
        }
    }

    @Operation(summary = "创建文件夹", description = "create by zqw")
    @Parameters({
            @Parameter(name = "connectionId", description = "连接ID", required = true),
            @Parameter(name = "path", description = "父级目录", required = true, schema = @Schema(example = "/opt")),
            @Parameter(name = "dirName", description = "文件夹名称", required = true, schema = @Schema(example = "/opt"))

    })
    @PutMapping("/dir")
    public ResponseEntity newDir(String connectionId, String path, String dirName) throws BaseException {
        try {
            sshService.newDir(connectionId, path, dirName);
            return getResponse();
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw Exceptions.baseException("创建文件夹出错", e);
        }
    }

    @Operation(summary = "删除文件或文件夹-2021-05-06", description = "create by zqw")
    @Parameters({
            @Parameter(name = "connectionId", description = "连接ID", required = true),
            @Parameter(name = "path", description = "文件或文件夹路径", required = true)

    })
    @DeleteMapping("/path")
    public ResponseEntity deletePath(String connectionId, String path) throws BaseException {
        try {
            sshService.deletePath(connectionId, path);
            return getResponse();
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw Exceptions.baseException("删除出错", e);
        }
    }

}
