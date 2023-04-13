package com.iscas.common.tools.mapstruct;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 与{@link TestUserDTO}映射的一个VO
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 16:30
 * @since jdk1.8
 */
@Accessors(chain = true)
@Data
public class TestUserVO1 {
    private Integer id;
    private String realName;

}
