package com.lt.structural.flyweight.instance1;

/**
 * @author liangtao
 * @Date 2019/11/27
 **/
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int r;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Override
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color
                +", x : " + x +", y :" + y +", radius :" + r);
    }
}
