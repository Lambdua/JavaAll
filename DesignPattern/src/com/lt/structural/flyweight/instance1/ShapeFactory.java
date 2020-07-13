package com.lt.structural.flyweight.instance1;

import java.util.HashMap;

/**
 * @author liangtao
 * @Date 2019/11/27
 **/
public class ShapeFactory {
    private static final HashMap<String,Shape> circleMap=new HashMap<>();

    private ShapeFactory(){

    }
    enum InstanceEnum{
        INSTANCE;
        private ShapeFactory shapeFactoryInstance=new ShapeFactory();
        public ShapeFactory getShapeFactoryInstance(){
            return INSTANCE.shapeFactoryInstance;
        }
    }

    public static ShapeFactory getInstance(){
        return InstanceEnum.INSTANCE.getShapeFactoryInstance();
    }

    public  Shape getCircle(String color){
        if (circleMap.containsKey(color)){
            return circleMap.get(color);
        }else {
            Circle circle = new Circle(color);
            circleMap.put(color,circle);
            return circle;
        }
    }


}
