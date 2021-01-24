package com.lt.creational.factory.pizza;

import com.lt.creational.factory.pizza.model.CheesePizza;
import com.lt.creational.factory.pizza.model.GreekPizza;
import com.lt.creational.factory.pizza.model.Pizza;

import java.util.Scanner;

/**
 * @author liangtao
 * @description 订购披萨
 * @Date 2021/1/24
 **/
public class OrderPizza {
    Scanner scanner = new Scanner(System.in);

    public OrderPizza() {
        Pizza pizza;
        int orderType;
        while (true) {
            orderType = getPizzaType();
            if (orderType == 1) {
                pizza = new GreekPizza();
            } else if (orderType == 2) {
                pizza = new CheesePizza();
            } else break;
            pizza.prepare().bake(2).cut(3).box();
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
