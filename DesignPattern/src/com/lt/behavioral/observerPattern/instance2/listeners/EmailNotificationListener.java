package com.lt.behavioral.observerPattern.instance2.listeners;

import java.io.File;

/**
 * @author liangtao
 * @description email通知观察者
 * @date 2021年03月30 15:29
 **/
public class EmailNotificationListener implements EventListener {
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed "
                + eventType +
                " operation with the following file: " + file.getName());
    }

}
