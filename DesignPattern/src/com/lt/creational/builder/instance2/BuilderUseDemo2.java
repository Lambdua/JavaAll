package com.lt.creational.builder.instance2;

import com.lt.creational.builder.instance1.Meal;

/**
 * @author liangtao
 * @Date 2019/9/18
 **/
public class BuilderUseDemo2 {

    public static void main(String[] args) {
        MealBuilder2 builder2=new MealBuilder2();
        Meal meal = builder2.getMeal("chicken", "cofe");
        meal.showItems();
    }
}
