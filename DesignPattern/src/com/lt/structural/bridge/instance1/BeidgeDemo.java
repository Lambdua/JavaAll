package com.lt.structural.bridge.instance1;

/**
 * @author liangtao
 * @Date 2019/9/22
 **/
public class BeidgeDemo {
    public static void main(String[] args) {
        Blue blue=new Blue();

        Circle circle=new CircleBridge(blue);
        circle.draw();
    }
}
