package com.lt.behavioral.commandPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/10/8
 **/
public class SellStock implements Order{
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    @Override
    public void execute() {
        abcStock.sell();
    }
}
