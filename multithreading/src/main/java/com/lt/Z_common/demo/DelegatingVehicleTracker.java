package com.lt.Z_common.demo;

import cn.hutool.core.map.MapUtil;
import com.lt.Z_common.demo.point.ImmutablePoint;

import java.util.Collections;
import java.util.Map;

/**
 * @author 梁先生
 * @Date 2020/10/12
 **/
public class DelegatingVehicleTracker {
    private final Map<String, ImmutablePoint> locations;
    private final Map<String,ImmutablePoint> unmodifiableMap;
    public DelegatingVehicleTracker(Map<String,ImmutablePoint> locations){
        //通过使用currentHashMap 来保证locations的写安全
        this.locations= MapUtil.newConcurrentHashMap(locations);
        //使用不可变的map，来保证读安全
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String,ImmutablePoint> getLocations(){
        return unmodifiableMap;
    }

    public ImmutablePoint getLocation(String id){
        return locations.get(id);
    }

    /**
     * 因为{@link ImmutablePoint} 不可变，所以set方法直接设置一个新的point
     * @param id
     * @param x
     * @param y
     */
    public void setLocation(String id,int x,int y){
            locations.put(id,new ImmutablePoint(x,y));
    }
}
