package com.lt.behavioral.commandPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/10/8
 **/
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.buy();
    }
}
