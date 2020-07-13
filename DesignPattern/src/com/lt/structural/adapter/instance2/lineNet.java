package com.lt.structural.adapter.instance2;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class lineNet implements NetInsert {
    @Override
    public void useNetInsert() {
        System.out.println("网线使用网线接口");
    }
}
