package com.lt.demo;

import com.lt.demo.point.SafePoint;

import java.util.Collections;
import java.util.Map;

/**
 * @author 梁先生
 * @Date 2020/10/12
 * 使用线程安全的point
 **/
public class PublishingVehicleTracker {
    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifyiableLocations;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = locations;
        this.unmodifyiableLocations = Collections.unmodifiableMap(this.locations);
    }

    public Map<String,SafePoint> getLocations(){
        return unmodifyiableLocations;
    }

    public SafePoint getLocation(String id){
        return locations.get(id);
    }

    /**
     * 因为Point已经改成线程安全的了，我们可以通过Point自己的set和get方法放心大胆的修改它
     */
    public void setLocation(String id,int x,int y){
        locations.put(id,new SafePoint(x,y));
    }
}
