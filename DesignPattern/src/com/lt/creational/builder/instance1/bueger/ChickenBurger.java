package com.lt.creational.builder.instance1.bueger;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class ChickenBurger extends Burger {

    @Override
    public String name() {
       return "鸡肉汉堡";
    }

    @Override
    public float price() {
        return 30;
    }
}
