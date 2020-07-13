package com.lt.creational.factorymethod.factory;

import com.lt.creational.factorymethod.HPKeyBoard;
import com.lt.creational.factorymethod.HPMouse;
import com.lt.creational.factorymethod.KeyBoard;
import com.lt.creational.factorymethod.Mouse;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public  class HPFactory extends PCFactory {
    @Override
    public Mouse getMouse() {
        return new HPMouse();
    }

    @Override
    public KeyBoard getKeyBoard() {

        return new HPKeyBoard();
    }
}
