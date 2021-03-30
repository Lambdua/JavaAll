package com.lt.behavioral.observerPattern.instance2.editor;

import com.lt.behavioral.observerPattern.instance2.publisher.EventManager;

import java.io.File;

/**
 * @author liangtao
 * @description 编辑器
 * @date 2021年03月30 15:33
 **/
public class Editor {
    public EventManager events;
    private File file;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

    public void openFile(String filePath) {
        this.file = new File(filePath);
        events.notify("open", file);
    }

    public void saveFile() throws Exception {
        if (this.file != null) {
            events.notify("save", file);
        } else {
            throw new Exception("Please open a file first.");
        }
    }
}
