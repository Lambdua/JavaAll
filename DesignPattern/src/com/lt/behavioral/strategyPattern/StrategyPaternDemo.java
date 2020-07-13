package com.lt.behavioral.strategyPattern;

import com.lt.behavioral.strategyPattern.instance1.Context;
import com.lt.behavioral.strategyPattern.instance1.OperationAdd;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class StrategyPaternDemo {
    public static void main(String[] args) {
        OperationAdd add=new OperationAdd();
        System.out.println(new Context(add).doStrategy(1, 2));
    }
}
