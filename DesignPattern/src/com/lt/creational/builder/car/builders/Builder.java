package com.lt.creational.builder.car.builders;

import com.lt.creational.builder.car.cars.CarType;
import com.lt.creational.builder.car.components.Engine;
import com.lt.creational.builder.car.components.GPSNavigator;
import com.lt.creational.builder.car.components.Transmission;
import com.lt.creational.builder.car.components.TripComputer;

/**
 * @author liangtao
 * @description 通用car生成器接口 ,当然这里也可以使用链式调用，即方法返回值为this
 * @Date 2021/2/1
 **/
public interface Builder {
    void setCarType(CarType carType);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
