package com.lt.structural.decorator.instance1;

/**
 * @author liangtao
 * @Date 2019/11/25
 **/
public abstract class ShapeDecorator implements Shape{
    Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }


    @Override
    public void draw() {
        shape.draw();
    }
}
