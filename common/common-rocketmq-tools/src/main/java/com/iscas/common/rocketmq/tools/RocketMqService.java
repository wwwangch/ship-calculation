package com.iscas.common.rocketmq.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * rocketmq操作工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/22 17:53
 * @since jdk1.8
 */
@Slf4j
public class RocketMqService {
    private String namesrvAddr;
    private String producerGroup;
    private String consumerGroup;
    private String topic;
    private String tags;
    private int timeout;


    /**
     * 初始化工具类
     * @param namesrvAddr   rocketmq服务地址，多个用;号隔开
     * @param topic         主题
     * @param tags           标识,多个用||号隔开
     * @param timeout       发送超时时长
     * */
    public RocketMqService(String namesrvAddr, String topic, String tags, int timeout) {
        this.namesrvAddr = namesrvAddr;
        this.topic = topic;
        this.tags = tags;
        this.timeout = timeout;
    }

    /**
     * 创建生产者对象
     * @return
     */
    private DefaultMQProducer createMQProducer(){
        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        //发送消息超时时长设置
        producer.setSendMsgTimeout(timeout);
        //发送失败，重试三次
        //producer.setRetryTimesWhenSendFailed(3);
        return producer;
    }

    /**
     * 发送消息
     * @param producerGroup 生产组
     * @param msgList       消息集合
     * @return
     * @throws MQClientException
     * @throws Exception
     */
    public List<SendResult> send(String producerGroup, List<String> msgList) throws MQClientException, Exception {
        return this.send(producerGroup, msgList, false, null, 0);
    }

    /**
     * 发送MQ数组消息
     * @param producerGroup     生产组
     * @param msgList           消息集合
     * @param isBatch           是否批处理
     * @param orderId           顺序编号
     * @param delayTimeLevel    消息等级(延后发送)，为0则立即发送，等级从1到10
     * @return
     * @throws MQClientException
     * @throws Exception
     */
    public List<SendResult> send(String producerGroup, List<String> msgList, boolean isBatch, Integer orderId, int delayTimeLevel) throws MQClientException, Exception {
        this.producerGroup = producerGroup;
        if (msgList == null || msgList.size()<=0){
            throw new RuntimeException("send rocket topic<" + topic + "> mq message to List<String> is null ...");
        }
        DefaultMQProducer producer = this.createMQProducer();
        List<Message> messageList = new ArrayList<Message>();
        //保证本批消息发送到topic同一个索引位queue，推荐使用具有业务意义的编号，如：订单号，保证同一个订单业务发送到同一个队列中
//        int order = RandomUtils.nextInt(1000, 10000);
        //封装到message对象中
        for (String msg : msgList){
            //delayTimeLevel：定时消息等级，指定的时间后才能传递，不支持任意精度时间。按level等级划分，默认从level=1开始,如果level=0则不延时，具体如messageDelayLevel值
            //messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            Message message = new Message(topic, tags, msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setDelayTimeLevel(delayTimeLevel);
            messageList.add(message);
            log.info("send message body:{}", message.toString());
        }
        try {
            producer.start();
            //是否批量发送，批量发送消息可提高传递小消息的性能
            if (isBatch) {
                return this.sendMqAlikeList(producer, messageList, orderId);
            }else {
                return this.sendMqDisaffinityList(producer, messageList, orderId);
            }
        }  catch (MQClientException mqce) {
            log.error("send rocket topic<" + topic + "> rocketMq client error:", mqce);
            throw mqce;
        }  catch (Exception e) {
            log.error("send rocket topic<" + topic + "> rocketMq send msg error:", e);
            throw e;
        } finally {
            //关闭生产者
            producer.shutdown();
        }
    }

