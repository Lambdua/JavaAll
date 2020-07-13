package com.lt.creational.factorymethod.factory;

import com.lt.creational.factorymethod.*;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public  class DeilFactory extends PCFactory {


    @Override
   public Mouse getMouse() {
        return new DeilMouse();
    }


    @Override
    public KeyBoard getKeyBoard() {
     return new DeilKeyBoard();
    }
}
