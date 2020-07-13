package com.lt.creational.builder.instance1.packing;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class Bottle implements Packing {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String pack() {
        return "瓶子打包";
    }

}
