package com.lt.demo.point;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 梁先生
 * @Date 2020/10/12
 * 不可变Point
 **/
@Data
@AllArgsConstructor
public class ImmutablePoint {
    private final int x;
    private final int y;

}
