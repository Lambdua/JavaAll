package com.lt.creational.builder.car.cars;

import com.lt.creational.builder.car.components.Engine;
import com.lt.creational.builder.car.components.GPSNavigator;
import com.lt.creational.builder.car.components.Transmission;
import com.lt.creational.builder.car.components.TripComputer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author liangtao
 * @description 汽车
 * @Date 2021/2/2
 **/
@RequiredArgsConstructor
@Getter
public class Car {

    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;
    /**
     * 汽油
     */
    private double fuel = 0;
}
