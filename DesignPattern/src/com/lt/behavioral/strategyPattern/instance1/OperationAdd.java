package com.lt.behavioral.strategyPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class OperationAdd implements Strategy {
    @Override
    public int  doOperation(int n1, int n2) {
        return n1+n2;
    }
}
