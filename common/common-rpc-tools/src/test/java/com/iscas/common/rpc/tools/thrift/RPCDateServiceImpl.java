package com.iscas.common.rpc.tools.thrift;

import org.apache.thrift.TException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/21 12:40
 * @since jdk1.8
 */
public class RPCDateServiceImpl implements RPCDateService.Iface{
    @Override
    public String getDate(String userName) throws TException {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("今天是" + "yyyy年MM月dd日 E kk点mm分");
        String nowTime = simpleDateFormat.format(now);
        return "Hello " + userName + "\n" + nowTime;
    }
}
