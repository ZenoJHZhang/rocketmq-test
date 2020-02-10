package com.zjh.rocketmqtest.controller;

import com.zjh.rocketmqtest.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2019/12/28 10:28
 */
@RestController
public class AppController {

    private final Producer producer;

    @Autowired
    public AppController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("test")
    public void test(){
        producer.makeMessage("123");
    }
}
