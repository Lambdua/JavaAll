package com.lt.demo;

import com.lt.demo.point.ImmutablePoint;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 梁先生
 * @Date 2020/10/12
 * 测试
 **/
public class DemoRunner {
    public static void main(String[] args) {
        Map<String, ImmutablePoint> locations=new HashMap<>();
        DelegatingVehicleTracker delegatingVehicleTracker = new DelegatingVehicleTracker(locations);
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 10; i++) {
                delegatingVehicleTracker.setLocation(String.valueOf(i),i+j,i+j);
            }
        }
        Map<String, ImmutablePoint> unmodifiablePoint = delegatingVehicleTracker.getLocations();
        unmodifiablePoint.forEach((k,v)-> System.out.println("k: "+k+", v:"+v));
    }
}
