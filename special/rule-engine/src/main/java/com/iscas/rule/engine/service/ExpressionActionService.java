package com.iscas.rule.engine.service;

import com.googlecode.aviator.AviatorEvaluator;
import com.iscas.rule.engine.anno.REAutowired;
import com.iscas.rule.engine.anno.REComponent;
import com.iscas.rule.engine.exception.RuleException;
import com.iscas.rule.engine.model.*;
import com.iscas.rule.engine.util.ConfigUtils;
import com.iscas.rule.engine.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * 执行表达式
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/24 16:07
 * @since jdk1.8
 */
@REComponent
@Slf4j
@SuppressWarnings({"JavadocDeclaration", "unchecked", "rawtypes", "unused"})
public class ExpressionActionService {
    /**
     * 存储当前获取到的硬件的各个数值
     * */
    private final Map<BaseDataBean, DataBean> currentDatas = new ConcurrentHashMap<>();

    /**
     * 执行规则的线程池
     * */
    private ThreadPoolExecutor threadPoolExecutor = null;

    @REAutowired
    private MemoryQueueService queueService;
    @REAutowired
    private MyExpressionService expressionService;

    private final Map<String, Long> alarmTime0Map = new ConcurrentHashMap<>();
    private final Map<String, Long> alarmTime1Map = new ConcurrentHashMap<>();
    private final Map<String, Long> alarmTime2Map = new ConcurrentHashMap<>();
    private final Map<String, Long> alarmTime3Map = new ConcurrentHashMap<>();

    private final Map<String, Integer> level0CountMap = new ConcurrentHashMap<>();
    private final Map<String, Integer> level1CountMap = new ConcurrentHashMap<>(  );
    private final Map<String, Integer> level2CountMap = new ConcurrentHashMap<>();
    private final Map<String, Integer> level3CountMap = new ConcurrentHashMap<>();

    public ExpressionActionService() {

    }

    /**
     * 执行表达式
     * */
    public boolean action(String expression, Map<String, Object> env) {
        com.googlecode.aviator.Expression compiledExp = AviatorEvaluator.compile(expression);
        // 执行表达式
        return (boolean) compiledExp.execute(env);
    }

