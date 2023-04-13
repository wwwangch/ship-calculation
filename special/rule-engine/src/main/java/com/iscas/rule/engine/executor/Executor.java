//package com.iscas.rule.engine.executor;
//
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.iscas.rule.engine.exception.RuleException;
//import com.iscas.rule.engine.model.Expression;
//import com.iscas.rule.engine.model.Threshold;
//import com.iscas.rule.engine.util.RuleEngineJsonUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
///**
// * 规则引擎执行器
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/1/21 14:47
// * @since jdk1.8
// */
//@Slf4j
//public class Executor {
//    private Executor() {}
//
////    @Deprecated
////    private static void setTestExpression() {
////        Expression expression = new Expression();
////        expression.setExpressionLevel1("(1==1) && (math.abs(p1)>3.8 && p1<1000) && ((p0 >=-100.0 && p0 <= 3000.0) && (((p0 >= -3.0 && p0 <= 6.0) || (p0 >= 200.0 && p0 <= 500.0)) ))");
////        expression.setExpressionLevel2("(1==1) && (math.abs(p1)>3.8 && p1<1000) && ((p0 >=-100.0 && p0 <= 3000.0) && (((p0 >= -32.0 && p0 <= -3.0) || (p0 >= 500.0 && p0 <= 800.0)) ))");
////        expression.setExpressionLevel3("(1==1) && (math.abs(p1)>3.8 && p1<1000) && ((p0 >=-100.0 && p0 <= 3000.0) && (((p0 >= -100.0 && p0 <= -32.0) || (p0 >= 800.0 && p0 <= 3000.0)) ))");
////        expression.setAlarmCount(2);
////        expression.setParamCode("M001CG091");
////        Regulation regulation = new Regulation();
////        regulation.setAlarmCount(2);
////
////        expression.setRegulation(regulation);
////        expression.setCheckInterval(600);
////        LinkedHashMap map = new LinkedHashMap();
////        map.put("p0", "M001CG091");
////        map.put("p1", "M001CG092");
////        expression.setParams(map);
////        System.setProperty("pattern", JsonUtils.toJson(Arrays.asList(expression)));
////    }
//
//    @Deprecated
//    private static void setTestExpression() {
//        Expression expression = RuleEngineJsonUtils.fromJson("{\"sateId\":\"R1FA7xC61F\",\"subsystem\":\"分系统2\",\"device\":\"所属单机2\",\"paramCode\":\"M001CG092\",\"paramName\":\"遥测12\",\"alarmCount\":0,\"checkInterval\":1,\"params\":{\"p1\":\"M001CG092\"},\"flowFlag\":true,\"flowExpresssions\":[{\"level\":\"error\",\"expression\":\"!((1 == 1) && ((p1)<100))\",\"alarmCount\":2,\"desc\":\"sdas\"}], \"sateName\":\"m21\"}", Expression.class);
//        System.setProperty("pattern", RuleEngineJsonUtils.toJson(Arrays.asList(expression)));
//    }
//
//    /**
//     * 开始执行任务
//     * */
//    public static void exec(String namesrv, String producerUrl) throws RuleException {
//
//        //todo 注入一个测试表达式，兼容测试
//        setTestExpression();
//        String expressionStr = System.getenv("pattern");
//        if (StringUtils.isEmpty(expressionStr)) {
//            expressionStr = System.getProperty("pattern");
//        }
//        //String expressionStr = System.getProperty("pattern");
//        List<Expression> expressions = RuleEngineJsonUtils.fromJson(expressionStr, new TypeReference<List<Expression>>() {
//        });
//        if (expressions == null || expressions.isEmpty()) {
//            throw new RuleException("无法从环境变量中取到规则表达式");
//        }
//        boolean flowFlag = expressions.get(0).isFlowFlag();
//        if (flowFlag) {
//            //知识流程
//            RocketMqFlowService rocketMqFlowService = new RocketMqFlowService();
//            rocketMqFlowService.startConsumer(namesrv,producerUrl);
//        } else {
//            //非知识的
//            RocketMqService rocketMqService = new RocketMqService();
//            rocketMqService.startConsumer(namesrv, producerUrl);
//        }
//
//
//    }
//
//    /**
//     * 插入报警原因和门限
//     * */
//    private static void insertThresholdStr(Map map, List<List<Threshold>> thresholds, int type, double p0Val, Threshold effective) {
//        if (CollectionUtils.isNotEmpty(thresholds)) {
//            for (List<Threshold> thresholdList : thresholds) {
//                if (CollectionUtils.isNotEmpty(thresholdList)) {
//                    Threshold threshold1 = null;
//                    Threshold threshold2 = null;
//                    Threshold threshold3 = null;
//                    for (Threshold threshold : thresholdList) {
//                        if (threshold.getType() == 1) {
//                            threshold1 = threshold;
//                        } else if (threshold.getType() == 2) {
//                            threshold2 = threshold;
//                        } else if (threshold.getType() == 3) {
//                            threshold3 = threshold;
//                        }
//                    }
//                    for (Threshold threshold : thresholdList) {
//                        int type1 = threshold.getType();
//                        if (type == type1 && threshold.isEnabled()) {
//                            Double high = threshold.getHigh();
//                            Double low = threshold.getLow();
//                            Double high2 = 0.0;
//                            Double low2 = 0.0;
//                            if (high != null && low != null) {
//                                String degree = "轻度";
//                                if (type == 1) {
//                                    degree = "轻度";
//                                    high2 = threshold2.getHigh();
//                                    low2 = threshold1.getHigh();
//                                    if (!(p0Val <= high2 && p0Val >= low2)) {
//                                        high2 = threshold1.getLow();
//                                        low2 = threshold2.getLow();
//                                    }
//                                } else if (type == 2) {
//                                    degree = "中度";
//                                    high2 = threshold3.getHigh();
//                                    low2 = threshold2.getHigh();
//                                    if (!(p0Val <= high2 && p0Val >= low2)) {
//                                        high2 = threshold2.getLow();
//                                        low2 = threshold3.getLow();
//                                    }
//                                } else if (type == 3) {
//                                    degree = "重度";
//                                    high2 = effective.getHigh();
//                                    low2 = threshold3.getHigh();
//                                    if (!(p0Val <= high2 && p0Val >= low2)) {
//                                        high2 = threshold3.getLow();
//                                        low2 = effective.getLow();
//                                    }
//                                }
//                                String reason = String.format("数值[%f]超出%s门限范围[%f~%f]", p0Val, degree, low2, high2);
//                                String thresholdStr = low + "~" + high;
//                                map.put("reason", reason);
//                                map.put("threshold", thresholdStr);
//                                return;
//                            }
//
//                        }
//                    }
//                }
//
//            }
//        }
//
//    }
//
//    /*
//    private static void flowAction(Expression expression, RocketMqService rocketMqService,
//                                   String alarmPrefix, String alarmTag, String topic) {
//        //报警次数
////        int alarmCount = expression.getAlarmCount();
//        //报警后再次检测的间隔
//        int checkInterval = expression.getCheckInterval();
//        //所属单机v
//        String device = expression.getDevice();
////        //包ID
//        String packageId = expression.getPackageId();
////        //参数代号
//        String paramCode = expression.getParamCode();
////        //参数代号名
//        String paramName = expression.getParamName();
////        //卫星ID
//        String sateId = expression.getSateId();
////        //子系统
//        String subsystem = expression.getSubsystem();
//        //参数
//        LinkedHashMap<String, String> params = expression.getParams();
//
//        //状态型表达式
////        String expressionLevel0 = expression.getExpressionLevel0();
////        //轻度门限表达式
////        String expressionLevel1 = expression.getExpressionLevel1();
////        //中度门限表达式
////        String expressionLevel2 = expression.getExpressionLevel2();
////        //中度门限表达式
////        String expressionLevel3 = expression.getExpressionLevel3();
////        //规则
////        Regulation regulation = expression.getRegulation();
//
//        List<FlowExpression> flowExpresssions = expression.getFlowExpresssions();
//
//        //这个code应该和上面的参数代号一样的
//        String code = paramCode;
//
//        List<Long> alaramTime = new ArrayList<>(flowExpresssions.size());
//        List<Integer> levelCount = new ArrayList<>(flowExpresssions.size());
//        for (int i = 0; i < flowExpresssions.size(); i++) {
//            alaramTime.add(-1L);
//            levelCount.add(0);
//        }
//
//        while (true) {
//            try {
//                for (int i = 0; i < flowExpresssions.size(); i++) {
//                    FlowExpression flowExpression = flowExpresssions.get(i);
//                    String expression1 = flowExpression.getExpression();
//                    int alarmCount = flowExpression.getAlarmCount();
//                    String desc = flowExpression.getDesc();
//                    String level = flowExpression.getLevel();
//                    String paramNamex = flowExpression.getParamName();
//                    String plan = flowExpression.getPlan();
//                    String status = flowExpression.getStatus();
//
//                    Map<String, Object> env = new HashMap<>();
//
////                    BlockingQueue<Map> blockingQueue = TestClass.blockingQueues.get(code);
////                    if (blockingQueue == null) {
////                        throw new RuntimeException("测试队列错误");
////                    }
////                    Map p0map = blockingQueue.take();
//                    byte[] bytes = null;
//                    if (!rocketMqService.isEmpty(topic)) {
//                        bytes = rocketMqService.get(topic, code, 3000, RandomStringUtils.random(5));
////                            TestRocketClass.countMap.put(code, TestRocketClass.countMap.get(code) - 1);
//                    }
//                    String s = new String(bytes, "utf-8");
//                    Map p0map = JsonUtils.fromJson(s, Map.class);
//
//                    Double p0Val = (Double) p0map.get("val");
//                    env.put("p0", p0Val);
//                    for (Map.Entry<String, String> entry : params.entrySet()) {
//                        String key = entry.getKey();
//                        String value = entry.getValue();
//
//
////                        BlockingQueue<Map> blockingQueuex = TestClass.blockingQueues.get(value);
////                        if (blockingQueuex == null) {
////                            throw new RuntimeException("测试队列错误");
////                        }
////                        Map pxMap = blockingQueuex.take();
//
//                        byte[] bytesx = null;
//                        if (!rocketMqService.isEmpty(topic)) {
//                            bytesx = rocketMqService.get(topic, value, 3000, RandomStringUtils.random(5));
//                            TestRocketClass.countMap.put(value, TestRocketClass.countMap.get(value) - 1);
//                        }
//                        String s2 = new String(bytesx, "utf-8");
//                        Map pxMap = JsonUtils.fromJson(s2, Map.class);
//
//                        Double pxVal = (Double) pxMap.get("val");
//                        env.put(key, pxVal);
//                    }
//
//                    //调用表达式
//                    if (alaramTime.get(i) == -1 || (System.currentTimeMillis() - alaramTime.get(i)) > checkInterval * 1000) {
//                        if (StringUtils.isNotEmpty(expression1)) {
//                            boolean action = ExpressionAction.action(expression1, env);
//                            System.out.println("状态型表达式:" + expression1 + "结果为:" + action);
//                            log.info("状态型表达式:" + expression1 + "结果为:" + action);
//                            if (action) {
//                                levelCount.set(i, levelCount.get(i) + 1);
//                                if (levelCount.get(i) >= alarmCount) {
//                                    levelCount.set(i, 0);
//                                    alaramTime.set(i, System.currentTimeMillis());
//                                    //报警
//                                    Map map = new HashMap();
//
//                                    if (Objects.equals("error", level)) {
//                                        level = "level1";
//                                    } else if (Objects.equals("warn-error", level)) {
//                                        level = "level2";
//                                    } else if (Objects.equals("full-error", level)) {
//                                        level = "level3";
//                                    }
//
//                                    map.put("type", level);
//                                    map.put("code", code);
//                                    map.put("value", p0Val);
//                                    map.put("device", device);
//                                    map.put("packageId", packageId);
//                                    map.put("paramName", paramName);
//                                    map.put("subsystem", subsystem);
//                                    map.put("sateId", sateId);
//                                    map.put("desc", desc);
//                                    map.put("alarmParamName", paramNamex);
//                                    map.put("plan", plan);
//                                    map.put("status", status);
//
//
//                                    map.put("threshold", expression1);
//
//                                    System.out.println(map);
////                                    TestClass.alarmQueue.put(map);
//                                    rocketMqService.put(UUID.randomUUID().toString(),
//                                            JsonUtils.toJson(map).getBytes("utf-8"),
//                                            alarmPrefix + topic, alarmTag);
//                                }
//
//                            } else {
//                                levelCount.set(i, 0);
//                            }
//                        }
//                    }
//
////                    if (!rocketMqService.isEmpty(alarmPrefix + topic)) {
////                        byte[] bytes1 = rocketMqService.get(alarmPrefix + topic, alarmTag, 1);
////                        if (bytes1 != null) {
////                            System.out.println(new String(bytes1, "utf-8"));
////                        }
////                    }
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("出错了!!");
//                continue;
//            }
//        }
//    }
//
//     */
//
////    public static void main(String[] args) {
////        Type type = RocketMqService.class;
////        Annotation[] annotations1 = type.getClass().getAnnotations();
////        Annotation[] annotations = type.getClass().getAnnotations();
////        for (Annotation annotation : annotations) {
////            Class<? extends Annotation> aClass = annotation.annotationType();
////            System.out.println(aClass);
////        }
////    }
//
//}
