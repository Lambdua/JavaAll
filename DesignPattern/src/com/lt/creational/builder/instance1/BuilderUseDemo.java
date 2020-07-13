package com.lt.creational.builder.instance1;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class BuilderUseDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder=new MealBuilder();
        Meal meal1 = mealBuilder.createMeal1();
        System.out.println(meal1.getCost());
        meal1.showItems();
    }
}
