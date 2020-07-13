package com.lt.creational.builder.instance1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/17
 **/
public class Meal {
    private List<Item> items=new ArrayList<>();
    public float getCost(){
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }
    public void addItem(Item item){
        items.add(item);
    }

    public void showItems(){
        for (Item item : items) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}
