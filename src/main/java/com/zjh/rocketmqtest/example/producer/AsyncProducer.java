package com.zjh.rocketmqtest.example.producer;

import com.zjh.rocketmqtest.util.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 异步-生产者
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 16:04
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer(Constants.TOPIC_GROUP);
        producer.setNamesrvAddr(Constants.NAME_SERVER);
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);


        int messageCount = 100;//发送消息数量
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);//定义条件

        for (int i = 0; i < 100; i++) {
            Message message = new Message(Constants.TOPIC_NAME,"ASYNC",(i + "异步").getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("成功发送 ：" + sendResult );
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println(e.getMessage());
                }
            });
        }

        countDownLatch.await(5, TimeUnit.SECONDS);
        producer.shutdown();
    }
}
