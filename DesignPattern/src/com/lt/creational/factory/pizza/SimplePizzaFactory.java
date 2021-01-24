package com.lt.creational.factory.pizza;

import com.lt.creational.factory.pizza.model.CheesePizza;
import com.lt.creational.factory.pizza.model.GreekPizza;
import com.lt.creational.factory.pizza.model.Pizza;

/**
 * @author liangtao
 * @description 披萨简单工厂类
 * @Date 2021/1/24
 **/
public class SimplePizzaFactory {
    public static Pizza createPizza(int orderType) {
        Pizza pizza = null;
        if (orderType == 1) {
            pizza = new GreekPizza();
        } else if (orderType == 2) {
            pizza = new CheesePizza();
            return new CheesePizza();
        }
        if (pizza != null) pizza.prepare().bake(2).cut(3).box();
        return pizza;
    }
}
