package com.lt.creational.factory.pizza;

import java.util.Scanner;

/**
 * @author liangtao
 * @description 订购披萨
 * @Date 2021/1/24
 **/
public class OrderPizza {
    Scanner scanner = new Scanner(System.in);

    public OrderPizza() {
        while (true) {
            SimplePizzaFactory.createPizza(getPizzaType());
        }
    }


    /**
     * 不停获取披萨订单
     *
     * @return java.lang.String
     * @author liangtao
     * @date 2021/1/24
     **/
    private int getPizzaType() {
        int input = scanner.nextInt();
        return input;
    }
}
