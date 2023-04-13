package com.iscas.log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/1 10:09
 * @since jdk1.8
 */
public class LogBaseEntity {
    /**
     * 日志记录时间
     * */
    protected Date time;

    /**
     * 日志级别 支持（DEBUG/INFO/WARN/ERROR），
     * */
    protected String level;

    /**
     * 描述信息
     * */
    protected String message;

//    /**
//     * 应用名称
//     * */
//    protected String appName;
//
//    /**
//     * 模块名称
//     * */
//    protected String moduleName;

    /**
     * 包名
     * */
    protected String packageName;

    /**
     * 类名
     * */
    protected String className;

    /**
     * 函数名
     * */
    protected String methodName;

    /**
     * 调用日志的类所在的文件名
     * */
    protected String fileName;

    /**
     * 在多少行写的日志
     * */
    protected Integer lineNumber;

    /**
     * 应用服务IP
     * */
    protected String appIp;

    /**
     * 机器名称
     * */
    protected String hostName;

    /**
     * 线程名称
     * */
    protected String threadName;

    /**
     * 是否有异常信息 0 不是，1 是
     * */
    protected Boolean throwableFlag = false;

    /**
     * 异常名称
     * */
    protected String throwableClassName;

    /**
     * 异常描述信息
     * */
    protected String throwableMsg;

    /**
     * 异常栈信息
     * */
    protected String throwableStackMsg;

    /**
     * 自定义参数，Map结构
     * */
    protected Map params = new HashMap<>();

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

//    public String getAppName() {
//        return appName;
//    }
//
//    public void setAppName(String appName) {
//        this.appName = appName;
//    }
//
//    public String getModuleName() {
//        return moduleName;
//    }
//
//    public void setModuleName(String moduleName) {
//        this.moduleName = moduleName;
//    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Boolean getThrowableFlag() {
        return throwableFlag;
    }

    public void setThrowableFlag(Boolean throwableFlag) {
        this.throwableFlag = throwableFlag;
    }

    public String getThrowableClassName() {
        return throwableClassName;
    }

    public void setThrowableClassName(String throwableClassName) {
        this.throwableClassName = throwableClassName;
    }

    public String getThrowableMsg() {
        return throwableMsg;
    }

    public void setThrowableMsg(String throwableMsg) {
        this.throwableMsg = throwableMsg;
    }

    public String getThrowableStackMsg() {
        return throwableStackMsg;
    }

    public void setThrowableStackMsg(String throwableStackMsg) {
        this.throwableStackMsg = throwableStackMsg;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "time=" + time +
                ", level='" + level + '\'' +
                ", message='" + message + '\'' +
//                ", appName='" + appName + '\'' +
//                ", moduleName='" + moduleName + '\'' +
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
