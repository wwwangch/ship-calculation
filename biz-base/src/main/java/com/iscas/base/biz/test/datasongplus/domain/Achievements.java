package com.iscas.base.biz.test.datasongplus.domain;

import com.iscas.datasong.lib.annotation.table.DSStoreType;
import com.iscas.datasong.lib.common.StoreType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/2 10:55
 * @since jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@DSStoreType(StoreType.Fulltext)
public class Achievements {
    /**id*/
    private String _id;

    /**标题英文*/
    private String titleEN;

    /**标题中文*/
    private String titleCN;

    /**作者-英文*/
    private String authorEN;

    /**作者-中文*/
    private String authorCN;

    /**获奖类别*/
    private String type;

    /**获奖等级*/
    private String level;

    /**语种*/
    private String language;

    /**所属領域*/
    private String field;

    /**目录/摘要 英文*/
    private String catalogEN;

    /**目录/摘要 中文*/
    private String catalogCN;

    /**链接*/
    private String url;

    /**时间*/
    private Date time;

}

