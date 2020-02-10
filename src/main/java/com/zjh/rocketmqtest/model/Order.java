package com.zjh.rocketmqtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2020/2/10 16:43
 */
@Data
@AllArgsConstructor
public class Order {
    private long orderId;
    private String status;

    public static List<Order> generateOrderList(){
        List<Order> orderList = new LinkedList<>();
        Order order1 = new Order(1,"创建");
        Order order2 = new Order(1,"支付");
        Order order7 = new Order(3,"创建");
        Order order3 = new Order(1,"完成");
        Order order4 = new Order(2,"创建");
        Order order5 = new Order(2,"支付");
        Order order8 = new Order(3,"支付");
        Order order6 = new Order(2,"完成");
        Order order9 = new Order(3,"完成");
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        return orderList;
    }
}
