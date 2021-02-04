package com.lt.creational.builder.car;

import com.lt.creational.builder.car.builders.CarBuilder;
import com.lt.creational.builder.car.builders.CarManualBuilder;
import com.lt.creational.builder.car.cars.Car;
import com.lt.creational.builder.car.cars.Manual;
import com.lt.creational.builder.car.director.Director;

/**
 * @author liangtao
 * @description 使用案例
 * @Date 2021/2/2
 **/
public class UseDemo {
    public static void main(String[] args) {
        Director director = new Director();
        // Director从客户端获取具体的构建器对象,自己并不知道确切的产品类型。
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);
        /*
        最后通过build生成一个指定的对象
         */
        Car car = builder.build();
        System.out.println("Car built:\n" + car.getCarType());

        CarManualBuilder manualBuilder = new CarManualBuilder();
        //这里director 并不知道自己是在创建什么鬼,它只知道这个类型的Car有什么属性。在Director中会将设置这些属性。
        director.constructCityCar(manualBuilder);
        Manual carManual = manualBuilder.build();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }
}
