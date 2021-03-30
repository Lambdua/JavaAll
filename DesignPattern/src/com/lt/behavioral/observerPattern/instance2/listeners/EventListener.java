package com.lt.behavioral.observerPattern.instance2.listeners;

import java.io.File;

/**
 * @author liangtao
 * @description 通用观察者接口/监听者
 * @date 2021年03月30 15:28
 **/
public interface EventListener {
    void update(String eventType, File file);
}
