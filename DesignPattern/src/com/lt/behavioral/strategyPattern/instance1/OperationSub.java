package com.lt.behavioral.strategyPattern.instance1;

import javax.xml.stream.events.StartDocument;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public class OperationSub implements Strategy {
    @Override
    public int doOperation(int n1, int n2) {
       return n1-n2;
    }
}
