package com.lt.behavioral.commandPattern.instance1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/10/8
 **/
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
