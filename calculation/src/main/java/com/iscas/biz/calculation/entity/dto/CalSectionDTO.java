package com.iscas.biz.calculation.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iscas.biz.calculation.enums.CheckType;
import lombok.Data;

import java.util.List;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/5/13 16:38
 */
@Data
public class CalSectionDTO {
    private Integer projectId;
    private Integer sectionId;
    /**
     * 剖面文件路径
     */
    @JsonProperty(value = "profile_file_path")
    private String profileFilePathOld;

    /**
     * 剖面文件名称
     */
    private String profileFileName;

    /**
     * 肋位号
     */
    private Double ribNumber;
    //是否半剖面
    private boolean isHalfProfile;

    //球扁钢数组
    private List bulbFlats;
    //T型材数组
    private List tProfiles;

}
