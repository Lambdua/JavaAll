package com.lt.creational.factorymethod;

import com.lt.creational.factorymethod.factory.DeilFactory;
import com.lt.creational.factorymethod.factory.HPFactory;
import com.lt.creational.factorymethod.factory.PCFactory;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class PCUseDemo {
    public static void main(String[] args) {
        PCFactory hpFactory = new HPFactory();
        hpFactory.getKeyBoard().createKeyBoard();
        hpFactory.getMouse().createMouse();

        PCFactory deilFactroy=new DeilFactory();
        deilFactroy.getMouse().createMouse();
        deilFactroy.getKeyBoard().createKeyBoard();

    }
}
