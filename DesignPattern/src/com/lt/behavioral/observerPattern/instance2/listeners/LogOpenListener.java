package com.lt.behavioral.observerPattern.instance2.listeners;

import java.io.File;

/**
 * @author liangtao
 * @description 日志操作监听者
 * @date 2021年03月30 15:30
 **/
public class LogOpenListener implements EventListener{
    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + ": Someone has performed "
                + eventType
                + " operation with the following file: " + file.getName());
    }
}
