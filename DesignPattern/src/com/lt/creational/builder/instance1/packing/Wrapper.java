package com.lt.creational.builder.instance1.packing;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class Wrapper implements Packing {
   private String name="纸盒";

    public String getName() {
        return name;
    }

    @Override
    public String pack() {
        return "纸盒打包";
    }
}
