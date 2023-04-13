package com.iscas.base.biz.desensitization;

import lombok.Getter;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/5 8:44
 * @since jdk1.8
 */
@Getter
public enum PrivacyTypeEnum {

    /**
     * 中文名
     * */
    CHINESE_NAME,

    /**
     * 固话
     * */
    FIXED_PHONE,

    /**
     * 手机号
     * */
     MOBILE_PHONE,

    /**
     * 住址
     * */
    ADDRESS,

    /**
     * 密码
     * */
    PASSWORD,

    /**
     * 银行卡号
     * */
    BANK_CARD,

    /**
     * 邮箱
     * */
    EMAIL,

    /**
     * 身份证
     * */
    ID_CARD,

    /**
     * 其他类型
     * */
    OTHER;
}
