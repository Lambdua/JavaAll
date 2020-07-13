package com.lt.creational.factorymethod;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class HPMouse implements Mouse {
    @Override
    public void createMouse() {
        System.out.println("HPMouse");
    }
}
