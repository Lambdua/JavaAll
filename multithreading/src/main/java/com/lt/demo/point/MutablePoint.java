package com.lt.demo.point;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 梁先生
 * @Date 2020/10/12
 * 车辆坐标的表示
 **/
@Data
@AllArgsConstructor
public class MutablePoint {
    private int x,y;

    public MutablePoint() {
        x=0;
        y=0;
    }

    public MutablePoint(MutablePoint point){
        this.x=point.getX();
        this.y=point.getY();
    }
}
