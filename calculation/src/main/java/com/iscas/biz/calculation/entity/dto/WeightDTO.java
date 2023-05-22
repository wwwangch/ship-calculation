package com.iscas.biz.calculation.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 16:38
 */
@Data
public class WeightDTO {
//    @JsonProperty(value = "project_id")
    private Integer projectId;

    /**
     * 载重文件名称
     */
    private String loadingFileName;

    /**
     * 载重文件路径
     */
//    @JsonProperty(value = "loadingFilepath")
    private String loadingFilePath;
}
