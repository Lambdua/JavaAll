package com.lt.structural.adapter.instance2;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class Mouse implements USBInsert {
    @Override
    public void insertUse() {
        System.out.println("鼠标使用USb接口");
    }
}
