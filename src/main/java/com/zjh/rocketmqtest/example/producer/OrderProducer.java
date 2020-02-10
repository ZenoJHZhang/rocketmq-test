package com.zjh.rocketmqtest.example.producer;

import com.alibaba.fastjson.JSON;
import com.zjh.rocketmqtest.model.Order;
import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 16:37
 */
public class OrderProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TOPIC_GROUP);
        producer.setNamesrvAddr(Constants.NAME_SERVER);
        producer.start();

        List<Order> orderList = Order.generateOrderList();
        for (Order order : orderList) {
            Message message = new Message(Constants.TOPIC_NAME, "order", JSON.toJSONString(order).getBytes());
            SendResult send = producer.send(message, (mqs, msg, arg) -> {
                long id = (long) arg;
                return mqs.get((int) id % mqs.size());
            }, order.getOrderId());
            SendStatus sendStatus = send.getSendStatus();
            if (sendStatus.equals(SendStatus.SEND_OK)){
                System.out.println("发送成功" + send.getMsgId());
            }
        }

        producer.shutdown();
    }
}
