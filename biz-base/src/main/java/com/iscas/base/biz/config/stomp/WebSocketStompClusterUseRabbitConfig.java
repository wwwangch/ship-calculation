package com.iscas.base.biz.config.stomp;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


/**
 * @author zhuquanwen
 */
@SuppressWarnings({"AlibabaRemoveCommentedCode", "unused", "CommentedOutCode"})
public class WebSocketStompClusterUseRabbitConfig {

    @Value("${rabbitmq.virtual-host:/}")
    private String virtualHost;
    @Value("${rabbitmq.relay-host}")
    private String relayHost;
    @Value("${rabbitmq.user}")
    private String user;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.heartbeatSendInterval}")
    private long heartbeatSendInterval;
    @Value("${rabbitmq.heartbeatReceiveInterval}")
    private long heartbeatReceiveInterval;
    @Value("${rabbitmq.stomp.port}")
    private int stompPort;
    @Value("${rabbitmq.amqp.port}")
    private int amqpPort;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(relayHost, amqpPort);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        // 发送消息回调,必须要设置
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            System.err.println("ConfirmCallback:     "+"相关数据："+correlationData);
//            System.err.println("ConfirmCallback:     "+"确认情况："+ack);
//            System.err.println("ConfirmCallback:     "+"原因："+cause);
        });

        rabbitTemplate.setReturnsCallback(returned -> {
//            System.err.println("ReturnCallback:     "+"消息："+returned.getMessage());
//            System.err.println("ReturnCallback:     "+"回应码："+returned.getReplyCode());
//            System.err.println("ReturnCallback:     "+"回应信息："+returned.getReplyText());
//            System.err.println("ReturnCallback:     "+"交换机："+returned.getExchange());
//            System.err.println("ReturnCallback:     "+"路由键："+returned.getRoutingKey());
        });

        return rabbitTemplate;
    }




//    /**
//     * 接受消息的监听，这个监听会接受消息队列topicQueue的消息
//     * 针对消费者配置
//     * @return
//     */
//    @Bean
//    public SimpleMessageListenerContainer messageContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//        container.setQueues(topicQueue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
//                byte[] body = message.getBody();
//                String msg = new String(body);
//                System.err.println("rabbitmq收到消息 : " +msg);
//                Boolean sendToWebsocket = wsService.sendMsg(msg);
//                if (sendToWebsocket){
//                    System.err.println("消息处理成功！ 已经推送到websocket！");
//                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
//                }
//            }
//        });
//        return container;
//    }

}
