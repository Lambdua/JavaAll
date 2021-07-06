package com.lt.Z_common.demo;

import cn.hutool.core.lang.Assert;
import com.lt.Z_common.demo.point.ImmutablePoint;
import com.lt.Z_common.demo.point.MutablePoint;
import com.lt.Z_common.demo.point.SafePoint;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 梁先生
 * @Date 2020/10/12
 * 监控车辆追踪器, 这里{@link MutablePoint}不是线程安全的，所以需要添加大量的synchronized同步方法。
 *
 * 这个车辆追踪器最大的问题就是 {@link MutablePoint} 类是一个易变的线程不安全类
 * 这导致我们不得不在 MonitorVehicleTracker 中加入大量的同步代码，
 * 所以我们考虑从修改 {@link MutablePoint} 类入手（所以说，构建大的线程安全模块，应该从构建小的线程安全模块入手）
 * 对于这个错误，我们有两种解决思路：
 * 1. 直接把 Point 变为一个不可变对象 {@link ImmutablePoint}；
 * 2. 构建一个可变但是线程安全的 Point类{@link SafePoint}，即给 Point 类中的 get 和 set 方法上加上同步，然后我们在 MonitorVehicleTracker 中就不用再使用同步了，相当于缩小了同步代码块的大小。
 **/
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    /**
     * 当locations 的size较大时，费时
     *
     * @return
     */
    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint location = locations.get(id);
        return location == null ? null : new MutablePoint(location);
    }

    /**
     * 设置location,需要同步
     * @param id
     * @param x
     * @param y
     */
    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint location = locations.get(id);
        Assert.isTrue(location!=null,"没有此Id :{}",id);
        location.setX(x);
        location.setY(y);
    }

    //深拷贝
    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        HashMap<String, MutablePoint> result = new HashMap<>(locations.size());
        locations.forEach((k, v) -> result.put(k, new MutablePoint(v)));
        return result;
    }

}
