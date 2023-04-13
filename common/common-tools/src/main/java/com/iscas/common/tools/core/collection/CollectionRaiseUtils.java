package com.iscas.common.tools.core.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 集合扩展工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/19 14:45
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
public class CollectionRaiseUtils {
    private CollectionRaiseUtils() {}

    /**
     * 判断集合是不是为空，如果集合里全都是null也判断
     * @since jdk1.8
     * @date 2021/1/6
     * @param collection 集合
     * @return boolean
     */
    public static boolean isEmpty(Collection collection) {
        boolean flag = collection == null || collection.size() <= 0;
        if (!flag) {
            Iterator iterator = collection.iterator();
            boolean notnullFlag = false;
            while (iterator.hasNext()) {
                if (iterator.next() != null) {
                    notnullFlag = true;
                    break;
                }
            }
            flag = !notnullFlag;
        }
        return flag;
    }

    /**
     * 判断集合是不是不为空，如果集合里全都是null也判断
     * @since jdk1.8
     * @date 2021/1/6
     * @param collection 集合
     * @return boolean
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 将一个集合按照一个条件分割
     * @since jdk1.8
     * @date 2021/8/23
     * @param list 待分割的集合
     * @param predicate 分割条件
     * @return java.util.List<java.util.List>
     */
    public static List<List> split(List list, Predicate<Object> predicate) {
        List<List> result = new ArrayList<>();
        List tmpList = new ArrayList();
        for (Object o : list) {
            if (predicate.test(o)) {
                if (tmpList.size() >0) {
                    result.add(tmpList);
                    tmpList = new ArrayList();
                }
            } else {
                //noinspection unchecked
                tmpList.add(o);
            }
        }
        if (tmpList.size() > 0) {
            result.add(tmpList);
        }
        return result;
    }


}
