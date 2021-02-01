package com.lt.creational.prototype;

/**
 * @author liangtao
 * @Date 2021/2/1
 **/
public class Circle extends Shape {
    public int radius;

    public Circle() {
    }

    public Circle(Circle source) {
        super(source);
        this.radius = source.radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Circle) || !super.equals(obj)) return false;
        Circle shape2 = (Circle) obj;
        return shape2.radius == radius;

    }
}
