package com.zjh.rocketmqtest.example.producer;

import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/11 12:27
 */
public class ScheduleProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TOPIC_GROUP);
        producer.setNamesrvAddr(Constants.NAME_SERVER);
        producer.start();

        Message message = new Message(Constants.TOPIC_NAME,"schedule","延时消息".getBytes());
        message.setDelayTimeLevel(3);
        SendResult send = producer.send(message);
        System.out.println(send);
    }
}
