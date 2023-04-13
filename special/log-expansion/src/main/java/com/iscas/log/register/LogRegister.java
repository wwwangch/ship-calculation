package com.iscas.log.register;

import com.iscas.logback.classic.param.LogParam;
import com.iscas.logback.classic.param.LogParams;
import com.iscas.logback.classic.param.ParamMemoryStore;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/1 10:12
 * @since jdk1.8
 */
public class LogRegister {

    public static void addStyle(LogParam ... logParamArgs) {
        if (logParamArgs != null) {
            LogParams logParams = new LogParams();
            for (LogParam logParam : logParamArgs) {
                logParams.getParams().add(logParam);
            }
            register(logParams);
        }
    }

    private static void register(LogParams logParams) {
        List<LogParams> paramList = ParamMemoryStore.paramList;
        //校验有没有重复
        boolean flag = false;
        for (LogParams params : paramList) {
            List<LogParam> paramsx = params.getParams();
            List<LogParam> paramsy = logParams.getParams();
            if (paramsx != null && paramsy != null && paramsx.size() == paramsy.size()) {
                flag = true;
                for (int i = 0; i < paramsx.size() ; i++) {
                    if (paramsx.get(i).getType() != paramsy.get(i).getType()) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    throw new RuntimeException("待注册的日志参数已经存在，不能重复注册!");
                }
            }
        }
        paramList.add(logParams);
    }
}
