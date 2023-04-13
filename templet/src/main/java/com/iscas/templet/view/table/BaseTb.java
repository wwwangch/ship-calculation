package com.iscas.templet.view.table;

import com.iscas.templet.exception.HeaderException;

/**
 * 处理TableRequestHeader的回调
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/24 21:43
 * @since jdk1.8
 */
public interface BaseTb {
    /**
     * 回调处理
     * @since jdk11
     * @date 2022/4/21
     * @param thrd 表头数据
     * @throws HeaderException 异常
     */
    default void headerCallback(TableHeaderResponseData thrd) throws HeaderException {
        //todo 啥都没做，重写时候可以编写表头后处理代码
    }
}
