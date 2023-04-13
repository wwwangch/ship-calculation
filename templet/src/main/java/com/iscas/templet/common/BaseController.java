package com.iscas.templet.common;

import com.iscas.templet.view.table.TableHeaderResponse;
import com.iscas.templet.view.table.TableResponse;
import com.iscas.templet.view.tree.TreeResponse;

/**
 * Controller基础控制类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused"})
public class BaseController {
    private static final ResponseEntity<Void> EMPTY_RES = new ResponseEntity<>();

    /**
     * 获取空的返回
     * @since jdk11
     * @date 2022/5/24
     * @return cn.ac.iscas.dmo.templet.common.ResponseEntity
     */
    public ResponseEntity<Void> emptyRes() {
        return BaseController.EMPTY_RES;
    }

    /**
     * 获取返回模板
     * @since jdk1.8
     * @date 2018/7/16
     * @return com.iscas.templet.common.ResponseEntity
     */
    public ResponseEntity getResponse() {
        return new ResponseEntity();
    }


    /**
     * 获取返回模板
     * @since jdk1.8
     * @date 2018/7/16
     * @param tClass 返回的泛型Class
     * @return com.iscas.templet.common.ResponseEntity
     */
    @SuppressWarnings("unused")
    public <T> ResponseEntity<T> getResponse(Class<T> tClass) {
        return new ResponseEntity<>();
    }

    /**
     * 获取树返回模板
     * @since jdk1.8
     * @date 2021/11/27
     * @return com.iscas.templet.common.TreeResponse
     */
    public TreeResponse getTreeResponse() {
        return new TreeResponse();
    }

    /**
     * 获取表头返回模板
     * @since jdk1.8
     * @date 2021/11/27
     * @return com.iscas.templet.common.TableHeaderResponse
     */
    public TableHeaderResponse getTableHeaderResponse() {
        return new TableHeaderResponse();
    }

    /**
     * 获取表返回模板
     * @since jdk1.8
     * @date 2021/11/27
     * @return com.iscas.templet.common.TableResponse
     */
    public TableResponse getTableResponse() {
        return new TableResponse();
    }

}
