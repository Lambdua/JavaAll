package com.lt.creational.builder.instance1;

import com.lt.creational.builder.instance1.bueger.ChickenBurger;
import com.lt.creational.builder.instance1.bueger.VegBurger;
import com.lt.creational.builder.instance1.drink.Cofe;
import com.lt.creational.builder.instance1.drink.Coke;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class MealBuilder {
    public Meal createMeal1(){

        Meal meal=new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Cofe());

        return meal;
    }


    public Meal createMeal2(){
        Meal meal=new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }
}
