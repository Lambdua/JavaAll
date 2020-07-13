package com.lt.creational.builder.instance2;

import com.lt.creational.builder.instance1.drink.Cofe;
import com.lt.creational.builder.instance1.drink.Coke;
import com.lt.creational.builder.instance1.drink.ColdDrink;

/**
 * @author liangtao
 * @Date 2019/9/18
 **/
public class DrinkBuilder {
    public static ColdDrink buildDrink(String type){
        if (type==null||type.equals("")){
            return null;
        }
        if (type.trim().toLowerCase().equals("cofe")){
            return new Cofe();
        }else if (type.trim().toLowerCase().equals("coke")){
            return new Coke();
        }
        return null;
    }
}
