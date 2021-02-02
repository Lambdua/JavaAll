package com.lt.creational.builder.car.components;

import com.lt.creational.builder.car.cars.Car;

/**
 * @author liangtao
 * @description 中央控制器
 * @Date 2021/2/2
 **/
public class TripComputer {
    private Car car;


    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car is started");
        } else {
            System.out.println("Car isn't started");
        }
    }
}