    /**
     * 从队列获取参数并执行
     * */
    @SuppressWarnings("InfiniteLoopStatement")
    public void exec() throws RuleException {
        //初始化线程池
        threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(Integer.parseInt(ConfigUtils.readProp("thread.queue.size"))),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                DataBean dataBean = queueService.pull();
                if (dataBean == null) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //存入当前变量缓存
                    BaseDataBean baseDataBean = new BaseDataBean(dataBean.getName(), dataBean.getPname(), dataBean.getType());
                    currentDatas.put(baseDataBean, dataBean);
                    //执行这个变量
                    execOneData(dataBean);
                }
            }
        });
    }

    /**
     * 执行某个变量的规则
     * */
    public void execOneData(DataBean dataBean) {
        Runnable runnable = () -> {
            BaseDataBean baseDataBean = new BaseDataBean(dataBean.getName(), dataBean.getPname(), dataBean.getType());
            Collection<Expression> expressions = expressionService.getExpressionByKey(dataBean.getName());
            if (CollectionUtils.isNotEmpty(expressions)) {
                for (Expression expression : expressions) {
                    //构建变量参数
                    Map<String, Object> env = new HashMap<>(16);
                    for (Map.Entry<String, String> entry : expression.getParams().entrySet()) {
                        String value = entry.getValue();
                        String key = entry.getKey();
                        if (Objects.equals(key, "p0")) {
                            env.put("time", dataBean.getTime());
                        }
                        BaseDataBean bdb = new BaseDataBean(value, dataBean.getPname(), dataBean.getType());
                        DataBean dBean = currentDatas.get(bdb);
                        Double aDouble = dBean.getData();
                        //noinspection ConstantConditions
                        if (aDouble == null) {
                            //某个参数没有值，停止遍历
                            break;
                        }
                        env.put(entry.getKey(), aDouble);
                    }
                    execExpression(expression, env);
                }
            }
        };
        threadPoolExecutor.execute(runnable);
    }

    @SuppressWarnings("AlibabaMethodTooLong")
    private void execExpression(Expression expression, Map<String, Object> env) {
        //状态型表达式
        String expressionLevel0 = expression.getExpressionLevel0();
        //轻度门限表达式
        String expressionLevel1 = expression.getExpressionLevel1();
        //中度门限表达式
        String expressionLevel2 = expression.getExpressionLevel2();
        //中度门限表达式
        String expressionLevel3 = expression.getExpressionLevel3();
        //报警次数
        int alarmCount = expression.getAlarmCount();
        //报警后再次检测的间隔
        int checkInterval = expression.getCheckInterval();
        //所属单机v
        String device = expression.getDevice();
        //包ID
        String packageId = expression.getPackageId();
        //参数代号
        String paramCode = expression.getParamCode();
        //参数代号名
        String paramName = expression.getParamName();
        //卫星ID
        String sateId = expression.getSateId();
        //子系统
        String subsystem = expression.getSubsystem();
        //参数
        LinkedHashMap<String, String> params = expression.getParams();
        //规则
        Regulation regulation = expression.getRegulation();

        if (regulation == null) {
            return;
        }

        try {
            Double p0Val = (Double) env.get("p0");
            LogUtils.debug(log, String.format("value=%f, expLevel1=%s, expLevel2=%s, expLevel3=%s", p0Val, expressionLevel1, expressionLevel2, expressionLevel3));
            String satelliteTime = ((Date)env.get("time")).getTime() + "";
            //调用表达式
            Long alarmTime0 = alarmTime0Map.get(paramCode);
            long millis = 1000L;
            if (alarmTime0 == null || alarmTime0 == -1 || (System.currentTimeMillis() - alarmTime0) > checkInterval * millis) {
                if (null != expressionLevel0) {
                    boolean action = this.action(expressionLevel0, env);
                    LogUtils.debug(log, "状态型表达式:" + expressionLevel0 + "结果为:" + action);
                    if (action) {
                        synchronized(paramCode.intern()){
                            int count = level0CountMap.get(paramCode) == null ? 0 : level0CountMap.get(paramCode);
                            level0CountMap.put(paramCode, count + 1);
                        }

                        LogUtils.debug(log, "当前记录参数值为："+ paramCode +"----时间为："+satelliteTime +"---当前故障数据出现次数为："+level0CountMap.get(paramCode));

                        if (level0CountMap.get(paramCode) >= alarmCount) {
                            synchronized(paramCode.intern()){
                                level0CountMap.put(paramCode, 0);
                                alarmTime0Map.put(paramCode, System.currentTimeMillis());
                            }
                            //报警
                            Map map = new HashMap(16);
                            map.put("type", "level0");
                            map.put("code", paramCode);
                            map.put("value", p0Val);
                            map.put("satelliteTime", satelliteTime);
                            map.put("device", device);
                            map.put("packageId", packageId);
                            map.put("paramName", paramName);
                            map.put("subsystem", subsystem);
                            map.put("sateId", sateId);
                            List<NormalVal> normalRegualations = regulation.getNormalRegualations();
                            if (normalRegualations.size() > 0) {
                                String reason = "";
                                StringBuilder express = new StringBuilder();
                                for (NormalVal normalRegualation : normalRegualations) {
                                    String comparisonCode = normalRegualation.getComparisonCode();
                                    Double value = normalRegualation.getValue();
                                    express.append(comparisonCode).append(value).append("&&");
                                }
                                if (express.length() > 1) {
                                    express = new StringBuilder(express.substring(0, express.length() - 2));
                                }
                                map.put("threshold", express.toString());
                                map.put("reason", String.format("数值[%f]超出正常值[%s]的范围", p0Val, express));

                            }
                            send(map);
                        }

                    } else {
                        synchronized(paramCode.intern()) {
                            level0CountMap.put(paramCode, 0);
                        }
                    }
                }
            }
            Long alarmTime1 = alarmTime1Map.get(paramCode);
            if (alarmTime1 == null || alarmTime1 == -1 || (System.currentTimeMillis() - alarmTime1) > checkInterval * millis) {
                if (null != expressionLevel1) {
                    boolean action = this.action(expressionLevel1, env);
                    LogUtils.debug(log, "表达式:" + expressionLevel1 + "结果为:" + action);
                    if (action) {
                        synchronized(paramCode.intern()) {
                            int count = level1CountMap.get(paramCode) == null ? 0 :  level1CountMap.get(paramCode);
                            level1CountMap.put(paramCode, count + 1);
                        }

                        LogUtils.debug(log, "当前记录参数值为："+ paramCode+"----时间为："+satelliteTime +"---当前故障数据出现次数为："+level1CountMap.get(paramCode));

                        if (level1CountMap.get(paramCode) >= alarmCount) {
                            synchronized(paramCode.intern()) {
                                level1CountMap.put(paramCode, 0);
                                alarmTime1Map.put(paramCode, System.currentTimeMillis());
                            }
                            //报警
                            Map map = new HashMap(16);
                            map.put("type", "level1");
                            map.put("code", paramCode);
                            map.put("value", p0Val);
                            map.put("satelliteTime", satelliteTime);
                            map.put("device", device);
                            map.put("packageId", packageId);
                            map.put("paramName", paramName);
                            map.put("subsystem", subsystem);
                            map.put("sateId", sateId);
                            System.out.println(map);
                            send(map);
                        }
                    } else {
                        synchronized(paramCode.intern()) {
                            level1CountMap.put(paramCode, 0);
                        }
                    }
                }
            }

            Long alarmTime2 = alarmTime2Map.get(paramCode);
            if (alarmTime2 == null || alarmTime2 == -1 || (System.currentTimeMillis() - alarmTime2) > checkInterval * millis) {
                if (null != expressionLevel2) {
                    boolean action = this.action(expressionLevel2, env);
                    LogUtils.debug(log, "表达式:" + expressionLevel2 + "结果为:" + action);
                    if (action) {
                        synchronized(paramCode.intern()) {
                            int count = level2CountMap.get(paramCode) == null ? 0 : level2CountMap.get(paramCode);
                            level2CountMap.put(paramCode, count + 1);
                        }
                        LogUtils.debug(log, "当前记录参数值为："+ paramCode +"----时间为："+satelliteTime +"---当前故障数据出现次数为："+level2CountMap.get(paramCode));
                        if (level2CountMap.get(paramCode) >= alarmCount) {
                            synchronized(paramCode.intern()) {
                                level2CountMap.put(paramCode, 0);
                                alarmTime2Map.put(paramCode, System.currentTimeMillis());
                            }
                            //报警
                            Map map = new HashMap(16);
                            map.put("type", "level2");
                            map.put("code", paramCode);
                            map.put("value", p0Val);
                            map.put("satelliteTime", satelliteTime);
                            map.put("device", device);
                            map.put("packageId", packageId);
                            map.put("paramName", paramName);
                            map.put("subsystem", subsystem);
                            map.put("sateId", sateId);
                            send(map);
                        }
                    } else {
                        synchronized(paramCode.intern()) {
                            level2CountMap.put(paramCode, 0);
                        }
                    }
                }
            }

            Long alarmTime3 = alarmTime3Map.get(paramCode);
            if (alarmTime3 == null || alarmTime3 == -1 || (System.currentTimeMillis() - alarmTime3) > checkInterval * millis) {
                if (null != (expressionLevel3)) {
                    boolean action = this.action(expressionLevel3, env);
                    LogUtils.debug(log, "表达式:" + expressionLevel3 + "结果为:" + action);
                    if (action) {
                        synchronized(paramCode.intern()) {
                            int count = level3CountMap.get(paramCode) == null ? 0 : level3CountMap.get(paramCode);
                            level3CountMap.put(paramCode, count + 1);
                        }
                        LogUtils.debug(log, "当前记录参数值为："+ paramCode+"----时间为："+satelliteTime +"---当前故障数据出现次数为："+level3CountMap.get(paramCode));
                        if (level3CountMap.get(paramCode) >= alarmCount) {
                            synchronized(paramCode.intern()) {
                                level3CountMap.put(paramCode, 0);
                                alarmTime3Map.put(paramCode, System.currentTimeMillis());
                            }
                            //报警
                            Map map = new HashMap(16);
                            map.put("type", "level3");
                            map.put("code", paramCode);
                            map.put("value", p0Val);
                            map.put("satelliteTime", satelliteTime);
                            map.put("device", device);
                            map.put("packageId", packageId);
                            map.put("paramName", paramName);
                            map.put("subsystem", subsystem);
                            map.put("sateId", sateId);

                            send(map);
                        }
                    } else {
                        //level3CountMap.put(p0IndicatorId, new AtomicInteger(0));
                        synchronized(paramCode.intern()) {
                            level3CountMap.put(paramCode, 0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**推送警告*/
    private void send(Map map) {
        //TODO 推送警告
    }

}
