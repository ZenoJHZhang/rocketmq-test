package com.zjh.rocketmqtest.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2019/12/16 14:40
 */
@RestController
public class TestController {
    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public TestController(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @GetMapping(value = "makeProducer")
    public void makeProducer() throws Exception {

    }
}
