package com.iscas.log.appender;

import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.WarnStatus;
import com.iscas.log.LogBaseEntity;
import com.iscas.log.QueueEntity;
import com.iscas.log.StaticInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/2/28 21:16
 * @since jdk1.8
 */
public abstract class CustomAppender<E> extends ContextAwareBase implements Appender<E> {
    protected boolean started = false;
//    protected CustomConfig customConfig = null;
//
//    public CustomConfig getCustomConfig() {
//        return customConfig;
//    }
//
//    public void setCustomConfig(CustomConfig customConfig) {
//        this.customConfig = customConfig;
//    }

    // using a ThreadLocal instead of a boolean add 75 nanoseconds per
    // doAppend invocation. This is tolerable as doAppend takes at least a few microseconds
    // on a real appender
    /**
     * The guard prevents an appender from repeatedly calling its own doAppend
     * method.
     */
    private ThreadLocal<Boolean> guard = new ThreadLocal<Boolean>();

    /**
     * Appenders are named.
     */
    protected String name;

    private FilterAttachableImpl<E> fai = new FilterAttachableImpl<E>();

    @Override
    public String getName() {
        return name;
    }

    private int statusRepeatCount = 0;
    private int exceptionCount = 0;

    static final int ALLOWED_REPEATS = 3;

    @Override
    public void doAppend(E eventObject) {
        // WARNING: The guard check MUST be the first statement in the
        // doAppend() method.

        // prevent re-entry.
        if (Boolean.TRUE.equals(guard.get())) {
            return;
        }

        try {
            guard.set(Boolean.TRUE);

            if (!this.started) {
                if (statusRepeatCount++ < ALLOWED_REPEATS) {
                    addStatus(new WarnStatus("Attempted to append to non started appender [" + name + "].", this));
                }
                return;
            }

            if (getFilterChainDecision(eventObject) == FilterReply.DENY) {
                return;
            }

            // ok, we now invoke derived class' implementation of append
            this.append(eventObject);

        } catch (Exception e) {
            if (exceptionCount++ < ALLOWED_REPEATS) {
                addError("Appender [" + name + "] failed to append.", e);
            }
        } finally {
            guard.set(Boolean.FALSE);
        }
    }

