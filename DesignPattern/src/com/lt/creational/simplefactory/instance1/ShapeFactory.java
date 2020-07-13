package com.lt.creational.simplefactory.instance1;


/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class ShapeFactory {
    public static Shape getShap(String detailName){
        if (detailName==null||detailName==""){
            return null;
        }
        if (detailName.trim().toLowerCase()=="circle"){
            return new Circle();
        }
        if (detailName.trim().toLowerCase()=="rectangle"){
            return new Rectangle();
        }
        if (detailName.trim().toLowerCase()=="square"){
            return new Square();
        }
        return null;
    }
}
