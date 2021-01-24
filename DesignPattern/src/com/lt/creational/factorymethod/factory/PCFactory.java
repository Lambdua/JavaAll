package com.lt.creational.factorymethod.factory;

import com.lt.creational.factorymethod.model.KeyBoard;
import com.lt.creational.factorymethod.model.Mouse;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public interface PCFactory {
    Mouse getMouse();

    KeyBoard getKeyBoard();

}
