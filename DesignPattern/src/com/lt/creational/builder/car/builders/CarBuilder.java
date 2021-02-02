package com.lt.creational.builder.car.builders;

import com.lt.creational.builder.car.cars.Car;
import com.lt.creational.builder.car.cars.CarType;
import com.lt.creational.builder.car.components.Engine;
import com.lt.creational.builder.car.components.GPSNavigator;
import com.lt.creational.builder.car.components.Transmission;
import com.lt.creational.builder.car.components.TripComputer;

/**
 * @author liangtao
 * @description
 * @Date 2021/2/2
 **/
public class CarBuilder implements Builder{
    private CarType carType;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;


    /**
     * build生成最终car
     */
    public Car build(){
        return new Car(carType,seats,engine,transmission,tripComputer,gpsNavigator);
    }

    @Override
    public void setCarType(CarType carType) {
        this.carType=carType;
    }

    @Override
    public void setSeats(int seats) {
        this.seats=seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine=engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission=transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer=tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator=gpsNavigator;
    }



}
