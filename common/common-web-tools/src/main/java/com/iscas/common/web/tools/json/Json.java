package com.iscas.common.web.tools.json;

import java.io.Serializable;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/9 11:11
 * @since jdk1.8
 */
public interface Json extends Serializable {
    /**
     * JSON转化
     * @since jdk11
     * @date 2022/4/18
     * @return java.lang.String
     */
    String toJson();
}
