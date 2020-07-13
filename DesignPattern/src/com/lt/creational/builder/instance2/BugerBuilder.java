package com.lt.creational.builder.instance2;

import com.lt.creational.builder.instance1.bueger.Burger;
import com.lt.creational.builder.instance1.bueger.ChickenBurger;
import com.lt.creational.builder.instance1.bueger.VegBurger;

/**
 * @author liangtao
 * @Date 2019/9/18
 **/
public class BugerBuilder {
    public static Burger buildBuger(String type){
        if (type==null||type.equals("")){
            return null;
        }
        if (type.trim().toLowerCase().equals("chicken")){
            return new ChickenBurger();
        }else if (type.trim().toLowerCase().equals("veg")){
            return new VegBurger();
        }
        return null;
    }
}
