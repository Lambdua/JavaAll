package com.lt.structural.flyweight.instance1;


import java.util.Random;

/**
 * @author liangtao
 * @Date 2019/11/27
 **/
public class FlyweightPatternDemo {
    private static final String colors[] = {
            "Red", "Green", "Blue", "White", "Black"
    };


    public static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    public static void main(String[] args) {
/*
        for (int i = 0; i < 10; i++) {
            String color = getRandomColor();
            Circle circle = (Circle) ShapeFactory.getCircle(color);
            circle.setR(1);
            circle.setX(2);
            circle.setY(3);
            circle.draw();
        }
*/


        Random random=new Random(12);
        for (int i = 0; i < 10; i++) {

//            System.out.println(random.nextInt(2));
            System.out.println((int)(Math.random()*colors.length));
        }
    }
}
