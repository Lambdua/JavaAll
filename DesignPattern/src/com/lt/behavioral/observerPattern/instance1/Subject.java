package com.lt.behavioral.observerPattern.instance1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/27
 **/
public class Subject {
    List<Observer> observerList=new ArrayList<>();
    int state;
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
        notifyObserver();
    }
    public void attach(Observer observer){
        observerList.add(observer);
    }
    public void remove(Observer observer){
        if (observerList.contains(observer)) {
            observerList.remove(observer);
        }
    }



    public void notifyObserver(){
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}
