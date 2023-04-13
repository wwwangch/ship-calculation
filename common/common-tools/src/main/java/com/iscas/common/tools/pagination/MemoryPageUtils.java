package com.iscas.common.tools.pagination;

import java.util.List;

/**
 * 内存分页工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/12 14:17
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class MemoryPageUtils {
    private MemoryPageUtils() {}

    /**
     * 内存内部对集合进行分页
     * @since jdk1.8
     * @date 2021/1/6
     * @param list 集合
     * @param pageNumber 页码
     * @param pageSize 每页的条数
     * @return java.util.List<T>
     */
    public static <T> List<T> getPageList(List<T> list, int pageNumber, int pageSize) {
        if (list == null) {
            return null;
        }
        assert pageNumber > 0;
        assert pageSize > 0;
        int start = pageSize * (pageNumber - 1);
        int end = pageSize * pageNumber;
        if (list.size() < end) {
            end = list.size();
        }
        return list.subList(start, end);
    }
}
