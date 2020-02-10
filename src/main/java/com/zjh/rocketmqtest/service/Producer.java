package com.zjh.rocketmqtest.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2019/12/28 10:16
 */
@Service
@Slf4j
public class Producer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    public void makeMessage(String message) {
        for (int i = 0; i < 100; i++) {
            rocketMQTemplate.syncSendOrderly("TopicTest:tag1",message,"1");
        }
    }

}
