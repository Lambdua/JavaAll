package com.lt.behavioral.observerPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/27
 **/
public class ConcreteObserverA extends Observer {

    public ConcreteObserverA() {
    }

    @Override
    void update() {
        System.out.println("A consurrent");
    }
}
