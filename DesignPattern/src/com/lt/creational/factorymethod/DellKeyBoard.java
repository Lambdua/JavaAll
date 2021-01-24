package com.lt.creational.factorymethod;

import com.lt.creational.factorymethod.model.KeyBoard;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class DellKeyBoard implements KeyBoard {
    @Override
    public void createKeyBoard() {
        System.out.println("DellKeyBoard");
    }
}
