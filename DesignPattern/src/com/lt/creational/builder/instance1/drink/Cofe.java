package com.lt.creational.builder.instance1.drink;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class Cofe extends ColdDrink {
    @Override
    public String name() {
       return "咖啡";
    }

    @Override
    public float price() {
        return 12;
    }
}
