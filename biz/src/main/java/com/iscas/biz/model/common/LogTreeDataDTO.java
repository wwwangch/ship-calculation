package com.iscas.biz.model.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 日志树的属性
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/25 14:52
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@Schema(title = "日志树的属性")
public class LogTreeDataDTO {
    @Schema(title = "是否为文件，false代表是文件夹")
    private boolean isFile;
    @Schema(title = "文件路径")
    private String filePath;
    @Schema(title = "文件名")
    private String fileName;
}
