package com.iscas.common.jgit.tools.model;

import lombok.Data;

/**
 * 对比结果
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/25 9:28
 * @since jdk1.8
 */
@Data
public class CompareResult {
    /**新文档的不同的开始索引*/
    private Integer pFrom;

    /**新文档的不同的结束索引*/
    private Integer pTo;

    /**旧文档的不同的开始索引*/
    private Integer cFrom;

    /**旧文档的不同的结束索引*/
    private Integer cTo;
}
