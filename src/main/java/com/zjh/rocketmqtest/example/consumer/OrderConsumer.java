package com.zjh.rocketmqtest.example.consumer;

import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 16:58
 */
public class OrderConsumer {
    public static void main (String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constants.TOPIC_GROUP);
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(Constants.TOPIC_NAME,"order");
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            context.setAutoCommit(true);
            for (MessageExt msg : msgs) {
                // 可以看到每个queue有唯一的consume线程来消费, 订单对每个queue(分区)有序
                System.out.println("consumeThread=" + Thread.currentThread().getName() + "queueId=" + msg.getQueueId() + ", content:" + new String(msg.getBody()));
            }

            try {
                //模拟业务逻辑处理中...
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });

        consumer.start();
    }
}
