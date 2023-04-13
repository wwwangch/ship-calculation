package com.iscas.templet.helper;

import com.iscas.templet.view.tree.TreeResponseData;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 树操作的帮助工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/16 18:14
 * @since jdk11
 */
@SuppressWarnings("unused")
public class TreeHelper {
    private TreeHelper() {
    }

    /**
     * 生成树
     *
     * @param data      数据
     * @param rootLabel 树的根上显示的名字
     * @param idKey     数据实体或map需要映射为树节点的id的属性key
     * @param pidKey    数据实体或map需要映射为树节点的pid的属性key
     * @param labelKey  数据实体或map需要映射为树节点的label的属性key
     * @return com.iscas.templet.view.tree.TreeResponseData<T>
     * @date 2022/6/16
     * @since jdk11
     */
    public static <T> TreeResponseData<T> createTree(List<T> data, String rootLabel, String idKey, String pidKey, String labelKey) {
        TreeResponseData<T> root = new TreeResponseData<>();
        root.setValue(null)
                .setLabel(rootLabel)
                .setId(null);
        Map<String, Field> fieldCache = new HashMap<>(4);
        if (!Objects.isNull(data)) {
            Map<Object, List<TreeResponseData<T>>> treeResponseDataMap = new HashMap<>(16);
            for (T datum : data) {
                Object id = CommonHelper.getVal(datum, idKey, fieldCache);
                Object labelObj = CommonHelper.getVal(datum, labelKey, fieldCache);
                String label = Objects.isNull(labelObj) ? null : String.valueOf(labelObj);
                TreeResponseData<T> treeResponseData = new TreeResponseData<T>()
                        .setId(id)
                        .setValue(id)
                        .setData(datum)
                        .setLabel(label);
                treeResponseDataMap.computeIfAbsent(CommonHelper.getVal(datum, pidKey, fieldCache), key -> new ArrayList<>()).add(treeResponseData);
            }

            // 生成树
            doCreateTree(root, treeResponseDataMap, 1);
        }
        return root;
    }

    private static <T> void doCreateTree(TreeResponseData<T> treeData, Map<Object, List<TreeResponseData<T>>> treeResponseDataMap, int level) {
        treeData.setLevel(level);
        Integer pid = (Integer) treeData.getId();
        List<TreeResponseData<T>> childs = treeResponseDataMap.get(pid);
        if (!Objects.isNull(childs)) {
            treeData.getChildren().addAll(childs);
            for (TreeResponseData<T> child : childs) {
                doCreateTree(child, treeResponseDataMap, level + 1);
            }
        }
    }

}
