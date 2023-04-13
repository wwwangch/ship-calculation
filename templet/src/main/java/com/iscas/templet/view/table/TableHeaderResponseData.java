package com.iscas.templet.view.table;

import com.iscas.templet.exception.Exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;
import java.util.List;

/**
 * @author zhuquanwen
 * @date 2017/12/25 17:04
 **/
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TableHeaderResponseData implements Serializable {
    /**
     * 表头列信息
     */
    protected List<TableField> cols;

    /**
     * 表的一些设置信息
     */
    protected TableSetting setting;

    public List<TableField> getCols() {
        return cols;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public TableHeaderResponseData clone() {

        ByteArrayOutputStream byteOut;
        ObjectOutputStream objOut = null;
        ByteArrayInputStream byteIn;
        ObjectInputStream objIn = null;
        try {
            byteOut = new ByteArrayOutputStream();
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(this);
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            objIn = new ObjectInputStream(byteIn);
            return (TableHeaderResponseData) objIn.readObject();
        } catch (IOException e) {
            throw Exceptions.runtimeException("Clone Object failed in IO.", e);
        } catch (ClassNotFoundException e) {
            throw Exceptions.runtimeException("Class not found.", e);
        } finally {
            try {
                if (objOut != null) {
                    objOut.close();
                }
                if (objIn != null) {
                    objIn.close();
                }
            } catch (IOException ignored) {
            }
        }
    }
}
