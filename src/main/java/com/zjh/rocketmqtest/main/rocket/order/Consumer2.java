package com.zjh.rocketmqtest.main.rocket.order;

import com.zjh.rocketmqtest.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/1/8 15:11
 */
public class Consumer2 {
    public static void a3() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constants.TEST_GROUP);

        consumer.setNamesrvAddr(Constants.NAME_SERVER);

        consumer.subscribe(Constants.TEST_TOPIC, "*");

        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            System.out.println(msgs);
            return ConsumeOrderlyStatus.SUCCESS;
        });

        consumer.start();

        System.out.printf("Consumer2 Started.%n");
    }

    public static void main(String[] args) throws Exception {
        a3();
    }
}
