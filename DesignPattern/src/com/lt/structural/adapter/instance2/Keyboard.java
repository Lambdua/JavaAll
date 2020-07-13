package com.lt.structural.adapter.instance2;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class Keyboard implements USBInsert{

    @Override
    public void insertUse() {
        System.out.println("键盘使用usb接口");
    }
}
