package com.lt.behavioral.strategyPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class Context {
    Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public int doStrategy(int n1,int n2){
        return strategy.doOperation(n1, n2);
    }
}

