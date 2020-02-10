package com.zjh.rocketmqtest.service;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2019/12/28 10:47
 */
@Service
@RocketMQMessageListener(consumerGroup = "test_consumer", topic = "TopicTest",selectorExpression = "tag1")
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println(message);
    }
}
