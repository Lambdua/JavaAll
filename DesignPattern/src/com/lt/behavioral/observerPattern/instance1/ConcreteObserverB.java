package com.lt.behavioral.observerPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/27
 **/
public class ConcreteObserverB extends Observer {


    @Override
    void update() {
        System.out.println("B consurrent");
    }

}
