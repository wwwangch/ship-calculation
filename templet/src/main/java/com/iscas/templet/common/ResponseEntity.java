package com.iscas.templet.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @date 2017/12/25 16:41
 **/
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
public class ResponseEntity<T> implements Serializable {

    /**
     * http状态码
     */
    protected Integer status = 200;
    /**
     * 给予用户提示的信息
     */
    protected String message;

    /**
     * 出现错误的详细描述(调试)
     */
    protected String desc;

    /**
     * 异常堆栈信息
     * */
    protected String stackTrace;

    /**
     * 返回值
     */
    protected T value;

    /**
     * 访问URL
     */
    protected String requestURL;

    /**
     * 当前接口访问耗时
     * */
    protected long tookInMillis;

    /**
     * 过时的参数，未来会删除
     * */
    @Deprecated
    protected int total;

    public ResponseEntity(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ResponseEntity() {
        super();
        this.message = "操作成功";
    }

    public ResponseEntity(String message){
        super();
        this.message = message;
    }

    public static <T> ResponseEntity<T> ok(T value) {
        return Objects.isNull(value) ? new ResponseEntity<>() : new ResponseEntity<T>().setValue(value);
    }

    public static ResponseEntity<Void> error(int status, String message, Exception e) {
        ResponseEntity<Void> res = new ResponseEntity<>(status, message);
        res.setDesc(e.getMessage());
        return res;
    }

}
