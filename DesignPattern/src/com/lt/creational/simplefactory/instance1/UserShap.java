package com.lt.creational.simplefactory.instance1;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class UserShap {
    public static void main(String[] args) {
        //使用工厂
        Shape circle = ShapeFactory.getShap("circle");
        circle.draw();
        Shape rectangle = ShapeFactory.getShap("rectangle");
        rectangle.draw();




        System.out.println();
    }
}
