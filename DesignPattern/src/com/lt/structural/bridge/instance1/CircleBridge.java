package com.lt.structural.bridge.instance1;

/**
 * @author liangtao
 * @Date 2019/9/22
 **/
public class CircleBridge  implements Circle{
    Red red;
    Blue blue;

    public CircleBridge(Blue blue) {
        this.blue = blue;
    }

    public CircleBridge(Red red) {
        this.red = red;
    }

    @Override
    public void draw() {
        if (red!=null){
            red.draw();
        }else {
            blue.draw();
        }
    }
}
