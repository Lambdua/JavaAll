package com.lt.creational.factorymethod.factory;

import com.lt.creational.factorymethod.KeyBoard;
import com.lt.creational.factorymethod.Mouse;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public abstract class PCFactory {
    public abstract Mouse getMouse();
    public abstract KeyBoard getKeyBoard();

}
