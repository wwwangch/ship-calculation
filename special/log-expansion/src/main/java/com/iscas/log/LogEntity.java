package com.iscas.log;

/**
 * 日志记录的实体
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/3/2 16:06
 * @since jdk1.8
 */
public class LogEntity extends LogBaseEntity {

    /**
     * 应用名称
     * */
    protected String appName;

    /**
     * 模块名称
     * */
    protected String moduleName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "time=" + time +
                ", level='" + level + '\'' +
                ", message='" + message + '\'' +
                ", appName='" + appName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", lineNumber=" + lineNumber +
                ", appIp='" + appIp + '\'' +
                ", hostName='" + hostName + '\'' +
                ", threadName='" + threadName + '\'' +
                ", throwableFlag=" + throwableFlag +
                ", throwableClassName='" + throwableClassName + '\'' +
                ", throwableMsg='" + throwableMsg + '\'' +
                ", throwableStackMsg='" + throwableStackMsg + '\'' +
                '}';
    }
}
