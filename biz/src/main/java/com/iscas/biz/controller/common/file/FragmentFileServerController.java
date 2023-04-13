package com.iscas.biz.controller.common.file;

import com.iscas.biz.domain.common.FileInfo;
import com.iscas.biz.service.common.FileInfoService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseException;
import com.iscas.templet.exception.Exceptions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件服务控制器-带断点续传
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/1/10 14:38
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Tag(name = "文件上传示例-支持断点续传-FragmentFileServerController")
public class FragmentFileServerController extends BaseController {
    private final FileInfoService fileInfoService;

    @PostMapping("/upload")
    @Operation(summary = "文件上传", description = "文件上传")
    @RequestBody(content = @Content(mediaType = "multipart/form-data", schema = @Schema(type = "object"), schemaProperties = {
            @SchemaProperty(name = "file", schema = @Schema(type = "string", format = "binary", description = "上传的文件")),
            @SchemaProperty(name = "suffix", schema = @Schema(type = "string", description = "文件后缀")),
            @SchemaProperty(name = "shardIndex", schema = @Schema(type = "string", description = "分片索引号")),
            @SchemaProperty(name = "shardSize", schema = @Schema(type = "string", description = "当前上传分片大小")),
            @SchemaProperty(name = "shardTotal", schema = @Schema(type = "string", description = "分片数目")),
            @SchemaProperty(name = "size", schema = @Schema(type = "string", description = "文件大小")),
            @SchemaProperty(name = "key", schema = @Schema(type = "string", description = "文件的key,MD5码：文件名 + 文件大小 + 文件类型 + 文件最后修改时间的MD5码")),
            @SchemaProperty(name = "name", schema = @Schema(type = "string", description = "文件名称"))
    }))
    public ResponseEntity upload(MultipartFile file, String suffix, int shardIndex, int shardSize,
                                 int shardTotal, int size, String key, String name) throws BaseException {
        try {
            fileInfoService.upload(file, suffix, shardIndex, shardSize, shardTotal, size, key, name);
        } catch (IOException | InterruptedException e) {
            throw Exceptions.baseException("文件上传出错", e);
        }
        return getResponse();
    }

    @PostMapping("/check")
    @Operation(summary = "文件检测", description = "文件检测")
    @Parameters(
            {
                    @Parameter(name = "key", description = "文件的key,MD5码：文件名 + 文件大小 + 文件类型 + 文件最后修改时间的MD5码", required = true)
            }
    )
    public ResponseEntity check(String key) throws BaseException {
        List<FileInfo> check = fileInfoService.check(key);
        //如果这个key存在的话 那么就获取上一个分片去继续上传
        if (check.size() != 0) {
            return getResponse().setValue(check.get(0));
        }
        return getResponse().setStatus(500).setValue("no sliver");
    }

}
