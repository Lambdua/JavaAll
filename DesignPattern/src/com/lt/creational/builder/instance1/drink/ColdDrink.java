package com.lt.creational.builder.instance1.drink;

import com.lt.creational.builder.instance1.Item;
import com.lt.creational.builder.instance1.packing.Bottle;
import com.lt.creational.builder.instance1.packing.Packing;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}
