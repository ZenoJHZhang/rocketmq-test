package com.zjh.rocketmqtest.main.rocket;

import com.zjh.rocketmqtest.constants.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2019/12/5 11:09
 */
public class SimpleExample {
    public static void main(String[] args) throws Exception {
        a1();
//        a2();
    }

    /**
     * 生产者
     * 1.注册nameSrv
     * 2.start();
     * 3.send Message
     * 4.shutdown
     */
    public static void a1() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TEST_GROUP);
        producer.setNamesrvAddr(Constants.NAME_SERVER);
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message message = new Message(Constants.TEST_TOPIC, "tagA", "ZJH".getBytes());
            producer.send(message);
        }

        producer.shutdown();
    }

    /**
     * 消费者
     * 1.注册nameSrv
     * 2.连接Topic
     * 3.注册消息监听listener
     * 4.start()
     *
     * @throws Exception
     */
    public static void a2() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(Constants.TEST_GROUP);
        consumer.setNamesrvAddr(Constants.NAME_SERVER);
        consumer.subscribe(Constants.TEST_TOPIC, "tagA");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(t -> System.out.println(new String(t.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
