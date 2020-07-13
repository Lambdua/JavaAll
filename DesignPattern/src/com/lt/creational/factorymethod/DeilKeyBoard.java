package com.lt.creational.factorymethod;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class DeilKeyBoard implements  KeyBoard{
    @Override
    public void createKeyBoard() {
        System.out.println("DeilKeyBoard");
    }
}
