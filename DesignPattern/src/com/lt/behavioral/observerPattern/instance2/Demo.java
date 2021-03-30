package com.lt.behavioral.observerPattern.instance2;

import com.lt.behavioral.observerPattern.instance2.editor.Editor;
import com.lt.behavioral.observerPattern.instance2.listeners.EmailNotificationListener;
import com.lt.behavioral.observerPattern.instance2.listeners.LogOpenListener;

/**
 * @author liangtao
 * @description 用例
 * @date 2021年03月30 15:38
 **/
public class Demo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.events.subscribe("open", new LogOpenListener("test.txt"));
        editor.events.subscribe("save", new EmailNotificationListener("admin@example.com"));

        try {
            editor.openFile("test.txt");
            editor.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
