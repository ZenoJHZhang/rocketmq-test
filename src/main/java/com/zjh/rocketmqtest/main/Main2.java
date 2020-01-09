package com.zjh.rocketmqtest.main;

/**
 * 类的说明
 *
 * @author 张江浩
 * @version 1.00
 * @date 2019/12/24 14:45
 */
public class Main2 {
    String today = "2019-12-24";

    public void sayHello(String message){
        System.out.println(message);
    }

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        main2.sayHello("123");
    }
}
