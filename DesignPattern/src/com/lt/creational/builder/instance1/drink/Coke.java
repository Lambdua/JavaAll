package com.lt.creational.builder.instance1.drink;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class Coke extends ColdDrink {

    @Override
    public String name() {
       return "coke";
    }

    @Override
    public float price() {
        return 99;
    }
}
