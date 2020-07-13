package com.lt.creational.builder.instance1;

import com.lt.creational.builder.instance1.packing.Packing;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public interface Item {
    String name();
    Packing packing();
    float price();
}
