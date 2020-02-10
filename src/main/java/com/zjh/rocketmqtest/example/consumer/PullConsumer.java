package com.zjh.rocketmqtest.example.consumer;

import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.PullStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 拉取-消费者
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 15:18
 */
public class PullConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer(Constants.TOPIC_GROUP);
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.start();

        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(Constants.TOPIC_NAME);
        for (MessageQueue messageQueue:messageQueues
             ) {
            long offset = consumer.fetchConsumeOffset(messageQueue, true);
            PullResult pull = consumer.pull(messageQueue, "ASYNC", offset, 1000);
            PullStatus pullStatus = pull.getPullStatus();
            if (pullStatus.equals(PullStatus.FOUND)){
                List<MessageExt> msgFoundList = pull.getMsgFoundList();
                for (MessageExt messageExt:msgFoundList
                     ) {
                    System.out.println(new String(messageExt.getBody()));
                }
            }
        }
    }
}