    /**
     * 快速或顺序发送消息
     * @param producer
     * @param messageList
     * @param orderId
     * @return
     * @throws Exception
     */
    private List<SendResult> sendMqDisaffinityList(DefaultMQProducer producer, List<Message> messageList, Integer orderId) throws Exception{
        List<SendResult> sendResultList = new ArrayList<SendResult>();
        for (Message m : messageList){
            try {
                if (orderId != null){
                    //顺序发送，适用场景：订单的下单、待支付、已支付、待打包、已发货等，一定要保证消息顺序消费
                    //对所有队例总数取模计算，获得索引位上的队列
                    sendResultList.add(producer.send(m, (list, message, o) -> list.get((Integer) o % list.size()), orderId));
                }else {
                    sendResultList.add(producer.send(m));
                }
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (MQBrokerException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sendResultList;
    }

    /**
     * 批量或批量顺序发送消息
     * 使用限制:同一批次的消息应具有：相同的主题，相同的waitStoreMsgOK，并且不支持计划。一批消息的总大小不得超过1M
     * @param producer
     * @param messageList
     * @param orderId 顺序编号
     * @return
     * @throws Exception
     */
    private List<SendResult> sendMqAlikeList(DefaultMQProducer producer, List<Message> messageList, Integer orderId) throws Exception{
        if (orderId != null){
            //顺序发送，适用场景：订单的下单、待支付、已支付、待打包、已发货等，一定要保证消息顺序消费
            //注意：如果新的topic，此处获取topic下的队列会出错，请先建立topic
            List<MessageQueue> list = producer.fetchPublishMessageQueues(topic);
            //对所有队例总数取模计算，获得索引位上的队列
            return Arrays.asList(producer.send(messageList, list.get(orderId % list.size())));
        }else {
            return Arrays.asList(producer.send(messageList));
        }
    }


    /**
     * 发送MQ消息(单个消息可使用此方法)
     * @param producerGroup     生产组
     * @param msg               消息
     * @return
     * @throws Exception
     */
    public SendResult send(String producerGroup,String msg) throws Exception{
        return this.send(producerGroup, Arrays.asList(msg)).get(0);
    }

    /**
     * 创建消费对象
     * @param isBroadcasting 是否为广播消费模式
     * @return
     * @throws MQClientException
     */
    private DefaultMQPushConsumer createMqConsumer(boolean isBroadcasting) throws MQClientException{
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        //订阅PushTopic下Tag为push的消息
        consumer.subscribe(topic, tags);
        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        //如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置消费模式为广播模式，即每个消费者都能接收到所有消息
        if (isBroadcasting) {
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }
        return consumer;
    }


    /**
     * 监听MQ队列，拉取MQ消息并进行消费
     * @param consumerGroup     消费组
     * @param headerInterface   回调方法
     * @throws MQClientException
     * @throws Exception
     */
    public void pull(String consumerGroup, HeaderInterface headerInterface) throws MQClientException, Exception {
        this.pull(consumerGroup, headerInterface, false, false);
    }
    /**
     * 监听MQ队列，拉取MQ消息并进行消费(支持广播消费模式)
     * @param consumerGroup     消费组
     * @param headerInterface   回调方法
     * @param isBroadcasting    是否为广播消费模式
     * @param isOrder           是否为顺序消息
     * @throws MQClientException
     * @throws Exception
     */
    public void pull(String consumerGroup, HeaderInterface headerInterface, boolean isBroadcasting, boolean isOrder) throws MQClientException, Exception {
        this.consumerGroup = consumerGroup;
        //消费者的组名
        DefaultMQPushConsumer consumer = this.createMqConsumer(isBroadcasting);
        try {
            if (isOrder){
                //顺序消费
                consumer.registerMessageListener(this. setOrderlyConsumeMessage(headerInterface));
            }else {
                //普通消费
                consumer.registerMessageListener(this.setConcurrentlyConsumeMessage(headerInterface));
            }
            consumer.start();
        } catch (Exception e) {
            log.error("rocketMq pull msg error:", e);
            throw e;
        } finally {
            this.addShutdownHook(consumer);
        }
    }

    /**
     * 消费端默认消息
     * @param headerInterface
     * @return
     */
    private MessageListenerConcurrently setConcurrentlyConsumeMessage(HeaderInterface headerInterface){
        return (List<MessageExt> list, ConsumeConcurrentlyContext context) -> {
            try {
                for (MessageExt message : list) {
                    headerInterface.execute(message);
                }
            } catch (Exception e) {
                log.error("rocketMq consumer pull msg error:", e);
                //稍后再试
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            //消费成功
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        };
    }

    /**
     * 消费端顺序消息
     * @param headerInterface
     * @return
     */
    private MessageListenerOrderly setOrderlyConsumeMessage(HeaderInterface headerInterface){
        return (list, context) -> {
            try {
                for (MessageExt message : list) {
                    headerInterface.execute(message);
                }
            } catch (Exception e) {
                log.error("rocketMq consumer pull msg error:", e);
                //稍后再试
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
            //消费成功
            return ConsumeOrderlyStatus.SUCCESS;
        };
    }

    /**
     * 函数式回调接口
     */
    @FunctionalInterface
    interface HeaderInterface{
        void execute(MessageExt message) throws IOException;
    }

    /**
     * 注册虚拟机器关机钩子事件
     * @param consumer
     */
    private void addShutdownHook(DefaultMQPushConsumer consumer){
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("jvm shutdown hook: close rocketMq consumer server ...");
            consumer.shutdown();
        }));
    }


}
