package com.iscas.biz.calculation.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 16:38
 */
@Data
public class CalSectionDTO {
//    @JsonProperty(value = "project_id")
    private Integer projectId;
    private Integer sectionId;

    /**
     * 剖面文件路径
     */
    private String profileFilePathOld;

    /**
     * 剖面文件名称
     */
//    @JsonProperty(value = "loadingFilepath")
    private String profileFileName;

    /**
     * 肋位号
     */
    private Double ribNumber;
    //球扁钢数组
    private  List bulbFlats;
    //T型材数组
    private  List tProfiles;
    //是否半剖面
    private boolean isHalfProfile;
}
