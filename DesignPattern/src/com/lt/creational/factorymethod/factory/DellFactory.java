package com.lt.creational.factorymethod.factory;

import com.lt.creational.factorymethod.*;
import com.lt.creational.factorymethod.model.KeyBoard;
import com.lt.creational.factorymethod.model.Mouse;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public  class DellFactory implements PCFactory {

    @Override
   public Mouse getMouse() {
        return new DellMouse();
    }


    @Override
    public KeyBoard getKeyBoard() {
     return new DellKeyBoard();
    }
}
