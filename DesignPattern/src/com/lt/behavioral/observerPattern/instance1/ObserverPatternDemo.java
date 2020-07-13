package com.lt.behavioral.observerPattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/27
 **/
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject=new Subject();
        new ConcreteObserverA();
        new ConcreteObserverB();
        new ConcreteObserverC();

        subject.attach(new ConcreteObserverA());
        subject.attach(new ConcreteObserverB());
        subject.attach(new ConcreteObserverC());
        subject.setState(2);


    }
}
