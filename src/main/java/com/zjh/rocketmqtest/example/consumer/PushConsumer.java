package com.zjh.rocketmqtest.example.consumer;

import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 推送-消费者
 * 需要注册监听器
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 15:51
 */
public class PushConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constants.TOPIC_GROUP);
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.subscribe(Constants.TOPIC_NAME,"*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt messageExt:msgs
                 ) {
                System.out.println(messageExt.toString());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();


    }
}
