package com.lt.creational.builder.instance1.bueger;

import com.lt.creational.builder.instance1.Item;
import com.lt.creational.builder.instance1.packing.Packing;
import com.lt.creational.builder.instance1.packing.Wrapper;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

}
