package com.lt.creational.builder.car.components;

import lombok.Getter;

/**
 * @author liangtao
 * @description GPS导航
 * @Date 2021/2/2
 **/
@Getter
public class GPSNavigator {
    private String route;

    public GPSNavigator() {
        this.route = "221b, Baker Street, London  to Scotland Yard, 8-10 Broadway, London";
    }

    public GPSNavigator(String manualRoute) {
        this.route = manualRoute;
    }

}
