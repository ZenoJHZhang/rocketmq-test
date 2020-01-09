package com.zjh.rocketmqtest.main.rocket;

import com.zjh.rocketmqtest.constants.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/1/8 10:11
 */
public class OrderExample {
    public static void a1() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TEST_GROUP);
        producer.setNamesrvAddr(Constants.NAME_SERVER);
        producer.start();

        int orderId = 1;

        Message message = new Message(Constants.TEST_TOPIC, "order", "orderExample".getBytes());

        for (int i = 0; i < 100 ; i++) {
            int id = i % 2;
            if (id == 0){
                id = 1;
            }
            else {
                id = 15;
            }
            int finalId = id;
            SendResult send = producer.send(message, (mqs, msg, arg) -> {
                return mqs.get(finalId);
            }, orderId);

            if (send.getSendStatus().equals(SendStatus.SEND_OK)) {
                Message message2 = new Message(Constants.TEST_TOPIC, "order", "orderExample2".getBytes());
                int finalId1 = id;
                producer.send(message2, (mqs, msg, arg) -> {
                    return mqs.get(finalId1);
                }, orderId);
            }
        }

        producer.shutdown();
    }


}
