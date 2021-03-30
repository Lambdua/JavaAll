package com.lt.behavioral.observerPattern.instance2.publisher;

import com.lt.behavioral.observerPattern.instance2.listeners.EventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangtao
 * @description 基础发布者
 * @date 2021年03月30 15:07
 **/
public class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType,EventListener listener){
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType,EventListener listener){
        listeners.get(eventType).remove(listener);
    }

    public void notify(String eventType, File file){
        listeners.get(eventType).forEach(listener -> listener.update(eventType,file));
    }


}
