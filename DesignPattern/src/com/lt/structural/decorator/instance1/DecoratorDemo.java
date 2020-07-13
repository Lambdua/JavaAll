package com.lt.structural.decorator.instance1;

/**
 * @author liangtao
 * @Date 2019/11/25
 **/
public class DecoratorDemo {
    public static void main(String[] args) {

        //使用装饰器进行装饰shape
        Shape cicle = new Cicle();
        Shape rectangle = new Rectangle();
        ShapeDecorator cicleDecorator = new RedShapeDecorator(cicle);
        ShapeDecorator rectangleDecorator = new RedShapeDecorator(rectangle);


        cicleDecorator.draw();
        System.out.println("------------");

        rectangleDecorator.draw();

    }
}
