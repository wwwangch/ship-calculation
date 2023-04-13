package com.iscas.biz.mp.util;

import com.iscas.biz.mp.enhancer.mapper.DynamicMapper;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidDataException;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/20 22:28
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
public class ValidatePropDistinctUtils {
    private ValidatePropDistinctUtils() {
    }

    public static Map validate(DynamicMapper dynamicMapper, String tableName, String colName, Object val) {
        String sql = "select count(*) as c from %s where %s = '%s'";
        sql = String.format(sql, tableName, colName, val.toString());
        return dynamicMapper.selectOneBySql(sql);
    }

    public static void validateFromMysql(DynamicMapper dynamicMapper, String tableName, String colName, Object val) throws ValidDataException {
        Map map = validate(dynamicMapper, tableName, colName, val);
        //noinspection AlibabaUndefineMagicConstant
        if (!Objects.equals(map.get("c"), 0L)) {
            Exceptions.formatValidDataException("表[{}]的列[{}]的值：[{}]不能重复", tableName, colName, val);
        }
    }
}
