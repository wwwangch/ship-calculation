package com.iscas.templet.view.chart.base;

import com.iscas.templet.exception.Exceptions;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuquanwen
 * @date 2018/4/11 11:33
 **/
@Getter
@Setter
@Deprecated
public class ChartResponseData implements Serializable,Cloneable {
    protected ChartTitle title = new ChartTitle();
    protected ChartAxis xAxis = new ChartAxis();
    protected ChartAxis yAxis = new ChartAxis();
    protected List<ChartSeries> series = new ArrayList<>();

    protected Legend legend = new Legend();
    protected Object others;

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public ChartResponseData clone() {
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
            return (ChartResponseData) objIn.readObject();
        } catch (IOException e) {
            throw Exceptions.runtimeException("Clone Object failed in IO.",e);
        } catch (ClassNotFoundException e) {
            throw Exceptions.runtimeException("Class not found.",e);
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
