package com.zjh.rocketmqtest.example.producer;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Random;

/**
 * 同步——生产者
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 14:58
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TOPIC_GROUP);
        producer.setNamesrvAddr(Constants.NAME_SERVER);
        producer.start();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Message message = new Message(Constants.TOPIC_NAME,Constants.TEST_TAG,String.valueOf(i).getBytes());
            //设置消息属性，消息过滤机制测试
            message.putUserProperty("age",String.valueOf(random.nextInt(100)));
            SendResult send = producer.send(message);
            System.out.println("同步-生产者-消息发送age ===>" + message.getProperty("age") );
        }

        producer.shutdown();
}
}