    protected void append(E eventObject) {
        QueueEntity queueEntity = new QueueEntity();
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            //获取本机ip
            String ip=addr.getHostAddress().toString();
            String hostName = addr.getHostName();
            queueEntity.setAppIp(ip);
            queueEntity.setHostName(hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//        queueEntity.setCustomConfig(customConfig);
        queueEntity.setEvent((LoggingEvent) eventObject);
        try {
            StaticInfo.LOG_QUEUE.put(queueEntity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable runnable = () -> {
            saveToDb();
        };
        StaticInfo.logThreadPool.submit(runnable);

    }

    protected LogBaseEntity getBaseInfo() throws InterruptedException {
        try {
            LogBaseEntity logEntity = new LogBaseEntity();
            QueueEntity queueEntity = StaticInfo.LOG_QUEUE.take();
            LoggingEvent event = queueEntity.getEvent();

            //注入logEntity各个值
//            logEntity.setAppName(queueEntity.getAppName());
            logEntity.setHostName(queueEntity.getHostName());
            logEntity.setAppIp(queueEntity.getAppIp());

            logEntity.setLevel(event.getLevel().levelStr);

            //线程名称
            logEntity.setThreadName(event.getThreadName());
            //处理message
            String formattedMessage = event.getFormattedMessage();
            //处理调用链
            StackTraceElement[] callerData = event.getCallerData();
            if (callerData != null && callerData.length > 0) {
                StackTraceElement stackTraceElement = callerData[0];
                String packageAndClassName = stackTraceElement.getClassName();
                if (packageAndClassName != null) {
                    if (packageAndClassName.contains(".")) {
                        String packageName = packageAndClassName.substring(0, packageAndClassName.lastIndexOf("."));
                        String className = packageAndClassName.substring(packageAndClassName.lastIndexOf(".") + 1);
                        logEntity.setClassName(className);
                        logEntity.setPackageName(packageName);
                    }
                }
                String fileName = stackTraceElement.getFileName();
                int lineNumber = stackTraceElement.getLineNumber();
                String methodName = stackTraceElement.getMethodName();
                logEntity.setFileName(fileName);
                logEntity.setLineNumber(lineNumber);
                logEntity.setMethodName(methodName);

            }

            //解析各个自定义参数
            String msg = analyzeParam(logEntity.getParams(), formattedMessage);
//            //添加modelName
//            if (formattedMessage != null) {
//                if (formattedMessage.contains("@MODEL:")) {
//                    String modelName = formattedMessage.substring(formattedMessage.indexOf("@MODEL:") + "@MODEL:".length() + 2);
//                    logEntity.setModuleName(modelName);
//                    formattedMessage = formattedMessage.substring(0, formattedMessage.indexOf("@MODEL:"));
//                }
//            }
//            logEntity.setMessage(formattedMessage);

            //处理时间
            long timeStamp = event.getTimeStamp();
            Date date = new Date(timeStamp);
            logEntity.setTime(date);
            //处理异常栈信息
            IThrowableProxy throwableProxy = event.getThrowableProxy();
            if (throwableProxy != null) {
                logEntity.setThrowableFlag(true);
                //处理异常名称
                String throwableProxyClassName = throwableProxy.getClassName();
                logEntity.setThrowableClassName(throwableProxyClassName);
                //异常描述信息
                String throwableProxyMessage = throwableProxy.getMessage();
                logEntity.setThrowableMsg(throwableProxyMessage);
                //异常栈信息
                StringBuilder throwableStack = new StringBuilder();
                getStackInfo(throwableProxy, throwableStack, true);
                logEntity.setThrowableStackMsg(throwableStack.toString());
            }
            logEntity.setMessage(msg);
            return logEntity;
//            System.out.println(logEntity);
        } catch (InterruptedException e) {
           throw e;
        }
//        return null;
    }

    private String analyzeParam(Map map, String formatMsg) {
        String result = formatMsg;
        if (formatMsg.contains("@@") && formatMsg.contains(":")) {
            result = formatMsg.substring(0, formatMsg.indexOf("@@"));
            String prefix = formatMsg.substring(0, formatMsg.lastIndexOf("@@"));
            String suffix = formatMsg.substring(formatMsg.lastIndexOf("@@") + 2);
            if (suffix.contains(":")) {
                String key = suffix.substring(0, suffix.indexOf(":"));
                String data = suffix.substring(suffix.indexOf(":") + 1);
                map.put(key, data);

            }
            if (prefix != null && !"".equalsIgnoreCase(prefix)) {
                String s = analyzeParam(map, prefix);
            }
        }
        return result;
    }

    public void saveToDb() {
        try {
            LogBaseEntity baseInfo = getBaseInfo();
            //TODO 这里保存数据库
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getStackInfo(IThrowableProxy throwableProxy, StringBuilder throwableStack, boolean flag) {
        String throwableProxyClassName = throwableProxy.getClassName();
        String throwableProxyMessage = throwableProxy.getMessage();
        if (flag) {
            throwableStack.append(throwableProxyClassName).append(":").append(throwableProxyMessage);
        } else {
            throwableStack.append("\n").append("Caused by:").append(throwableProxyClassName).append(":").append(throwableProxyMessage);
        }
        StackTraceElementProxy[] stackTraceElementProxyArray = throwableProxy.getStackTraceElementProxyArray();
        if (stackTraceElementProxyArray != null) {
            for (StackTraceElementProxy stackTrace: stackTraceElementProxyArray) {
                throwableStack.append("\n").append("    ").append(stackTrace.getSTEAsString());
            }
        }
        IThrowableProxy cause = throwableProxy.getCause();
        if (cause != null) {
            getStackInfo(cause, throwableStack, false);
        }
    }

    /**
     * Set the name of this appender.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public void stop() {
        started = false;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[" + name + "]";
    }

    @Override
    public void addFilter(Filter<E> newFilter) {
        fai.addFilter(newFilter);
    }

    @Override
    public void clearAllFilters() {
        fai.clearAllFilters();
    }

    @Override
    public List<Filter<E>> getCopyOfAttachedFiltersList() {
        return fai.getCopyOfAttachedFiltersList();
    }

    @Override
    public FilterReply getFilterChainDecision(E event) {
        return fai.getFilterChainDecision(event);
    }
}
