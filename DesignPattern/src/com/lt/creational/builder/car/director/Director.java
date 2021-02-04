package com.lt.creational.builder.car.director;

import com.lt.creational.builder.car.builders.Builder;
import com.lt.creational.builder.car.cars.CarType;
import com.lt.creational.builder.car.components.Engine;
import com.lt.creational.builder.car.components.GPSNavigator;
import com.lt.creational.builder.car.components.Transmission;
import com.lt.creational.builder.car.components.TripComputer;

/**
 * @author liangtao
 * @description Director 定义了构建步骤的顺序。它通过通用的Builder接口与一个builder对象一起使用。因此，它可能不知道正在制造什么产品。
 * @Date 2021/2/2
 **/
public class Director {

    /**
     * 制作一个告诉汽车
     */
    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    /**
     * 制作一个城市汽车
     * @param builder
     */
    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    /**
     * 制作一个SUV
     * @param builder
     */
    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }
}
