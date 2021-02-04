package com.lt.creational.builder.car.components;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author liangtao
 * @description 引擎
 * @Date 2021/2/1
 **/
@Getter
public class Engine {
    private final double volume;
    //里程
    private double mileage;

    @Getter(AccessLevel.NONE)
    private boolean started;

    public Engine(double volume, double mileage) {
        this.volume = volume;
        this.mileage = mileage;
    }

    public void on(){
        started=true;
    }

    public void off(){
        started=false;
    }

    public boolean isStarted(){
        return started;
    }

    public void go(double mielage){
        if (started) this.mileage+=mielage;
        else System.out.println("尚未启动, 请先启动");
    }
}
