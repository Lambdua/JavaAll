package com.lt.structural.adapter.instance2;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class Demo {
    public static void main(String[] args) {
        USBInsert usbInsert=new USBAdapter(new lineNet());
        usbInsert.insertUse();
    }
}
