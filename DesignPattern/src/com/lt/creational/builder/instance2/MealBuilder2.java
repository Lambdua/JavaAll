package com.lt.creational.builder.instance2;

import com.lt.creational.builder.instance1.Meal;
import com.lt.creational.builder.instance1.bueger.Burger;
import com.lt.creational.builder.instance1.drink.ColdDrink;

/**
 * @author liangtao
 * @Date 2019/9/18
 **/
public class MealBuilder2 {

    public Meal getMeal(String burgerType,String drinkType){
        Burger burger = BugerBuilder.buildBuger(burgerType);
        ColdDrink drink = DrinkBuilder.buildDrink(drinkType);
        Meal meal=new Meal();
        meal.addItem(burger);
        meal.addItem(drink);

        return meal;
    }

}
